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
import android.widget.ListView;
import android.widget.TextView;


import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import andoird.fiestapp.Object.ListSoiree;
import andoird.fiestapp.Object.ParticipantSoiree;
import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.Object.User;
import andoird.fiestapp.rest.Rest;


public class Activity_MainActivity2 extends AppCompatActivity {
    private static final String TAG = "Activity_MainActivity2";

//    private CallbackManager callbackManager;
//    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_liste_soiree);

        MyApplication app = (MyApplication) getApplicationContext();
        ///User me = app.myUser;

        Rest rest = null;
        try {
            rest = new Rest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject objMySoiree = new JSONObject();
            objMySoiree.put("idUser","5a33d555092592ae4cf42c66");

            ListSoiree list =(ListSoiree) rest.execute("/MySoirees",objMySoiree).get();
            for(int i=0;i<list.getListSoiree().size();i++){
                Log.d(TAG,list.getListSoiree().get(i).toString());
                app.listeSoirees.add(list.getListSoiree().get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.list);
        ListeDeSoireesPourClient listeSoiree = new ListeDeSoireesPourClient();


//        ParticipantSoiree personne1=new ParticipantSoiree("mathieu","pas_parti");
//        ParticipantSoiree personne2=new ParticipantSoiree("sebastien","pas_parti");
//        ParticipantSoiree personne3=new ParticipantSoiree("nicolas","pas_parti");
//
//        int[] la_position={0,20};
//        Soiree soiree1= new Soiree("15zefzefef15", 90619995, 10061995, "titre de la Soiree",la_position);
//        Soiree soiree2= new Soiree("15zefzefef15", 90619995, 10061995, "titre de la Soiree2",la_position);
//        Soiree soiree3= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree3",la_position);
//        Soiree soiree4= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree4",la_position);
//        Soiree soiree5= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree5",la_position);
//        Soiree soiree6= new Soiree("15zefzefef15", 90619995,  10061995, "titre de la Soiree6",la_position);
//
//        soiree1.addParticipant(personne1);
//        soiree1.addParticipant(personne2);
//        soiree1.addParticipant(personne3);
//        soiree2.addParticipant(personne1);
//        soiree2.addParticipant(personne2);
//        soiree2.addParticipant(personne3);
//        soiree3.addParticipant(personne1);
//        soiree3.addParticipant(personne2);
//        soiree3.addParticipant(personne3);
//        soiree4.addParticipant(personne1);
//        soiree4.addParticipant(personne2);
//        soiree4.addParticipant(personne3);
//        soiree5.addParticipant(personne1);
//        soiree5.addParticipant(personne2);
//        soiree5.addParticipant(personne3);
//        soiree6.addParticipant(personne1);
//        soiree6.addParticipant(personne2);
//        soiree6.addParticipant(personne3);
//
//
//        app.listeSoirees.add(soiree1);
//        app.listeSoirees.add(soiree2);
//        app.listeSoirees.add(soiree3);
//        app.listeSoirees.add(soiree4);
//        app.listeSoirees.add(soiree5);
//        app.listeSoirees.add(soiree6);

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, app.listeSoirees, app);
        listView.setAdapter(adapter);

        /*Le listener de quand on clique sur un item*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyApplication app = (MyApplication) getApplicationContext();
                String sIdSoireeADetailler = ((TextView) view.findViewById(R.id.nom_ami)).getText().toString();


                int idSoireeADetailler=0;
                for(int a=0;a<app.listeSoirees.size();a++){
                    String chaine= app.listeSoirees.get(a).getNom_soiree();
                    if(chaine.equals(sIdSoireeADetailler)){
                        idSoireeADetailler=a;
                    }
                }
                //idSoireeADetailler = Integer.parseInt(sIdSoireeADetailler);
                app.laSoiree=app.listeSoirees.get(idSoireeADetailler);
                Intent intent = new Intent(Activity_MainActivity2.this, activity_soiree_detail.class);
                startActivity(intent);
            }
        });



        Button logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(Activity_MainActivity2.this, MainActivity.class);
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
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityEtat.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.soirees:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, Activity_MainActivity2.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.notifications:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityNotifications.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.amis:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityAmis.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
            case R.id.ajouter_soiree:
                activite_a_lancer = new Intent(Activity_MainActivity2.this, ActivityReglages.class);
                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(activite_a_lancer);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
