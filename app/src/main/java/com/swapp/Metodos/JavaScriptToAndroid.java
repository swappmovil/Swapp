package com.swapp.Metodos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.JavascriptInterface;

import com.swapp.Librerias.Ubicacion.Ubicacion;
import com.swapp.SwappMainActivity;

/**
 * Created by angel on 23/07/16.
 */
public class JavaScriptToAndroid {

    SwappMainActivity activity;
    Ubicacion ubicacion;
    public String URL;

    public JavaScriptToAndroid(SwappMainActivity activity){
        this.activity = activity;
        ubicacion = new Ubicacion(activity);
    }

    @JavascriptInterface
    public boolean GPS() {
        if( ubicacion.GPS() ){
            activity.gps = null;
            return true;
        }else{
            return false;
        }
    }

    @JavascriptInterface
    public String getJSON_GPS() {
        if( activity.gps != null ){
            return activity.gps.toString();
        }else{
            return null;
        }
    }

    @JavascriptInterface
    public String getJSON_HTTP() {
        return activity.json;
    }

    @JavascriptInterface
    public boolean HTTP(String url) {
        URL = url;
        if( compruebaConexion(activity) ) {
            new Thread(new Runnable() {
                public void run() {
                    activity.client.excecute(URL);
                }
            }).start();
            activity.json = null;
            return true;
        }else {
            activity.json = "Sin Internet";
        }
        return false;
    }

    public boolean compruebaConexion(Context context) {
        boolean connected = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < redes.length; i++) {
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

}
