package munisys.net.ma.munisysinventory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.adapters.SpinnerAdapter;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Site;

public class SecondeActivity extends HomeActivity implements AdapterView.OnItemSelectedListener {


    private Button validerIdentification;
    private EditText burreauEtage,serviceCentre,direction;
    private SearchableSpinner client,site;
    private TextInputLayout input_burreauEtage, input_serviceCentre, input_direction;
    private ArrayList<Client> clients;
    private Db_gest db;
    private ArrayList<Site> sites;
    private TextView ville,contact,tel;
    private SpinnerAdapter<Client> adapterClient;
    private SpinnerAdapter<Site> adapterSite;

    private ImageButton newClient,newSite;

    private LinearLayout layoutVille,layoutContact,layoutTel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.content_seconde, frameLayout);
        activityId = R.layout.content_seconde;
        setTitle("Identification");
        db = new Db_gest(this,16);

        newClient = (ImageButton) view.findViewById(R.id.newClient);
        newSite = (ImageButton) view.findViewById(R.id.newSite);

        layoutVille = (LinearLayout) view.findViewById(R.id.layoutVille);
        layoutContact = (LinearLayout) view.findViewById(R.id.layoutContact);
        layoutTel = (LinearLayout) view.findViewById(R.id.layoutTel);

        layoutVille.setVisibility(LinearLayout.GONE);
        layoutContact.setVisibility(LinearLayout.GONE);
        layoutTel.setVisibility(LinearLayout.GONE);

        newClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SecondeActivity.this);
                dialog.setContentView(R.layout.add_client);

                final EditText input_client = (EditText) dialog.findViewById(R.id.input_client);
                 final EditText input_site = (EditText) dialog.findViewById(R.id.input_site);
                final EditText input_contact = (EditText) dialog.findViewById(R.id.input_contact);
                final EditText input_tel = (EditText) dialog.findViewById(R.id.input_tel);
                final SearchableSpinner selectedVille = (SearchableSpinner) dialog.findViewById(R.id.selectedVille);
                Button btn_creer = (Button)dialog.findViewById(R.id.btn_create);
                Button btn_annuler = (Button)dialog.findViewById(R.id.btn_annuler);



                btn_creer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String client1 = input_client.getText().toString();
                        final String site = input_site.getText().toString();
                        final String contact = input_contact.getText().toString();
                        final String tel = input_tel.getText().toString();
                        final String ville = selectedVille.getSelectedItem().toString();
                        int clientId = db.insererClient2(client1,site,ville,contact,tel);
                        db.insererSite(site,ville,clientId);
                        client.setAdapter(new SpinnerAdapter(getApplicationContext(),R.layout.model,db.getAllClients()));
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Nouveau Client Ajouter",Toast.LENGTH_SHORT).show();
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

        newSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SecondeActivity.this);
                dialog.setContentView(R.layout.add_site);

                final EditText input_site = (EditText) dialog.findViewById(R.id.input_site1);
                final SearchableSpinner selectedVille = (SearchableSpinner) dialog.findViewById(R.id.selectedVille1);
                SearchableSpinner selectedClient = (SearchableSpinner) dialog.findViewById(R.id.selectedClient1);
                selectedClient.setAdapter(new SpinnerAdapter(getApplicationContext(),R.layout.model,db.getAllClients()));
                Button btn_create = (Button)dialog.findViewById(R.id.btn_create1);
                Button btn_annuler = (Button)dialog.findViewById(R.id.btn_annuler1);

                btn_create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String site1 = input_site.getText().toString();
                        final String ville = selectedVille.getSelectedItem().toString();
                        final String client = selectedVille.getSelectedItem().toString();
                        int clientId = db.getClient(client);
                        db.insererSite(site1,ville,clientId);
                        site.setAdapter(new SpinnerAdapter(getApplicationContext(),R.layout.model,db.getSitesClient(clientId)));
                        Toast.makeText(getApplicationContext(),"Nouveau Site Ajouter",Toast.LENGTH_SHORT).show();
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


        db = new Db_gest(this,16);
        clients = db.getAllClients();
        validerIdentification = (Button) view.findViewById(R.id.validerIdentification);
        burreauEtage = (EditText) view.findViewById(R.id.burreauEtage);
        burreauEtage.clearFocus();
        serviceCentre = (EditText) view.findViewById(R.id.serviceCentre);
        direction = (EditText) view.findViewById(R.id.direction);
        client = (SearchableSpinner) view.findViewById(R.id.client);
         adapterClient =  new SpinnerAdapter(this,R.layout.model,clients);
        client.setFocusable(true);
        client.setFocusableInTouchMode(true);
        client.requestFocus();
        client.setAdapter(adapterClient);
        sites = new ArrayList<>();
        site = (SearchableSpinner) view.findViewById(R.id.site);
        adapterSite = new SpinnerAdapter(getApplicationContext(),R.layout.model,sites);
        site.setAdapter(adapterSite);


        ville = (TextView) view.findViewById(R.id.ville);
        contact = (TextView) view.findViewById(R.id.contact);
        tel = (TextView) view.findViewById(R.id.tel);


        client.setOnItemSelectedListener(this);
        site.setOnItemSelectedListener(this);




        /*Intent intent = getIntent();
        final Bundle b = intent.getExtras();
        final Intervenant intervenant = (Intervenant) b.get("intervenant");*/



        validerIdentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondeActivity.this,ThirdActivity.class);
                final Bundle bundle = new Bundle();
                Client client = new Client();
                client.setBurreauEtage(burreauEtage.getText().toString());
                client.setDirection(direction.getText().toString());
                client.setClient(client.getClient());
                client.setVille("FES");
                client.setSite("ABOU FARES 2");
                bundle.putSerializable("client",client);
                //bundle.putSerializable("intervenant",intervenant);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    void afficher_list(ArrayList<Site> sites1) {
        sites.clear();
        sites.addAll(sites1);
        adapterSite.notifyDataSetChanged();
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateBurreauEtage()) {
            return;
        }

        if (!validateServiceCentre()) {
            return;
        }

        if (!validateDirection()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateBurreauEtage() {
        if (burreauEtage.getText().toString().trim().isEmpty()) {
            burreauEtage.setError("error burreauEtage");
            requestFocus(burreauEtage);
            return false;
        } else {
            input_burreauEtage.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateServiceCentre() {
        String email = serviceCentre.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            input_serviceCentre.setError("error service centre");
            requestFocus(serviceCentre);
            return false;
        } else {
            input_serviceCentre.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDirection() {
        if (direction.getText().toString().trim().isEmpty()) {
            input_direction.setError("error direction");
            requestFocus(direction);
            return false;
        } else {
            input_direction.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.client:
                Client client1 = (Client) parent.getItemAtPosition(position);
                ArrayList<Site> site1 = db.getSitesClient(client1.getId());
                Toast.makeText(getApplicationContext(),"Client Selected",Toast.LENGTH_SHORT).show();
                afficher_list(site1);
                contact.setText(client1.getContact());
                tel.setText(client1.getTelephone());
                layoutVille.setVisibility(LinearLayout.GONE);
                layoutContact.setVisibility(LinearLayout.GONE);
                layoutTel.setVisibility(LinearLayout.GONE);
                break;
            case R.id.site:
                Site c = (Site) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Site Selected",Toast.LENGTH_SHORT).show();
                ville.setText(c.getVille());
                layoutVille.setVisibility(LinearLayout.VISIBLE);
                layoutContact.setVisibility(LinearLayout.VISIBLE);
                layoutTel.setVisibility(LinearLayout.VISIBLE);

                break;
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_layout_name:
                    //validateName();
                    break;
                case R.id.input_serviceCentre:
                    //validateEmail();
                    break;
                case R.id.input_direction:
                    //validatePassword();
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_home, menu);

        MenuItem search = menu.findItem(R.id.action_settings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.action_save:
                save();
                return true;
            case R.id.action_delete:
                delete();
                return true;
            case R.id.action_settings:
                return true;
        }*/
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
    }
}
