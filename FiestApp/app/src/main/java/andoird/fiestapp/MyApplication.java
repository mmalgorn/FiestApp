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


    public MyApplication(){
        this.listeSoirees=new LinkedList();
    }
//    public List<Soiree> getListeSoiree(){return listeSoirees;}
//
//    public void setListeSoiree(List<Soiree> liste){
//        this.listeSoirees = liste;
//    }
}
