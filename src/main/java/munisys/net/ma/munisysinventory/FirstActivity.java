package munisys.net.ma.munisysinventory;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import munisys.net.ma.munisysinventory.adapters.SpinnerAdapter;
import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Session;
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.User;

public class FirstActivity extends HomeActivity {

    private Button confirmerIntervenant;
    private MultiSpinnerSearch searchMultiSpinnerUnlimited;
    private ArrayList<User> users;
    private TextView intervenantPrincipale;

    private ImageButton newIntervenant;
    private Session session;
    private ArrayList<Intervenant> intervenants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_first);

       View view = getLayoutInflater().inflate(R.layout.content_first, frameLayout);
        setTitle("Select intervenant");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDi


        activityId = R.layout.content_first;
        session = new Session(this);
        intervenants = new ArrayList<>();
        intervenants.add(db.getIntervenantByName(session.getNameUser()));

        intervenantPrincipale = (TextView) view.findViewById(R.id.intervenantPrincipale);
        intervenantPrincipale.setText(session.getNameUser());


        newIntervenant = (ImageButton) findViewById(R.id.newIntervenant);
        newIntervenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(FirstActivity.this);
                dialog.setContentView(R.layout.add_invetervenant);

                final EditText input_client = (EditText) dialog.findViewById(R.id.input_intervenant);

                Button btn_creer = (Button)dialog.findViewById(R.id.btn_create_intervenant);
                Button btn_annuler = (Button)dialog.findViewById(R.id.btn_annuler);

                btn_creer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.insererIntervenant(input_client.getText().toString());
                        searchMultiSpinnerUnlimited.setItems(db.getIntervenants(session.getNameUser()),-1);
                        searchMultiSpinnerUnlimited.getMyAdapter().notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });
                btn_annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



                dialog.show();
            }
        });


        List<Site> sites = db.getAllSites();
        List<Client> clients = db.getAllClients();
        for(Client c: clients){
            Log.e("Client ",c.toString());
        }
        for(Site site : sites){
            Log.e("Site ",site.toString());
        }


        users = db.getALLUser();

        confirmerIntervenant = (Button) view.findViewById(R.id.validerIntervenant);
        final List<Intervenant> list = db.getAllIntervenants();



        searchMultiSpinnerUnlimited = (MultiSpinnerSearch) findViewById(R.id.searchMultiSpinnerUnlimited);
        searchMultiSpinnerUnlimited.setItems(db.getIntervenants(session.getNameUser()), -1, new SpinnerListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        //Log.i("Select", i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                        Intervenant intervenant  = new Intervenant(((int) items.get(i).getId()),items.get(i).getName());
                        intervenants.add(intervenant);
                        //Toast.makeText(getApplicationContext(),"Intervenant Selectionner :"+items.get(i).getName(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


       confirmerIntervenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondeActivity.class);
                Bundle bundle = new Bundle();
                for(Intervenant e:intervenants) {
                    Log.e("First Activity ", e.toString());
                }
                bundle.putSerializable("intervenants",intervenants);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(this,"Back pressed",Toast.LENGTH_SHORT).show();
        return true;
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_home, menu);

        MenuItem search = menu.findItem(R.id.action_settings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
            Intent intent  = new Intent(this,FirstActivity.class);
            startActivity(intent);
            break;
           /* case R.id.action_save:
                save();
                return true;
            case R.id.action_delete:
                delete();
                return true;
            case R.id.action_settings:
                return true;*/
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent  = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}
