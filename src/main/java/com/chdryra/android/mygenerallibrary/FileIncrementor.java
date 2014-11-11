/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 November, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by: Rizwan Choudrey
 * On: 11/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FileIncrementor {
    private static final String TAG                         = "FileCreator";
    private static final String ERROR_CREATING_FILE_MESSAGE = "Error creating file";
    private static final String ERROR_NO_STORAGE_MESSAGE    = "No storage available";

    private String mSystemDir;
    private String mDirectory;
    private String mFileName;
    private String mExtension;
    private long mFileCounter = 0;

    public FileIncrementor(String systemDir, String dir, String fileName, String extension) {
        mSystemDir = systemDir;
        mDirectory = dir;
        mFileName = fileName;
        mExtension = extension;
    }

    public void clearDirectory() {
        while (mFileCounter > 0) {
            File file = getFile(1);
            if (!deleteFile(file)) {
                Log.i(TAG, "Problems deleting file: " + file.getAbsolutePath());
            }
            --mFileCounter;
        }
    }

    public boolean createNewFile() throws IOException {
        boolean success;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = getFile(0);
            mFileCounter++;

            if (file.exists()) return createNewFile();

            success = createFile(file);
        } else {
            throw new IOException(ERROR_NO_STORAGE_MESSAGE);
        }

        return success;
    }

    public File getLastFileCreated() {
        File file;
        long i = 1;
        do {
            file = getFile(i);
        } while (!file.exists() && i++ < mFileCounter);

        return file;
    }

    private File getFile(long offsetFromCounter) {
        File dir = new File(mSystemDir, mDirectory);

        long number = Math.max(mFileCounter - offsetFromCounter, 0);
        String numberExt = "_" + number;
        String fileName;
        if (mFileName != null && mFileName.length() > 0) {
            fileName = number > 0 ? mFileName + numberExt : mFileName;
        } else {
            fileName = numberExt;
        }

        String extension = null;
        if (mExtension != null && mExtension.length() > 0) {
            extension = "." + mExtension;
        }

        return new File(dir, fileName + extension);
    }

    private boolean createFile(File file) throws IOException {
        try {
            if (!file.exists()) {
                if (file.mkdirs()) {
                    Log.i(TAG, "Created " + file.toString());
                    return file.exists();
                } else {
                    return file.createNewFile();
                }
            }
        } catch (IOException e) {
            //Caller should handle exception
            throw new IOException(ERROR_CREATING_FILE_MESSAGE, e);
        }

        return false;
    }

    private boolean deleteFile(File file) {
        boolean success = false;
        if (file.exists()) {
            success = file.delete();
            if (!success) {
                Log.i(TAG, "Problem deleting file: " + file.toString());
            }
        }

        return success;
    }
}
