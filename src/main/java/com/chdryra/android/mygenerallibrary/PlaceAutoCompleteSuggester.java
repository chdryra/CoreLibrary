/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 20/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class PlaceAutoCompleteSuggester implements StringFilterAdapter.StringFilter {
    private final LatLng mLatLng;

//Constructors
    public PlaceAutoCompleteSuggester(LatLng latLng) {
        mLatLng = latLng;
    }

    private String formatAddress(String address) {
        String[] addressComponents = address.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append(addressComponents[0]);
        if (addressComponents.length > 1) {
            sb.append(",");
            sb.append(addressComponents[1]);
        }

        return sb.toString();
    }

//Overridden
    @Override
    public ArrayList<String> filter(String query) {
        ArrayList<String> suggestions = GooglePlacesApi.fetchAutoCompleteSuggestions(query,
                mLatLng);

        ArrayList<String> shortened = new ArrayList<String>();
        for (String suggestion : suggestions) {
            shortened.add(formatAddress(suggestion));
        }

        return shortened;
    }
}
