package munisys.net.ma.munisysinventory.entities;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mehdibouhafs on 06/07/2017.
 */

public class Inventaire {

    private int id;
    private Client client;
    private ArrayList<ProduitInventaire> produitsInventaire;
    private Intervenant intervenant;
    private Date dateInventaire;

    public Inventaire() {
        this.dateInventaire = new Date();
    }

    public Inventaire(int id, Client client, ArrayList<ProduitInventaire> produitsInventaire, Intervenant intervenant, Date dateInventaire) {
        this.id = id;
        this.client = client;
        this.produitsInventaire = produitsInventaire;
        this.intervenant = intervenant;
        this.dateInventaire = dateInventaire;
    }

    public Inventaire(Client client, ArrayList<ProduitInventaire> produitsInventaire, Intervenant intervenant) {
        this.client = client;
        this.produitsInventaire = produitsInventaire;
        this.intervenant = intervenant;
        this.dateInventaire = new Date();
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

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
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

    @Override
    public String toString() {
        return "Inventaire{" +
                "id=" + id +
                ", client=" + client +
                ", produits =" + produitsInventaire +
                ", intervenant=" + intervenant +
                ", dateInventaire=" + dateInventaire +
                '}';
    }
}
