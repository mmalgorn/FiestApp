package andoird.fiestapp.Object;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by mathieu on 14/12/2017.
 */

public class User {

    private String id;
    private String nom;
    private String prenom;
    private int[] position;

    public User(String id,String nom, String prenom, int[] position) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.position = position;
    }

    public User(String nom, String prenom, int[] pos) {
        this.nom=nom;
        this.prenom=prenom;
        this.position=pos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", position=" + Arrays.toString(position) +
                '}';
    }

    public void setId(String id){
        this.id = id;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("nom", this.nom);
            obj.put("prenom", this.prenom);
                obj.put("position", this.position[0]+","+this.position[1]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

