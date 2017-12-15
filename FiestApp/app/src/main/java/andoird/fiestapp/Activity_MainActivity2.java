package andoird.fiestapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

import andoird.fiestapp.Object.ParticipantSoiree;
import andoird.fiestapp.Object.Soiree;

import static android.view.View.*;


public class Activity_MainActivity2 extends AppCompatActivity {

//    private CallbackManager callbackManager;
//    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkLocationPermission();
        setContentView(R.layout.activity_liste_soiree);

        MyApplication app = (MyApplication) getApplicationContext();

        ListView listView = (ListView) findViewById(R.id.list);
        ListeDeSoireesPourClient listeSoiree = new ListeDeSoireesPourClient();


        ParticipantSoiree personne1=new ParticipantSoiree("mathieu","pas_parti");
        ParticipantSoiree personne2=new ParticipantSoiree("sebastien","pas_parti");
        ParticipantSoiree personne3=new ParticipantSoiree("nicolas","pas_parti");

        Double[] la_position={Double.valueOf(0), Double.valueOf(20)};
        Soiree soiree1= new Soiree("15zefzefef15", 90619995, 10061995, "titre de la Soiree",la_position);
        Soiree soiree2= new Soiree("15zefzefef15", 90619995, 10061995, "titre de la Soiree2",la_position);
        Soiree soiree3= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree3",la_position);
        Soiree soiree4= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree4",la_position);
        Soiree soiree5= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree5",la_position);
        Soiree soiree6= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree6",la_position);

        soiree1.addParticipant(personne1);
        soiree1.addParticipant(personne2);
        soiree1.addParticipant(personne3);
        soiree2.addParticipant(personne1);
        soiree2.addParticipant(personne2);
        soiree2.addParticipant(personne3);
        soiree3.addParticipant(personne1);
        soiree3.addParticipant(personne2);
        soiree3.addParticipant(personne3);
        soiree4.addParticipant(personne1);
        soiree4.addParticipant(personne2);
        soiree4.addParticipant(personne3);
        soiree5.addParticipant(personne1);
        soiree5.addParticipant(personne2);
        soiree5.addParticipant(personne3);
        soiree6.addParticipant(personne1);
        soiree6.addParticipant(personne2);
        soiree6.addParticipant(personne3);




        app.listeSoirees.add(soiree1);
        app.listeSoirees.add(soiree2);
        app.listeSoirees.add(soiree3);
        app.listeSoirees.add(soiree4);
        app.listeSoirees.add(soiree5);
        app.listeSoirees.add(soiree6);

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, app.listeSoirees, app);
        listView.setAdapter(adapter);

        /*Le listener de quand on clique sur un item*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyApplication app = (MyApplication) getApplicationContext();
                String sIdSoireeADetailler = ((TextView) view.findViewById(R.id.localisation)).getText().toString();


                int idSoireeADetailler=0;
                for(int a=0;a<app.listeSoirees.size();a++){
                    Double[] pos= app.listeSoirees.get(a).getPosition();
                    String chaine=String.valueOf(pos[0])+String.valueOf(pos[1]);
                    if(chaine.equals(sIdSoireeADetailler)){
                        idSoireeADetailler=a;
                    }
                }
                //idSoireeADetailler = Integer.parseInt(sIdSoireeADetailler);
                app.laSoiree=app.listeSoirees.get(idSoireeADetailler);
                Intent intent = new Intent(Activity_MainActivity2.this, activity_soiree_detail.class);
                startActivity(intent);
            }
        });


        //LoginButton loginButton2 = (LoginButton) findViewById(R.id.login_button_2);
        //si on est pas connecté on retourne a la page d'avant

//        loginButton2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void OnClick(View v){
//                Intent activite_a_lancer = new Intent(Activity_MainActivity2.this, MainActivity.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//            }
//
//        });


        Button logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(Activity_MainActivity2.this, MainActivity.class);
                startActivity(login);
                finish();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.barre_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent activite_a_lancer;
        switch (item.getItemId()){
            case R.id.etat:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityEtat.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.soirees:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, Activity_MainActivity2.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.notifications:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityNotifications.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.amis:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.ajouter_soiree:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityReglages.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location")
                        .setMessage("Permission")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(Activity_MainActivity2.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            Intent intent = new Intent(this, GpsService.class);
            startService(intent);
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(this, GpsService.class);
                        startService(intent);
                        //Request location updates:
                        //  locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }



}
