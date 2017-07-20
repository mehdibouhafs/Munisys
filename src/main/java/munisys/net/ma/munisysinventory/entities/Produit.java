package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class Produit implements Serializable {

    private String modele;
    private String equipement;
    private String marque;
    private String matricule;
    private String nInventaire;



    public Produit() {
    }

    public Produit(String modele, String equipement, String marque, String matricule,String nInventaire) {
        this.modele = modele;
        this.equipement = equipement;
        this.marque = marque;
        this.matricule = matricule;
        this.nInventaire = nInventaire;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getnInventaire() {
        return nInventaire;
    }

    public void setnInventaire(String nInventaire) {
        this.nInventaire = nInventaire;
    }


    @Override
    public String toString() {
        return "Produit{" +
                "modele='" + modele + '\'' +
                ", equipement='" + equipement + '\'' +
                ", marque='" + marque + '\'' +
                ", matricule='" + matricule + '\'' +
                ", nInventaire='" + nInventaire + '\'' +
                '}';
    }
}