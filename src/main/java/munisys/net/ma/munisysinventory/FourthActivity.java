package munisys.net.ma.munisysinventory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import munisys.net.ma.munisysinventory.adapters.AdaptorForProduit;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

public class FourthActivity extends HomeActivity {

    private RecyclerView recyclerView;
    private ArrayList<ProduitInventaire> produits;

    private TextView intervenants,client,date,burreauEtage,serviceCentre,site,direction,ville,autreTravailRealise;
    private CheckBox electriciteSeparer,onduleurOperationnel,depoussierage,majAntivirus,etatCablage,empEquipement;
    private RecyclerView my_recycler_view;
    private Bundle bundle;
    private AdaptorForProduit mAdaptorForProduit;
    private Client client1;
    private SiteInventaire siteInventaire1;
    private ArrayList<ProduitInventaire> produitsInventaires;
    private ArrayList<Intervenant> intervenants1;
    private Button valider;
    private ImageView logo;
    private Inventaire inventaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.content_fourth, frameLayout);
        activityId = R.layout.content_fourth;
        setTitle("Validation Inventaire");
        Intent intent = getIntent();
        bundle = intent.getExtras();

        produits = new ArrayList<>();


        Toast.makeText(this, "Fourth", Toast.LENGTH_SHORT).show();
        client = (TextView) view.findViewById(R.id.client);
        date = (TextView) view.findViewById(R.id.date);
        burreauEtage = (TextView) view.findViewById(R.id.burreauEtage);
        serviceCentre = (TextView) view.findViewById(R.id.serviceCentre);
        site = (TextView) view.findViewById(R.id.site);
        direction = (TextView) view.findViewById(R.id.direction);
        ville = (TextView) view.findViewById(R.id.ville);
        autreTravailRealise = (TextView) view.findViewById(R.id.autreTravailRealise);
        autreTravailRealise.setEnabled(false);
        intervenants = (TextView) view.findViewById(R.id.Intervenants);
        electriciteSeparer = (CheckBox) view.findViewById(R.id.electriciteSeparer);
        electriciteSeparer.setEnabled(false);
        onduleurOperationnel = (CheckBox) view.findViewById(R.id.onduleurOperationnel);
        onduleurOperationnel.setEnabled(false);
        depoussierage = (CheckBox) view.findViewById(R.id.depoussierage);
        depoussierage.setEnabled(false);
        majAntivirus = (CheckBox) view.findViewById(R.id.majAntivirus);
        majAntivirus.setEnabled(false);
        etatCablage = (CheckBox) view.findViewById(R.id.etatCablage);
        etatCablage.setEnabled(false);
        empEquipement = (CheckBox) view.findViewById(R.id.empEquipement);
        empEquipement.setEnabled(false);
        my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        my_recycler_view.setLayoutManager(mLayoutManager);
        my_recycler_view.setItemAnimator(new DefaultItemAnimator());
        my_recycler_view.setNestedScrollingEnabled(false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        logo = (ImageView) view.findViewById(R.id.logo);






        produitsInventaires = (ArrayList<ProduitInventaire>) bundle.get("produitInventaires");
        mAdaptorForProduit = new AdaptorForProduit(produitsInventaires, this,1);
        recyclerView.setAdapter(mAdaptorForProduit);

        client1 = (Client) bundle.get("client");
        siteInventaire1 = (SiteInventaire) bundle.get("siteInventaire");
        inventaire = (Inventaire) bundle.get("inventaire");
        electriciteSeparer.setChecked(inventaire.isElectriciteSeparer());
        onduleurOperationnel.setChecked(inventaire.isOnduleurOperationnel());
        depoussierage.setChecked(inventaire.isDepoussierage());
        empEquipement.setChecked(inventaire.isEmpEquipements());
        etatCablage.setChecked(inventaire.isEtatCablage());
        majAntivirus.setChecked(inventaire.isMajAntivirus());
        autreTravailRealise.setText(inventaire.getAutreTravauxRealiser());

        try {
            fileToImageView(client1.getLogo());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.i("Fourth Client",client1.toString());
        Log.i("Fourth SiteInventaire",siteInventaire1.toString());
        for(ProduitInventaire produitInventaire : (ArrayList<ProduitInventaire>) bundle.get("produitInventaires")){
            Log.i("Fourth produit",produitInventaire.toString());
        }

        client.setText(client1.getClient());
        date.setText(db.getDateTime(new Date()));
        ville.setText(siteInventaire1.getVille());
        burreauEtage.setText(siteInventaire1.getBurreauEtage());
        serviceCentre.setText(siteInventaire1.getServiceCentre());
        direction.setText(siteInventaire1.getDirection());
        site.setText(siteInventaire1.getSite());
        intervenants1 = (ArrayList<Intervenant>) bundle.get("intervenants");

        StringBuffer inters = new StringBuffer();
        int i = 1;
        for (Intervenant intervenant : intervenants1) {
            inters.append(intervenant.getNomIntervenant());
            if (i < intervenants1.size()) {
                inters.append("-");
            }
            i++;
        }

        String intersv = inters.toString();
        intervenants.setText(intersv);

        final Inventaire inventaire = (Inventaire) bundle.get("inventaire");




        final int electrisiteSeparer =  inventaire.isElectriciteSeparer()?1:0;
        final int onduleurOperationnel = inventaire.isOnduleurOperationnel()?1:0;
        final int majAntivirus = inventaire.isMajAntivirus()?1:0;
        final int depoussierage = inventaire.isDepoussierage()?1:0;
        final int empCorrect = inventaire.isEmpEquipements()?1:0;
        final int etatCablage = inventaire.isEtatCablage()?1:0;

        valider = (Button) view.findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Long idInventaire = db.insererInventaire(client1.getId(),new Date(),electrisiteSeparer,onduleurOperationnel,majAntivirus,depoussierage,empCorrect,etatCablage,inventaire.getAutreTravauxRealiser());
                siteInventaire1.setIdIventaire(idInventaire.intValue());
                db.insererSiteInventaire(siteInventaire1);



                for (Intervenant intervenant : intervenants1) {
                    Log.e("Interventants Fourth",intervenant.toString());
                    db.insererIntervenantInventaire(idInventaire.intValue(),intervenant.getId());
                }

                for(ProduitInventaire produitInventaire :produitsInventaires) {
                    db.insererProduitInventaire(produitInventaire.getModele(),idInventaire.intValue(),produitInventaire.getNomPoste(),produitInventaire.getSn(),
                            produitInventaire.getAddIp(),produitInventaire.isDhcp(),produitInventaire.getCollaborateur());
                }

                Toast.makeText(getApplicationContext(),"Inventaire créer avec succès",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });


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
        //super.onBackPressed();
        Intent intent = new Intent(this,MiddleActivitry.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void fileToImageView(String filePath) throws IOException {

        InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(Uri.parse(filePath));
        Bitmap bmp = BitmapFactory.decodeStream(inputStream);
        if( inputStream != null ) inputStream.close();

        logo.setImageBitmap(bmp);
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

    }

}
