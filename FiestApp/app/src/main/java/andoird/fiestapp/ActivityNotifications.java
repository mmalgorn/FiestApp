package andoird.fiestapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import andoird.fiestapp.Object.Notification;

public class ActivityNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        MyApplication app = (MyApplication) getApplicationContext();
        ListView listView = (ListView) findViewById(R.id.list_notif);
        ArrayList<Notification> listeNotif = app.Notifs;

        MySimpleArrayAdapterNotif adapter = new MySimpleArrayAdapterNotif(this, listeNotif, app);
        listView.setAdapter(adapter);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.notifications);
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
                                activite_a_lancer = new Intent(ActivityNotifications.this, ActivityEtat.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.soirees:
                               /* textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);*/
                                activite_a_lancer = new Intent(ActivityNotifications.this, Activity_MainActivity2.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.amis:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityNotifications.this, ActivityAmis.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.notifications:
                                /*textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityNotifications.this, ActivityNotifications.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.ajouter_soiree:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityNotifications.this, ActivityReglages.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                        }
                        return false;
                    }
                });


    }


}
