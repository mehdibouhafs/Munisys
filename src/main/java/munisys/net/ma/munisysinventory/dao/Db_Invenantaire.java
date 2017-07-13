package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.IntervenantInventaire;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_Invenantaire extends Db_ProduitInventaire implements IIventaireService {

    public Db_Invenantaire(Context context, int version) {
        super(context, version);
    }

    /*db.execSQL("Create Table Inventaire(id Integer Primary Key AUTOINCREMENT,clientId Integer,siteIventaireId Integer," +
                "intervenantIventaireId Integer,dateInventaire Datetime DEFAULT CURRENT_DATE)");*/


    @Override
    public void insererInventaire(int idClient, int idIntervenant,int siteIventaireId, Date dateInventaire) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        valeurs.put("clientId",idClient);
        valeurs.put("siteIventaireId",siteIventaireId);
        valeurs.put("intervenantIventaireId",idIntervenant);
        valeurs.put("dateInventaire",getDateTime(dateInventaire));

        db.insert("Inventaire",null,valeurs);
        db.close();

    }

    @Override
    public void deleteInventaire(int id) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Inventaire","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    /*@Override
    public void majInventaire(int id, int idClient, int idIntervenant, Date dateInventaire) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        //valeurs.put("id",id);
        valeurs.put("clientId",idClient);
        valeurs.put("intervenantId",idIntervenant);
        valeurs.put("dateInventaire",getDateTime(dateInventaire));

        db.update("Inventaire",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }*/

    @Override
    public Inventaire getInventaire(int id) {
        SQLiteDatabase db=getReadableDatabase();
        Inventaire e=new Inventaire();
        Cursor cur=db.rawQuery("select * from Inventaire where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setIntervenants(getIntervenantsInventaire(cur.getColumnIndex("id")));
            e.setSiteInventaire(getSiteInventaire(cur.getInt(cur.getColumnIndex("siteIventaireId"))));
            e.setClient(getClient(cur.getInt(cur.getColumnIndex("clientId"))));
            e.setProduits(getAllProduitsInventaire(id));
            e.setDateInventaire(getDate(cur.getString(cur.getColumnIndex("dateInventaire"))));
        }
        cur.close();
        db.close();

        return e;
    }

    @Override
    public ArrayList<Inventaire> getAllInventaires() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Inventaire> arl=new ArrayList<Inventaire>();
        Cursor cur=db.rawQuery("select * from Inventaire",null);
        Client client;
        ArrayList<ProduitInventaire> produits;
        ArrayList<Intervenant> intervenants;
        SiteInventaire siteInventaire;
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                client = getClient(cur.getInt(cur.getColumnIndex("clientId")));
                produits = getAllProduitsInventaire(cur.getInt(cur.getColumnIndex("id")));
                intervenants = getIntervenantsInventaire(cur.getInt(cur.getColumnIndex("id")));
                siteInventaire = getSiteInventaire(cur.getInt(cur.getColumnIndex("siteIventaireId")));
                Date date = getDate(cur.getString(cur.getColumnIndex("dateInventaire")));
                arl.add(new Inventaire(cur.getInt(cur.getColumnIndex("id")),client,siteInventaire,intervenants,produits,date));
                cur.moveToNext();
            }
        cur.close();
        db.close();

        return arl;
    }


    public String getDateTime(Date dateInventaire){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(dateInventaire);

    }
    public Date getDate(String dateInventaire){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateInventaire);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
    public Boolean getIntervenantBoolean(String nomIntervenant) {
        return null;
    }
}
