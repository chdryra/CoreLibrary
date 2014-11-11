/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 November, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.media.ExifInterface;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by: Rizwan Choudrey
 * On: 11/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class EXIFtoLatLngConverter {
    private boolean mIsValid = false;
    private Double mLatitude, mLongitude;

    public EXIFtoLatLngConverter(ExifInterface exif) {
        String lat = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
        String latRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
        String lng = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
        String lngRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

        if (lat != null && latRef != null && lng != null && lngRef != null) {
            mIsValid = true;

            //Latitude in [-90,+90] depending on hemisphere
            //Latitude in [-180,+180] depending on hemisphere
            mLatitude = latRef.equals("N") ? toDegreesDecimal(lat) : -toDegreesDecimal(lat);
            mLongitude = lngRef.equals("E") ? toDegreesDecimal(lng) : -toDegreesDecimal(lng);
        }
    }

    private Double toDegreesDecimal(String DMS) {
        //Want degree decimal format from rational DMS format.
        //Exif: in ""degrees, minutes, seconds"" (DMS) in rational format.
        //Degree Decimal = degrees + minutes/60 + seconds/3600.

        String[] sDMS = DMS.split(",", 3);
        String[] sD = sDMS[0].split("/", 2);
        String[] sM = sDMS[1].split("/", 2);
        String[] sS = sDMS[2].split("/", 2);

        Double degrees = Double.valueOf(sD[0]) / Double.valueOf(sD[1]);
        Double minutes = Double.valueOf(sM[0]) / Double.valueOf(sM[1]);
        Double seconds = Double.valueOf(sS[0]) / Double.valueOf(sS[1]);

        return degrees + (minutes / 60) + (seconds / 3600);
    }

    public LatLng getLatLng() {
        if (isValid()) {
            return new LatLng(mLatitude, mLongitude);
        } else {
            return null;
        }
    }

    public boolean isValid() {
        return mIsValid;
    }
}
