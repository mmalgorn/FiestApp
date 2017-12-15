package andoird.fiestapp.Object;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mathieu on 14/12/2017.
 */


public class Soiree {
    private String idCreateur;
    private int date;
    private int dateFin;
    private String nom_soiree;
    private String id;
    private ArrayList<ParticipantSoiree> participants;
    private Double[] position;

    public Soiree(String id,String idCreateur, int date, int dateFin, String nom_soiree, Double[] position,ArrayList<ParticipantSoiree> participants) {
        this.id=id;
        this.idCreateur = idCreateur;
        this.date = date;
        this.dateFin = dateFin;
        this.nom_soiree = nom_soiree;
        this.participants = participants;
        this.position = position;
    }


    public Soiree(String idCreateur, int date, int dateFin, String nom_soiree, Double[] position) {
        this.idCreateur = idCreateur;
        this.date = date;
        this.dateFin = dateFin;
        this.nom_soiree = nom_soiree;
        this.participants = new ArrayList<ParticipantSoiree>();
        this.position = position;
    }

    public Soiree(String idCreateur, int date, int dateFin, String nom_soiree, ArrayList<ParticipantSoiree> participants, Double[] position) {
        this.idCreateur = idCreateur;
        this.date = date;
        this.dateFin = dateFin;
        this.nom_soiree = nom_soiree;
        this.participants = participants;
        this.position = position;
    }

    public void addParticipant(ParticipantSoiree participantSoiree){
        boolean isPresent = false;
        for(int i = 0;i<this.participants.size();i++){
            if(this.participants.get(i).getId().equals(participantSoiree.getId())){
                isPresent = true;
            }
        }
        if(!isPresent) {
            this.participants.add(participantSoiree);
        }
    }

    public void removeParticipant(ParticipantSoiree participantSoiree){
        for(int i = 0;i<this.participants.size();i++){
            if(this.participants.get(i).getId().equals(participantSoiree.getId())){
                this.participants.remove(i);
            }
        }
    }

    public String getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(String idCreateur) {
        this.idCreateur = idCreateur;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDateFin() {
        return dateFin;
    }

    public void setDateFin(int dateFin) {
        this.dateFin = dateFin;
    }

    public String getNom_soiree() {
        return nom_soiree;
    }

    public void setNom_soiree(String nom_soiree) {
        this.nom_soiree = nom_soiree;
    }

    public ArrayList<ParticipantSoiree> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<ParticipantSoiree> participants) {
        this.participants = participants;
    }

    public Double[] getPosition() {
        return position;
    }

    public void setPosition(Double[] position) {
        this.position = position;
    }

    public String getId(){return this.id;}

    public void setId(String id){this.id = id;}

    @Override
    public String toString() {
        return "Soiree{" +
                "id='" + id +'\'' +
                "idCreateur='" + idCreateur + '\'' +
                ", date=" + date +
                ", dateFin=" + dateFin +
                ", nom_soiree='" + nom_soiree + '\'' +
                ", participants=" + participants +
                ", position=" + Arrays.toString(position) +
                '}';
    }

    public JSONObject toJSONObject(){
        JSONObject obj = new JSONObject();
        String part = "";

        if(this.participants.size()>0) {
            part = "[";
            for (int i = 0; i < this.participants.size(); i++) {
                part+= "{\"id\":\"";
                part+=this.participants.get(i).getId();
                part+= "\",\"status\":\"";
                part+=this.participants.get(i).getStatus();
                part+="\"}";
                if(i!=this.participants.size()-1){
                    part+=",";
                }
            }
            part+="]";
        }
        try {
            obj.put("id",this.getId());
            obj.put("idCreateur",this.getIdCreateur());
            obj.put("nom_soiree",this.getNom_soiree());
            obj.put("date",this.getDate());
            obj.put("dateFin",this.getDateFin());
            obj.put("participants",part);
            obj.put("position", this.position[0]+","+this.position[1]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    };
}
