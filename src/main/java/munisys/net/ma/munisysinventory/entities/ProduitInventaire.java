package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class ProduitInventaire extends Produit implements Serializable {

    private int idProduitInventaire;
    private String nomPoste;
    private String addIp;
    private boolean dhcp;
    private boolean electriciteSepare;
    private boolean onduleurOperationnel;
    private String collaborateur;


    public ProduitInventaire() {
    }

    public ProduitInventaire(String modele, String equipement, String marque, String matricule,
                             String nInventaire, String sn,String collaborateur, String nomPoste, String addIp, boolean dhcp,
                             boolean electriciteSepare, boolean onduleurOperationnel) {

        super(modele, equipement, marque, matricule,sn,nInventaire);
        this.nomPoste = nomPoste;
        this.addIp = addIp;
        this.dhcp = dhcp;
        this.electriciteSepare = electriciteSepare;
        this.onduleurOperationnel = onduleurOperationnel;
        this.collaborateur = collaborateur;
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

    public boolean isElectriciteSepare() {
        return electriciteSepare;
    }

    public void setElectriciteSepare(boolean electriciteSepare) {
        this.electriciteSepare = electriciteSepare;
    }

    public boolean isOnduleurOperationnel() {
        return onduleurOperationnel;
    }

    public void setOnduleurOperationnel(boolean onduleurOperationnel) {
        this.onduleurOperationnel = onduleurOperationnel;
    }

    public String getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(String collaborateur) {
        this.collaborateur = collaborateur;
    }




}
