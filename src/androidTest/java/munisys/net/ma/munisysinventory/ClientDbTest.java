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
import munisys.net.ma.munisysinventory.entities.Intervenant;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientDbTest {

    private Db_gest db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_gest(context,15);
    }

    @Test
    public void insert_isCorrect() throws Exception {


        /*this.db.insererClient("GCAM","ABOU FARES","INFORMATIQUE","ERRACHIDIA","2.8 / 2ETAGE","DOSI","0537217270","MEKOUAR MOHAMMED");
        this.db.insererClient("CGI","ABOU FARES","INFORMATIQUE","ERRACHIDIA","2.8 / 2ETAGE","DOSI","0537217270","MEKOUAR MOHAMMED");
        this.db.insererClient("APPLE","ABOU FARES","INFORMATIQUE","ERRACHIDIA","2.8 / 2ETAGE","DOSI","0537217270","MEKOUAR MOHAMMED");
        */
        ArrayList<Client> clients =  this.db.getAllClients();
        for (Client e:clients
             ) {
            Log.e("Client ",e.getClient() +" id " + e.getId());
        }
        assertEquals(clients.size(), 3);
    }

    @Test
    public void get_isCorrect() throws Exception {
        Client client =  this.db.getClient(1);
        Log.e("Client get  ",client.getClient() +" id " + client.getId());
        assertEquals(client.getId(), 1);
    }

    @Test
    public void maj_isCorrect() throws Exception {
        this.db.majClient(1, "GCAM", "AnfaPlace", "Haut Direction", "Casablanca Ain sbaa",
                "Burrea10", "Service management", "0664606953", "apple@contact.fr");
        assertEquals(this.db.getClient(1).getClient(), "Apple");
    }



    // Intervenant

   /* @Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteClient(2);
        assertEquals(this.db.getAllClients().size(),2);
    }*/



}
