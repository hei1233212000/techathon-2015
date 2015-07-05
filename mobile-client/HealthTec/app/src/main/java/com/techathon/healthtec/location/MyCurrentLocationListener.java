package com.techathon.healthtec.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Paptimus on 4/7/2015.
 */
public class MyCurrentLocationListener implements LocationListener {

    ArrayList<Location> locationRecordList = new ArrayList<Location>();

    public ArrayList<Location> getLocationRecordList(){
        return locationRecordList;
    }

    public void setLocationRecordList(ArrayList<Location> locationRecordList){
        this.locationRecordList = locationRecordList;
    }

    @Override
    public void onLocationChanged(Location location) {

        String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
        locationRecordList.add(location);
        //I make a log to see the results
        //Log.e("MY CURRENT LOCATION", "add location: " + myLocation);

    }

    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    public void onProviderEnabled(String s) {

    }

    public void onProviderDisabled(String s) {

    }
}