package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by Admin on 7/10/2017.
 */

public class QuakeInfo {
    private double qMagnitude;
    private String qLocation;
    private long qTimeInMilliseconds;
    private String qUrl;

    public long getqTimeInMilliseconds() {
        return qTimeInMilliseconds;
    }

    public double getqMagnitude() {
        return qMagnitude;
    }

    public String getqLocation() {
        return qLocation;
    }


    public String getqUrl() {
        return qUrl;
    }

    public QuakeInfo(double magnitude, String location, long timeInMilliseconds, String url) {
        qMagnitude = magnitude;
        qLocation = location;
        qTimeInMilliseconds = timeInMilliseconds;
        qUrl = url;


    }
}
