package andoird.fiestapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivitySoirees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_soiree);



        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.soirees);
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
                                activite_a_lancer = new Intent(ActivitySoirees.this, ActivityEtat.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.soirees:
                               /* textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);*/
                                activite_a_lancer = new Intent(ActivitySoirees.this, Activity_MainActivity2.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.amis:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivitySoirees.this, ActivityAmis.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.notifications:
                                /*textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivitySoirees.this, ActivityNotifications.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.ajouter_soiree:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivitySoirees.this, ActivityReglages.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                        }
                        return false;
                    }
                });


        
    }
}
