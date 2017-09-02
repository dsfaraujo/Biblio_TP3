package com.example.eleves.biblio_tp3;

/**
 * Created by eleves on 2017-09-02.
 */

public class LivreExPret {

    private String num_exemplaire;
    private String titre;
    private String adresse_ex;
    private String isbn;

    public LivreExPret(String num_exemplaire, String titre, String adresse_ex, String isbn) {
        this.num_exemplaire = num_exemplaire;
        this.titre = titre;
        this.adresse_ex = adresse_ex;
        this.isbn = isbn;
    }

    public LivreExPret(){

    }
    @Override
    public String toString() {
        return "Numéro d'exemplaire: " + num_exemplaire + '\n' +
                "Titre :" + titre + '\n' +
                "Adresse dans la bibliothèque: " + adresse_ex + '\n' +
                "ISBN: " + isbn + '\n';
    }

    public String getNum_exemplaire() {
        return num_exemplaire;
    }

    public void setNum_exemplaire(String num_exemplaire) {
        this.num_exemplaire = num_exemplaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse_ex() {
        return adresse_ex;
    }

    public void setAdresse_ex(String adresse_ex) {
        this.adresse_ex = adresse_ex;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
