/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.CollectionReference;
import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.DataReference;
import com.chdryra.android.corelibrary.ReferenceModel.Interfaces.Size;

import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 15/02/2018
 * Email: rizwan.choudrey@gmail.com
 */

public class SizeReferencer {
    public <T, C extends Collection<T>> DataReference<Size> newSizeReference
            (CollectionReference<T, C, Size> collectionReference) {
        return new SizeReference<>(collectionReference);
    }
}
