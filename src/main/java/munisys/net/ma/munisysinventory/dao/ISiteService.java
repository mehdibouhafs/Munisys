package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.Site;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface ISiteService {
    public boolean insererSite(String site, String ville,int cliendId,String telephone,String contact);
    public void deleteSite(int id);
    public void majSite(int id, String site, String ville, int clientId,String telephone,String contact);
    public Site getSite(int id);
    public ArrayList<Site> getSitesClient(int id);
    public ArrayList<Site> getAllSites();
    public Boolean getSiteBoolean(String site,String ville,int clientID);

}
