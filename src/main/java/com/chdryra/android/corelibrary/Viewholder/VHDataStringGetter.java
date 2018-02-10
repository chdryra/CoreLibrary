/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Viewholder;

/**
 * Simple interface for extracting strings from data more complex than {@link com.chdryra
 * .android.mygenerallibrary.VHDString} in order to use the {@link
 * VHString} ViewHolder. For example,
 * simple text display cells in the {@link com.chdryra.android.corelibrary
 * .ViewHolderAdapter}
 * framework.
 */
public interface VHDataStringGetter {
    String getString(ViewHolderData data);
}
