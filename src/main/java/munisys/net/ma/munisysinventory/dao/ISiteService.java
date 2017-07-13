package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.Site;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface ISiteService {
    public void insererSite(String site, String ville,int cliendId);
    public void deleteSite(int id);
    public void majSite(int id, String site, String ville, int clientId);
    public Site getSite(int id);
    public ArrayList<Site> getSitesClient(int id);
    public ArrayList<Site> getAllSites();

}
