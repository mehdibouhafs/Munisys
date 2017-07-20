package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;
import munisys.net.ma.munisysinventory.entities.Site;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_ProduitInventaire extends Db_Produit implements IProduitInventaireService {

    public Db_ProduitInventaire(Context context, int version) {
        super(context, version);
    }



    //db.execSQL("Create Table ProduitInventaire( id Integer Primary Key AUTOINCREMENT,modele String,idIventaire Text,
    // nInventaire Text,SN Text,nomPoste Text,addIp Text,dhcp Integer,electriciteSepare Integer,onduleurOperationnel Integer,collaborateur Text)");

    @Override
    public void insererProduitInventaire(String idProduit,int inventaireId, String nomPoste,String sn,
                                         String addIp, Boolean dhcp, String collaborateur) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        //valeurs.put("id",id);
        valeurs.put("idProduit",idProduit);
        valeurs.put("idIventaire",inventaireId);
        valeurs.put("sn",sn.toUpperCase().toString());
        valeurs.put("nomPoste",nomPoste);
        valeurs.put("addIp",addIp);
        valeurs.put("dhcp",dhcp);
        valeurs.put("collaborateur",collaborateur);

        db.insert("ProduitInventaire",null,valeurs);
        db.close();
    }

    public void deleteProduitInventaire(int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("ProduitInventaire","id=?",new String[]{String.valueOf(id)});
        db.close();

    }

    @Override
    public void deleteProduitInventairebyIdInventaire(int idIventaire) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("ProduitInventaire","idIventaire=?",new String[]{String.valueOf(idIventaire)});
        db.close();
    }


    @Override
    public void majProduitInventaire(int id, String idProduit,int inventaireId,String sn,String nomPoste, String addIp, Boolean dhcp,String collaborateur) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("idProduit",idProduit);
        valeurs.put("idIventaire",inventaireId);
        valeurs.put("sn",sn);
        valeurs.put("nomPoste",nomPoste);
        valeurs.put("addIp",addIp);
        valeurs.put("dhcp",dhcp);
        valeurs.put("collaborateur",collaborateur);

        db.update("ProduitInventaire",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }


    @Override
    public ProduitInventaire getProduitInventaire(int id) {
        SQLiteDatabase db=getReadableDatabase();
        ProduitInventaire e=new ProduitInventaire();
        Cursor cur=db.rawQuery("select * from ProduitInventaire where idIventaire=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {
            Produit produit = getProduit(cur.getString(cur.getColumnIndex("idProduit")));
            e.setEquipement(produit.getEquipement());
            e.setMatricule(produit.getMatricule());
            e.setMarque(produit.getMarque());
            e.setnInventaire(produit.getnInventaire());
            e.setIdProduitInventaire(cur.getInt(cur.getColumnIndex("id")));
            e.setIdInventaire(cur.getInt(cur.getColumnIndex("idIventaire")));
            e.setModele(cur.getString(cur.getColumnIndex("idProduit")));
            e.setNomPoste(cur.getString(cur.getColumnIndex("nomPoste")));
            e.setSn(cur.getString(cur.getColumnIndex("sn")));
            e.setAddIp(cur.getString(cur.getColumnIndex("addIp")));
            e.setCollaborateur(cur.getString(cur.getColumnIndex("collaborateur")));
        }
        cur.close();
        db.close();
        return e;
    }

    @Override
    public ArrayList<ProduitInventaire> getAllProduitsInventaire(int idInventaire) {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<ProduitInventaire> arl=new ArrayList<ProduitInventaire>();
        Cursor cur=db.rawQuery("select * from ProduitInventaire where idIventaire = ?",new String[]{String.valueOf(idInventaire)});
        if(cur.moveToFirst())

            while(cur.isAfterLast()==false)
            {
                Produit produit = getProduit(cur.getString(cur.getColumnIndex("idProduit")));
                //Log.e("searchProduit",produit.toString());
                if(produit!=null) {  //attention
                    arl.add(new ProduitInventaire(cur.getString(cur.getColumnIndex("idProduit")),
                            produit.getEquipement(),
                            produit.getMarque(), produit.getMatricule(),
                            produit.getnInventaire(),
                            cur.getString(cur.getColumnIndex("sn")), cur.getString(cur.getColumnIndex("collaborateur")), cur.getString(cur.getColumnIndex("nomPoste")),
                            cur.getString(cur.getColumnIndex("addIp")), cur.getInt(cur.getColumnIndex("dhcp"))
                            , cur.getInt(cur.getColumnIndex("idIventaire"))));
                }
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return arl;
    }

    @Override
    public ArrayList<ProduitInventaire> getAllProduitsInventaire() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<ProduitInventaire> produitInventaires=new ArrayList<ProduitInventaire>();
        Cursor cur=db.rawQuery("select * from ProduitInventaire",null);

        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                Produit produit = getProduit(cur.getString(cur.getColumnIndex("idProduit")));
                //Log.e("searchProduit",produit.toString());
                if(produit!=null) {  //attention
                    produitInventaires.add(new ProduitInventaire(cur.getString(cur.getColumnIndex("idProduit")),
                            produit.getEquipement(),
                            produit.getMarque(), produit.getMatricule(),
                            produit.getnInventaire(),
                            cur.getString(cur.getColumnIndex("sn")), cur.getString(cur.getColumnIndex("collaborateur")), cur.getString(cur.getColumnIndex("nomPoste")),
                            cur.getString(cur.getColumnIndex("addIp")), cur.getInt(cur.getColumnIndex("dhcp"))
                            , cur.getInt(cur.getColumnIndex("idIventaire"))));
                }
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return produitInventaires;

    }

    @Override
    public void dropTableProduitInventaire() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("Delete from ProduitInventaire");
        db.close();
    }


}
