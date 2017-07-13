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

import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Collaborateur;
import munisys.net.ma.munisysinventory.entities.Site;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SiteDbTest {

    private Db_gest db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_gest(context,15);
    }

    @Test
    public void insert_isCorrect() throws Exception {
        /*this.db.insererSite("ABOU FARES","ERRACHIDIA",1);
        this.db.insererSite("SIDI MOUMEN","CASABLANCA",2);
        this.db.insererSite("BENSOUDA","FES",2);*/


        ArrayList<Site> sites =  this.db.getAllSites();
        for (Site e:sites
                ) {
            Log.e( "id = " + e.getId() +"Site  ",e.getSite() +" ville = "+ e.getVille() +  " idClient " + e.getClientId());
        }

        assertEquals(sites.size(), 3);
    }

    @Test
    public void get_isCorrect() throws Exception {
        Site site =  this.db.getSite(2);

        assertEquals(site.getId(), 2);
    }

    /*@Test
    public void maj_isCorrect() throws Exception {
        this.db.majSite(2,"SIDI MOUMEN","CASABLANCA",3);
        assertEquals(this.db.getSite(2).getVille(), "CASABLANCA");
    }*/



    // Intervenant

    /*@Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteCollaborateur(1);
        assertEquals(this.db.getAllCollaborateurs().size(),3);
    }*/



}
