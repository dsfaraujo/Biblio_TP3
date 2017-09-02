package com.example.eleves.biblio_tp3;

import java.util.Arrays;

/**
 * Created by eleves on 7/31/2017.
 */

public class Livre {

    private String ISBN;
    private String rare;
    private String id_auteur;
    private String id_genre;
    private String id_editeur;
    private String langue;
    private String id_titre;
    byte[] photo;


    public Livre(){}

    public Livre(String ISBN, String rare, String id_auteur, String id_genre, String id_editeur, String langue, String id_titre, byte[] photo) {
        this.ISBN = ISBN;
        this.rare = rare;
        this.id_auteur = id_auteur;
        this.id_genre = id_genre;
        this.id_editeur = id_editeur;
        this.langue = langue;
        this.id_titre = id_titre;

    }



    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getRare() {
        return rare;
    }

    @Override
    public String toString() {

        return  '\n' + "Auteur: "+ id_auteur +'\n' +
                '\n' + "Genre: " + id_genre +'\n' +
                '\n' + "Langue: " + langue  + '\n' ;
    }






    public void setRare(String rare) {
        this.rare = rare;
    }

    public String getId_auteur() {
        return id_auteur;
    }

    public void setId_auteur(String id_auteur) {
        this.id_auteur = id_auteur;
    }

    public String getId_genre() {
        return id_genre;
    }

    public void setId_genre(String id_genre) {
        this.id_genre = id_genre;
    }

    public String getId_editeur() {
        return id_editeur;
    }

    public void setId_editeur(String id_editeur) {
        this.id_editeur = id_editeur;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getId_titre() {
        return id_titre;
    }

    public void setId_titre(String id_titre) {
        this.id_titre = id_titre;
    }


}
