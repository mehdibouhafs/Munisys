package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_SiteInventaire extends Db_Site implements ISiteInventaireService {

    public Db_SiteInventaire(Context context, int version) {
        super(context, version);
    }


    // db.execSQL("Create Table SiteInventaire(idInventaire Integer Primary Key AUTOINCREMENT,siteId Integer,direction Text,ville Text,
    // burreauEtage Text,serviceOrCentre Text,telephone Text,contact Text");

    @Override
    public void insererSiteInventaire(SiteInventaire siteInventaire) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("siteId",siteInventaire.getIdSite());
        valeurs.put("direction",siteInventaire.getDirection());
        valeurs.put("ville",siteInventaire.getVille());

        valeurs.put("burreauEtage",siteInventaire.getBurreauEtage());
        valeurs.put("serviceOrCentre",siteInventaire.getServiceCentre());
        valeurs.put("telephone",siteInventaire.getTelephone());
        valeurs.put("contact",siteInventaire.getContact());

        db.insert("SiteInventaire",null,valeurs);
        db.close();
    }

    @Override
    public void deleteSiteInventaire(int id) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("SiteInventaire","id=?",new String[]{String.valueOf(id)});
        db.close();
    }


    @Override
    public SiteInventaire getSiteInventaire(int id) {
        SQLiteDatabase db=getReadableDatabase();
        SiteInventaire e=new SiteInventaire();
        Cursor cur=db.rawQuery("select * from SiteInventaire where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {   e.setIdSiteInventaire(cur.getInt(cur.getColumnIndex("idInventaire")));
            Site site = getSite(cur.getInt(cur.getColumnIndex("siteId")));
            e.setSite(site.getSite());
            e.setVille(site.getVille());
            e.setClientId(site.getClientId());
            e.setDirection(cur.getString(cur.getColumnIndex("direction")));
            e.setBurreauEtage(cur.getString(cur.getColumnIndex("burreauEtage")));
            e.setServiceCentre(cur.getString(cur.getColumnIndex("serviceCentre")));
            e.setTelephone(cur.getString(cur.getColumnIndex("telephone")));
            e.setContact(cur.getString(cur.getColumnIndex("contact")));
        }
        cur.close();
        db.close();
        return e;
    }

    @Override
    public ArrayList<SiteInventaire> getAllSiteInventaire() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<SiteInventaire> arl=new ArrayList<SiteInventaire>();
        Cursor cur=db.rawQuery("select * from SiteInventaire",null);
        SiteInventaire siteInventaire;
        Site site;
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                site = getSite(cur.getInt(cur.getColumnIndex("siteId")));

                siteInventaire  = new SiteInventaire(cur.getInt(cur.getColumnIndex("idInventaire")),cur.getInt(cur.getColumnIndex("siteId")),
                         site.getClientId(),site.getSite(),site.getVille(),
                         cur.getString(cur.getColumnIndex("direction")),cur.getString(cur.getColumnIndex("burreauEtage")),
                         cur.getString(cur.getColumnIndex("serviceCentre")),cur.getString(cur.getColumnIndex("telephone")),
                         cur.getString(cur.getColumnIndex("contact")));


                arl.add(siteInventaire);
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return arl;
    }
}
