/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Imaging;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class BitmapLoader {
    private final String mFile;
    private final LoadListener mListener;

    public interface LoadListener {
        //abstract
        public void onBitmapLoaded(Bitmap bitmap);
    }

    //Constructors
    public BitmapLoader(String file, LoadListener listener) {
        mFile = file;
        mListener = listener;
    }

    public void load(int maxWidth, int maxHeight) {
        new BitmapLoaderTask().execute(maxWidth, maxHeight);
    }

    private class BitmapLoaderTask extends AsyncTask<Integer, Void, Bitmap> {

        //Overridden
        @Override
        protected Bitmap doInBackground(Integer... params) {
            int maxWidth = params[0];
            int maxHeight = params[1];

            //TODO separate out post processing into separate tasks. Should just be loading.
            Bitmap bitmap = ImageHelper.getBitmap(mFile, maxWidth, maxHeight);
            Bitmap rescaled = ImageHelper.rescalePreservingAspectRatio(bitmap, maxWidth, maxHeight);

            return ImageHelper.rotateBitmapUsingExif(ImageHelper.getExif(mFile), rescaled);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mListener.onBitmapLoaded(bitmap);
        }
    }
}
