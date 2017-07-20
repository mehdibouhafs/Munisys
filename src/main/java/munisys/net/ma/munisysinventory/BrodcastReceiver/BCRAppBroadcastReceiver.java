package munisys.net.ma.munisysinventory.BrodcastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oem.barcode.BCRIntents;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 14/07/2017.
 */

public class BCRAppBroadcastReceiver extends BroadcastReceiver {

    private Context ctx;
    private TextView equipement,marque,matricule,nInventaire;
    private EditText addIp,collaborateur,nomPoste,sn,edit_modele,edit_sn;
    private CheckBox dhcp;
    private LinearLayout layout_groupFind,layout_groupEdit,layout_groupT;
    private TextView note;
    private Db_Invenantaire db;
    private boolean modelFilled;
    private String data;

    public BCRAppBroadcastReceiver(Context ctx, EditText editModele,EditText edit_sn, EditText collaborateur, EditText nomPoste,
                                   EditText addIp, CheckBox dhcp,
                                   LinearLayout layout_groupFind, LinearLayout layout_groupEdit,
                                   LinearLayout layout_groupT,TextView note, TextView equipement, TextView marque, TextView matricule,
                                   EditText sn, TextView nInventaire) {

        this.ctx = ctx;
        this.edit_modele = editModele;
        this.addIp = addIp;
        this.collaborateur = collaborateur;
        this.nomPoste = nomPoste;
        this.dhcp = dhcp;
        this.layout_groupFind = layout_groupFind;
        this.note = note;
        this.equipement = equipement;
        this.marque = marque;
        this.matricule = matricule;
        this.sn = sn;
        this.nInventaire = nInventaire;
        this.layout_groupEdit = layout_groupEdit;
        this.layout_groupT = layout_groupT;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(BCRIntents.ACTION_NEW_DATA)) {

            int id = intent.getIntExtra(BCRIntents.EXTRA_BCR_TYPE, -1);
            byte[] data = intent.getByteArrayExtra(BCRIntents.EXTRA_BCR_DATA);
            String modele = new String(data);

            if((!modele.isEmpty() && edit_modele.getText().toString().isEmpty())) {
                db = new Db_Invenantaire(this.ctx, 16);
                    Produit p = db.getProduit(modele.trim());
                    if (p != null) {
                        fill(p.getModele(), p.getEquipement(), p.getMarque(), p.getMatricule(), p.getnInventaire());
                       // edit_modele.setText(new String(data));
                        edit_modele.setFocusable(true);
                        edit_modele.setFocusableInTouchMode(true);
                        edit_modele.requestFocus();
                        addIp.setEnabled(true);
                        this.data = modele;
                        collaborateur.setEnabled(true);
                        nomPoste.setEnabled(true);
                        addIp.setEnabled(true);
                        dhcp.setEnabled(true);
                        sn.setEnabled(true);
                        note.setText("please scan S.N");
                        layout_groupFind.setVisibility(LinearLayout.VISIBLE);
                        layout_groupT.setVisibility(View.VISIBLE);
                        layout_groupEdit.setVisibility(LinearLayout.GONE);
                        Toast.makeText(ctx, "Produit disponible !", Toast.LENGTH_SHORT).show();
                    } else {
                    Toast.makeText(ctx, "Produit indisponible", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ctx)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Confirmation")
                            .setMessage("Voullez-vous ajouter un nouveau produit ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    layout_groupFind.setVisibility(LinearLayout.GONE);
                                    layout_groupEdit.setVisibility(LinearLayout.VISIBLE);
                                    layout_groupT.setVisibility(View.VISIBLE);
                                    sn.setEnabled(true);
                                }

                            })
                            .setNegativeButton("No", null)
                            .show();
                         }
            }
            if((!modele.isEmpty()&& !edit_modele.getText().toString().isEmpty() && sn.getText().toString().isEmpty())){
                Toast.makeText(ctx,"SN",Toast.LENGTH_SHORT).show();
                sn.getText().clear();
                sn.setText(modele.toString().trim());
                }

        }


    }
    public void fill(String modele,String equipement1,String marque1,String matricule1,String nInventaire1){

        //edit_modele.setText(modele);
        equipement.setText(equipement1);
        marque.setText(marque1);
        matricule.setText(matricule1);
        nInventaire.setText(nInventaire1);


    }

}
