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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

import static android.view.View.*;


public class Activity_MainActivity2 extends AppCompatActivity {

//    private CallbackManager callbackManager;
//    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_liste_soiree);

        MyApplication app = (MyApplication) getApplicationContext();

        ListView listView = (ListView) findViewById(R.id.list);
        ListeDeSoireesPourClient listeSoiree = new ListeDeSoireesPourClient();

        List<String> invitation= new LinkedList();
        invitation.add("Sebastien");
        invitation.add("jacky");
        invitation.add("Nico");
        invitation.add("Pedro");
        invitation.add("Marco");



        SoireePourClient soiree1= new SoireePourClient(1,"Soiree de Mathieu", 16, 9061995,47100,"Descritpion d'une soiree sympa","mathieu",invitation);
        SoireePourClient soiree2= new SoireePourClient(2,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree3= new SoireePourClient(3,"Soiree de Nico", 22, 9061995,58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree4= new SoireePourClient(4,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree5= new SoireePourClient(5,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree6= new SoireePourClient(6,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree7= new SoireePourClient(7,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree8= new SoireePourClient(8,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree9= new SoireePourClient(9,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree10= new SoireePourClient(10,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree11= new SoireePourClient(11,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree12= new SoireePourClient(12,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);
        SoireePourClient soiree13= new SoireePourClient(13,"Soiree de Nico", 22,9061995, 58600,"Descritpion d'une deuxieme soiree sympa","mathieu",invitation);



        listeSoiree.add(soiree1);
        listeSoiree.add(soiree2);
        listeSoiree.add(soiree3);
        listeSoiree.add(soiree4);
        listeSoiree.add(soiree5);
        listeSoiree.add(soiree6);
        listeSoiree.add(soiree7);
        listeSoiree.add(soiree8);
        listeSoiree.add(soiree9);
        listeSoiree.add(soiree10);
        listeSoiree.add(soiree11);
        listeSoiree.add(soiree12);
        listeSoiree.add(soiree13);


        app.setListeSoiree(listeSoiree);
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,listeSoiree, app);
        listView.setAdapter(adapter);

        /*Le listener de quand on clique sur un item*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyApplication app = (MyApplication) getApplicationContext();
                String sIdSoireeADetailler = ((TextView) view.findViewById(R.id.id_soiree)).getText().toString();
                int idSoireeADetailler = Integer.parseInt(sIdSoireeADetailler);
                app.setSoireeEnDetail(app.getListeSoiree().get(idSoireeADetailler));
                Intent intent = new Intent(Activity_MainActivity2.this, activity_soiree_detail.class);
                startActivity(intent);
            }
        });


        //LoginButton loginButton2 = (LoginButton) findViewById(R.id.login_button_2);
        //si on est pas connecté on retourne a la page d'avant

//        loginButton2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void OnClick(View v){
//                Intent activite_a_lancer = new Intent(Activity_MainActivity2.this, MainActivity.class);
//                activite_a_lancer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(activite_a_lancer);
//            }
//
//        });


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
