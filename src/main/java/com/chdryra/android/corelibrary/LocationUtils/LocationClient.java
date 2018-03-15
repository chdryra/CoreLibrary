/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.LocationUtils;

import android.location.Location;
import android.support.annotation.Nullable;

import com.chdryra.android.corelibrary.AsyncUtils.CallbackMessage;

/**
 * Created by: Rizwan Choudrey
 * On: 10/06/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface LocationClient {
    interface Locatable {
        void onLocated(@Nullable Location location, CallbackMessage message);

        void onConnected(@Nullable Location location, CallbackMessage message);
    }

    void connect(Locatable callback);

    void disconnect();

    void locate();
}
