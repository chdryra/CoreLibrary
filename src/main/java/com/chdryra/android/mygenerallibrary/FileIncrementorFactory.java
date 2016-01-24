/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import java.io.File;

/**
 * Created by: Rizwan Choudrey
 * On: 13/01/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FileIncrementorFactory {
    private File mSystemDir;
    private String mDefaultStem;

    public FileIncrementorFactory(File systemDir) {
        mSystemDir = systemDir;
    }

    public FileIncrementor newJpgFileIncrementor(String newDir,
                                                 String fileName) {
        return new FileIncrementor(mSystemDir, newDir, fileName, "jpg");
    }

}
