/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.FileUtils;

import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by: Rizwan Choudrey
 * On: 11/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FileIncrementor {
    private static final String TAG = "FileCreator";
    private static final String ERROR_CREATING_FILE_MESSAGE = "Error creating file";
    private static final String ERROR_FILE_EXISTS = "File exists";

    private File mDirectory;
    private String mFileName;
    private String mExtension;
    private long mFileCounter = 0;

    //Constructors
    public FileIncrementor(File systemDir, String newDir, String fileName, String extension) {
        if (!systemDir.exists()) {
            Log.e(TAG, "systemDir must exist!");
            return;
        }

        mDirectory = new File(systemDir, newDir);
        boolean mkdirs = mDirectory.mkdirs();

        mFileName = fileName;
        mExtension = extension;
    }

    public void deleteCreatedFiles() {
        while (mFileCounter > 0) {
            File file = getFile(1);
            if (!deleteFile(file)) {
                Log.i(TAG, "Problems deleting file: " + file.getAbsolutePath());
            }
            --mFileCounter;
        }
    }

    public void deleteCreatedDirectory() {
        deleteRecursive(mDirectory);
    }

    public File createNewFile() throws IOException {
        File file = getFile(0);
        mFileCounter++;
        if (file.exists()) return createNewFile();

        if (createFile(file)) {
            return file;
        } else {
            //Should never get here....
            mFileCounter--;
            throw new IOException(ERROR_FILE_EXISTS);
        }
    }

    public boolean deleteLastFile() {
        File lastFile = getFile(1);
        boolean success = lastFile.delete();
        if (success) mFileCounter--;
        return success;
    }

    private File getFile(long offsetFromCounter) {
        long number = Math.max(mFileCounter - offsetFromCounter, 0);
        String numberExt = "_" + String.valueOf(number);
        String fileName;
        if (mFileName != null && mFileName.length() > 0) {
            fileName = number > 0 ? mFileName + numberExt : mFileName;
        } else {
            fileName = mDirectory.getName() + numberExt;
        }

        String extension = null;
        if (mExtension != null && mExtension.length() > 0) {
            extension = "." + mExtension;
        }

        return new File(mDirectory, fileName + extension);
    }

    private boolean createFile(File file) throws IOException {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new IOException(ERROR_CREATING_FILE_MESSAGE, e);
        }
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

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }
}
