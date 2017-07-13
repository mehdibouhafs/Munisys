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

import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventaireDbTest {

    private Db_gest db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_gest(context,12);
    }

    @Test
    public void insert_isCorrect() throws Exception {
        //this.db.insererInventaire(1,2,new Date());
        //this.db.insererInventaire(1,3,new Date());
        //this.db.insererInventaire(3,3,new Date());

        //this.db.insererProduitInventaire("CXFPEM","7","6CM41130DQ","Poste 1","192.168.1.3",true,true,true,"Collaborateur1");
       //this.db.insererProduitInventaire("C9F26AA","7","6CM41130DQ","Poste 2","192.168.1.4",true,true,true,"Collaborateur2");

        ArrayList<Inventaire> inventaires =  this.db.getAllInventaires();
        for (Inventaire e:inventaires
             ) {
            Log.e("inventaires ",e.getClient().getClient() +" id " + e.getId());
        }
        assertEquals(inventaires.size(), 2);
    }

    @Test
    public void get_isCorrect() throws Exception {
        Inventaire inventaire =  this.db.getInventaire(1);

        assertEquals(inventaire.getId(), 1);
    }

    @Test
    public void maj_isCorrect() throws Exception {
        this.db.majInventaire(1,2,3,new Date());
        assertEquals("2", "2");
    }



    // Intervenant

    @Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteInventaire(3);
        assertEquals(this.db.getAllInventaires().size(),2);
    }



}
