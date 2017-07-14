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

import munisys.net.ma.munisysinventory.dao.Db_Client;
import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientDbTest {

    private Db_Invenantaire db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_Invenantaire(context,8);
    }



    @Test
    public void get_isCorrect() throws Exception {
        int id =  this.db.getClient("GCAM");
        Log.e("Client get  ",id +"");
        assertEquals(id, 1);
    }



    @Test
    public void insert_isCorrect() throws Exception {

        //this.db.dropTableClient();
       /*this.db.insererClient("GCAM");
        this.db.insererClient("Maroc Bureau");
        this.db.insererClient("BMCI");
        this.db.insererClient("Munisys");
        this.db.insererClient("CIH");*/

        /*ArrayList<Client> clients =  this.db.getAllClients();

        for (Client e:clients
                ) {
            Log.e("Client ",e.getClient() +" id " + e.getId());
        }
        this.db.insererClient("GCAM");*/
        assertEquals(this.db.insererClient("GCAM"),false);
    }

    // Intervenant

   /* @Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteClient(2);
        assertEquals(this.db.getAllClients().size(),2);
    }*/



}
