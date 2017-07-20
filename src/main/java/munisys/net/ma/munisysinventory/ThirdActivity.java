package munisys.net.ma.munisysinventory;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oem.barcode.BCRIntents;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import munisys.net.ma.munisysinventory.BrodcastReceiver.BCRAppBroadcastReceiver;
import munisys.net.ma.munisysinventory.adapters.AdaptorForProduit;
import munisys.net.ma.munisysinventory.adapters.SpinnerAdapter;
import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Equipement;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Marque;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;
import munisys.net.ma.munisysinventory.entities.Site;

public class ThirdActivity extends HomeActivity {

    private EditText collaborateur,nomPoste,addIp;
    private CheckBox dhcp;
    private EditText modele;
    private FloatingActionButton addOther;
    private Button next;
    private AdaptorForProduit mAdaptorForProduit;
    private RecyclerView recyclerView;
    private TextView equipement,marque,matricule,nbProduits,nInventaire,client1,clientName;
    private EditText matricule1,nInventaire1,sn;
    private SearchableSpinner equipement1,marque1;
    private MenuItem search;
    private TextView note;
    private BCRAppBroadcastReceiver mBroadcastReceiver;
    private ImageView logo;
    private Bundle bundle;
    private boolean showed = true;
    private boolean validate;

    private LinearLayout layout_form, layout_groupFind,layout_groupEdit,layout_groupT;



    public boolean checkHide = false;

    private ArrayList<ProduitInventaire> produitInventaires;

