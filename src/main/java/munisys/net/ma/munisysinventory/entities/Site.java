package munisys.net.ma.munisysinventory.entities;

/**
 * Created by mehdibouhafs on 11/07/2017.
 */

public class Site {

    private int idSite;
    private int clientId;
    private String site;
    private String ville;

    public Site() {
    }

    public Site(int idSite, int clientId, String site, String ville) {
        this.idSite = idSite;
        this.clientId = clientId;
        this.site = site;
        this.ville = ville;
    }

    public Site(int clientId, String site, String ville) {
        this.clientId = clientId;
        this.site = site;
        this.ville = ville;
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



    @Override
    public String toString() {
        return  site + " => " + ville;
    }
}
