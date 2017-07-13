package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;

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
    public void insererProduitInventaire(String idProduit, String nInventaire, String sn, String nomPoste,
                                         String addIp, Boolean dhcp, Boolean electriciteSepare, Boolean onduleurOperationnel, String collaborateur) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        //valeurs.put("id",id);
        valeurs.put("idProduit",idProduit);
        valeurs.put("nInventaire",nInventaire);
        valeurs.put("sn",sn);
        valeurs.put("nomPoste",nomPoste);
        valeurs.put("addIp",addIp);
        valeurs.put("dhcp",dhcp);
        valeurs.put("electriciteSepare",electriciteSepare);
        valeurs.put("onduleurOperationnel",onduleurOperationnel);
        valeurs.put("collaborateur",collaborateur);

        db.insert("Produit",null,valeurs);
        db.close();
    }

    public void deleteProduitInventaire(int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("ProduitInventaire","id=?",new String[]{String.valueOf(id)});
        db.close();

    }



    @Override
    public void majProduitInventaire(int id, String idProduit, String nInventaire, String sn, String nomPoste, String addIp, Boolean dhcp, Boolean electriciteSepare, Boolean onduleurOperationnel, String collaborateur) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("idProduit",idProduit);
        valeurs.put("nInventaire",nInventaire);
        valeurs.put("sn",sn);
        valeurs.put("nomPoste",nomPoste);
        valeurs.put("addIp",addIp);
        valeurs.put("dhcp",dhcp);
        valeurs.put("electriciteSepare",electriciteSepare);
        valeurs.put("onduleurOperationnel",onduleurOperationnel);
        valeurs.put("collaborateur",collaborateur);

        db.update("ProduitInventaire",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }


    @Override
    public ProduitInventaire getProduitInventaire(int id) {
        SQLiteDatabase db=getReadableDatabase();
        ProduitInventaire e=new ProduitInventaire();
        Cursor cur=db.rawQuery("select * from ProduitInventaire where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {   e.setIdProduitInventaire(cur.getInt(cur.getColumnIndex("id")));
            e.setModele(cur.getString(cur.getColumnIndex("idProduit")));
            e.setnInventaire(cur.getString(cur.getColumnIndex("nInventaire")));
            e.setNomPoste(cur.getString(cur.getColumnIndex("nomPoste")));
            //e.setDhcp(cur.geti(cur.getColumnIndex("telephone")));
            //e.setOnduleurOperationnel(cur.getColumnIndex("telephone")));
            //e.setElectriciteSepare(cur.getColumnIndex("telephone")));
            e.setAddIp(cur.getString(cur.getColumnIndex("addIp")));
            e.setSN(cur.getString(cur.getColumnIndex("matricule")));
        }
        cur.close();
        db.close();
        return e;
    }

    @Override
    public ArrayList<ProduitInventaire> getAllProduitsInventaire(int idInventaire) {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<ProduitInventaire> arl=new ArrayList<ProduitInventaire>();
        Cursor cur=db.rawQuery("select * from ProduitInventaire",null);
        if(cur.moveToFirst())

            while(cur.isAfterLast()==false)
            {
                /*arl.add(new Produit(cur.getInt(cur.getColumnIndex("id")),cur.getString(cur.getColumnIndex("modele")), cur.getString(cur.getColumnIndex("equipement")),
                        cur.getString(cur.getColumnIndex("marque")),cur.getInt(cur.getColumnIndex("nInventaire")),cur.getString(cur.getColumnIndex("matricule")),
                        cur.getString(cur.getColumnIndex("SN")), cur.getString(cur.getColumnIndex("nomPoste")),cur.getString(cur.getColumnIndex("addIp")),cur.getString(cur.getColumnIndex("dhcp")),cur.getString(cur.getColumnIndex("electriciteSepare")),cur.getString(cur.getColumnIndex("onduleurOperationnel")),
                        cur.getString(cur.getColumnIndex("collaborateur"))));*/
                arl.add(new ProduitInventaire());
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return arl;
    }
}
