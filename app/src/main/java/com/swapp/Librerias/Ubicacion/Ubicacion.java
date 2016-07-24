package com.swapp.Librerias.Ubicacion;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.swapp.SwappMainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by angel on 22/07/16.
 */

public class Ubicacion implements LocationListener {

    private SwappMainActivity activity;
    private String proveedor;
    private LocationManager manager;
    private boolean providerOn;

    public Ubicacion(Context ctx) {
        this.activity = (SwappMainActivity) ctx;
        manager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        proveedor = LocationManager.GPS_PROVIDER;
        providerOn = manager.isProviderEnabled(proveedor);
    }

    public boolean GPS() {
        if (manager.isProviderEnabled(proveedor)) {
            providerOn = true;

            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1340);
                }

            } else {
                manager.requestLocationUpdates(proveedor, 1000, 1, this);
            }

            return true;
        } else {
            providerOn = false;
            return false;
        }
    }

    public void getLocation() {

        if (providerOn) {

            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1340);
                }
            }else{
                Location lc = manager.getLastKnownLocation(proveedor);
                if( lc != null ){

                    try {
                        JSONObject json = new JSONObject();
                        json.put("x", lc.getLatitude());
                        json.put("y", lc.getLongitude());

                        activity.gps = json;

                    } catch (JSONException e) {}

                }
            }

        }

    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(activity, perm));
    }

}
