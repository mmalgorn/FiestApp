package andoird.fiestapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.rest.Rest;

public class ActivityReglages extends AppCompatActivity {

    private static final String TAG = "ACTIVITY REGLAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglages);
        final MyApplication app = (MyApplication) getApplicationContext();
        LatLng latLng;

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                MyApplication app = (MyApplication) getApplicationContext();
                app.latLng=place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


        Button textDatedebut =  (Button)findViewById(R.id.creation_invitation);
        textDatedebut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText textDebut = (EditText)findViewById(R.id.date_debut);
                EditText date_fin = (EditText)findViewById(R.id.date_fin);
                EditText nom_soiree = (EditText)findViewById(R.id.nom_soiree);

                JSONObject soireeACreer = new JSONObject();
                try {
                    soireeACreer.put("idCreateur",app.getMyUser().getId());
                    soireeACreer.put("position",app.getMyUser().getPosition());
                    soireeACreer.put("date",textDebut.getText().toString());
                    soireeACreer.put("dateFin",date_fin.getText().toString());
                    soireeACreer.put("nom",nom_soiree.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("CREATION SOIREE", soireeACreer.toString() );
                try {
                    Rest rest = new Rest();
                    Object ret = rest.execute("/AddSoiree",soireeACreer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //on redirige vers la liste d'amis à selectionner
                Intent activite_a_lancer = new Intent(ActivityReglages.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.ajouter_soiree);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent activite_a_lancer;
                        switch (item.getItemId()) {
                            case R.id.etat:
                              /*  item.setVisibility(View.VISIBLE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.GONE);*/
                                activite_a_lancer = new Intent(ActivityReglages.this, ActivityEtat.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.soirees:
                               /* textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);*/
                                activite_a_lancer = new Intent(ActivityReglages.this, Activity_MainActivity2.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.amis:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityReglages.this, ActivityAmis.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.notifications:
                                /*textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityReglages.this, ActivityNotifications.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.ajouter_soiree:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityReglages.this, ActivityReglages.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                        }
                        return false;
                    }
                });

    }


}
