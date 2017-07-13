package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.User;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_Intervenant extends Db_User implements IIntervenantService {

    public Db_Intervenant(Context context, int version) {
        super(context, version);
    }


    //db.execSQL("Create Table Intervenant(id Integer Primary Key AUTOINCREMENT,nomIntervenant Text)");


    public boolean insererIntervenant(String nomIntervenant)
    {
        if(getIntervenantBoolean(nomIntervenant)==null) {


            SQLiteDatabase db = getWritableDatabase();
            ContentValues valeurs = new ContentValues();

            //valeurs.put("id",id);
            valeurs.put("nomIntervenant", nomIntervenant);

            db.insert("Intervenant", null, valeurs);
            db.close();
            return true;
        }
        return false;
    }

    @Override
    public void deleteIntervenant(int id) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Intervenant","id=?",new String[]{String.valueOf(id)});

        db.close();
    }


    public void majIntervenant(int id,String nomIntervenant)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("id",id);
        valeurs.put("nomIntervenant",nomIntervenant);

        db.update("Intervenant",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }




    public Intervenant getIntervenant(int Id)

    {
        SQLiteDatabase db=getReadableDatabase();
        Intervenant e=new Intervenant();
        Cursor cur=db.rawQuery("select * from Intervenant where id=?",new String[]{String.valueOf(Id)} );

        if(cur.moveToFirst())
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setNomIntervenant(cur.getString(cur.getColumnIndex("nomIntervenant")));


        }
        cur.close();
        db.close();

        return e;


    }

    @Override
    public ArrayList<Intervenant> getAllIntervenants() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Intervenant> arl=new ArrayList<Intervenant>();
        Cursor cur=db.rawQuery("select * from Intervenant",null);
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                arl.add(new Intervenant(cur.getInt(cur.getColumnIndex("id")),cur.getString(cur.getColumnIndex("nomIntervenant"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return arl;
    }

    @Override
    public Boolean getIntervenantBoolean(String nomIntervenant) {
        SQLiteDatabase db=getReadableDatabase();
        String selectQuery = "select * from Intervenant where nomIntervenant = '"+nomIntervenant +"'";
        Cursor cur=db.rawQuery(selectQuery,null);
        cur.moveToFirst();
        if(cur.getCount() > 0){
            return true;
        }
        cur.close();
        db.close();

        return false;
    }


}
