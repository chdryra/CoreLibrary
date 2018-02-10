/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.AsyncUtils;

import android.os.AsyncTask;

/**
 * Created by: Rizwan Choudrey
 * On: 17/02/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class DelayTask extends AsyncTask<Long, Void, Void> {
    @Override
    protected Void doInBackground(Long... delay) {
        try {
            Thread.sleep(delay[0]);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        return null;
    }
}
