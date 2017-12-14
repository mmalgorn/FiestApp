package com.example.mathieu.fiestapp.Object;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mathieu on 14/12/2017.
 */


public class Soiree {
    private String idCreateur;
    private int date;
    private int dateFin;
    private String nom_soiree;
    private ArrayList<ParticipantSoiree> participants;

    public Soiree(String idCreateur, int date, int dateFin, String nom_soiree, String participants) {
        this.idCreateur = idCreateur;
        this.date = date;
        this.dateFin = dateFin;
        this.nom_soiree = nom_soiree;
        this.participants = new ArrayList<ParticipantSoiree>();
    }

    public void addParticipant(ParticipantSoiree participantSoiree){
        boolean isPresent = false;
        for(int i = 0;i<this.participants.size();i++){
            if(this.participants.get(i).getId().equals(participantSoiree.getId())){
                isPresent = true;
            }
        }
        if(isPresent) {
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

    @Override
    public String toString() {
        return "Soiree{" +
                "idCreateur='" + idCreateur + '\'' +
                ", date=" + date +
                ", dateFin=" + dateFin +
                ", nom_soiree='" + nom_soiree + '\'' +
                ", participants=" + participants +
                '}';
    }

    public JSONObject to
}
