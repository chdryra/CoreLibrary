/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.LocationUtils;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 08/07/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class LatLngMidpoint {

    public LatLng getGeoMidpoint(Iterable<LatLng> latLngs) {
        ArrayList<Coords> coords = new ArrayList<>();
        for (LatLng latLng : latLngs) {
            coords.add(new Coords(latLng));
        }

        Coords average = getAverageCoords(coords);

        double lng = Math.atan2(average.y, average.x);
        double hyp = Math.sqrt(average.x * average.x + average.y * average.y);
        double lat = Math.atan2(average.z, hyp);

        return new LatLng(Math.toDegrees(lat), Math.toDegrees(lng));
    }

    private Coords getAverageCoords(ArrayList<Coords> coords) {
        double x = 0;
        double y = 0;
        double z = 0;

        double num = (double)coords.size();
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
