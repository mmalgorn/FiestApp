package andoird.fiestapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity{


    Button facebook=null;
    private CallbackManager callbackManager;
    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //si on est deja connecté on va a l'acceuil
        if (Profile.getCurrentProfile() != null) {
            Intent activite_a_lancer= new Intent(MainActivity.this, Activity_MainActivity2.class);
            activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(activite_a_lancer);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
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

        }
        // Initialize your instance of callbackManager//
        callbackManager = CallbackManager.Factory.create();
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
                                 Intent activite_a_lancer = new Intent(MainActivity.this, Activity_MainActivity2.class);
                                 activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                 startActivity(activite_a_lancer);

                    }

                    // If the user cancels the login, then call onCancel//
                    @Override
                    public void onCancel() {
                        Log.d(TAG, "Cancel");
//                        Intent activite_a_lancer = new Intent(MainActivity.this, MainActivity.class);
//                        activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(activite_a_lancer);

                    }

                    // If an error occurs, then call onError//
                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, exception.toString());
//                        Intent activite_a_lancer = new Intent(MainActivity.this, MainActivity.class);
//                        activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(activite_a_lancer);
                    }
                });
        Log.d(TAG, "BeforeLogin recup" + AccessToken.getCurrentAccessToken());
        if (Profile.getCurrentProfile() != null) {

            Log.d(TAG, Profile.getCurrentProfile().getFirstName());

            //}
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + Profile.getCurrentProfile().getId() + "/taggable_friends?limit=500",
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
                                    for (int i = 0; i < amis.length(); i++) {
                                        Log.d(TAG, amis.get(i).toString());
                                    }
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
