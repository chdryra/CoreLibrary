/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 8 July, 2015
 */

package com.chdryra.android.mygenerallibrary;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by: Rizwan Choudrey
 * On: 08/07/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class AverageLatLng {
    private LatLng mGeoMidpoint;

    public AverageLatLng(LatLng[] latLngs) {
        Coords[] coords = new Coords[latLngs.length];
        for (int i = 0; i < latLngs.length; ++i) {
            LatLng latLng = latLngs[i];
            coords[i] = new Coords(latLng);
        }

        Coords average = getAverageCoords(coords);

        double lng = Math.atan2(average.y, average.x);
        double hyp = Math.sqrt(average.x * average.x + average.y * average.y);
        double lat = Math.atan2(average.z, hyp);

        mGeoMidpoint = new LatLng(Math.toDegrees(lat), Math.toDegrees(lng));
    }

    public LatLng getGeoMidpoint() {
        return mGeoMidpoint;
    }

    private Coords getAverageCoords(Coords[] coords) {
        double x = 0;
        double y = 0;
        double z = 0;

        int num = coords.length;
        for (Coords c : coords) {
            x += c.x / num;
            y += c.y / num;
            z += c.z / num;
        }

        return new Coords(x, y, z);
    }

    private class Coords {
        private double x;
        private double y;
        private double z;

        private Coords(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        private Coords(LatLng latLng) {
            double latRad = Math.toRadians(latLng.latitude);
            double lngRad = Math.toRadians(latLng.longitude);
            x = Math.cos(latRad) * Math.cos(lngRad);
            y = Math.cos(latRad) * Math.sin(lngRad);
            z = Math.sin(latRad);
        }
    }
}
