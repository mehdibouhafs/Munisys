package munisys.net.ma.munisysinventory;

import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import munisys.net.ma.munisysinventory.adapters.AdaptorForInventory;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

public class InventoriesActivity extends HomeActivity {

    private RecyclerView recyclerView;
    private ArrayList<Inventaire> inventaires;
    private AdaptorForInventory mAdaptorForInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.content_inventories, frameLayout);
        activityId = R.layout.content_inventories;
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view2);
        inventaires = new ArrayList<>();
        mAdaptorForInventory = new AdaptorForInventory(inventaires);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdaptorForInventory);
        //Toast.makeText(this,"Invotriries Activity",Toast.LENGTH_SHORT).show();

        prepareMovieData();

    }

    private void prepareMovieData() {

        Inventaire inventaire = new Inventaire();
        Inventaire inventaire2 = new Inventaire();
        Inventaire inventaire3 = new Inventaire();
        ArrayList<ProduitInventaire> produits = new ArrayList<>();
        Client c = new Client();

        c.setClient("GCAM");



        Client c1 = new Client();

        c1.setClient("Munisys");




        ProduitInventaire p = new ProduitInventaire();

        p.setCollaborateur("Samir");
        p.setSn("6CM311130DQ");
        p.setnInventaire("595959");

        ProduitInventaire p1 = new ProduitInventaire();

        p.setCollaborateur("Samir");
        p.setSn("6CM311130DQ");
        p.setnInventaire("595959");

        ProduitInventaire p2 = new ProduitInventaire();

        p.setCollaborateur("Samir");
        p.setSn("6CM311130DQ");
        p.setnInventaire("595959");



        produits.add(p1);produits.add(p);produits.add(p2);

        inventaire.setProduits(produits);
        inventaire2.setProduits(produits);
        inventaire3.setProduits(produits);
        inventaires.add(inventaire);
        inventaires.add(inventaire2);
        inventaires.add(inventaire3);




        mAdaptorForInventory.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
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
                mAdaptorForInventory.getFilter().filter(newText);
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
}
