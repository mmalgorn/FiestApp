package andoird.fiestapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import andoird.fiestapp.Object.MapMarqueurUser;
import andoird.fiestapp.Object.Soiree;
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

        LatLng positionSoiree = new LatLng(app.laSoiree.getPosition()[0],app.laSoiree.getPosition()[1]);

        mMap.addMarker(new MarkerOptions()
                .position(positionSoiree).title("Soiree")
                .zIndex(1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_fiestapp))
        );
//        LatLng positionUser = new LatLng(app.laSoiree.getPosition()[0]+1,app.laSoiree.getPosition()[1]+2);
//        mMap.addMarker(new MarkerOptions()
//                .position(positionUser)
//                .title("Marqueur ")
//                .snippet("statut ")
//                .icon(BitmapDescriptorFactory.defaultMarker(60 ))
//                .zIndex(2.0f)
//        );
//        Log.d("TAG", String.valueOf(app.laSoiree.getParticipants().size()));
//
//        for(int i=0; i<10; i++){
//            LatLng positionUser2 = new LatLng(app.laSoiree.getPosition()[0]+i,app.laSoiree.getPosition()[1]+i);
//            mMap.addMarker(new MarkerOptions()
//                                .position(positionUser2)
//                                .title("Marqueur "+i)
//                                .snippet("statut "+i)
//                            .icon(BitmapDescriptorFactory.defaultMarker(12*i%360))
//            );
//        }
//
        mMap.moveCamera(CameraUpdateFactory.newLatLng(positionSoiree));

//        Rest rest = null;
//        try {
//            rest = new Rest();
//            JSONObject obj1 = new JSONObject();
//
//
//            Soiree s = (Soiree) rest.execute("/GetSoiree", obj1).get();
//            if (s != null) {
//                int length = s.getParticipants().size();
//
//                for(int i=0; i< length; i++){
//                    rest = new Rest();
//                    User u = (User) rest.execute("/FindUserById", s.getParticipants().get(i).getId()).get();
//                    MapMarqueurUser monMarqueur = new MapMarqueurUser(u.getPosition(),u.getPrenom(),s.getParticipants().get(i).getStatus());
//                    LatLng positionUser2 = new LatLng(monMarqueur.getPosition()[0], monMarqueur.getPosition()[1]);
//                    if(s.getIdCreateur().equals(u.getId())){
//                        mMap.addMarker(new MarkerOptions()
//                                .position(positionUser2)
//                                .title(monMarqueur.getPrenom())
//                                .snippet(monMarqueur.getStatut())
//                                .flat(true)
//                                .zIndex(2.0f)
//                        );
//
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(positionUser));
//                    }
//                    else{
//                        mMap.addMarker(new MarkerOptions()
//                                .position(positionUser)
//                                .title(monMarqueur.getPrenom())
//                                .snippet(monMarqueur.getStatut())
//                                .icon(BitmapDescriptorFactory.defaultMarker(12 * i % 360))
//                        );
//
//                    }
//                }
//            } else {
//                int[] posS = new int[2];
//                s = new Soiree("5a3317814c18cb19cde88c84", 78, 78, "TestSoire", posS);
//            }
//        }
//        catch(InterruptedException e){
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
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
