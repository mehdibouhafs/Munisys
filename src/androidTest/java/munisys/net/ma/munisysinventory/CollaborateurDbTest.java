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
import munisys.net.ma.munisysinventory.entities.Collaborateur;
import munisys.net.ma.munisysinventory.entities.Intervenant;

import static org.junit.Assert.assertEquals;

/**
 * Created by mehdibouhafs on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CollaborateurDbTest {

    private Db_gest db;

    private Context context;



    @Before
    public void instantiate(){
        this.context = InstrumentationRegistry.getTargetContext();
        this.db = new Db_gest(context,9);
    }

    @Test
    public void insert_isCorrect() throws Exception {
        this.db.insererCollaborateur("testIntervenant");
        this.db.insererCollaborateur("testIntervenant");
        this.db.insererCollaborateur("testIntervenant");


        ArrayList<Collaborateur> collaborateurs =  this.db.getAllCollaborateurs();
        for (Collaborateur e:collaborateurs
             ) {
            Log.e("intervenant ",e.getNomCollaborateur() +" id " + e.getId());
        }
        assertEquals(collaborateurs.size(), 3);
    }

    @Test
    public void get_isCorrect() throws Exception {
        Collaborateur collaborateur =  this.db.getCollaborateur(2);

        assertEquals(collaborateur.getId(), 2);
    }

    @Test
    public void maj_isCorrect() throws Exception {
        this.db.majCollaborateur(2,"Abdeslam");
        assertEquals(this.db.getCollaborateur(2).getNomCollaborateur(), "Abdeslam");
    }



    // Intervenant

    @Test
    public void delete_isCorrect() throws Exception {
        this.db.deleteCollaborateur(1);
        assertEquals(this.db.getAllCollaborateurs().size(),3);
    }



}
