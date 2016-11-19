package com.example.android.quakereport;

import java.util.Date;

import static com.example.android.quakereport.R.id.date;
import static com.example.android.quakereport.R.id.magnitude;

/**
 * Created by JRStrele on 12/11/2016.
 */

public class Earthquake {
    /** Magnitude     */
    private double mMagnitude;
    /**Location     */
    private String mLocation;
    /**time of Quake     */
    private long mTimeInMilliseconds;

    private String mUrl;

    /** Earthquake object     */
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }
    /** define the constructors for the Earthquake */
    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {return mUrl;}
    }
