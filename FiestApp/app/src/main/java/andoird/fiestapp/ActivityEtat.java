package andoird.fiestapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.facebook.login.LoginManager;

public class ActivityEtat extends AppCompatActivity {

    public int ID_NOTIFICATION = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat);
        RadioGroup groupe= (RadioGroup)findViewById(R.id.radioGroup);
        MyApplication app = (MyApplication) getApplicationContext();

        if(app.statut=="false"){groupe.check(R.id.pas_parti);
        app.statut="true";}
        else{groupe.check(app.statut_id);}

        RadioGroup a=(RadioGroup)findViewById(R.id.radioGroup);
        app.statut_id=a.getCheckedRadioButtonId();

        Button logout = (Button)findViewById(R.id.bouton_ok);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                MyApplication app = (MyApplication) getApplicationContext();

                RadioGroup a=(RadioGroup)findViewById(R.id.radioGroup);
                if(a.getCheckedRadioButtonId()!= app.statut_id){
                    app.statut_id=a.getCheckedRadioButtonId();
                    /*ENVOI AU SERVEUR LE CHANGEMENT DE STATUT DU MEC*/
                };
                Intent login = new Intent(ActivityEtat.this, Activity_MainActivity2.class);
                startActivity(login);
                finish();
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.etat);
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
                                activite_a_lancer = new Intent(ActivityEtat.this, ActivityEtat.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.soirees:
                               /* textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);*/
                                activite_a_lancer = new Intent(ActivityEtat.this, Activity_MainActivity2.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.amis:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityEtat.this, ActivityAmis.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.notifications:
                                /*textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityEtat.this, ActivityNotifications.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.ajouter_soiree:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityEtat.this, ActivityReglages.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                        }
                        return false;
                    }
                });






    }



}
