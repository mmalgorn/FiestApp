package com.example.mathieu.fiestapp.rest;

/**
 * Created by mathieu on 13/12/2017.
 */
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.mathieu.fiestapp.Object.User;
import com.goebl.david.Response;
import com.goebl.david.Webb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class Rest extends AsyncTask {

    private static final String TAG = "REST";
    URL url;
    private String path = "http://192.168.196.1:3000";
    StringBuffer response;
    String responseText;
    private static final String POST_PARAMS_NOM = "{nom:'Malgorn',prenom:'Mathieu'";
    private static final String POST_PARAMS_PRENOM = "prenom=Mathieu";
    private static final String POST_URL = "http://192.168.196.1:3000/FindUser";

    public Rest() throws JSONException {



    }

    @Override
    protected Object doInBackground(Object[] objects) {

        // create the client (one-time, can be used from different threads
        //webb.setBaseUri(SyncPreferences.REST_ENDPOINT);
        //webb.setDefaultHeader(Webb.HDR_USER_AGENT, Const.UA);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
        this.path = "http://192.168.196.1:3000";
        Object retour = null;
        switch (objects[0].toString()){
            case "/FindUser":
                this.path+=objects[0].toString();
                retour = FindUser((JSONObject)objects[1]);
                break;
            case "/FindUserById":
                this.path+=objects[0].toString();
                retour = FindUserById((JSONObject) objects[1]);
                break;
            case "/AddUser":
                this.path+=objects[0].toString();
                retour = AddUser((JSONObject) objects[1]);
                break;
        }
// later we authenticate



//        webb.delete("/session").asVoid();
        return retour;
    }

    private Object AddUser(JSONObject obj) {
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            Log.d(TAG,obj.toString()+obj.getString("prenom"));
            response = webb
                    .post(this.path)
                    .param("prenom", obj.get("prenom").toString())
                    .param("nom", obj.get("nom").toString())
                    .param("position",obj.get("position").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject apiResult = response.getBody();
        try {

            Log.d(TAG,apiResult.toString());
            return apiResult.getString("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"Erreur Find USer");

        return null;
    }

    public Object FindUser(JSONObject obj){
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            Log.d(TAG,obj.toString()+obj.getString("prenom"));
            response = webb
                    .post(this.path)
                    .param("prenom", obj.get("prenom").toString())
                    .param("nom", obj.get("nom").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject apiResult = response.getBody();
        try {
            int[] position = new int[2];
            position[0] = apiResult.getJSONArray("position").getJSONArray(0).getInt(0);
            position[1] = apiResult.getJSONArray("position").getJSONArray(1).getInt(0);
            User user = new User(apiResult.getString("_id"),apiResult.getString("nom"),apiResult.getString("prenom"),position);
            Log.d(TAG,apiResult.toString());
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"Erreur Find USer");

        return null;

    }

    public Object FindUserById(JSONObject obj){
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            response = webb
                    .post(this.path)
                    .param("id", obj.get("id").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject apiResult = response.getBody();
        try {
            int[] position = new int[2];
            position[0] = apiResult.getJSONArray("position").getJSONArray(0).getInt(0);
            position[1] = apiResult.getJSONArray("position").getJSONArray(1).getInt(0);
            User user = new User(apiResult.getString("_id"),apiResult.getString("nom"),apiResult.getString("prenom"),position);
            Log.d(TAG,apiResult.toString());
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"Erreur Find USer");

        return null;

    }


}


