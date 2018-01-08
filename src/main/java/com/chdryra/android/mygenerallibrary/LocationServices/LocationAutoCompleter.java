/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.LocationServices;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class LocationAutoCompleter implements AutoCompleter<LocatedPlace> {
    private final LatLng mLatLng;
    private final LocationPredicter mProvider;

    public interface LocationPredicter {
        ArrayList<LocatedPlace> fetchPredictions(String query, LatLng latLng);

        void disconnect();
    }

    public LocationAutoCompleter(LocatedPlace place, LocationPredicter provider) {
        mLatLng = place.getLatLng();
        mProvider = provider;
    }

    @Override
    public List<LocatedPlace> filter(String query) {
        return mProvider.fetchPredictions(query, mLatLng);
    }

    @Override
    public void disconnectFromProvider() {
        mProvider.disconnect();
    }
}
