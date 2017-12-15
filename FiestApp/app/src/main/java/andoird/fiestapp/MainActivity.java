package andoird.fiestapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;


import andoird.fiestapp.Object.User;
import andoird.fiestapp.rest.Rest;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity {


    Button facebook = null;
    private CallbackManager callbackManager;
    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();


        Log.d(TAG, "MAIN ACTIViTY");


        Log.d(TAG,"AFTER PERMISSION");

        //si on est deja connecté on va a l'acceuil
        Log.d(TAG, "BEFORE IF");
        if (Profile.getCurrentProfile() != null) {
            try {
                Log.d(TAG, "ADD PROFILE");
                Rest rest = new Rest();
                JSONObject obj = new JSONObject();
                obj.put("prenom", Profile.getCurrentProfile().getFirstName());
                obj.put("nom", Profile.getCurrentProfile().getLastName());
                User user;
                if ((user = (User) rest.execute("/FindUser", obj).get()) == null) {
                    MyApplication app = new MyApplication();
                    Double[] pos = new Double[2];
                    pos[0] = 0.0;
                    pos[1] = 0.0;
                    user = new User(
                            Profile.getCurrentProfile().getLastName(),
                            Profile.getCurrentProfile().getFirstName(),
                            pos
                    );
                    rest = new Rest();
                    rest.execute("/AddUser", user.toJSONObject());

                    rest = new Rest();
                    obj = new JSONObject();
                    obj.put("prenom", Profile.getCurrentProfile().getFirstName());
                    obj.put("nom", Profile.getCurrentProfile().getLastName());
                    user = (User) rest.execute("/FindUser", obj).get();
                } ;
                MyApplication app = ((MyApplication)getApplicationContext());
                app.setMyUser(user);
                if(user!=null)
                Log.d(TAG,"After set User" + user.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            Intent activite_a_lancer = new Intent(MainActivity.this, Activity_MainActivity2.class);
           activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
           startActivity(activite_a_lancer);
        }


        // Initialize your instance of callbackManager//

        Log.d(TAG, "BeforeLogin");

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");


        LoginManager.getInstance().registerCallback(callbackManager,

                // If the login attempt is successful, then call onSuccess and pass the LoginResult//
                new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // Print the user’s ID and the Auth Token to Android Studio’s Logcat Monitor//
                        Log.d(TAG, "User ID: " +
                                loginResult.getAccessToken().getUserId() + "\n" +
                                "Auth Token: " + loginResult.getAccessToken().getToken());

                        if (Profile.getCurrentProfile() != null) {
                            try {
                                Log.d(TAG, "ADD PROFILE");
                                Rest rest = new Rest();
                                JSONObject obj = new JSONObject();
                                obj.put("prenom", Profile.getCurrentProfile().getFirstName());
                                obj.put("nom", Profile.getCurrentProfile().getLastName());
                                User user;
                                if ((user = (User) rest.execute("/FindUser", obj).get()) == null) {
                                    MyApplication app = new MyApplication();
                                    Double[] pos = new Double[2];
                                    pos[0] = 0.0;
                                    pos[1] = 0.0;
                                    user = new User(
                                            Profile.getCurrentProfile().getLastName(),
                                            Profile.getCurrentProfile().getFirstName(),
                                            pos
                                    );
                                    rest = new Rest();
                                    rest.execute("/AddUser", user.toJSONObject());

                                    rest = new Rest();
                                    obj = new JSONObject();
                                    obj.put("prenom", Profile.getCurrentProfile().getFirstName());
                                    obj.put("nom", Profile.getCurrentProfile().getLastName());
                                    user = (User) rest.execute("/FindUser", obj).get();
                                } ;
                                MyApplication app = ((MyApplication)getApplicationContext());
                                app.setMyUser(user);

                                Log.d(TAG,"After set User" + user.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                            Intent activite_a_lancer = new Intent(MainActivity.this, Activity_MainActivity2.class);
                            activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(activite_a_lancer);
                        }

                        Intent activite_a_lancer = new Intent(MainActivity.this, Activity_MainActivity2.class);
                        activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(activite_a_lancer);

                    }

                    // If the user cancels the login, then call onCancel//
                    @Override
                    public void onCancel() {
                        Log.d(TAG, "Cancel");
                    }

                    // If an error occurs, then call onError//
                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, exception.toString());
                    }
                });
        Log.d(TAG, "BeforeLogin recup" + AccessToken.getCurrentAccessToken());
        if (Profile.getCurrentProfile() != null) {

            Log.d(TAG, Profile.getCurrentProfile().getFirstName());


            //User userToAdd = new User();

            //}
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + Profile.getCurrentProfile().getId() + "/taggable_friends?limit=50",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
            /* handle the result */
                            if (response != null) {
                                Log.d(TAG, response.getJSONObject().toString());
                                try {
                                    JSONArray amis = response.getJSONObject().getJSONArray("data");
                                    Log.d(TAG, "nb Amis" + amis.length());
                                    List<String> liste_a_afficher=new LinkedList();
                                    for (int i = 0; i < amis.length(); i++) {
                                        // Log.d(TAG, amis.get(i).toString()); //ON RECUPERE LA LISTE ICI
                                        Log.d(TAG, ((JSONObject) amis.get(i)).getString("name")); //ON RECUPERE LA LISTE ICI
                                        liste_a_afficher.add(((JSONObject) amis.get(i)).getString("name"));

                                    }
                                    MyApplication app = (MyApplication) getApplicationContext();
                                    app.setAmis(liste_a_afficher);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else Log.d(TAG, "No response");
                        }
                    }
            ).executeAsync();
        }


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, requestCode + " " + resultCode + " " + data.toString());
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }




}
