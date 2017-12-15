package andoird.fiestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ActivityAmis extends AppCompatActivity {


    public final String TAG = "ActivityAmis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amis);



//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/" + Profile.getCurrentProfile().getId() + "/taggable_friends?limit=50",
//                null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//            /* handle the result */
//                        if (response != null) {
//                            Log.d(TAG, response.getJSONObject().toString());
//                            try {
//                                JSONArray amis = response.getJSONObject().getJSONArray("data");
//                                Log.d(TAG, "nb Amis" + amis.length());
//                                List<String> liste_a_afficher=new LinkedList();
//                                for (int i = 0; i < amis.length(); i++) {
//                                   // Log.d(TAG, amis.get(i).toString()); //ON RECUPERE LA LISTE ICI
//                                    Log.d(TAG, ((JSONObject) amis.get(i)).getString("name")); //ON RECUPERE LA LISTE ICI
//                                    liste_a_afficher.add(((JSONObject) amis.get(i)).getString("name"));
//
//                                }
//                                MyApplication app = (MyApplication) getApplicationContext();
//                                app.setAmis(liste_a_afficher);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        } else Log.d(TAG, "No response");
//                    }
//                }
//        ).executeAsync();





        ListView listView = (ListView) findViewById(R.id.list_ami);

        MyApplication app = (MyApplication) getApplicationContext();

//        List<String> amis;
//        do{
//            amis=app.getAmis();
//        }while(amis.size()==0);
        List<String> amis=app.getAmis();


        MySimpleArrayAdapterAmi adapter = new MySimpleArrayAdapterAmi(this, amis, app);
        listView.setAdapter(adapter);

        /*Le listener de quand on clique sur un item*/
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                MyApplication app = (MyApplication) getApplicationContext();
//                String sIdSoireeADetailler = ((TextView) view.findViewById(R.id.nom_ami)).getText().toString();
//
//
//                int idSoireeADetailler=0;
//                for(int a=0;a<app.listeSoirees.size();a++){
//                    String chaine= app.listeSoirees.get(a).getNom_soiree();
//                    if(chaine.equals(sIdSoireeADetailler)){
//                        idSoireeADetailler=a;
//                    }
//                }
//                //idSoireeADetailler = Integer.parseInt(sIdSoireeADetailler);
//                app.laSoiree=app.listeSoirees.get(idSoireeADetailler);
//                Intent intent = new Intent(ActivityAmis.this, Activity_MainActivity2.class);
//                startActivity(intent);
//            }
//        });













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
