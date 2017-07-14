package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Site;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_Site extends Db_Client implements ISiteService {

    public Db_Site(Context context, int version) {
        super(context, version);
    }


    // db.execSQL("Create Table Site(id Integer Primary Key AUTOINCREMENT,clientId Integer,site Text,ville Text)");

    @Override
    public ArrayList<Site> getSitesClient(int id) {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Site> arl=new ArrayList<Site>();
        Site e;
        Cursor cur=db.rawQuery("select * from Site where clientId=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {
            while(cur.isAfterLast()==false) {
                e = new Site();
                e.setIdSite(cur.getInt(cur.getColumnIndex("id")));
                e.setSite(cur.getString(cur.getColumnIndex("site")));
                e.setClientId(cur.getInt(cur.getColumnIndex("clientId")));
                e.setVille(cur.getString(cur.getColumnIndex("ville")));
                arl.add(e);
                cur.moveToNext();
            }

        }
        cur.close();
        db.close();
        return arl;
    }

    @Override
    public ArrayList<Site> getAllSites() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Site> arl=new ArrayList<Site>();
        Cursor cur=db.rawQuery("select * from Site",null);
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                arl.add(new Site(cur.getInt(cur.getColumnIndex("id")),cur.getInt(cur.getColumnIndex("clientId")),cur.getString(cur.getColumnIndex("site")),
                        cur.getString(cur.getColumnIndex("ville")),cur.getString(cur.getColumnIndex("telephone")),cur.getString(cur.getColumnIndex("contact"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return arl;
    }

    @Override
    public Boolean getSiteBoolean(String site, String ville, int clientID) {
        SQLiteDatabase db=getReadableDatabase();
        String selectQuery = "select * from Site where site = '"+site +"' and ville = '"+ville+"' and clientId = "+clientID;
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
    public boolean insererSite(String site, String ville, int cliendId,String telephone,String contact) {

        if(!getSiteBoolean(site,ville,cliendId)) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues valeurs = new ContentValues();

            //valeurs.put("id",id);
            valeurs.put("site", site);
            valeurs.put("ville", ville);
            valeurs.put("clientId", cliendId);
            valeurs.put("telephone", telephone);
            valeurs.put("contact", contact);
            db.insert("Site", null, valeurs);
            db.close();
            return true;
        }
        return false;
    }

    @Override
    public void deleteSite(int id) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Site","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void majSite(int id, String site, String ville, int clientId,String tel,String contact) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        //valeurs.put("id",id);
        valeurs.put("site",site);
        valeurs.put("ville",ville);
        valeurs.put("telephone",tel);
        valeurs.put("contact",contact);
        valeurs.put("clientId",clientId);

        db.update("Site",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public Site getSite(int id) {
        SQLiteDatabase db=getReadableDatabase();
        Site e=new Site();
        Cursor cur=db.rawQuery("select * from Site where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {   e.setIdSite(cur.getInt(cur.getColumnIndex("id")));
            e.setSite(cur.getString(cur.getColumnIndex("site")));
            e.setVille(cur.getString(cur.getColumnIndex("ville")));
            e.setClientId(cur.getInt(cur.getColumnIndex("clientId")));


        }
        cur.close();
        db.close();
        return e;
    }
}
