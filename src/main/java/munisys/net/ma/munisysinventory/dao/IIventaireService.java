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
    /*Create Table Inventaire(id Integer Primary Key AUTOINCREMENT,clientId Integer,siteIventaireId Integer," +
                    "intervenantIventaireId Integer,dateInventaire Datetime DEFAULT CURRENT_DATE,electriciteSepare Integer,onduleurOperationnel Integer,
                    majAntivirus Integer,depoussierage Integer,empEquipement Integer,etatCablage Integer*/

    public Long insererInventaire(int idClient, Date dateInventaire,int electriciteSepare,int onduleurOperationnel,
                                  int majAntivirus,int depoussierage,int empEquipement,int etatCablage,String autreTravauxRealiser);


    public void deleteInventaire(int id);
    //public void majInventaire(int id,int idClient, int idIntervenant,int siteIventaireId, Date dateInventaire);
    public Inventaire getInventaire(int id);
    public ArrayList<Inventaire> getAllInventaires();
    public void dropTableInventaire();
}
