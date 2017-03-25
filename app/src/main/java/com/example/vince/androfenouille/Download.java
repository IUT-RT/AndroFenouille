package com.example.vince.androfenouille;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Clément on 10/03/2017.
 */

public class Download {
    public Download(){

    }
    public String DownloadJson(String uRl){
        InputStream is;
        try {
            URL url = new URL(uRl);
            URLConnection c = url.openConnection();
            c.connect();
            is = c.getInputStream();
        } catch (MalformedURLException e1) {
            is = null;
        } catch (ProtocolException e1) {
            is = null;
        } catch (IOException e1) {
            is = null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {}

        try {
            is.close();
        } catch (IOException e) {}
        return sb.toString();
    }
}

