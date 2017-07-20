package munisys.net.ma.munisysinventory.entities;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class SiteInventaire extends Site {

    private int idSiteInventaire;
    private String direction;
    private int idIventaire;
    private String burreauEtage;
    private String serviceCentre;
    private String telephone;
    private String contact;


    public SiteInventaire() {
    }


    public SiteInventaire(int idSite,int clientId, String site, String ville,int idIventaire, String direction, String burreauEtage, String serviceCentre, String telephone, String contact) {
        super(idSite,clientId, site, ville);
        this.idIventaire = idIventaire;
        this.direction = direction;
        this.burreauEtage = burreauEtage;
        this.serviceCentre = serviceCentre;
        this.telephone = telephone;
        this.contact = contact;

    }

    public SiteInventaire(int idSiteInventaire,int idSite,int clientId, String site, String ville,int idIventaire,
                          String direction, String burreauEtage, String serviceCentre, String telephone, String contact) {
        super(idSite,clientId, site, ville);
        this.idIventaire = idIventaire;
        this.idSiteInventaire = idSiteInventaire;
        this.direction = direction;
        this.burreauEtage = burreauEtage;
        this.serviceCentre = serviceCentre;
        this.telephone = telephone;
        this.contact = contact;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getIdIventaire() {
        return idIventaire;
    }

    public void setIdIventaire(int idIventaire) {
        this.idIventaire = idIventaire;
    }

    @Override
    public String toString() {
        return "SiteInventaire{" +
                "idSiteInventaire=" + idSiteInventaire +
                ", direction='" + direction + '\'' +
                ", idIventaire=" + idIventaire +
                ", burreauEtage='" + burreauEtage + '\'' +
                ", serviceCentre='" + serviceCentre + '\'' +
                ", telephone='" + telephone + '\'' +
                ", contact='" + contact + '\'' +
                "} " + super.toString();
    }
}
