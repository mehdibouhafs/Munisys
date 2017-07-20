package munisys.net.ma.munisysinventory;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProduitInventaireDbTest {

    private Db_Invenantaire db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_Invenantaire(context,16);
    }



    @Test
    public void get_isCorrect() throws Exception {
        //this.db.deleteProduitInventaire(2);
        ProduitInventaire produitInventaire =  this.db.getProduitInventaire(2);

        Log.e("produitInventaire ",produitInventaire.toString());
        assertEquals(produitInventaire.getModele(), "6111035000027");
    }


    @Test
    public void insert_isCorrect() throws Exception {
        /*this.db.insererProduit("C9F26AA","Ecran Pc","HP","4 240");
        this.db.insererProduit("AY032AV","PC bureau","HP","4 240");
        this.db.insererProduit("CE399AE","Imprimante","HP","4 240");
        this.db.insererProduit("CR399AE","Imprimante","HP","4 240");
        this.db.insererProduit("CY399AE","Imprimante","HP","4 240");*/
        //this.db.insererProduit("740617198256","Ecran Pc","HP","4240","195599439","1");
        //this.db.deleteProduitInventaire(2);
        //this.db.insererProduitInventaire("6111035000027",2,"Poste11","sn02","192.169.1.1",true,"AHMED CHARFAOUI");

        ArrayList<ProduitInventaire> produitInventaires =  this.db.getAllProduitsInventaire(2);
        for (ProduitInventaire produitInventaire:produitInventaires) {
            Log.e("produit Inventaire 3",produitInventaire.getModele() +" id " + produitInventaire.getEquipement());
        }
        assertEquals(produitInventaires.size(), 1);
    }
    // Intervenant

   /* @Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteProduit("AY032AV");
        assertEquals(this.db.getAllProduits().size(),2);
    }*/



}
