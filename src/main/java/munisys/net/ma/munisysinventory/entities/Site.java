package munisys.net.ma.munisysinventory.entities;

/**
 * Created by mehdibouhafs on 11/07/2017.
 */

public class Site {

    private int id;
    private String site;
    private String ville;
    private int clientId;


    public Site() {
    }

    public Site(int id, String site, String ville, int clientId) {
        this.id = id;
        this.site = site;
        this.ville = ville;
        this.clientId = clientId;
    }

    public Site(String site, String ville, int clientId) {
        this.site = site;
        this.ville = ville;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
