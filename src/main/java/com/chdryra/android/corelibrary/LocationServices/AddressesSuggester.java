/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.LocationServices;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 11/01/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface AddressesSuggester {
    interface AddressSuggestionsListener {
        void onAddressSuggestionsFound(ArrayList<LocatedPlace> addresses);
    }

    void fetchAddresses(LatLng latLng, int number, AddressSuggestionsListener listener);
}
