package munisys.net.ma.munisysinventory;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.adapters.AdaptorForProduit;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

public class FourthActivity extends HomeActivity {

    private RecyclerView recyclerView;
    private ArrayList<ProduitInventaire> produits;

    private TextView intervenant,client,date,burreauEtage,serviceCentre,site,direction,ville;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.content_fourth, frameLayout);
        activityId = R.layout.content_fourth;
        setTitle("Validation Inventaire");

        produits = new ArrayList<>();


        Toast.makeText(this,"Fourth",Toast.LENGTH_SHORT).show();
        intervenant = (TextView) view.findViewById(R.id.intervenant);
        client = (TextView) view.findViewById(R.id.client);
        date = (TextView) view.findViewById(R.id.date);
        burreauEtage = (TextView) view.findViewById(R.id.burreauEtage);
        serviceCentre = (TextView) view.findViewById(R.id.serviceCentre);
        site = (TextView) view.findViewById(R.id.site);
        direction = (TextView) view.findViewById(R.id.direction);
        ville = (TextView) view.findViewById(R.id.ville);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        Intervenant intervenant1 = (Intervenant) b.get("intervenant");
        Client client1 = (Client) b.get("client");
        intervenant.setText(intervenant1.getNomIntervenant());
        client.setText(client1.getClient());
        date.setText("13/06/2017");
       // burreauEtage.setText(client1.get);
       // serviceCentre.setText(client1.getServiceCentre());
        //site.setText(client1.getSite());
        //direction.setText(client1.getDirection());
        //ville.setText(client1.getVille());

        prepareMovieData();

       //Intent intent = new Intent(this,HomeActivity.class);
        //startActivity(intent);*/

    }

    private void prepareMovieData() {
        ProduitInventaire p = new ProduitInventaire();
        //p.setModele("C02949");
        p.setCollaborateur("Samir");
        //p.setEquipement("Ecran Pc");
       // p.setMarque("HP");
       // p.setModele("029595");
        //p.setSN("6CM311130DQ");
       // p.setMatricule("4 240");
        p.setnInventaire("595959");
        p.setElectriciteSepare(false);
        p.setOnduleurOperationnel(false);
        p.setAddIp("192.168.1.101");
        p.setDhcp(true);
        p.setNomPoste("AM5930F");




        produits.add(p);
        produits.add(p);
        produits.add(p);
        produits.add(p);

        //mAdaptorForProduit.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_search, menu);

        MenuItem search = menu.findItem(R.id.search);


        return true;
    }
    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("textSearch",newText);
                //mAdaptorForProduit.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
        super.onBackPressed();
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }
}
