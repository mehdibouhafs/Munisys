package munisys.net.ma.munisysinventory;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.adapters.AdaptorForProduit;
import munisys.net.ma.munisysinventory.adapters.SpinnerAdapter;
import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

public class ThirdActivity extends HomeActivity {

    private EditText collaborateur,nomPoste,addIp;
    private CheckBox dhcp,electriciteSeparer,onduleurOperationnel;
    private EditText modele;
    private FloatingActionButton addOther;
    private AdaptorForProduit mAdaptorForProduit;
    private RecyclerView recyclerView;
    private Db_Invenantaire db;
    private TextView equipement,marque,matricule,nbProduits,nInventaire,sn;
    private ImageButton newModele;
    private MenuItem search;

    private LinearLayout layout_form,layoutEquipement,layoutMaMa,layoutSn;


    public boolean checkHide = false;

    private ArrayList<ProduitInventaire> produitInventaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.content_third, frameLayout);
        activityId = R.layout.content_third;
        produitInventaires = new ArrayList<>();
        setTitle("Informatiques");

        layout_form = (LinearLayout) findViewById(R.id.layout_form);
        layoutEquipement = (LinearLayout) findViewById(R.id.layoutEquipement);
        layoutMaMa = (LinearLayout) findViewById(R.id.layoutMaMa);
        layoutSn = (LinearLayout) findViewById(R.id.layoutSn);

        layoutEquipement.setVisibility(LinearLayout.GONE);
        layoutMaMa.setVisibility(LinearLayout.GONE);
        layoutSn.setVisibility(LinearLayout.GONE);

        db = new Db_Invenantaire(this,1);

        mAdaptorForProduit = new AdaptorForProduit(produitInventaires);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdaptorForProduit);



        //prepareMovieData();

        ArrayList<Produit> produits = db.getAllProduits();

        modele = (EditText) view.findViewById(R.id.modele);
       // modele.setAdapter(new SpinnerAdapter(this,R.layout.model,produits));
        //modele.setFocusable(true);
        //modele.setFocusableInTouchMode(true);
        //modele.requestFocus();
        nbProduits = (TextView) view.findViewById(R.id.nbProduits);

        equipement = (TextView) view.findViewById(R.id.equipement);
        equipement.clearFocus();
        marque = (TextView) view.findViewById(R.id.marque);
        matricule = (TextView) view.findViewById(R.id.matricule);
        nInventaire = (TextView) view.findViewById(R.id.nInventaire);
        sn = (TextView) view.findViewById(R.id.sn);
        collaborateur = (EditText) view.findViewById(R.id.collaborateur);
        nomPoste = (EditText) view.findViewById(R.id.nomPoste);
        addIp = (EditText) view.findViewById(R.id.addip);

        dhcp = (CheckBox) view.findViewById(R.id.dhcp);
        electriciteSeparer = (CheckBox) view.findViewById(R.id.electriciteSeparer);
        onduleurOperationnel = (CheckBox) view.findViewById(R.id.onduleurOperationnel);
        addOther = (FloatingActionButton) view.findViewById(R.id.ajouterMateriel);
        //confirmer = (Button) view.findViewById(R.id.confirmer);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        //final Intervenant intervenant = (Intervenant) b.get("intervenant");
        //final Client client = (Client) b.get("client");

        newModele = (ImageButton) view.findViewById(R.id.addModele);

        newModele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ThirdActivity.this);
                dialog.setContentView(R.layout.add_modele);

                final EditText input_modele = (EditText) dialog.findViewById(R.id.input_modele);
                final EditText input_equipement = (EditText) dialog.findViewById(R.id.input_equipement);
                final EditText input_marque = (EditText) dialog.findViewById(R.id.input_marque);
                final EditText input_matricule = (EditText) dialog.findViewById(R.id.input_matricule);


                Button btn_create = (Button)dialog.findViewById(R.id.btn_create);
                btn_create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String modele1 = input_modele.getText().toString();
                        final String equipement = input_equipement.getText().toString();
                        final String marque = input_marque.getText().toString();
                        final String matricule = input_matricule.getText().toString();
                        db.insererProduit(modele1,equipement,marque,matricule);
                        //modele.setAdapter(new SpinnerAdapter(getApplicationContext(),R.layout.model,db.getAllProduits()));
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Nouveau Modele Ajouter",Toast.LENGTH_SHORT).show();
                    }
                });
                Button btn_annuler = (Button)dialog.findViewById(R.id.btn_annuler);
                btn_annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        modele.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutMaMa.setVisibility(LinearLayout.GONE);
                layoutEquipement.setVisibility(LinearLayout.GONE);
                layoutSn.setVisibility(LinearLayout.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //marque.setText(produit.getMarque());
               // Produit produit = (Produit) parent.getItemAtPosition(position);
                //equipement.setText(produit.getEquipement());
                //matricule.setText(produit.getMatricule());
                layoutMaMa.setVisibility(LinearLayout.VISIBLE);
                layoutEquipement.setVisibility(LinearLayout.VISIBLE);
                layoutSn.setVisibility(LinearLayout.VISIBLE);

            }
        })
        ;

        addOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    ProduitInventaire produitInventaire = new ProduitInventaire();
                    produitInventaire.setModele(modele.getText().toString());
                    produitInventaire.setEquipement(equipement.getText().toString());
                    produitInventaire.setMatricule(matricule.getText().toString());
                    produitInventaire.setMarque(marque.getText().toString());

                    produitInventaire.setnInventaire(nInventaire.getText().toString());
                    produitInventaire.setCollaborateur(collaborateur.getText().toString());
                    produitInventaire.setDhcp(dhcp.isChecked());
                    produitInventaire.setSN(sn.getText().toString());
                    produitInventaire.setAddIp(addIp.getText().toString());
                    produitInventaire.setNomPoste(nomPoste.getText().toString());
                    produitInventaire.setElectriciteSepare(electriciteSeparer.isChecked());
                    produitInventaire.setOnduleurOperationnel(onduleurOperationnel.isChecked());
                    produitInventaires.add(produitInventaire);
                    mAdaptorForProduit.notifyDataSetChanged();
                    nbProduits.setText("Nombre de produits ajouté = "+produitInventaires.size());
                    Toast.makeText(getApplicationContext(), "Produit Ajouter avec succès", Toast.LENGTH_SHORT).show();
                    reset();
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tout le formulaire", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FourthActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("intervenant",intervenant);
                b.putSerializable("client",client);
                b.putSerializable("produitInventaire",produitInventaires);
                intent.putExtras(b);
                reset();
                startActivity(intent);
                finish();
            }
        });*/



        //Intent intent = new Intent(this,FourthActivity.class);
        //startActivity(intent);
    }



    public void reset (){
        nInventaire.setText("");
        sn.setText("");
        collaborateur.setText("");
        nomPoste.setText("");
        addIp.setText("");
        dhcp.setChecked(false);
        electriciteSeparer.setChecked(false);
        onduleurOperationnel.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //this.checkHide = false;

        this.search =  (MenuItem) menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(this.search);
        search(searchView);

        MenuItemCompat.setOnActionExpandListener(this.search,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        // Return true to allow the action view to expand
                        //townList.setVisibility(View.VISIBLE);
                        layout_form.setVisibility(LinearLayout.GONE);
                        return true;
                    }
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        // When the action view is collapsed, reset the query
                        //townList.setVisibility(View.INVISIBLE);
                        // Return true to allow the action view to collapse
                        layout_form.setVisibility(LinearLayout.VISIBLE);
                        return true;
                    }
                });

        return true;
    }

    public boolean validate() {
        boolean valid = true;

        String modele1 = modele.getText().toString();
        String nInventaire1  = nInventaire.getText().toString();
        String sn1 = sn.getText().toString();
        String collaborateur1 =  collaborateur.getText().toString();
        String nomPoste1 = nomPoste.getText().toString();
        String addIp1 = addIp.getText().toString();


        if(modele1.isEmpty()){
                modele.setError("Le modèle est obligatoire");
                Toast.makeText(getApplicationContext(),"Modele Obligatoire",Toast.LENGTH_SHORT).show();
                valid = false;

        }else {
            modele.setError(null);
        }

        if (nInventaire1.isEmpty()) {
            nInventaire.setError("le numero d'inventaire est obligatoire");
            valid = false;
        } else {
            nInventaire.setError(null);
        }

        if (collaborateur1.isEmpty()) {
            collaborateur.setError("le nom du collaborateur est obligatoire");
            valid = false;
        } else {
            collaborateur.setError(null);
        }

        if (nomPoste1.isEmpty()) {
            nomPoste.setError("le nom du poste est obligatoire");
            valid = false;
        } else {
            nomPoste.setError(null);
        }

        if (addIp1.isEmpty()) {
            addIp.setError("Address Ip est obligatoire");
            valid = false;
        } else {
            addIp.setError(null);
        }

        if (sn1.isEmpty()) {
            sn.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            sn.setError(null);
        }

        return valid;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(getApplicationContext(),"itemm = "+item.getItemId(),Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){

            /*case R.id.action_search:
                if(!checkHide){
                    checkHide = true;
                    layout_form.setVisibility(LinearLayout.GONE);
                }else{
                    checkHide = false;
                    layout_form.setVisibility(LinearLayout.VISIBLE);
                }

                break;*/
        }

            return  true;

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
                mAdaptorForProduit.getFilter().filter(newText);
                return true;
            }


        });


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




        produitInventaires.add(p);
        produitInventaires.add(p);
        produitInventaires.add(p);
        produitInventaires.add(p);

        mAdaptorForProduit.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,SecondeActivity.class);
        startActivity(intent);
    }
}
