package com.swapp;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.swapp.Librerias.Notificaciones.BarNotificaciones;
import com.swapp.Librerias.Web.HttpClient;
import com.swapp.Librerias.Web.OnHttpRequestComplete;
import com.swapp.Librerias.Web.Response;
import com.swapp.Metodos.AndroidToJavaScript;
import com.swapp.Metodos.JavaScriptToAndroid;
import com.swapp.ServerConfig.ServerConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SwappMainActivity extends AppCompatActivity {

    public WebView web;
    public List url_back;
    public boolean cerrar;
    private ImageView act;
    public String json;
    public JSONObject gps;

    SwappMainActivity activity;

    public HttpClient client;

    public AndroidToJavaScript androidToJavaScript;

    public BarNotificaciones notificacion;

    public int noti = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swapp_main);

        getSupportActionBar().hide();

        url_back = new ArrayList();
        url_back.add(ServerConfig.URL_SERVER_LOCAL);

        cerrar = false;

        notificacion = new BarNotificaciones(this);

        web = (WebView) findViewById(R.id.web);
        web.setWebChromeClient(new WebChromeClient());
        web.getSettings().setJavaScriptEnabled(true);

        web.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {

            }
        });

        //web.clearCache(true);

        web.loadUrl(ServerConfig.URL_SERVER_LOCAL);
        web.addJavascriptInterface(new JavaScriptToAndroid(this), "SWAPP");

        notificacion = new BarNotificaciones(this);

        act = (ImageView) findViewById(R.id.act);
        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                web.clearCache(true);
                web.loadUrl(ServerConfig.URL_SERVER_LOCAL);

                notificacion.triggerNotification(
                    "Titulo de prueba "+noti,
                    "Mensaje de prueba "+noti,
                    BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.actualizar
                    )
                );

                noti++;

            }
        });

        androidToJavaScript = new AndroidToJavaScript(this);

        activity = this;

        client = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    String resultado = status.getResult().toString();
                    try {
                        JSONObject json = new JSONObject(resultado);
                        activity.json = json.toString();
                        androidToJavaScript.SWAPP_HTTP();
                        return;
                    }catch (JSONException e){

                        activity.json = status.getResult().toString();
                        androidToJavaScript.SWAPP_HTTP();

                        return;
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (cerrar) {
            super.onBackPressed();
        } else {
            if (!web.getUrl().equalsIgnoreCase(ServerConfig.URL_SERVER)) {
                int index = url_back.size() - 1;
                if( index < 0 ){ index = 0; }
                url_back.remove(index);
                index = url_back.size() - 1;
                if( index < 0 ){ index = 0; }
                web.loadUrl(url_back.get(index).toString());
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("¿Desea salir de la aplicación?");
                alertDialog.setTitle("Salir de la aplicación");
                alertDialog.setIcon(android.R.drawable.ic_lock_power_off);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        cerrar = true;
                        onBackPressed();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                });
                alertDialog.show();
            }
        }
    }


}
