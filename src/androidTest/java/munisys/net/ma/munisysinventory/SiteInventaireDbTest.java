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
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SiteInventaireDbTest {

    private Db_Invenantaire db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_Invenantaire(context,16);
    }





    @Test
    public void get_isCorrect2() throws Exception {
        ArrayList<SiteInventaire> siteInventaires =  this.db.getSiteInventaire(1);
        Log.e("onesiteinventaire",siteInventaires.toString());
        assertEquals(siteInventaires.get(1).getIdSiteInventaire(), 1);

    }



    @Test
    public void insert_isCorrect() throws Exception {

        //int idSite,int clientId, String site, String ville,int idIventaire, String direction, String burreauEtage, String serviceCentre, String telephone, String contact
        this.db.insererSiteInventaire(new SiteInventaire(1,1,"ABOU FARES","FES",1,"Direction","burreauEtage","serviceCentre","0606959","m@b.c"));

        ArrayList<SiteInventaire> siteInventaires =  this.db.getAllSiteInventaire();
        for (SiteInventaire e:siteInventaires
                ) {
            Log.e( "siteinveantaire",e.toString());
        }

        assertEquals(siteInventaires.size(), 1);
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
