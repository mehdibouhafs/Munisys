package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface ISiteInventaireService {
    public Long insererSiteInventaire(SiteInventaire siteInventaire);
    public void deleteSiteInventaire(int idSiteInventaire);
    public void deleteSiteInventairebyIdInventaire(int idIventaire);
    //public void majSiteInventaire(int idSiteInventaire,SiteInventaire siteInventaire);
    public ArrayList<SiteInventaire> getSiteInventaire(int idInventaire);
    public ArrayList<SiteInventaire> getAllSiteInventaire();
    public void dropTableSiteInventaire();
}
