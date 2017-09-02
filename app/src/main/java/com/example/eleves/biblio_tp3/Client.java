package com.example.eleves.biblio_tp3;

import java.util.List;

/**
 * Created by eleves on 7/31/2017.
 */

public class Client {

    private String id_client;
    private String nom;
    private String prenom;
    private String courriel;
    private String telephone;
    private String adresse;
    private String dtNaissance;

    public Client(String id_client, String nom, String prenom, String courriel, String telephone, String adresse, String dtNaissance) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
        this.telephone = telephone;
        this.adresse = adresse;
        this.dtNaissance = dtNaissance;
    }
    public Client(){}

    @Override
    public String toString() {
        return nom + prenom +  courriel + telephone + adresse +   dtNaissance;
    }

    public String toString1(Client client)
    {
        return nom;
    }

    public String getId_client() {return id_client;}

    public void setId_client(String id_client) {this.id_client = id_client;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getCourriel() {return courriel;}

    public void setCourriel(String courriel) {this.courriel = courriel;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}

    public String getAdresse() {return adresse;}

    public void setAdresse(String adresse) {this.adresse = adresse;}

    public String getDtNaissance() {return dtNaissance;}

    public void setDtNaissance(String dtNaissance) {this.dtNaissance = dtNaissance;}
}