    private ArrayList<Intervenant> intervenants;
    private Client client2;
    private Site site2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.content_third, frameLayout);
        /*final android.app.ActionBar ab = getActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayShowTitleEnabled(false);
         final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view2 = inflater.inflate(R.layout.action_bar_edit,null);
        ab.setCustomView(view);
        ab.setDisplayShowCustomEnabled(true);*/

        setTitle("Informatiques");
        modele = (EditText) view.findViewById(R.id.modele);
        modele.setFocusable(true);
        modele.setFocusableInTouchMode(true);
        modele.requestFocus();
        modele.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (modele.getText().toString().trim().isEmpty()) {
                    sn.getText().clear();
                    layout_groupFind.setVisibility(View.GONE);
                    layout_groupEdit.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Modele vide ", Toast.LENGTH_SHORT).show();
                } else {
                        Toast.makeText(getApplicationContext(), " Test() ", Toast.LENGTH_SHORT).show();
                        test();
                }
            }
        });
        nbProduits = (TextView) view.findViewById(R.id.nbProduits);
        client1 = (TextView) view.findViewById(R.id.client);
        clientName = (TextView) view.findViewById(R.id.clientName);
        logo  = (ImageView) view.findViewById(R.id.logo);


        note = (TextView) view.findViewById(R.id.note);
        IntentFilter filter = new IntentFilter();
        filter.addAction(BCRIntents.ACTION_NEW_DATA);

        Intent intent = getIntent();
        this.bundle = intent.getExtras();
        if(this.bundle!=null) {
            intervenants = (ArrayList<Intervenant>) bundle.get("intervenants");
            client2 = (Client) bundle.get("client");
            site2 = (Site) bundle.get("siteInventaire");
        }


        try {
            fileToImageView(client2.getLogo());
            clientName.setText(client2.getClient());
        } catch (IOException e) {
            e.printStackTrace();
        }


        activityId = R.layout.content_third;
        produitInventaires = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        ArrayList<Produit> produits = db.getAllProduits();

        layout_groupFind = (LinearLayout) view.findViewById(R.id.layout_groupFind);
        layout_groupEdit = (LinearLayout) view.findViewById(R.id.layout_groupEdit);
        layout_groupT = (LinearLayout) view.findViewById(R.id.layout_groupT);
        layout_groupT.setVisibility(View.VISIBLE);
        layout_groupEdit.setVisibility(LinearLayout.GONE);
        layout_groupFind.setVisibility(LinearLayout.GONE);
        equipement1 = (SearchableSpinner) view.findViewById(R.id.equipement1);
        marque1 = (SearchableSpinner) view.findViewById(R.id.marque1);
        matricule1 = (EditText) view.findViewById(R.id.matricule1);
        matricule1.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        nInventaire1 = (EditText) view.findViewById(R.id.nInventaire1);
        nInventaire1.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        layout_form = (LinearLayout) view.findViewById(R.id.layout_form);
        equipement = (TextView) view.findViewById(R.id.equipement);
        equipement.clearFocus();
        marque = (TextView) view.findViewById(R.id.marque);
        matricule = (TextView) view.findViewById(R.id.matricule);
        nInventaire = (TextView) view.findViewById(R.id.nInventaire);
        sn = (EditText) view.findViewById(R.id.sn);
        collaborateur = (EditText) view.findViewById(R.id.collaborateur);
        collaborateur.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        nomPoste = (EditText) view.findViewById(R.id.nomPoste);
        nomPoste.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        addIp = (EditText) view.findViewById(R.id.addip);
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start,
                                       int end, Spanned dest, int dstart, int dend) {
                if (end > start) {
                    String destTxt = dest.toString();
                    String resultingTxt = destTxt.substring(0, dstart) +
                            source.subSequence(start, end) +
                            destTxt.substring(dend);
                    if (!resultingTxt.matches ("^\\d{1,3}(\\." +
                            "(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                        return "";
                    } else {
                        String[] splits = resultingTxt.split("\\.");
                        for (int i=0; i<splits.length; i++) {
                            if (Integer.valueOf(splits[i]) > 255) {
                                return "";
                            }
                        }
                    }
                }
                return null;
            }
        };
        addIp.setFilters(filters);


        dhcp = (CheckBox) view.findViewById(R.id.dhcp);
        addOther = (FloatingActionButton) view.findViewById(R.id.ajouterMateriel);

        reset();

        /*mBroadcastReceiver = new BCRAppBroadcastReceiver(this,modele,sn,collaborateur,nomPoste,addIp,dhcp,
                layout_groupFind,layout_groupEdit,layout_groupT,note,equipement,marque,matricule,sn,nInventaire);
        //this.getApplicationContext().registerReceiver(mBroadcastReceiver, filter);*/


        addOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(layout_groupFind.getVisibility()==View.VISIBLE){

                    if(validate()) {
                        ProduitInventaire produitInventaire = new ProduitInventaire();
                        produitInventaire.setModele(modele.getText().toString().trim());
                        produitInventaire.setEquipement(equipement.getText().toString());
                        produitInventaire.setMatricule(matricule.getText().toString());
                        produitInventaire.setMarque(marque.getText().toString());
                        produitInventaire.setSn(sn.getText().toString().trim());
                        produitInventaire.setnInventaire(nInventaire.getText().toString());
                        produitInventaire.setCollaborateur(collaborateur.getText().toString());
                        produitInventaire.setDhcp(dhcp.isChecked());
                        produitInventaire.setAddIp(addIp.getText().toString());
                        produitInventaire.setNomPoste(nomPoste.getText().toString());
                        produitInventaires.add(produitInventaire);
                        mAdaptorForProduit.notifyDataSetChanged();
                        validate = true;
                        Toast.makeText(getApplicationContext(), "Produit Ajouter avec succès", Toast.LENGTH_SHORT).show();
                        reset();
                    }else{
                        validate = false;
                        Toast.makeText(getApplicationContext(), "Erreur verfier les champs du formulaire", Toast.LENGTH_LONG).show();
                    }

                }else if(layout_groupEdit.getVisibility()==View.VISIBLE){
                    if(equipement1.getSelectedItem()!=null && marque1.getSelectedItem()!=null) {
                        ProduitInventaire produitInventaire = new ProduitInventaire();
                        produitInventaire.setModele(modele.getText().toString());
                        produitInventaire.setEquipement(equipement1.getSelectedItem().toString());
                        produitInventaire.setMatricule(matricule.getText().toString());
                        produitInventaire.setMarque(marque1.getSelectedItem().toString());
                        produitInventaire.setSn(sn.getText().toString());
                        produitInventaire.setnInventaire(nInventaire.getText().toString());
                        produitInventaire.setCollaborateur(collaborateur.getText().toString());
                        produitInventaire.setDhcp(dhcp.isChecked());
                        produitInventaire.setAddIp(addIp.getText().toString());
                        produitInventaire.setNomPoste(nomPoste.getText().toString());
                        produitInventaires.add(produitInventaire);
                        mAdaptorForProduit.notifyDataSetChanged();
                        validate = true;
                        reset2();
                        Toast.makeText(getApplicationContext(), "Nouveau Produit Ajouter avec succès", Toast.LENGTH_SHORT).show();
                    }else{
                        validate =false;
                        Toast.makeText(getApplicationContext(), "la selection de l'equipement et de la marque est obligatoire", Toast.LENGTH_SHORT).show();
                    }

                }

                if(validate) {
                    new AlertDialog.Builder(ThirdActivity.this)
                            .setMessage("Voullez-vous ajouter un autre produit ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    nbProduits.setText(produitInventaires.size() + "");
                                    note.setVisibility(View.VISIBLE);
                                    layout_groupFind.setVisibility(LinearLayout.GONE);
                                    layout_groupEdit.setVisibility(LinearLayout.GONE);
                                    Log.d("ThirdActivity", "onClick: yes");
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(getApplicationContext(), MiddleActivitry.class);
                                    Bundle b = getBundle();
                                    b.putSerializable("produitInventaires", produitInventaires);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                    Log.d("ThirdActivity", "onClick: no");
                                }
                            })
                            .setCancelable(false)
                            .show();
                }

            }
        });


        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MiddleActivitry.class);
                Bundle b = new Bundle();
                b.putSerializable("produitInventaire",produitInventaires);
                intent.putExtras(b);
                reset();
                startActivity(intent);
                finish();
            }
        });*/



        //Intent intent = new Intent(this,FourthActivity.class);
        //startActivity(intent);
        mAdaptorForProduit = new AdaptorForProduit(produitInventaires,modele,collaborateur,nomPoste,addIp,dhcp,
                layout_form,layout_groupFind,layout_groupT,note,equipement,marque,matricule,sn,nInventaire,nbProduits,0);
        recyclerView.setAdapter(mAdaptorForProduit);
    }


    public void reset (){
        note.setText("Veuillez scanner le modèle");
        modele.setText("");
        modele.setFocusable(true);
        modele.setFocusableInTouchMode(true);
        modele.requestFocus();
        sn.setText("");
        sn.setEnabled(false);
        collaborateur.setEnabled(false);
        nomPoste.setEnabled(false);
        addIp.setEnabled(false);
        dhcp.setEnabled(false);
        layout_groupFind.setVisibility(LinearLayout.GONE);
        layout_groupEdit.setVisibility(LinearLayout.GONE);
        collaborateur.setText("");
        nomPoste.setText("");
        addIp.setText("");
        dhcp.setChecked(false);

    }

    public void test(){
        db = new Db_Invenantaire(getApplicationContext(), 16);
        Produit p = db.getProduit(modele.getText().toString().trim());
        if (p != null) {
            //modele.getText().clear();
           equipement.setText(p.getEquipement());
            marque.setText(p.getMarque());
            matricule.setText(p.getMatricule());
            nInventaire.setText(p.getnInventaire());
            sn.getText().clear();
            addIp.setEnabled(true);
            collaborateur.setEnabled(true);
            nomPoste.setEnabled(true);
            addIp.setEnabled(true);
            dhcp.setEnabled(true);
            sn.setEnabled(true);
            note.setText("please scan S.N");
            layout_groupFind.setVisibility(LinearLayout.VISIBLE);
            layout_groupEdit.setVisibility(LinearLayout.GONE);
            //layout_groupT.setVisibility(View.VISIBLE);
            showed = true;
            Toast.makeText(this, "Produit disponible !", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Produit  indisponible", Toast.LENGTH_SHORT).show();
            if(showed) {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmation")
                        .setMessage("Voullez-vous ajouter un nouveau produit ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showed = false;
                                layout_groupFind.setVisibility(LinearLayout.GONE);
                                layout_groupEdit.setVisibility(LinearLayout.VISIBLE);
                                sn.getText().clear();
                                sn.setEnabled(true);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(modele.getText().toString().length()>12) {
                                    showed = true;
                                }
                                //modele.getText().clear();
                                layout_groupFind.setVisibility(LinearLayout.GONE);
                                layout_groupEdit.setVisibility(LinearLayout.GONE);
                                dialog.dismiss();

                            }
                        })
                        .show();
            }
        }
    }


    public void reset2 (){
        note.setText("Veuillez scanner le modèle");
        modele.setText("");
        modele.setFocusable(true);
        modele.setFocusableInTouchMode(true);
        modele.requestFocus();
        sn.setText("");
        sn.setEnabled(false);
        collaborateur.setEnabled(false);
        nomPoste.setEnabled(false);
        addIp.setEnabled(false);
        dhcp.setEnabled(false);
        layout_groupFind.setVisibility(LinearLayout.GONE);
        layout_groupEdit.setVisibility(LinearLayout.GONE);
        collaborateur.setText("");
        nomPoste.setText("");
        addIp.setText("");
        dhcp.setChecked(false);
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


        String collaborateur1 =  collaborateur.getText().toString();
        String nomPoste1 = nomPoste.getText().toString();
        String sn1 = sn.getText().toString();

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
        if(!validateProduitInventaire(sn1)){
            note.setText("Vous avez déjà un produit avec ce numero de serie");
            sn.setError("Vous avez déjà ajouter ce numero de serie");
            valid = false;
        }else{
            sn.setError(null);
        }


        return valid;
    }


    public boolean validate2() {
        boolean valid = true;

        String modele3 = modele.getText().toString();
        String matricule3 = matricule1.getText().toString();
        Marque marque3 = (Marque) marque1.getSelectedItem();
        String nInventaire3  = nInventaire1.getText().toString();
        Equipement equipement3 = (Equipement) equipement1.getSelectedItem();
        String collaborateur3 =  collaborateur.getText().toString();
        String nomPoste3 = nomPoste.getText().toString();
        String addIp3 = addIp.getText().toString();
        String sn1 = sn.getText().toString();


        if(modele3.isEmpty()){
            modele.setError("le modele est obligatoire");
            valid = false;

        }else {
            modele.setError(null);
        }

        if(!validateProduitInventaire(sn1)){
            note.setText("Vous avez déjà un produit avec ce numero de serie");
            sn.setError("Vous avez déjà ajouter ce numero de serie");
            valid = false;
        }else{
            sn.setError(null);
        }

        if(sn1.isEmpty()){
            sn.setError("Le numero de serie est obligatoire");
            valid = false;
        }else{
            sn.setError(null);
        }


        if(marque3!=null){
            Toast.makeText(getApplicationContext(),"La marques est Obligatoire",Toast.LENGTH_SHORT).show();
            valid = false;

        }else {

        }


        if (nInventaire3.isEmpty()) {
            nInventaire1.setError("le numero d'inventaire est obligatoire");
            valid = false;
        } else {
            nInventaire1.setError(null);
        }

        if(equipement3 != null){
            Toast.makeText(getApplicationContext(),"l'equipement est Obligatoire",Toast.LENGTH_SHORT).show();
            valid = false;

        }else {

        }

        if (!modele3.isEmpty() && equipement3!=null && marque3!=null && !matricule3.isEmpty() && !sn1.isEmpty()){
            collaborateur.setEnabled(true);
            nomPoste.setEnabled(true);
            addIp.setEnabled(true);
            dhcp.setEnabled(true);
        }

        if (matricule3.isEmpty()) {
            matricule1.setError("le numero d'inventaire est obligatoire");
            valid = false;
        } else {
            matricule1.setError(null);
        }


        if (collaborateur3.isEmpty()) {
            collaborateur.setError("le nom du collaborateur est obligatoire");
            valid = false;
        } else {
            collaborateur.setError(null);
        }

        if (nomPoste3.isEmpty()) {
            nomPoste.setError("le nom du poste est obligatoire");
            valid = false;
        } else {
            nomPoste.setError(null);
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(this,SecondeActivity.class);
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

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }


    public boolean validateProduitInventaire(String sn){

        String sn1 = sn.toUpperCase().toString();

        for(ProduitInventaire produitInventaire : produitInventaires){
            if(produitInventaire.getSn().equals(sn1)){
                return false;
            }
        }

        return true;

    }
}
