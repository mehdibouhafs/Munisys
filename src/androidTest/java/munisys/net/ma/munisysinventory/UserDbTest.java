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
import munisys.net.ma.munisysinventory.entities.User;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDbTest {

    private Db_Invenantaire db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_Invenantaire(context,16);
    }



    @Test
    public void get_isCorrect() throws Exception {
        User user =  this.db.getUser("a@b.c","123456");
        Log.e("User get  ",user.getName() +" id " + user.getEmail());
        assertEquals(user.getId(), 3);
    }

    @Test
    public void insert_isCorrect() throws Exception {

        //this.db.dropTableUsers();
       /* this.db.insererUser("MR Driss Farhat","a@b.c","123456");
        this.db.insererUser("MR Bouhafs Mehdi","a@d.c","123456");*/
        this.db.insererUser("MR Kitoni mohammed 3","a@z3.c","123456");
        this.db.insererIntervenant("MR Kitoni mohammed 3");

        //this.db.insererUser("mehdibouhafs2","a@d.c","123456");

        ArrayList<User> users =  this.db.getALLUser();
        for (User e:users
                ) {
            Log.e( "USER ",e.getName() +" mail " + e.getEmail() +" id "+e.getId());
        }
        assertEquals(users.size(), 2); //same email
    }


    // Intervenant

   /* @Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteClient(2);
        assertEquals(this.db.getAllClients().size(),2);
    }*/



}
