package andoird.fiestapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import andoird.fiestapp.Object.User;
import andoird.fiestapp.rest.Rest;

public class GpsService extends Service
{
    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;
      public MyApplication app;
    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;

        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
            app = (MyApplication) MyApplication.getAppContext();

            User user = app.getMyUser();
            if(user!=null) {
                Log.d(TAG, user.toString());
                Double[] pos = new Double[2];
                pos[0] = mLastLocation.getLatitude();
                pos[1] = mLastLocation.getLongitude();
                JSONObject objPos = new JSONObject();
                try {
                    objPos.put("id", user.getId());
                    objPos.put("position", pos[0] + "," + pos[1]);

                    Rest rest = new Rest();

                    Object ret = rest.execute("/UpdatePosition", objPos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                user.setPosition(pos);
                app.setMyUser(user);
            }

        }

        @Override
        public void onLocationChanged(Location location)
        {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            app = (MyApplication) MyApplication.getAppContext();

            User user = app.getMyUser();

            if(user != null) {
                Log.d(TAG,"NON NULL");
                Double[] pos = new Double[2];
                pos[0] = mLastLocation.getLatitude();
                pos[1] = mLastLocation.getLongitude();
                JSONObject objPos = new JSONObject();
                try {
                    objPos.put("id", user.getId());
                    objPos.put("position", pos[0] + "," + pos[1]);

                    Rest rest = new Rest();
                    Object ret = rest.execute("/UpdatePosition", objPos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                user.setPosition(pos);
                app.setMyUser(user);
            }
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        Log.e(TAG, "onCreate");
        app = (MyApplication)getApplicationContext();

        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }


}