package andoird.fiestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class ActivityAmis extends AppCompatActivity {


    public final String TAG = "ActivityAmis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amis);




        final ListView listView = (ListView) findViewById(R.id.list_ami);
        MyApplication app = (MyApplication) getApplicationContext();

        List<String> amis=app.getAmis();

        MySimpleArrayAdapterAmi adapter = new MySimpleArrayAdapterAmi(this, amis, app);
        listView.setAdapter(adapter);

        //Créer la liste amisChecked et selon si les boites sont cliqué on va les ajouter a la liste
        //app.amisCheck=new LinkedList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyApplication app = (MyApplication) getApplicationContext();
                String TextNomAmi = ((TextView) view.findViewById(R.id.nom_ami)).getText().toString();
                ImageView couleur = (ImageView)view.findViewById(R.id.couleur);
                //CheckBox check=(CheckBox)findViewById(R.id.check);
                Log.d("COUCOU", TextNomAmi);

                if ( !app.amisCheck.contains(TextNomAmi) ){
                    app.amisCheck.add(TextNomAmi);
                    couleur.setBackgroundColor(0xFF669900);
                    Log.d("COUCOU", " ONAJOUTE");


                }
                else{
                    if(app.amisCheck.contains(TextNomAmi)){
                        app.amisCheck.remove(app.amisCheck.indexOf(TextNomAmi));
                        couleur.setBackgroundColor(0xFF00DDFF);
                        Log.d("COUCOU", " ONENLEVE");


                    }
                }
            }
        });

        //Quand on valide envoyé la liste des amisCheck au serveur
        Button validation=(Button)findViewById(R.id.envoyer_invitation);
        validation.setOnClickListener(  new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MyApplication app = (MyApplication) getApplicationContext();

                //On envoi en rest l'ajout des personnes au match (dans app.amisCheck)
                for(int i=0;i<app.amisCheck.size();i++){
                    Log.d("ActivityAmis", app.amisCheck.get(i));

                    //ENVOI AU SERVERUR DE LA REQUETE AVEC   le nom : app.amisCheck.get(i)
                    /*

                    //CODE D'envoi serveur REST
                     */
                }

                Intent activite_a_lancer = new Intent(ActivityAmis.this, Activity_MainActivity2.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
            }
        } );
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
                activite_a_lancer = new Intent(ActivityAmis.this, ActivityEtat.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.soirees:
                activite_a_lancer = new Intent(ActivityAmis.this, Activity_MainActivity2.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.notifications:
                activite_a_lancer = new Intent(ActivityAmis.this, ActivityNotifications.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.amis:
                activite_a_lancer = new Intent(ActivityAmis.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.ajouter_soiree:
                activite_a_lancer = new Intent(ActivityAmis.this, ActivityReglages.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
