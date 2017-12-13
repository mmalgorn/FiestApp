package com.example.mathieu.fiestapp.rest;

/**
 * Created by mathieu on 13/12/2017.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class Rest {

    private static final String serveur =
            "http://localhost:3000";

    public URL urlRest;
    public HttpURLConnection connection;

    public Rest() {
        try {
            this.urlRest = new URL(serveur);
            this.connection = (HttpURLConnection) this.urlRest.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getUser(String nom,String prenom){
        this.connection.setRequestProperty();
    }
}


