package andoird.fiestapp;

import java.util.List;

/**
 * Created by nicod on 12/12/2017.
 */

public class SoireePourClient {

    public int id_soiree;
    public String titre_soiree;
    public int date;
    public int heure;
    public int localisation;
    public String description;

    private String id_createur;
    private List<String> tableau_id_invites;



    public SoireePourClient(int id_soiree, String titre_soiree, int heure, int date, int localisation, String description,String id_createur,List<String> tableau_id_invites){
        this.id_soiree=id_soiree;
        this.titre_soiree=titre_soiree;
        this.heure=heure;
        this.localisation=localisation;
        this.description=description;
        this.id_createur=id_createur;
        this.tableau_id_invites=tableau_id_invites;
    }
}
