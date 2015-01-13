/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 13 January, 2015
 */

package com.chdryra.android.mygenerallibrary;

import java.io.File;

/**
 * Created by: Rizwan Choudrey
 * On: 13/01/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FileIncrementorFactory {

    public static FileIncrementor newImageFileIncrementor(File systemDir, String newDir,
            String fileName) {
        return new ImageFileIncrementor(systemDir, newDir, fileName);
    }

    public static class ImageFileIncrementor extends FileIncrementor {
        private static final String EXT_IMAGE = "jpg";

        public ImageFileIncrementor(File systemDir, String newDir, String fileName) {
            super(systemDir, newDir, fileName, EXT_IMAGE);
        }
    }
}
