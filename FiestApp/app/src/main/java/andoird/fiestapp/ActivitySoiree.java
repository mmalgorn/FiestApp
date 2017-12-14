package andoird.fiestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivitySoiree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soiree_creation);








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
                activite_a_lancer = new Intent(ActivitySoiree.this, ActivityEtat.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.soirees:
                activite_a_lancer = new Intent(ActivitySoiree.this, Activity_MainActivity2.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.notifications:
                activite_a_lancer = new Intent(ActivitySoiree.this, ActivityNotifications.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.amis:
                activite_a_lancer = new Intent(ActivitySoiree.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.ajouter_soiree:
                activite_a_lancer = new Intent(ActivitySoiree.this, ActivityReglages.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
