package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_Produit extends Db_SiteInventaire implements IProduitService {

    public Db_Produit(Context context, int version) {
        super(context, version);
    }

    //db.execSQL("Create Table Produit(modele String Primary Key,equipement Text,marque Text,matricule Text)");

    @Override
    public void insererProduit(String modele, String equipement, String marque, String matricule,String nInventaire) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("modele",modele);
        valeurs.put("equipement",equipement);
        valeurs.put("nInventaire",nInventaire);
        valeurs.put("marque",marque);
        valeurs.put("matricule",matricule);
        db.insert("Produit",null,valeurs);
        db.close();
    }

    @Override
    public void deleteProduit(String modele) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Produit","modele=?",new String[]{String.valueOf(modele)});

        db.close();
    }

    @Override
    public void majProduit(String modele1, String modele, String equipement, String marque, String matricule,String nInventaire) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("modele",modele);
        valeurs.put("equipement",equipement);
        valeurs.put("nInventaire",nInventaire);
        valeurs.put("marque",marque);
        db.update("Produit",valeurs,"modele=?",new String[]{String.valueOf(modele1)});
        db.close();
    }

    @Override
    public Produit getProduit(String modele) {
        SQLiteDatabase db=getReadableDatabase();
        Produit e = null;
        Cursor cur=db.rawQuery("select * from Produit where modele = ?",new String[]{String.valueOf(modele)} );

        if(cur.moveToFirst())
        {
            e = new Produit();
            e.setModele(cur.getString(cur.getColumnIndex("modele")));
            e.setEquipement(cur.getString(cur.getColumnIndex("equipement")));
            e.setMarque(cur.getString(cur.getColumnIndex("marque")));
            e.setMatricule(cur.getString(cur.getColumnIndex("matricule")));
            e.setnInventaire(cur.getString(cur.getColumnIndex("nInventaire")));

        }
        cur.close();
        db.close();
        return e;
    }

    @Override
    public ArrayList<Produit> getAllProduits() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Produit> arl=new ArrayList<Produit>();
        Cursor cur=db.rawQuery("select * from Produit",null);

        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {

                arl.add(new Produit(cur.getString(cur.getColumnIndex("modele")),
                        cur.getString(cur.getColumnIndex("equipement")),
                        cur.getString(cur.getColumnIndex("marque")),
                        cur.getString(cur.getColumnIndex("matricule")),
                        cur.getString(cur.getColumnIndex("nInventaire"))
                        ));
                cur.moveToNext();
            }

        cur.close();
        db.close();

        return arl;
    }

    @Override
    public Boolean getProduitBoolean(String modele, String equipement, String marque, String matricule) {
        SQLiteDatabase db=getReadableDatabase();
        String selectQuery = "select * from Produit where modele = '"+modele +"'and equipement = '"+equipement+"' and marque = '"+marque+
        "' and matricule = '"+matricule+"'";
        Cursor cur=db.rawQuery(selectQuery,null);
        cur.moveToFirst();
        if(cur.getCount() > 0){
            return true;
        }
        cur.close();
        db.close();

        return false;
    }

    @Override
    public void dropTableProduit() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("Delete from Produit");
        db.close();
    }
}
