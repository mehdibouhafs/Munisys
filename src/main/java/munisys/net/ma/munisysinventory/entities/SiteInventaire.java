package munisys.net.ma.munisysinventory.entities;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class SiteInventaire extends Site {

    private int idSiteInventaire;
    private String direction;
    private String burreauEtage;
    private String serviceCentre;



    public SiteInventaire() {
    }


    public SiteInventaire(int clientId, String site, String ville, String direction, String burreauEtage, String serviceCentre, String telephone, String contact) {
        super(clientId, site, ville,telephone,contact);
        this.direction = direction;
        this.burreauEtage = burreauEtage;
        this.serviceCentre = serviceCentre;

    }

    public SiteInventaire(int idSiteInventaire,int idSite,int clientId, String site, String ville,
                          String direction, String burreauEtage, String serviceCentre, String telephone, String contact) {
        super(clientId, site, ville,telephone,contact);
        this.idSiteInventaire = idSiteInventaire;
        this.direction = direction;
        this.burreauEtage = burreauEtage;
        this.serviceCentre = serviceCentre;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBurreauEtage() {
        return burreauEtage;
    }

    public void setBurreauEtage(String burreauEtage) {
        this.burreauEtage = burreauEtage;
    }

    public String getServiceCentre() {
        return serviceCentre;
    }

    public void setServiceCentre(String serviceCentre) {
        this.serviceCentre = serviceCentre;
    }

    public int getIdSiteInventaire() {
        return idSiteInventaire;
    }

    public void setIdSiteInventaire(int idSiteInventaire) {
        this.idSiteInventaire = idSiteInventaire;
    }
}
