package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface ISiteInventaireService {
    public void insererSiteInventaire(SiteInventaire siteInventaire);
    public void deleteSiteInventaire(int idSiteInventaire);
    //public void majSiteInventaire(int idSiteInventaire,SiteInventaire siteInventaire);
    public SiteInventaire getSiteInventaire(int id);
    public ArrayList<SiteInventaire> getAllSiteInventaire();
}
