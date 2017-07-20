package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.IntervenantInventaire;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_IntervenantInventaire extends Db_Intervenant implements IIntervenantInventaireService {

    public Db_IntervenantInventaire(Context context, int version) {
        super(context, version);
    }

   // db.execSQL("Create Table IntervenantInventaire(idInventaire Integer,idIntervenant Integer)");

    @Override
    public void insererIntervenantInventaire(int idInventaire, int idIntervenant) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("idInventaire",idInventaire);
        valeurs.put("idIntervenant",idIntervenant);
        db.insert("IntervenantInventaire",null,valeurs);
        db.close();
    }

    @Override
    public void deleteIntervenantInventaire(int idIntervenant) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("IntervenantInventaire","id=?",new String[]{String.valueOf(idIntervenant)});
        db.close();
    }

    @Override
    public void deleteIntervenantInventairebyIdInventaire(int idIventaire) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("IntervenantInventaire","idInventaire=?",new String[]{String.valueOf(idIventaire)});
        db.close();
    }

    @Override
    public ArrayList<IntervenantInventaire> getAllIntervenantInventaire(int idInventaire) {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<IntervenantInventaire> arl=new ArrayList<IntervenantInventaire>();
        Cursor cur=db.rawQuery("select * from IntervenantInventaire where idInventaire=?",new String[]{String.valueOf(idInventaire)});
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                arl.add(new IntervenantInventaire(cur.getInt(cur.getColumnIndex("idInventaire")),cur.getInt(cur.getColumnIndex("idIntervenant"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return arl;
    }

    @Override
    public ArrayList<Intervenant> getIntervenantsInventaire(int idInventaire) {
        ArrayList<Intervenant> intervenants = new ArrayList<>();

        ArrayList<IntervenantInventaire> intervenantInventaires = getAllIntervenantInventaire(idInventaire);
        Intervenant intervenant;
        for (IntervenantInventaire e : intervenantInventaires){
            intervenant = getIntervenant(e.getIntervenantId());
            intervenants.add(intervenant);
        }

        return intervenants;
    }

    @Override
    public void dropTableIntervenantInventaire() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("Delete from IntervenantInventaire");
        db.close();
    }


}
