
package com.chdryra.android.mygenerallibrary;

import android.graphics.Bitmap;
import android.media.ExifInterface;
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
            Bitmap bitmap = ImageHelper.getBitmap(mFile, maxWidth, maxHeight);
            Bitmap exactRescale = ImageHelper.rescalePreservingAspectRatio(bitmap, maxWidth,
                    maxHeight);

            ExifInterface exif = ImageHelper.getEXIF(mFile);
            return ImageHelper.rotateBitmapUsingExif(exif, exactRescale);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mListener.onBitmapLoaded(bitmap);
        }
    }
}
