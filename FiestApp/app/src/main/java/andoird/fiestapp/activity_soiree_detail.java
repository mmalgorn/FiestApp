package andoird.fiestapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class activity_soiree_detail extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MyApplication app = (MyApplication) getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soiree_detail);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        Button logout = (Button)findViewById(R.id.changer_etat);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(activity_soiree_detail.this, ActivityEtat.class);
                startActivity(login);
                finish();
            }
        });




        TextView textLocalisation = (TextView)findViewById(R.id.localisation);
        TextView textHeure = (TextView)findViewById(R.id.heure);
        TextView textDescription = (TextView)findViewById(R.id.description);


        textLocalisation.setText(String.valueOf(app.laSoiree.getPosition()[0])+String.valueOf(app.laSoiree.getPosition()[1]));
        textHeure.setText(String.valueOf(app.laSoiree.getDate()));
        textDescription.setText(app.laSoiree.getNom_soiree());



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MyApplication app = (MyApplication) getApplicationContext();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(app.laSoiree.getPosition()[0], app.laSoiree.getPosition()[1]);
        mMap.addMarker(new MarkerOptions().position(sydney).title(app.laSoiree.getNom_soiree()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //ajoute les entrées de menu_test à l'ActionBar
//        getMenuInflater().inflate(R.menu.barre_menu, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent activite_a_lancer;
//        switch (item.getItemId()){
//            case R.id.etat:
//                activite_a_lancer = new Intent(activity_soiree_detail.this, ActivityEtat.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//                return true;
//            case R.id.soirees:
//                activite_a_lancer = new Intent(activity_soiree_detail.this, Activity_MainActivity2.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//                return true;
//            case R.id.notifications:
//                activite_a_lancer = new Intent(activity_soiree_detail.this, ActivityNotifications.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//                return true;
//            case R.id.amis:
//                activite_a_lancer = new Intent(activity_soiree_detail.this, ActivityAmis.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//                return true;
//            case R.id.ajouter_soiree:
//                activite_a_lancer = new Intent(activity_soiree_detail.this, ActivityReglages.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
