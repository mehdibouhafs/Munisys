package munisys.net.ma.munisysinventory.dao;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 08/07/2017.
 */

public interface IProduitService {
    public void insererProduit(String modele, String equipement,String marque,String matricule,String nInventaire);
    public void deleteProduit(String modele);
    public void majProduit(String modele1,String modele,String equipement,String marque,String matricule,String nInventaire);
    public Produit getProduit(String modele);
    public ArrayList<Produit> getAllProduits();
    public Boolean getProduitBoolean(String modele,String equipement,String marque,String matricule);
    public void dropTableProduit();

}
