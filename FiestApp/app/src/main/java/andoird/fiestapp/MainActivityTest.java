package andoird.fiestapp;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import andoird.fiestapp.Object.User;
import andoird.fiestapp.rest.Rest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;


public class MainActivityTest extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CallbackManager callbackManager;
    public static final String TAG = "MainActivity";

    private TextView textFavorites;
    private TextView textSchedules;
    private TextView textMusic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    if (savedInstanceState == null) {
        // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, mainFragment).commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }*/
        setContentView(andoird.fiestapp.R.layout.activity_maintest);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(andoird.fiestapp.R.id.map);
        mapFragment.getMapAsync(this);

       /* try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.mathieu.fiestapp",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/


        //Facebook

        // Initialize your instance of callbackManager//
        callbackManager = CallbackManager.Factory.create();
        Log.d(TAG,"BeforeLogin");

        // Register your callback//
        LoginButton loginButton = (LoginButton) findViewById(andoird.fiestapp.R.id.login_button);
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
                    }

                    // If the user cancels the login, then call onCancel//
                    @Override
                    public void onCancel() {
                        Log.d(TAG,"Cancel");

                    }

                    // If an error occurs, then call onError//
                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG,exception.toString());
                    }
                });
        Log.d(TAG,"BeforeLogin"+ AccessToken.getCurrentAccessToken());
        if(Profile.getCurrentProfile()!=null) {
            Log.d(TAG, Profile.getCurrentProfile().getFirstName());


            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + Profile.getCurrentProfile().getId() + "/taggable_friends?limit=500",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
            /* handle the result */
    /*                        if (response != null) {
                                Log.d(TAG, response.getJSONObject().toString());
                                try {
                                    JSONArray amis = response.getJSONObject().getJSONArray("data");
                                    Log.d(TAG, "nb Amis" + amis.length());
                                    for (int i = 0; i < amis.length(); i++) {
                                        Log.d(TAG, amis.get(i).toString());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else Log.d(TAG, "No response");
                        */}
                    }
            ).executeAsync();
        }
        /*textFavorites = (TextView) findViewById(R.id.text_favorites);
        textSchedules = (TextView) findViewById(R.id.text_schedules);
        textMusic = (TextView) findViewById(R.id.text_music);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                textFavorites.setVisibility(View.VISIBLE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_schedules:
                                textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);
                                break;
                            case R.id.action_music:
                                textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);
                                break;
                        }
                        return false;
                    }
                });
*/
        Rest rest = null;
        try {
            rest = new Rest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("prenom", "Mathieu");
            obj.put("nom","Malgorn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            User user = (User) rest.execute("/FindUser",obj).get();
            Log.d(TAG, user.toString());
            obj.put("id",user.getId());
            rest = new Rest();
            user = (User) rest.execute("/FindUserById",obj).get();
            Log.d(TAG,user.toString());
            int[] pos = new int[2];
            pos[0] = 35;
            pos[1] = 45;
            User usertoAdd = new User("Test","Test",pos);
            rest = new Rest();
            Log.d(TAG,usertoAdd.toJSONObject().toString());
            Object ret = rest.execute("/AddUser",usertoAdd.toJSONObject());

            rest = new Rest();
            JSONObject obj1 = new JSONObject();
            obj1.put("nom_soiree","test");
            obj1.put("date","10");
            obj1.put("idCreateur","5a32e3eee94a48ff1c722f5b");
            rest.execute("/GetSoiree",obj1);


            Log.d(TAG,ret.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(45.386482, -71.93042700000001);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Thomas t'es PD"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
