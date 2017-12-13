package andoird.fiestapp;

/**
 * Created by nicod on 12/12/2017.
 */

public class SoireePourClient {

    public int id_soiree;
    public String titre_soiree;
    public int heure;
    public int localisation;
    public String description;


    public SoireePourClient(int id_soiree, String titre_soiree, int heure, int localisation, String description){
        this.id_soiree=id_soiree;
        this.titre_soiree=titre_soiree;
        this.heure=heure;
        this.localisation=localisation;
        this.description=description;
    }
}
