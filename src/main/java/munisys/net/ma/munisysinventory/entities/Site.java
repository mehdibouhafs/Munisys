package munisys.net.ma.munisysinventory.entities;

/**
 * Created by mehdibouhafs on 11/07/2017.
 */

public class Site {

    private int idSite;
    private int clientId;
    private String site;
    private String ville;
    private String telephone;
    private String contact;

    public Site() {
    }

    public Site(int idSite, int clientId, String site, String ville, String telephone, String contact) {
        this.idSite = idSite;
        this.clientId = clientId;
        this.site = site;
        this.ville = ville;
        this.telephone = telephone;
        this.contact = contact;
    }

    public Site(int clientId, String site, String ville, String telephone, String contact) {
        this.clientId = clientId;
        this.site = site;
        this.ville = ville;
        this.telephone = telephone;
        this.contact = contact;
    }

    public int getIdSite() {
        return idSite;
    }

    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    @Override
    public String toString() {
        return  site + " => " + ville;
    }
}
