package munisys.net.ma.munisysinventory.entities;

import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mehdibouhafs on 06/07/2017.
 */

public class Inventaire implements Serializable {

    private int id;
    private Client client;
    private ArrayList<ProduitInventaire> produitsInventaires;
    private ArrayList<Intervenant> intervenants;
    private ArrayList<SiteInventaire> siteInventaires;
    private Date dateInventaire;

    private boolean electriciteSeparer;
    private boolean majAntivirus;
    private boolean empEquipements;
    private boolean onduleurOperationnel;
    private boolean depoussierage;
    private boolean etatCablage;

    private String autreTravauxRealiser;

    public Inventaire() {
        
    }

    public Inventaire(int id, Client client, ArrayList<ProduitInventaire> produitsInventaire, ArrayList<Intervenant> intervenants, ArrayList<SiteInventaire> siteInventaire, Date dateInventaire,
                      boolean electriciteSeparer, boolean majAntivirus, boolean empEquipements, boolean onduleurOperationnel, boolean depoussierage, boolean etatCablage, String autreTravauxRealiser) {
        this.id = id;
        this.client = client;
        this.produitsInventaires = produitsInventaire;
        this.intervenants = intervenants;
        this.siteInventaires = siteInventaire;
        this.dateInventaire = dateInventaire;
        this.electriciteSeparer = electriciteSeparer;
        this.majAntivirus = majAntivirus;
        this.empEquipements = empEquipements;
        this.onduleurOperationnel = onduleurOperationnel;
        this.depoussierage = depoussierage;
        this.etatCablage = etatCablage;
        this.autreTravauxRealiser = autreTravauxRealiser;
    }

    public Inventaire(Client client, ArrayList<ProduitInventaire> produitsInventaire, ArrayList<Intervenant> intervenants, ArrayList<SiteInventaire> siteInventaire, Date dateInventaire, boolean electriciteSeparer, boolean majAntivirus, boolean empEquipements, boolean onduleurOperationnel, boolean depoussierage, boolean etatCablage, String autreTravauxRealiser) {
        this.client = client;
        this.produitsInventaires = produitsInventaire;
        this.intervenants = intervenants;
        this.siteInventaires = siteInventaire;
        this.dateInventaire = dateInventaire;
        this.electriciteSeparer = electriciteSeparer;
        this.majAntivirus = majAntivirus;
        this.empEquipements = empEquipements;
        this.onduleurOperationnel = onduleurOperationnel;
        this.depoussierage = depoussierage;
        this.etatCablage = etatCablage;
        this.autreTravauxRealiser = autreTravauxRealiser;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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


    public ArrayList<ProduitInventaire> getProduitsInventaires() {
        return produitsInventaires;
    }

    public void setProduitsInventaires(ArrayList<ProduitInventaire> produitsInventaires) {
        this.produitsInventaires = produitsInventaires;
    }

    public ArrayList<SiteInventaire> getSiteInventaires() {
        return siteInventaires;
    }

    public void setSiteInventaires(ArrayList<SiteInventaire> siteInventaires) {
        this.siteInventaires = siteInventaires;
    }

    public boolean isElectriciteSeparer() {
        return electriciteSeparer;
    }

    public void setElectriciteSeparerInt(int  electriciteSeparer) {
        if(electriciteSeparer == 1) {
            this.electriciteSeparer = true;
        }else{
            this.electriciteSeparer = false;
        }
    }

    public boolean isMajAntivirus() {
        return majAntivirus;
    }

    public void setMajAntivirusInt(int majAntivirus) {
        if(majAntivirus == 1) {
            this.majAntivirus = true;
        }else{
            this.majAntivirus = false;
        }
    }

    public boolean isEmpEquipements() {
        return empEquipements;
    }

    public void setEmpEquipementsInt(int empEquipements) {
        if(empEquipements==1) {
            this.empEquipements = true;
        }else{
            this.empEquipements = false;
        }
    }

    public boolean isOnduleurOperationnel() {
        return onduleurOperationnel;
    }

    public void setOnduleurOperationnelInt(int onduleurOperationnel) {
        if(onduleurOperationnel == 1) {
            this.onduleurOperationnel = true;
        }else{
            this.onduleurOperationnel = true;
        }
    }

    public boolean isDepoussierage() {
        return depoussierage;
    }

    public void setDepoussierageInt(int depoussierage) {
        if(depoussierage == 1) {
            this.depoussierage = true;
        }else{
            this.depoussierage = false;
        }
    }

    public boolean isEtatCablage() {
        return etatCablage;
    }

    public void setEtatCablage(int etatCablage) {
        if(etatCablage==1) {
            this.etatCablage = true;
        }else{
            this.etatCablage = false;
        }
    }

    public String getAutreTravauxRealiser() {
        return autreTravauxRealiser;
    }

    public void setAutreTravauxRealiser(String autreTravauxRealiser) {
        this.autreTravauxRealiser = autreTravauxRealiser;
    }

    @Override
    public String toString() {
        return "Inventaire{" +
                "id=" + id +
                ", client=" + client +
                ", produitsInventaire=" + produitsInventaires +
                ", intervenants=" + intervenants +
                ", siteInventaire=" + siteInventaires +
                ", dateInventaire=" + dateInventaire +
                ", electriciteSeparer=" + electriciteSeparer +
                ", majAntivirus=" + majAntivirus +
                ", empEquipements=" + empEquipements +
                ", onduleurOperationnel=" + onduleurOperationnel +
                ", depoussierage=" + depoussierage +
                ", etatCablage=" + etatCablage +
                ", autreTravauxRealiser='" + autreTravauxRealiser + '\'' +
                '}';
    }

    public void setElectriciteSeparer(boolean electriciteSeparer) {
        this.electriciteSeparer = electriciteSeparer;
    }

    public void setMajAntivirus(boolean majAntivirus) {
        this.majAntivirus = majAntivirus;
    }

    public void setEmpEquipements(boolean empEquipements) {
        this.empEquipements = empEquipements;
    }

    public void setOnduleurOperationnel(boolean onduleurOperationnel) {
        this.onduleurOperationnel = onduleurOperationnel;
    }

    public void setDepoussierage(boolean depoussierage) {
        this.depoussierage = depoussierage;
    }

    public void setEtatCablage(boolean etatCablage) {
        this.etatCablage = etatCablage;
    }
}
