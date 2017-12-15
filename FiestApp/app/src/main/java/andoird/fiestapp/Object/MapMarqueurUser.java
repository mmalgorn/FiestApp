package andoird.fiestapp.Object;

/**
 * Created by Sebastien on 14/12/2017.
 */

public class MapMarqueurUser {
    private Double[] position;
    private String prenom;
    private String statut;

    public MapMarqueurUser(Double[] pos, String prenom, String statut){
        this.position=pos;
        this.prenom=prenom;
        this.statut=statut;
    }

    public Double[] getPosition() {
        return position;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getStatut() {
        return statut;
    }

    public void setPosition(Double[] position) {
        this.position = position;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
