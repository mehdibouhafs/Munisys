package munisys.net.ma.munisysinventory.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mehdibouhafs on 04/07/2017.
 */

public class ProduitInventaire implements Serializable {

    private int id;
    private String idProduit;
    private String nInventaire;
    private String SN;
    private String nomPoste;
    private String addIp;
    private boolean dhcp;
    private boolean electriciteSepare;
    private boolean onduleurOperationnel;
    private String collaborateur;
    private Produit produit;


    public ProduitInventaire() {
    }


    public ProduitInventaire(String idProduit, String nInventaire, String SN, String nomPoste, String addIp, boolean dhcp, boolean electriciteSepare, boolean onduleurOperationnel, String collaborateur) {
        this.idProduit = idProduit;
        this.nInventaire = nInventaire;
        this.SN = SN;
        this.nomPoste = nomPoste;
        this.addIp = addIp;
        this.dhcp = dhcp;
        this.electriciteSepare = electriciteSepare;
        this.onduleurOperationnel = onduleurOperationnel;
        this.collaborateur = collaborateur;
    }

    public ProduitInventaire(int id, String idProduit, String nInventaire, String SN, String nomPoste, String addIp, boolean dhcp, boolean electriciteSepare, boolean onduleurOperationnel, String collaborateur) {
        this.id = id;
        this.idProduit = idProduit;
        this.nInventaire = nInventaire;
        this.SN = SN;
        this.nomPoste = nomPoste;
        this.addIp = addIp;
        this.dhcp = dhcp;
        this.electriciteSepare = electriciteSepare;
        this.onduleurOperationnel = onduleurOperationnel;
        this.collaborateur = collaborateur;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getnInventaire() {
        return nInventaire;
    }

    public void setnInventaire(String nInventaire) {
        this.nInventaire = nInventaire;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
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



    @Override
    public String toString() {
        return "ProduitInventaire{" +
                "id='" + id + '\'' +
                ", idProduit='" + idProduit + '\'' +
                ", nInventaire=" + nInventaire +
                ", SN='" + SN + '\'' +
                ", nomPoste='" + nomPoste + '\'' +
                ", addIp='" + addIp + '\'' +
                ", dhcp=" + dhcp +
                ", electriciteSepare=" + electriciteSepare +
                ", onduleurOperationnel=" + onduleurOperationnel +
                ", collaborateur='" + collaborateur + '\'' +
                '}';
    }
}
