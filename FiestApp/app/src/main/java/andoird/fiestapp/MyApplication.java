package andoird.fiestapp;


import android.app.Application;
import android.content.Context;


import java.util.LinkedList;
import java.util.List;

import andoird.fiestapp.Object.Soiree;
import andoird.fiestapp.Object.User;

/**
 * Created by nicod on 12/12/2017.
 */

public class MyApplication extends Application {
    private static Context context;
    public List<Soiree> listeSoirees;
    public Soiree laSoiree;
    public User myUser ;
    public Double lat;
    public Double lon;


    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return MyApplication.context;
    }


    public MyApplication(){
        this.listeSoirees=new LinkedList();
       // this.myUser = new User("test","test",new Double[2]);
    }
//    public List<Soiree> getListeSoiree(){return listeSoirees;}
//
//    public void setListeSoiree(List<Soiree> liste){
//        this.listeSoirees = liste;
//    }

    public User getMyUser(){return this.myUser;}

    public void setMyUser(User myUser){this.myUser=myUser;}

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
