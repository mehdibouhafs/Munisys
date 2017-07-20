package munisys.net.ma.munisysinventory.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.R;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

/**
 * Created by mehdibouhafs on 28/03/2017.
 */

public class AdaptorForProduit extends RecyclerView.Adapter<AdaptorForProduit.MyViewHolder> implements Filterable {


    ArrayList<ProduitInventaire> list_don;
    ArrayList<ProduitInventaire> list_don_filtred;
    Context ctx;
    private TextView equipement,marque,matricule,sn,nInventaire;
    private TextView edit_modele,nbProduit;
    private EditText addIp,collaborateur,nomPoste;
    private CheckBox dhcp;
    private LinearLayout layout_group,layout_find,layout_groupT;
    private TextView note;
    private TextView number;
    private int duppliquer;


    public AdaptorForProduit(ArrayList<ProduitInventaire> list_don, Context ctx,int duppliquer) {
        this.list_don = list_don;
        this.list_don_filtred = list_don;
        this.ctx = ctx;
        this.duppliquer = duppliquer;
    }

    public AdaptorForProduit(ArrayList<ProduitInventaire> list_don, TextView editModele, EditText collaborateur, EditText nomPoste,
                             EditText addIp, CheckBox dhcp,
                             LinearLayout layout_group,
                             LinearLayout layout_find, LinearLayout layout_groupT, TextView note, TextView equipement, TextView marque, TextView matricule,
                             TextView sn, TextView nInventaire, TextView nbProduit,int duppliquer)
    {
        this.list_don = list_don;
        this.list_don_filtred = list_don;
        this.edit_modele = editModele;
        this.addIp = addIp;
        this.collaborateur = collaborateur;
        this.nomPoste = nomPoste;
        this.dhcp = dhcp;
        this.layout_group = layout_group;
        this.layout_find = layout_find;
        this.note = note;
        this.equipement = equipement;
        this.marque = marque;
        this.matricule = matricule;
        this.sn = sn;
        this.nInventaire = nInventaire;
        this.nbProduit = nbProduit;
        this.layout_groupT = layout_groupT;
        this.duppliquer = duppliquer;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.modelinfo,
                parent,
                false);
        this.ctx = parent.getContext();
        MyViewHolder vh = new MyViewHolder(v);

        return  vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ProduitInventaire produit = list_don_filtred.get(position);

        Log.e("Produit",produit.toString());
        //nbProduit.setText(list_don.size());
        holder.collaborateur.setText("Collaborateur : "+produit.getCollaborateur());
        holder.equipement.setText("Equipement : "+produit.getEquipement());
        holder.modele.setText("Modèle : " +produit.getModele().trim());
       // holder.nInventaire.setText("N° inventaire : "+produit.getnInventaire());
        //holder.sn.setText("Numero de serie : "+produit.getSn());
        holder.nomPoste.setText("Nom du poste : "+produit.getNomPoste());
       // holder.addip.setText("Adresse ip : "+produit.getAddIp());
        //holder.dhcp.setChecked(produit.isDhcp());
        int i = position +1;
        holder.number.setText(" N°"+i+" ");
        if(duppliquer==1){
            holder.btnDupp.setVisibility(View.GONE);
        }


      //  holder.matricule.setText("Matricule : "+produit.getEquipement());
       // holder.marque.setText("Marque : "+produit.getMarque());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Confirm");
                builder.setMessage("êtes-vous sùr de vouloir supprimer le produit = "+produit.getModele() + " ?");

                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Toast.makeText(ctx,"Produit supprimer avec succès : "+produit.getModele(),Toast.LENGTH_SHORT).show();
                        list_don.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();



            }
        });

        holder.btnDupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                afficher(produit.getModele(),produit.getEquipement(),produit.getMarque(),produit.getMatricule(),produit.getCollaborateur(),
                        produit.getNomPoste(),produit.getAddIp(),produit.isDhcp(),produit.getSn(),produit.getnInventaire());
                Toast.makeText(ctx,"Duppliquer Item in "+position,Toast.LENGTH_SHORT).show();
            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"ITEM Click in "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        //nbProduit.setText(list_don.size());
        return list_don_filtred.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();

                if (charString.isEmpty()) {

                list_don_filtred = list_don;
            } else {

                ArrayList<ProduitInventaire> filteredList = new ArrayList<>();

                for (ProduitInventaire produit : list_don) {

                    if (produit.getSn().toLowerCase().contains(charString) || produit.getModele().toLowerCase().contains(charString) ||produit.getNomPoste().toLowerCase().contains(charString) || produit.getCollaborateur().toLowerCase().contains(charString) || produit.getSn().toLowerCase().contains(charString)
                            || String.valueOf(produit.getnInventaire()).toLowerCase().contains(charString)){

                        filteredList.add(produit);
                    }
                }

                list_don_filtred = filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = list_don_filtred;
                return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list_don_filtred = (ArrayList<ProduitInventaire>) results.values;
            notifyDataSetChanged();
        }
        };
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView modele;
        TextView equipement;
       // TextView matricule;
        //TextView marque;
        //TextView nInventaire ;
        TextView collaborateur ;
        //TextView sn;
        CheckBox electriciteSeparer;
        CheckBox dhcp;
        CheckBox onduleurOperationnel;
        TextView addip;
        TextView nomPoste;
        ImageButton btnDelete,btnDupp;
        TextView number;


        public MyViewHolder(View v) {
            super(v); // done this way instead of view tagging
             this.modele = (TextView) v.findViewById(R.id.modele1);
            this.equipement = (TextView) v.findViewById(R.id.equipement1);
            //this.nInventaire = (TextView) v.findViewById(R.id.ninventaire1);
            this.collaborateur = (TextView) v.findViewById(R.id.collaborateur1);
           // this.sn = (TextView) v.findViewById(R.id.sn1);
            //this.dhcp = (CheckBox) v.findViewById(R.id.dhcp1);
            //this.addip = (TextView) v.findViewById(R.id.addip1);
            this.nomPoste = (TextView) v.findViewById(R.id.nomPoste1);
            //this.matricule = (TextView) v.findViewById(R.id.matricule1);
            //this.marque = (TextView) v.findViewById(R.id.marque1);
            this.btnDelete = (ImageButton) v.findViewById(R.id.btn_delete);
            this.btnDupp = (ImageButton) v.findViewById(R.id.btn_dupp);
            this.number = (TextView) v.findViewById(R.id.number);

        }
    }

    public void afficher (String modele1,String equipement1,
                          String marque1,String matricule1,String collaborateur1,
                          String nomPoste1,String addip1,boolean dhcp1,String sn1,String nIventaire1){

        note.setText("Scan to update modele");
        note.setVisibility(View.VISIBLE);
        layout_group.setVisibility(LinearLayout.VISIBLE);
        layout_find.setVisibility(View.VISIBLE);
        layout_groupT.setVisibility(View.VISIBLE);
        edit_modele.setHint(modele1);
        equipement.setText(equipement1);
        marque.setText(marque1);
        matricule.setText(matricule1);
        collaborateur.setText(collaborateur1);
        collaborateur.setEnabled(true);
        nomPoste.setText(nomPoste1);
        nomPoste.setEnabled(true);
        addIp.setText(addip1);
        addIp.setEnabled(true);
        dhcp.setChecked(dhcp1);
        dhcp.setEnabled(true);
        sn.setText(sn1);

        nInventaire.setText(nIventaire1);



    }




}
