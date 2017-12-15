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
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.Object.User;
import andoird.fiestapp.rest.Rest;

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
                Rest rest = null;
                JSONObject friend = new JSONObject();
                String[] array = TextNomAmi.split(" ");
                try {
                    friend.put("nom",array[1]);
                    friend.put("prenom",array[0]);
                    friend.put("position",app.laSoiree.getPosition());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("INVITER AMI", friend.toString() );
                try {
                    rest = new Rest();
                    Object ret = rest.execute("/AddUser",friend);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    rest = new Rest();
                    User u = (User) rest.execute("/FindUser",friend).get();
                    
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
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



        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.amis);
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
                                activite_a_lancer = new Intent(ActivityAmis.this, ActivityEtat.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.soirees:
                               /* textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);*/
                                activite_a_lancer = new Intent(ActivityAmis.this, Activity_MainActivity2.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.amis:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityAmis.this, ActivityAmis.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.notifications:
                                /*textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityAmis.this, ActivityNotifications.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                            case R.id.ajouter_soiree:
                           /*     textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*/
                                activite_a_lancer = new Intent(ActivityAmis.this, ActivityReglages.class);
                                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(activite_a_lancer);
                                break;
                        }
                        return false;
                    }
                });


    }


}
