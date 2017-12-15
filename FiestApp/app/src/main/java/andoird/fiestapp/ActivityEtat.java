package andoird.fiestapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
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
                activite_a_lancer = new Intent(ActivityEtat.this, ActivityEtat.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.soirees:
                activite_a_lancer = new Intent(ActivityEtat.this, Activity_MainActivity2.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.notifications:
                activite_a_lancer = new Intent(ActivityEtat.this, ActivityNotifications.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.amis:
                activite_a_lancer = new Intent(ActivityEtat.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.ajouter_soiree:
                activite_a_lancer = new Intent(ActivityEtat.this, ActivityReglages.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
