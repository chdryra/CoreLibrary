/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * A hotchpotch of image related tasks that can be performed on an image file.
 */
public class ImageHelper {
    private static final String TAG = "ImageHelper";
    private static DateFormat sFormatter;

    static {
        sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static boolean bitmapExists(String filePath) {
        BitmapFactory.Options options = getBitmapInfo(filePath);
        return options.outHeight > 0;
    }

    public static Bitmap getBitmap(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = getBitmapInfo(filePath);
        options.inSampleSize = getInSampleSize(options.outHeight, options.outWidth, reqHeight,
                reqWidth);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap rescalePreservingAspectRatio(Bitmap bitmap, int maxWidth, int maxHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float ratioWidthToHeight = (float) width / height;

        float newWidth = width > height ? maxWidth : maxHeight * ratioWidthToHeight;
        float newHeight = newWidth / ratioWidthToHeight;

        return Bitmap.createScaledBitmap(bitmap, (int) newWidth, (int) newHeight, true);
    }

    public static Bitmap rotateBitmapUsingExif(ExifInterface exif, Bitmap bitmap) {
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

        if (orientation == 1) return bitmap;

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

    public static LatLng getLatLngFromExif(ExifInterface exif) {
        LatLng latLng = null;
        float[] ll = new float[2];
        if (exif.getLatLong(ll)) {
            latLng = new LatLng(ll[0], ll[1]);
        }

        return latLng;
    }

    public static Date getDateTimeFromEXIF(ExifInterface exif) {
        //From ExifInterface source code
        String dateTimeString = exif.getAttribute(ExifInterface.TAG_DATETIME);
        ParsePosition pos = new ParsePosition(0);

        try {
            Date dateTime = sFormatter.parse(dateTimeString, pos);
            if (dateTime == null) return null;
            return dateTime;
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static ExifInterface getExif(String filePath) {
        ExifInterface exif = null;
        if (filePath != null && filePath.length() > 0) {
            try {
                exif = new ExifInterface(filePath);
            } catch (IOException e) {
                //OK for EXIF to be null if none found
                Log.i(TAG, "IOEXception: No EXIF found in " + filePath);
            }
        }

        return exif;
    }

    public static Intent getImageChooserIntents(Activity activity, String imageFileForCapture) {
        Uri imageUri = Uri.fromFile(new File(imageFileForCapture));

        //Create intents
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = activity.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName,
                    res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntents.add(intent);
        }

        final Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new
                Parcelable[cameraIntents.size()]));

        return chooserIntent;
    }

    private static BitmapFactory.Options getBitmapInfo(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        return options;
    }

    private static int getInSampleSize(int height, int width, int reqHeight, int reqWidth) {
        int inSampleSize = 1;

        //Need to find greatest power of 2 that yields an image >= required dimensions
        if (height > 0 && width > 0 && reqHeight > 0 && reqWidth > 0 && height > reqHeight &&
                width > reqWidth) {
            int bitsHeight = 31 - Integer.numberOfLeadingZeros(height / reqHeight);
            int bitsWidth = 31 - Integer.numberOfLeadingZeros(width / reqWidth);
            int bits = Math.min(bitsHeight, bitsWidth);
            inSampleSize = 1 << bits;
        }

        return inSampleSize;
    }
}
