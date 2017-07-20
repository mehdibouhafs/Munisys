package munisys.net.ma.munisysinventory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import munisys.net.ma.munisysinventory.adapters.SpinnerAdapter;
import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

public class SecondeActivity extends HomeActivity implements AdapterView.OnItemSelectedListener {


    private static final int REQUEST_PICTURE = 199;
    private static final String TAG = "SecondeActivity";
    private Button validerIdentification;
    private EditText burreauEtage,serviceCentre,direction,contact,tel;
    private SearchableSpinner client,site;
    private TextInputLayout input_burreauEtage, input_serviceCentre, input_direction;
    private ArrayList<Client> clients;
    private ArrayList<Site> sites;
    private TextView ville;
    private SpinnerAdapter<Client> adapterClient;
    private SpinnerAdapter<Site> adapterSite;
    private ImageView logo;

    private ImageButton newClient,newSite;
    private ImageView upload0;

    private LinearLayout layoutVille;
    private Dialog dialog;
    private ArrayList<Intervenant> intervenants;
    private Bundle b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.content_seconde, frameLayout);
        activityId = R.layout.content_seconde;
        setTitle("Identification");
        logo = (ImageView) view.findViewById(R.id.logo);

        Intent intent = getIntent();
        b = intent.getExtras();
        if(b.get("intervenants")!=null) {
            intervenants = (ArrayList<Intervenant>) b.get("intervenants");
            if (intervenants != null) {
                for (Intervenant e : intervenants) {
                   // Log.e("SECONDE ACTIVITY", e.toString());
                }
            }
        }

        newClient = (ImageButton) view.findViewById(R.id.newClient);
        newSite = (ImageButton) view.findViewById(R.id.newSite);
        layoutVille = (LinearLayout) view.findViewById(R.id.layoutVille);
        layoutVille.setVisibility(LinearLayout.GONE);
        clients = db.getAllClients();
        validerIdentification = (Button) view.findViewById(R.id.validerIdentification);
        burreauEtage = (EditText) view.findViewById(R.id.burreauEtage);
        burreauEtage.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        burreauEtage.clearFocus();
        serviceCentre = (EditText) view.findViewById(R.id.serviceCentre);
        serviceCentre.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        direction = (EditText) view.findViewById(R.id.direction);
        direction.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        contact = (EditText) view.findViewById(R.id.contact);
        contact.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        tel = (EditText) view.findViewById(R.id.tel);
        client = (SearchableSpinner) view.findViewById(R.id.client);
         adapterClient =  new SpinnerAdapter(this,R.layout.model,clients);
        client.setFocusable(true);
        client.setFocusableInTouchMode(false);
        client.requestFocus();
        client.setAdapter(adapterClient);
        sites = new ArrayList<>();
        site = (SearchableSpinner) view.findViewById(R.id.site);
        adapterSite = new SpinnerAdapter(getApplicationContext(),R.layout.model,sites);
        site.setAdapter(adapterSite);
        ville = (TextView) view.findViewById(R.id.ville);
        client.setOnItemSelectedListener(this);
        site.setOnItemSelectedListener(this);


        newClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewClient.class);
                startActivityForResult(intent,REQUEST_PICTURE);

            }
        });

        newSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SecondeActivity.this);
                dialog.setContentView(R.layout.add_site);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;

                final EditText input_site = (EditText) dialog.findViewById(R.id.input_site1);
                input_site.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                final SearchableSpinner selectedVille = (SearchableSpinner) dialog.findViewById(R.id.selectedVille1);
                final SearchableSpinner selectedClient = (SearchableSpinner) dialog.findViewById(R.id.selectedClient1);
                selectedClient.setAdapter(new SpinnerAdapter(getApplicationContext(),R.layout.model,db.getAllClients()));
                Button btn_create = (Button)dialog.findViewById(R.id.btn_create1);
                Button btn_annuler = (Button)dialog.findViewById(R.id.btn_annuler1);

                btn_create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String site1 = input_site.getText().toString();
                        if(selectedClient.getSelectedItem() != null && selectedVille.getSelectedItem() !=null) {
                            final String ville = selectedVille.getSelectedItem().toString();
                            final Client client0 = (Client) selectedClient.getSelectedItem();
                            Log.e("Client id ", client + "Client Name " + client);
                            db.insererSite(site1, ville, client0.getId());
                            site.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.model, db.getSitesClient(client0.getId())));
                            Toast.makeText(getApplicationContext(), "Nouveau Site Ajouter", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "Veuillez selectionner le client et la ville", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btn_annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });



        validerIdentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondeActivity.this,ThirdActivity.class);
                final Bundle bundle = new Bundle();
                if(client.getSelectedItem()!=null && site.getSelectedItem()!=null) {
                    Client client1 = (Client) client.getSelectedItem();
                    Site site1 = (Site) site.getSelectedItem();
                    Log.e("Seconde 1", client1.toString());
                    Log.e("Seconde 2", site1.toString());// Inventaire id 0
                    SiteInventaire siteInventaire = new SiteInventaire(site1.getIdSite(),client1.getId(),site1.getSite(),site1.getVille(),0,direction.getText().toString(),
                            burreauEtage.getText().toString(),serviceCentre.getText().toString(),tel.getText().toString(),contact.getText().toString());

                    bundle.putSerializable("client", client1);
                    bundle.putSerializable("siteInventaire", siteInventaire);
                    bundle.putSerializable("intervenants", intervenants);
                    //bundle.putSerializable("intervenant",intervenant);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"la selection du client et le site est obligatoire",Toast.LENGTH_SHORT).show();
                }

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
                Log.e("CLient item selected 1 ",client1.toString());
                try {
                    fileToImageView(client1.getLogo());
                    for(Site c :db.getSitesClient(client1.getId()) ) {
                        Log.i("SITE", c.toString());
                    }
                    site.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.model,db.getSitesClient(client1.getId())));
                } catch (IOException e) {
                    e.printStackTrace();
                }




                Toast.makeText(getApplicationContext(),"Client Selected",Toast.LENGTH_SHORT).show();
                layoutVille.setVisibility(LinearLayout.GONE);

                break;
            case R.id.site:
                Site c = (Site) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Site Selected",Toast.LENGTH_SHORT).show();
                ville.setText(c.getVille());
                layoutVille.setVisibility(LinearLayout.VISIBLE);
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
        //super.onBackPressed();
        Intent intent = new Intent(this,FirstActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 200) {
            if (requestCode == REQUEST_PICTURE) {
                // Get the url from data
                //Uri selectedImageUri = data.getData();
                    client.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.model, db.getAllClients()));
            }
        }
    }


    public void fileToImageView(String filePath) throws IOException {

        InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(Uri.parse(filePath));
        Bitmap bmp = BitmapFactory.decodeStream(inputStream);
        if( inputStream != null ) inputStream.close();
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        logo.setImageBitmap(bmp);

    }
}
