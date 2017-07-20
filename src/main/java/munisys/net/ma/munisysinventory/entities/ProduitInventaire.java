package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class ProduitInventaire extends Produit implements Serializable {

    private int idProduitInventaire;
    private int idInventaire;
    private String sn;
    private String nomPoste;
    private String addIp;
    private boolean dhcp;
    private String collaborateur;


    public ProduitInventaire() {
    }

    public ProduitInventaire(String modele, String equipement, String marque, String matricule,
                             String nInventaire, String sn,String collaborateur, String nomPoste, String addIp,boolean dhcp,
                             int idInventaire) {

        super(modele, equipement, marque, matricule,nInventaire);
        this.sn = sn;
        this.nomPoste = nomPoste;
        this.addIp = addIp;
        this.dhcp = dhcp;
        this.collaborateur = collaborateur;
        this.idInventaire = idInventaire;
    }

    public ProduitInventaire(String modele, String equipement, String marque, String matricule,
                             String nInventaire, String sn,String collaborateur, String nomPoste, String addIp,int dhcp,
                             int idInventaire) {

        super(modele, equipement, marque, matricule,nInventaire);
        this.sn = sn;
        this.nomPoste = nomPoste;
        this.addIp = addIp;
        this.idInventaire=idInventaire;
        this.collaborateur = collaborateur;
        if(dhcp==1) {
            this.dhcp = true;
        }else{
            this.dhcp = false;
        }


    }




    public int getIdProduitInventaire() {
        return idProduitInventaire;
    }

    public void setIdProduitInventaire(int idProduitInventaire) {
        this.idProduitInventaire = idProduitInventaire;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public boolean isDhcp() {
        return dhcp;
    }

    public void setDhcp(boolean dhcp) {
        this.dhcp = dhcp;
    }

    public String getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(String collaborateur) {
        this.collaborateur = collaborateur;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getIdInventaire() {
        return idInventaire;
    }

    public void setIdInventaire(int idInventaire) {
        this.idInventaire = idInventaire;
    }

    @Override
    public String toString() {
        return "ProduitInventaire{" +
                "idProduitInventaire=" + idProduitInventaire +
                "idInventaire="+idInventaire+
                "sn=" + sn +
                ", nomPoste='" + nomPoste + '\'' +
                ", addIp='" + addIp + '\'' +
                ", dhcp=" + dhcp +
                ", collaborateur='" + collaborateur + '\'' +
                "} " + super.toString();
    }
}
