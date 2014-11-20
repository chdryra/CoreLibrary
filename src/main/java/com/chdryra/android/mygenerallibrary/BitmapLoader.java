
package com.chdryra.android.mygenerallibrary;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class BitmapLoader {
    private final String       mFile;
    private final LoadListener mListener;

    public interface LoadListener {
        public void onBitmapLoaded(Bitmap bitmap);
    }

    public BitmapLoader(String file, LoadListener listener) {
        mFile = file;
        mListener = listener;
    }

    public void load(int maxWidth, int maxHeight) {
        new BitmapLoaderTask().execute(maxWidth, maxHeight);
    }

    private class BitmapLoaderTask extends AsyncTask<Integer, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Integer... params) {
            int maxWidth = params[0];
            int maxHeight = params[1];

            //TODO separate out post processing into separate tasks. Should just be loading.
            Bitmap bitmap = ImageHelper.getBitmap(mFile, maxWidth, maxHeight);
            Bitmap rescaled = ImageHelper.rescalePreservingAspectRatio(bitmap, maxWidth, maxHeight);

            return ImageHelper.rotateBitmapUsingExif(ImageHelper.getEXIF(mFile), rescaled);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mListener.onBitmapLoaded(bitmap);
        }
    }
}
