package andoird.fiestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import andoird.fiestapp.Object.MapMarqueurUser;
import andoird.fiestapp.Object.User;
import andoird.fiestapp.rest.Rest;

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


        textLocalisation.setText(String.valueOf(app.laSoiree.getPosition()[0])+" , "+String.valueOf(app.laSoiree.getPosition()[1]));
        textHeure.setText(String.valueOf("Debut de la soiree: "+app.laSoiree.getDate()));
        textDescription.setText(app.laSoiree.getNom_soiree());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        MyApplication app = (MyApplication) getApplicationContext();

        LatLng positionSoiree = new LatLng(app.laSoiree.getPosition()[0], app.laSoiree.getPosition()[1]);

        mMap.addMarker(new MarkerOptions()
                .position(positionSoiree).title("Soiree")
                .zIndex(1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_fiestapp))
        );

        Rest rest = null;
        try {
            rest = new Rest();




            int length = app.laSoiree.getParticipants().size();

            for (int i = 0; i < length; i++) {
                rest = new Rest();
                JSONObject obj1 = new JSONObject();
                obj1.put("id",app.laSoiree.getParticipants().get(i).getId());
                User u = (User) rest.execute("/FindUserById", obj1).get();
                MapMarqueurUser monMarqueur = new MapMarqueurUser(u.getPosition(), u.getPrenom(), app.laSoiree.getParticipants().get(i).getStatus());
                LatLng positionUser2 = new LatLng(monMarqueur.getPosition()[0], monMarqueur.getPosition()[1]);
                if (app.getMyUser().getId().equals(u.getId())) {
                    mMap.addMarker(new MarkerOptions()
                            .position(positionUser2)
                            .title(monMarqueur.getPrenom())
                            .snippet(monMarqueur.getStatut())
                            .flat(true)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.cursor_user))
                            .zIndex(2.0f)
                    );
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(positionUser2));
                } else {
                    mMap.addMarker(new MarkerOptions()
                            .position(positionUser2)
                            .title(monMarqueur.getPrenom())
                            .snippet(monMarqueur.getStatut())
                            .icon(BitmapDescriptorFactory.defaultMarker(60 * i % 360))
                    );
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };


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
