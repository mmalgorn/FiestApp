package andoird.fiestapp;


import android.app.Application;


import java.util.LinkedList;
import java.util.List;

import andoird.fiestapp.Object.Soiree;

/**
 * Created by nicod on 12/12/2017.
 */

public class MyApplication extends Application {
    public List<Soiree> listeSoirees;
    public Soiree laSoiree;
    public List<String> amis;
    /*Pour nico*/
    public String statut;
    public int statut_id;

    public MyApplication(){

        this.listeSoirees=new LinkedList();
        this.statut="false";
        this.laSoiree=null;
        this.amis=new LinkedList();
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
}
