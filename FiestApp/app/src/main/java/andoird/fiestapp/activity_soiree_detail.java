package andoird.fiestapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soiree_detail);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(45.386482, -71.93042700000001);
        mMap.addMarker(new MarkerOptions().position(sydney).title("UN PD VIT ICI ET NOUS FAIT A BOUFFER TOUS LES JOURS"));
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
//            case R.id.reglages:
//                activite_a_lancer = new Intent(activity_soiree_detail.this, ActivityReglages.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
