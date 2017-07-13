package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;
import java.util.Date;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IIventaireService {
    public void insererInventaire(int idClient, int idIntervenant,int siteIventaireId, Date dateInventaire);


    public void deleteInventaire(int id);
    //public void majInventaire(int id,int idClient, int idIntervenant,int siteIventaireId, Date dateInventaire);
    public Inventaire getInventaire(int id);
    public ArrayList<Inventaire> getAllInventaires();
}
