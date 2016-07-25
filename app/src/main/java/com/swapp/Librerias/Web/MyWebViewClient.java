package com.swapp.Librerias.Web;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by angel on 25/07/16.
 */
public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if(url.contains("somePartOfYourUniqueUrl")){
            return super.shouldOverrideUrlLoading(view, url);
        } else {

            /*
            String[] path = url.split("/");

            System.out.println( path.toString() );

            Log.i("Consola", url);
            Log.i("Consola", path[3]);

            if( path[3].equalsIgnoreCase("v2.7") ){
                view.loadUrl(url);
                view.loadUrl("http://vlzangel.cuccfree.com/swapp/index.php?ubicacion=home");
            }else{
                view.loadUrl(url);
            }
            */

            view.loadUrl(url);

            return true;
        }

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        Log.i("Consola", "Pagina Cargada: "+url);

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        Log.i("Consola", "Cargando Pagina: "+url);
    }
}
