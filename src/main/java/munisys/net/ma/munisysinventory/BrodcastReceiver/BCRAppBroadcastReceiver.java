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

import munisys.net.ma.munisysinventory.dao.Db_Invenantaire;
import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 14/07/2017.
 */

public class BCRAppBroadcastReceiver extends BroadcastReceiver {

    private Context ctx;
    private TextView edit_modele,equipement,marque,matricule,sn,nInventaire;
    private EditText addIp,collaborateur,nomPoste;
    private CheckBox dhcp,onduleurOp,electricite;
    private LinearLayout layout_group;
    private TextView note;
    private Db_Invenantaire db;

    public BCRAppBroadcastReceiver(Context ctx, TextView editModele,EditText collaborateur, EditText nomPoste, EditText addIp, CheckBox dhcp,
                                   CheckBox electricite,CheckBox onduleurOp,LinearLayout layout_group,TextView note,TextView equipement,TextView marque,TextView matricule,TextView sn,TextView nInventaire) {

        this.ctx = ctx;
        this.edit_modele = editModele;
        this.addIp = addIp;
        this.collaborateur = collaborateur;
        this.nomPoste = nomPoste;
        this.dhcp = dhcp;
        this.onduleurOp = onduleurOp;
        this.electricite = electricite;
        this.layout_group = layout_group;

        this.note = note;
        this.equipement = equipement;
        this.marque = marque;
        this.matricule = matricule;
        this.sn = sn;
        this.nInventaire = nInventaire;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(BCRIntents.ACTION_NEW_DATA)) {

            int id = intent.getIntExtra(BCRIntents.EXTRA_BCR_TYPE, -1);
            byte[] data = intent.getByteArrayExtra(BCRIntents.EXTRA_BCR_DATA);


            String modele = new String(data);
            if(!modele.isEmpty()) {
                db = new Db_Invenantaire(this.ctx, 11);
                    Produit p = db.getProduit(modele.trim());
                    if (p != null) {
                        fill(p.getModele(), p.getEquipement(), p.getMarque(), p.getMatricule(), p.getSn(), p.getnInventaire());
                        //edit_modele.setText(new String(data));
                        edit_modele.setFocusable(true);
                        edit_modele.setFocusableInTouchMode(true);
                        edit_modele.requestFocus();
                        addIp.setEnabled(true);
                        collaborateur.setEnabled(true);
                        nomPoste.setEnabled(true);
                        addIp.setEnabled(true);
                        dhcp.setEnabled(true);
                        electricite.setEnabled(true);
                        onduleurOp.setEnabled(true);
                        layout_group.setVisibility(LinearLayout.VISIBLE);
                        note.setVisibility(View.INVISIBLE);
                    }

            }

           /* DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                        case DialogInterface.BUTTON_POSITIVE:
                            break;
                    }
                    dialog.dismiss();
                }
            };*/

            //builder.setPositiveButton(android.R.string.ok, onClickListener);
            //AlertDialog dialog = builder.create();
            //dialog.setCancelable(true);
            //dialog.show();

            //Toast.makeText(mActivity,new String(data), Toast.LENGTH_LONG).show();
        }
    }
    public void fill(String modele,String equipement1,String marque1,String matricule1,String sn1,String nInventaire1){
        edit_modele.setText(modele);
        equipement.setText(equipement1);
        marque.setText(marque1);
        matricule.setText(matricule1);
        sn.setText(sn1);
        nInventaire.setText(nInventaire1);


    }

}
