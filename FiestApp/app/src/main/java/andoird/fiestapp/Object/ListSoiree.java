package andoird.fiestapp.Object;

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
        for (int i = 0;i<listSoiree.size();i++){
            if(listSoiree.get(i).getId().equals(soiree.getId())){
                isPresent = true;
            }
        }
        if(!isPresent){
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
