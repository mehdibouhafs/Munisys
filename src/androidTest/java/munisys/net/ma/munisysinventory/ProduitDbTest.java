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
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Produit;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProduitDbTest {

    private Db_Invenantaire db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_Invenantaire(context,16);
    }



    @Test
    public void get_isCorrect() throws Exception {
        Produit produit =  this.db.getProduit("740617198256");

        Log.e("produit ",produit.toString());
        assertEquals(produit.getModele(), "740617198256");
    }


    @Test
    public void insert_isCorrect() throws Exception {
        /*this.db.insererProduit("C9F26AA","Ecran Pc","HP","4 240");
        this.db.insererProduit("AY032AV","PC bureau","HP","4 240");
        this.db.insererProduit("CE399AE","Imprimante","HP","4 240");
        this.db.insererProduit("CR399AE","Imprimante","HP","4 240");
        this.db.insererProduit("CY399AE","Imprimante","HP","4 240");*/
        //this.db.insererProduit("740617198256","Ecran Pc","HP","4240","195599439","1");

        //this.db.insererProduit("6111035000027","Ecran Pc","HP","4240","1955994");
        this.db.insererProduit("740617198256","Cle Usb","Kingston","4241","919199");

        ArrayList<Produit> produits =  this.db.getAllProduits();
        for (Produit e:produits
                ) {
            Log.e("produit ",e.getModele() +" id " + e.getEquipement());
        }
        assertEquals(produits.size(), 1);
    }
    // Intervenant

   /* @Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteProduit("AY032AV");
        assertEquals(this.db.getAllProduits().size(),2);
    }*/



}
