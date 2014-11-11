/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;

/**
 * A hotchpotch of image related tasks that can be performed on behalf of, or on, one image file.
 */
public class ImageHelper {
    private static final String TAG                         = "ImageHelper";
    private static final String ERROR_CREATING_FILE_MESSAGE = "Error creating file!";

    private String        mImageFilePath;
    private ExifInterface mEXIF;

    protected ImageHelper() {
    }

    public ImageHelper(String imageFilePath) {
        setImageFilePath(imageFilePath);
    }

    public String getImageFilePath() {
        return mImageFilePath;
    }

    protected void setImageFilePath(String imageFilePath) {
        mImageFilePath = imageFilePath;
        mEXIF = null;
        getEXIF();
    }

    public boolean bitmapExists() {
        BitmapFactory.Options options = getInDecodeBoundsOptions();
        BitmapFactory.decodeFile(mImageFilePath, options);
        return options.outHeight != -1;
    }

    public Bitmap getBitmap(int maxWidth, int maxHeight) {
        BitmapFactory.Options options = getInDecodeBoundsOptions();
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(mImageFilePath, options);
        bitmap = rotateBitmapUsingExif(mImageFilePath, bitmap);

        return bitmap;
    }

    public LatLng getLatLngFromEXIF() {
        LatLng latlng = null;
        EXIFtoLatLngConverter converter = new EXIFtoLatLngConverter(getEXIF());

        if (converter.isValid()) latlng = converter.getLatLng();

        return latlng;
    }

    public ExifInterface getEXIF() {
        if (mImageFilePath != null) {
            if (mEXIF == null) {
                try {
                    mEXIF = new ExifInterface(mImageFilePath);
                } catch (IOException e) {
                    //OK for EXIF to be null if none found
                    Log.i(TAG, "IOEXception: No EXIF found in " + mImageFilePath);
                }
            }
        } else {
            mEXIF = null;
        }

        return mEXIF;
    }

    public boolean createImageFile() throws IOException {
        File file = new File(mImageFilePath);
        try {
            if (!file.exists() && mImageFilePath != null) {
                if (file.getParentFile().mkdirs()) Log.i(TAG, "Created " + mImageFilePath);
                return file.createNewFile();
            }
        } catch (IOException e) {
            //Caller should handle exception
            throw new IOException(ERROR_CREATING_FILE_MESSAGE, e);
        }

        return false;
    }

    public void deleteImageFile() {
        File file = new File(mImageFilePath);
        if (file.exists() && !file.delete()) {
            Log.i(TAG, "Problem deleting file: " + mImageFilePath);
        }

        mImageFilePath = null;
        mEXIF = null;
    }

    private BitmapFactory.Options getInDecodeBoundsOptions() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mImageFilePath, options);

        return options;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


    private Bitmap rotateBitmapUsingExif(String imageFilePath, Bitmap bitmap) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imageFilePath);
        } catch (IOException e) {
            //OK for EXIF to be null if not found
            Log.i(TAG, "IOException: No EXIF found in " + imageFilePath);
        }

        if (exif == null) {
            return bitmap;
        }

        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

        if (orientation == 1) {
            return bitmap;
        }

        Matrix matrix = new Matrix();
        switch (orientation) {
            case 2:
                matrix.setScale(-1, 1);
                break;
            case 3:
                matrix.setRotate(180);
                break;
            case 4:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case 5:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case 6:
                matrix.setRotate(90);
                break;
            case 7:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case 8:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }

        try {
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                    matrix, true);
        } catch (OutOfMemoryError e) {
            Log.i(TAG, "OutOfMemoryError: trying to rotate bitmap. Returning original bitmap");
            e.printStackTrace();
            return bitmap;
        }
    }
}
