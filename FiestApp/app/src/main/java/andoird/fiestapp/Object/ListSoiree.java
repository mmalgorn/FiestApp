package andoird.fiestapp.Object;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mathieu on 14/12/2017.
 */

public class ListSoiree {
    ArrayList<Soiree> listSoiree;


    public ListSoiree(ArrayList<Soiree> listSoiree) {
        this.listSoiree = listSoiree;
    }

    public ListSoiree() {
        listSoiree = new ArrayList<Soiree>();
    }

    @Override
    public String toString() {
        return "ListSoiree{" +
                "listSoiree=" + listSoiree +
                '}';
    }

    public ArrayList<Soiree> getListSoiree() {
        return listSoiree;
    }

    public void setListSoiree(ArrayList<Soiree> listSoiree) {
        this.listSoiree = listSoiree;
    }

    public void addSoiree(Soiree soiree){
        boolean isPresent = false;
        Log.d("LISTSOIREE", String.valueOf(listSoiree.size()));
        for (int i = 0;i<listSoiree.size();i++){
            Log.d("LISTSOIRE",""+listSoiree.get(i).getId().equals(soiree.getId()));
            if(listSoiree.get(i).getId().equals(soiree.getId())){
                isPresent = true;
            }
        }
        if(!isPresent){
            Log.d("LISTSOIRE","ADD SOIREE");
            listSoiree.add(soiree);
        }
    }
    public void deleteSoiree(Soiree soiree){
        for(int i =0;i<listSoiree.size();i++){
            if(listSoiree.get(i).getId().equals(soiree.getId())){
                listSoiree.remove(i);
            }
        }
    }
}
