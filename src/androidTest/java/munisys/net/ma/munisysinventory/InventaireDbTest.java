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
import java.util.Date;

import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventaireDbTest {

    private Db_Invenantaire db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_Invenantaire(context,16);
    }



    @Test
    public void get_isCorrect() throws Exception {
        //this.db.deleteInventaire(2);
        Inventaire e =  this.db.getInventaire(6);
        //Log.e("inventaires ",e.toString());
        assertEquals(e.getId(), 6);
    }

    @Test
    public void insert_isCorrect() throws Exception {
        //this.db.insererInventaire(2,10,new Date(),1,1,1,1,1,1,"c bon");
        //this.db.insererInventaire(1,3,new Date());
        //this.db.insererInventaire(3,3,new Date());
       // this.db.insererInventaire(1,2,3,new Date());
       // this.db.insererInventaire(2,3,4,new Date());
        //this.db.insererProduitInventaire("CXFPEM","7","6CM41130DQ","Poste 1","192.168.1.3",true,true,true,"Collaborateur1");
        //this.db.insererProduitInventaire("C9F26AA","7","6CM41130DQ","Poste 2","192.168.1.4",true,true,true,"Collaborateur2");
        //this.db.dropTableInventaire();
        this.db.deleteInventaire(3);this.db.deleteInventaire(4);this.db.deleteInventaire(5);
       ArrayList<Inventaire> inventaires =  this.db.getAllInventaires();
        for (Inventaire e:inventaires
                ) {
            Log.e("Inventairesss",e.toString());
        }
        assertEquals(inventaires.size(), 2);
    }




    // Intervenant




}
