/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Bucketing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 05/12/2016
 * Email: rizwan.choudrey@gmail.com
 */

public class Bucket<BucketingValue, ItemType> {
    private final BucketRange<BucketingValue> mRange;
    private final List<ItemType> mBucketed;

    public Bucket(BucketRange<BucketingValue> range) {
        mRange = range;
        mBucketed = new ArrayList<>();
    }

    public BucketRange<BucketingValue> getRange() {
        return mRange;
    }

    public List<ItemType> getBucketedItems() {
        return new ArrayList<>(mBucketed);
    }

    public boolean bucketIfInRange(BucketingValue value, ItemType item) {
        boolean inRange = mRange.inRange(value);
        if (inRange) mBucketed.add(item);
        return inRange;
    }

    public boolean hasOverlap(Bucket<BucketingValue, ItemType> otherBucket) {
        return getRange().hasOverlap(otherBucket.getRange());
    }

    public int size() {
        return mBucketed.size();
    }
}
