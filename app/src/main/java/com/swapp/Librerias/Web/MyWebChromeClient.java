package com.swapp.Librerias.Web;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

/**
 * Created by angel on 25/07/16.
 */
public class MyWebChromeClient extends WebChromeClient {

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

        System.out.println(consoleMessage);

        return super.onConsoleMessage(consoleMessage);
    }
}
