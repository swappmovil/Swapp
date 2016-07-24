package com.swapp.Metodos;

import com.swapp.SwappMainActivity;

/**
 * Created by angel on 23/07/16.
 */
public class AndroidToJavaScript {

    SwappMainActivity activity;

    public AndroidToJavaScript(SwappMainActivity activity){
        this.activity = activity;
    }

    public void SWAPP_GPS(String coordenadas){
        //activity.web.loadUrl("javascript:SWAPP_GPS('" + coordenadas + "')");
    }

    public void SWAPP_HTTP(){
        //activity.web.loadUrl("javascript:SWAPP_HTTP_INTERNA()");
    }

}
