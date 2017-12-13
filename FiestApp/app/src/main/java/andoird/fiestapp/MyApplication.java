package andoird.fiestapp;


import android.app.Application;

/**
 * Created by nicod on 12/12/2017.
 */

public class MyApplication extends Application {
    private ListeDeSoireesPourClient listeSoirees;
    private SoireePourClient SoireeEnDetail;

    public ListeDeSoireesPourClient getListeSoiree(){return listeSoirees;}
    public SoireePourClient getSoireeEnDetail(){return SoireeEnDetail;}

    public void setListeSoiree(ListeDeSoireesPourClient liste){
        this.listeSoirees = liste;
    }
    public void setSoireeEnDetail(SoireePourClient soiree) {
        this.SoireeEnDetail = soiree;
    }
}
