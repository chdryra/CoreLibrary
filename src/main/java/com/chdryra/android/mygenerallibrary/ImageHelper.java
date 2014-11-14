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
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
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
import java.util.ArrayList;
import java.util.List;

/**
 * A hotchpotch of image related tasks that can be performed on an image file.
 */
public class ImageHelper {
    private static final String TAG = "ImageHelper";

    public static boolean bitmapExists(String filePath) {
        BitmapFactory.Options options = getInDecodeBoundsOptions(filePath);
        BitmapFactory.decodeFile(filePath, options);
        return options.outHeight != -1;
    }

    public static Bitmap getBitmap(String filePath, int maxWidth, int maxHeight) {
        BitmapFactory.Options options = getInDecodeBoundsOptions(filePath);
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        bitmap = rotateBitmapUsingExif(filePath, bitmap);

        return bitmap;
    }

    public static LatLng getLatLngFromEXIF(ExifInterface exif) {
        LatLng latlng = null;
        ExifToLatLngParser converter = new ExifToLatLngParser(exif);

        if (converter.isValid()) latlng = converter.getLatLng();

        return latlng;
    }

    public static ExifInterface getEXIF(String filePath) {
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
        File imageFile = new File(imageFileForCapture);
        Uri imageUri = Uri.fromFile(imageFile);

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
                android.provider.MediaStore.Images.Media
                        .EXTERNAL_CONTENT_URI);
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new
                Parcelable[cameraIntents.size()]));

        return chooserIntent;
    }

    private static BitmapFactory.Options getInDecodeBoundsOptions(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        return options;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
            int reqHeight) {
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


    private static Bitmap rotateBitmapUsingExif(String imageFilePath, Bitmap bitmap) {
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

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Intent getImageChooserIntents(Activity activity, File fileForImageCapture) {
        //Set up image file for capture
        Uri imageUri = Uri.fromFile(fileForImageCapture);

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

}
