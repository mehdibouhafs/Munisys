package munisys.net.ma.munisysinventory.entities;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mehdibouhafs on 06/07/2017.
 */

public class Inventaire {

    private int id;
    private Client client;
    private ArrayList<ProduitInventaire> produitsInventaire;
    private ArrayList<Intervenant> intervenants;
    private SiteInventaire siteInventaire;
    private Date dateInventaire;

    public Inventaire() {
        this.dateInventaire = new Date();
    }

    public Inventaire(Client client, ArrayList<Intervenant>  intervenants, SiteInventaire siteInventaire, Date dateInventaire) {
        this.client = client;
        this.intervenants = intervenants;
        this.siteInventaire = siteInventaire;
        this.dateInventaire = dateInventaire;
    }

    public Inventaire(int id, Client client, SiteInventaire siteInventaire, ArrayList<Intervenant>  intervenants,ArrayList<ProduitInventaire> produitsInventaire, Date dateInventaire) {
        this.id = id;
        this.client = client;
        this.intervenants = intervenants;
        this.siteInventaire = siteInventaire;
        this.dateInventaire = dateInventaire;
        this.produitsInventaire = produitsInventaire;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<ProduitInventaire> getProduits() {
        return produitsInventaire;
    }

    public void setProduits(ArrayList<ProduitInventaire> produits) {
        this.produitsInventaire = produits;
    }

    public ArrayList<Intervenant> getIntervenants() {
        return intervenants;
    }

    public void setIntervenants(ArrayList<Intervenant> intervenants) {
        this.intervenants = intervenants;
    }

    public Date getDateInventaire() {
        return dateInventaire;
    }

    public void setDateInventaire(Date dateInventaire) {
        this.dateInventaire = dateInventaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ProduitInventaire> getProduitsInventaire() {
        return produitsInventaire;
    }

    public void setProduitsInventaire(ArrayList<ProduitInventaire> produitsInventaire) {
        this.produitsInventaire = produitsInventaire;
    }

    public SiteInventaire getSiteInventaire() {
        return siteInventaire;
    }

    public void setSiteInventaire(SiteInventaire siteInventaire) {
        this.siteInventaire = siteInventaire;
    }

    @Override
    public String toString() {
        return "Inventaire{" +
                "client=" + client +
                ", siteInventaire=" + siteInventaire +
                ", dateInventaire=" + dateInventaire +
                '}';
    }
}
