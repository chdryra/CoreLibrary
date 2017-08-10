/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.LocationServices;

import com.chdryra.android.mygenerallibrary.TextUtils.StringFilterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 20/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class StringAutoCompleterLocation implements StringFilterAdapter.StringFilter {
    private final AutoCompleterLocation mAutoCompleter;

    public StringAutoCompleterLocation(AutoCompleterLocation autoCompleter) {
        mAutoCompleter = autoCompleter;
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

    @Override
    public ArrayList<String> filter(String query) {
        List<LocatedPlace> places = mAutoCompleter.filter(query);

        ArrayList<String> shortened = new ArrayList<>();
        for (LocatedPlace place : places) {
            shortened.add(formatAddress(place.getDescription()));
        }

        return shortened;
    }
}
