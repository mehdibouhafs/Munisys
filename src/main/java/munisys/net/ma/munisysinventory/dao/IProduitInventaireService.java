package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IProduitInventaireService {
    public void insererProduitInventaire(String idProduit,int inventaireId,String sn,String nomPoste,String addIp,Boolean dhcp,String collaborateur);


    public void deleteProduitInventaire(int id);
    public void deleteProduitInventairebyIdInventaire(int idIventaire);
    public void majProduitInventaire(int id,String idProduit,int inventaireId,String sn,String nomPoste,String addIp,Boolean dhcp,String collaborateur);
    public ProduitInventaire getProduitInventaire(int id);
    public ArrayList<ProduitInventaire> getAllProduitsInventaire(int idInventaire);
    public ArrayList<ProduitInventaire> getAllProduitsInventaire();


    public void dropTableProduitInventaire();


}
