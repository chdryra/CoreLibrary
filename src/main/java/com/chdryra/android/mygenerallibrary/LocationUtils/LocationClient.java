/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.LocationUtils;

import android.location.Location;

/**
 * Created by: Rizwan Choudrey
 * On: 10/06/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface LocationClient {
    interface Locatable {
        void onLocated(Location location);

        void onConnected(Location location);
    }

    void connect();

    void disconnect();

    boolean locate();
}
