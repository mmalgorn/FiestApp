package andoird.fiestapp.rest;

/**
 * Created by mathieu on 13/12/2017.
 */

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.goebl.david.Response;
import com.goebl.david.Webb;
import com.goebl.david.WebbException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import andoird.fiestapp.Object.ParticipantSoiree;
import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.Object.User;

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
            case "/GetSoiree":
                this.path+=objects[0].toString();
                retour = GetSoiree((JSONObject) objects[1]);
                break;
            case "/AddSoiree":
                this.path+=objects[0].toString();
                retour = AddSoiree((JSONObject) objects[1]);
                break;
            case "/FinSoiree":
                this.path+=objects[0].toString();
                retour = removeSoiree((JSONObject) objects[1]);
                break;
            case "/UpdateStatus":
                this.path+=objects[0].toString();
                retour = UpdateStatus((JSONObject) objects[1]);
                break;
            case "/UpdatePosition" :
                this.path+=objects[0].toString();
                retour = UpdatePosition((JSONObject) objects[1]);
                break;
            case "/NewPart" :
                this.path+=objects[0].toString();
                retour = UpdatePart((JSONObject) objects[1]);
                break;
            case "/DeadFriend" :
                this.path+=objects[0].toString();
                retour = UpdatePart((JSONObject) objects[1]);
                break;

        }
// later we authenticate



//        webb.delete("/session").asVoid();
        return retour;
    }

    private Object UpdatePart(JSONObject obj) {
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            response = webb
                    .post(this.path)
                    .param("idSoiree", obj.get("idSoiree").toString())
                    .param("idUser", obj.get("idUser").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (WebbException e){
            e.printStackTrace();
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {

                Log.d(TAG,"UPDATE PART"+ apiResult.toString());
                return apiResult.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG, "Erreur Modify Status");
        return null;


    }

    private Object UpdatePosition(JSONObject obj) {
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            response = webb
                    .post(this.path)
                    .param("id", obj.get("id").toString())
                    .param("position", obj.get("position").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (WebbException e){
            e.printStackTrace();
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {

                Log.d(TAG, apiResult.toString());
                return apiResult.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG, "Erreur Modify Status");
        return null;
    }

    private Object UpdateStatus(JSONObject obj) {
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            response = webb
                    .post(this.path)
                    .param("status", obj.get("status").toString())
                    .param("idUser", obj.get("idUser").toString())
                    .param("idSoiree",obj.get("idSoiree").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (WebbException e){
            e.printStackTrace();
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {

                Log.d(TAG, apiResult.toString());
                return apiResult.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG, "Erreur Modify Status");
        return null;
    }

    private Object removeSoiree(JSONObject obj) {
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            response = webb
                    .post(this.path)
                    .param("nom", obj.get("nom").toString())
                    .param("date", obj.get("date").toString())
                    .param("idCreateur",obj.get("idCreateur").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (WebbException e){
            e.printStackTrace();
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {

                Log.d(TAG, apiResult.toString());
                return apiResult.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG, "Erreur Remove Soiree");
        return null;

    }

    private Object AddSoiree(JSONObject obj) {
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            response = webb
                    .post(this.path)
                    .param("date", obj.get("date").toString())
                    .param("dateFin", obj.get("dateFin").toString())
                    .param("nom",obj.get("nom_soiree").toString())
                    .param("idCreateur",obj.get("idCreateur"))
                    .param("position",obj.get("position"))
                    .param("participants",obj.get("participants"))

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            Log.d(TAG,"Erreur Json");
            Log.d(TAG,e.getMessage());
        }catch (WebbException e){
            Log.d(TAG,"Erreur com AddSoiree");
            Log.d(TAG,e.getMessage());
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {

                Log.d(TAG, apiResult.toString());
                return apiResult.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG, "Erreur Add Soiree");
        return null;
    }



    private Object GetSoiree(JSONObject obj) {
        Webb webb = Webb.create();
        Response<JSONObject> response = null;
        try {
            response = webb
                    .post(this.path)
                    .param("nom_soiree", obj.get("nom_soiree").toString())
                    .param("date", obj.get("date").toString())
                    .param("idCreateur",obj.get("idCreateur").toString())

                    .ensureSuccess()
                    .asJsonObject();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (WebbException e){
            e.printStackTrace();
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {
                Log.d(TAG, apiResult.toString());
                ArrayList<ParticipantSoiree> part = new ArrayList<>();
                JSONArray partJSON = new JSONArray(apiResult.getString("participants"));
                ParticipantSoiree ps;
                for (int i = 0; i < partJSON.length(); i++) {
                    ps = new ParticipantSoiree(
                            partJSON.getJSONObject(i).getString("id"),
                            partJSON.getJSONObject(i).getString("status"));
                    part.add(ps);
                }
                int[] position = new int[2];
                position[0] = apiResult.getJSONArray("position").getInt(0);
                position[1] = apiResult.getJSONArray("position").getInt(0);
                Log.d(TAG, partJSON.toString());
                Soiree soiree = new Soiree(
                        apiResult.getString("_id"),
                        apiResult.getString("idCreateur"),
                        apiResult.getInt("date"),
                        apiResult.getInt("datefin"),
                        apiResult.getString("nom_soiree"),
                        position,
                        part


                );
                Log.d(TAG, apiResult.toString());
                return soiree;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG,"Erreur Find USer");

        return null;


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
            Log.d(TAG,"Erreur Json");
        }catch (WebbException e){
            Log.d(TAG,"Erreur com");
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {

                Log.d(TAG, apiResult.toString());
                return apiResult.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Erreur Find USer");
        }
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
        }catch (WebbException e){
            Log.d(TAG,"Erreur com");
        }

        if(response!=null) {
            JSONObject apiResult = response.getBody();
            try {
                int[] position = new int[2];
                position[0] = apiResult.getJSONArray("position").getJSONArray(0).getInt(0);
                position[1] = apiResult.getJSONArray("position").getJSONArray(1).getInt(0);
                User user = new User(apiResult.getString("_id"), apiResult.getString("nom"), apiResult.getString("prenom"), position);
                Log.d(TAG, apiResult.toString());
                return user;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Erreur Find USer");
        }
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


