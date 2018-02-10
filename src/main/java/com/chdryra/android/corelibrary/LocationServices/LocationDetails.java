/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.LocationServices;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

/**
 * Created by: Rizwan Choudrey
 * On: 11/01/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface LocationDetails {
    String getId();

    LocationProvider getProvider();

    LatLng getLatLng();

    String getName();

    String getAddress();

    String getAttributions();

    Locale getLocale();

    String getPhoneNumber();

    Uri getWebsiteUri();
}
