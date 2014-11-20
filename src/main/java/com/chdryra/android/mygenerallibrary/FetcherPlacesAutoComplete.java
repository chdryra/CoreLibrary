/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 20 November, 2014
 */

package com.chdryra.android.mygenerallibrary;

import com.chdryra.android.remoteapifetchers.FetcherPlacesAPI;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 20/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FetcherPlacesAutoComplete {
    private LatLng mLatLng;

    public FetcherPlacesAutoComplete(LatLng latLng) {
        mLatLng = latLng;
    }

    public ArrayList<String> fetch(String query) {
        return FetcherPlacesAPI.fetchAutoCompleteSuggestions(query, mLatLng);
    }
}
