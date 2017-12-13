package andoird.fiestapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicod on 12/12/2017.
 */

public class ListeDeSoireesPourClient {


    public List<SoireePourClient> TabSoireesClient=new ArrayList<SoireePourClient>();

    public ListeDeSoireesPourClient(){}

    public ListeDeSoireesPourClient(ListeDeSoireesPourClient ListeSoireesServeur){
        for(int i=0;i<ListeSoireesServeur.size();i++){
            TabSoireesClient.add(ListeSoireesServeur.get(i));
        }
    }

    public void add(SoireePourClient matchClient){
        TabSoireesClient.add(matchClient);
    }
    public void set(int index, SoireePourClient soireeClient){
        TabSoireesClient.set(index,soireeClient);
    }
    public int size(){return TabSoireesClient.size();}

    public SoireePourClient get(int index){
        return TabSoireesClient.get(index);
    }

    public SoireePourClient get_match_by_ID(int ID){
        SoireePourClient res;

        for(int i=0;i<10;i++) {
            res=TabSoireesClient.get(i);
            if(res.id_soiree==ID) {
                return res;
            }
        }
        return null;
    }

    public void remove(int index){
        TabSoireesClient.remove(index);
    }

    public void remove_by_ID(int ID){
        for(int i=0;i<10;i++) {
            if(TabSoireesClient.get(i).id_soiree==ID) {
                TabSoireesClient.remove(i);
            }
        }
    }

}
