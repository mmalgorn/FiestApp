package andoird.fiestapp;


import android.app.Application;
import android.content.Context;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import andoird.fiestapp.Object.ListSoiree;
import andoird.fiestapp.Object.Notification;
import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.Object.User;

/**
 * Created by nicod on 12/12/2017.
 */

public class MyApplication extends Application {
    private static Context context;
    public ListSoiree listeSoirees;
    public Soiree laSoiree;
    public User myUser ;
    public Double lat;
    public Double lon;
    public List<String> amis;
    public List<String> amisCheck;
    public Soiree soireeNotif;
    public ArrayList<Notification> Notifs = new ArrayList<Notification>();
    public LatLng latLng;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return MyApplication.context;
    }



    /*Pour nico*/
    public String statut;
    public int statut_id;

    public MyApplication(){

        this.listeSoirees=new ListSoiree();
        this.statut="false";
        this.laSoiree=null;
        this.amis=new LinkedList();
        this.amisCheck=new LinkedList();

    }

    public void setAmis(List<String> liste){
        this.amis=liste;
    }

    public List<String> getAmis(){
        return amis;
    }
//    public List<Soiree> getListeSoiree(){return listeSoirees;}
//
//    public void setListeSoiree(List<Soiree> liste){
//        this.listeSoirees = liste;
//    }

    public User getMyUser(){return this.myUser;}

    public void setMyUser(User myUser){this.myUser=myUser;}

    public ListSoiree getListeSoirees(){return listeSoirees;}

    public void setSoireeNotif(Soiree soireeNotif){this.soireeNotif=soireeNotif;}

    public Soiree getSoireeNotif(){return soireeNotif;}

    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public Double getLat(){
        return this.lat;
    }
    public Double getLon(){
        return this.lon;
    }
}
