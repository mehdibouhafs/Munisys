package munisys.net.ma.munisysinventory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

public class MiddleActivitry extends HomeActivity {

    private CheckBox electriciteSepare,onduleurOperationnel,depoussierage,majAntivirus,empEquipement,etatCablage;
    private EditText autreTravauxRealise;
    private Button validerInformations;
    private  Bundle bundle;
    private  Inventaire inventaire;
    private Client client;
    private ArrayList<Intervenant> intervenants;
    private SiteInventaire siteInventaire;
    private ArrayList<ProduitInventaire> produitInventaires;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_middle_activitry);
        View view = getLayoutInflater().inflate(R.layout.activity_middle_activitry, frameLayout);
        setTitle("Informations");

        electriciteSepare = (CheckBox) view.findViewById(R.id.electriciteSeparer);
        onduleurOperationnel = (CheckBox) view.findViewById(R.id.onduleurOperationnel);
        depoussierage = (CheckBox) view.findViewById(R.id.depoussierage);
        majAntivirus = (CheckBox) view.findViewById(R.id.majAntivirus);
        empEquipement = (CheckBox) view.findViewById(R.id.empEquipement);
        etatCablage = (CheckBox) view.findViewById(R.id.etatCablage);
        logo = (ImageView) view.findViewById(R.id.logo);


        final Intent intent = getIntent();
        bundle =  intent.getExtras();
        client = (Client) bundle.get("client");
        try {
            fileToImageView(client.getLogo());
        } catch (IOException e) {
            e.printStackTrace();
        }

        validerInformations = (Button) findViewById(R.id.validerInformations);




         intervenants = (ArrayList<Intervenant>) bundle.get("intervenants");

         siteInventaire = (SiteInventaire) bundle.get("siteInventaire");
        produitInventaires = (ArrayList<ProduitInventaire>) bundle.get("produitInventaires");


        Log.e("client middle ",client.toString());
        Log.e("site middle ",siteInventaire.toString());

        for (ProduitInventaire p : produitInventaires){
            Log.e("Produit inventaire ",p.toString());
        }

        validerInformations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),FourthActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });




        autreTravauxRealise = (EditText) view.findViewById(R.id.autreTravauxRealise);
        validerInformations = (Button) view.findViewById(R.id.validerInformations);






        validerInformations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventaire = new Inventaire();
                inventaire.setClient(client);
                ArrayList<SiteInventaire> siteInventaires = new ArrayList<SiteInventaire>();
                siteInventaires.add(siteInventaire);
                inventaire.setSiteInventaires(siteInventaires);
                inventaire.setIntervenants(intervenants);
                inventaire.setElectriciteSeparer(electriciteSepare.isChecked());
                inventaire.setOnduleurOperationnel(onduleurOperationnel.isChecked());
                inventaire.setMajAntivirus(majAntivirus.isChecked());
                inventaire.setDepoussierage(depoussierage.isChecked());
                inventaire.setEmpEquipements(empEquipement.isChecked());
                inventaire.setEtatCablage(etatCablage.isChecked());
                inventaire.setAutreTravauxRealiser(autreTravauxRealise.getText().toString());

                bundle.putSerializable("inventaire",inventaire);
                Intent intent = new Intent(getApplicationContext(),FourthActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(this,ThirdActivity.class);
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
