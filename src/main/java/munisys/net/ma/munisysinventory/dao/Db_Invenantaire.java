package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
                    "intervenantIventaireId Integer,dateInventaire Datetime DEFAULT CURRENT_DATE,electriciteSepare Integer,onduleurOperationnel Integer,
                    majAntivirus Integer,depoussierage Integer,empEquipement Integer,etatCablage Integer,autreTravaux Text)");
;*/


    @Override
    public Long insererInventaire(int idClient,Date dateInventaire,int electriciteSepare,int onduleurOperationnel,
                                  int majAntivirus,int depoussierage,int empEquipement,int etatCablage,String autreTravauxRealiser) {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("clientId",idClient);
        valeurs.put("dateInventaire",getDateTime(dateInventaire));
        valeurs.put("electriciteSepare",electriciteSepare);
        valeurs.put("onduleurOperationnel",onduleurOperationnel);
        valeurs.put("majAntivirus",majAntivirus);
        valeurs.put("depoussierage",depoussierage);
        valeurs.put("empEquipement",empEquipement);
        valeurs.put("etatCablage",etatCablage);
        valeurs.put("autreTravaux",autreTravauxRealiser);
        Long id = db.insert("Inventaire",null,valeurs);
        db.close();
        return id;

    }

    @Override
    public void deleteInventaire(int id) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Inventaire","id=?",new String[]{String.valueOf(id)});
        deleteIntervenantInventairebyIdInventaire(id);
        deleteSiteInventairebyIdInventaire(id);
        deleteProduitInventairebyIdInventaire(id);
        db.close();
    }


    @Override
    public Inventaire getInventaire(int id) {
        SQLiteDatabase db=getReadableDatabase();
        Inventaire e = null;
        Cursor cur=db.rawQuery("select * from Inventaire where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {
            e = new Inventaire();
            e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setIntervenants(getIntervenantsInventaire(cur.getColumnIndex("id")));
            e.setDateInventaire(getDate(cur.getString(cur.getColumnIndex("dateInventaire"))));
            e.setElectriciteSeparerInt(cur.getInt(cur.getColumnIndex("electriciteSepare")));
            e.setOnduleurOperationnelInt(cur.getInt(cur.getColumnIndex("onduleurOperationnel")));
            e.setMajAntivirusInt(cur.getInt(cur.getColumnIndex("majAntivirus")));
            e.setDepoussierageInt(cur.getInt(cur.getColumnIndex("depoussierage")));
            e.setEmpEquipementsInt(cur.getInt(cur.getColumnIndex("empEquipement")));
            e.setEtatCablage(cur.getInt(cur.getColumnIndex("etatCablage")));
            e.setAutreTravauxRealiser(cur.getString(cur.getColumnIndex("autreTravaux")));
            Log.e("ClientID",cur.getInt(cur.getColumnIndex("clientId"))+"");
            Client client = getClient(cur.getInt(cur.getColumnIndex("clientId")));
            ArrayList<ProduitInventaire> produits = getAllProduitsInventaire(cur.getInt(cur.getColumnIndex("id")));
            ArrayList<Intervenant> intervenants = getIntervenantsInventaire(cur.getInt(cur.getColumnIndex("id")));
            ArrayList<SiteInventaire> siteInventaires =  getSiteInventaire(cur.getInt(cur.getColumnIndex("id")));
            Date date = getDate(cur.getString(cur.getColumnIndex("dateInventaire")));
            e.setDateInventaire(date);
            e.setClient(client);
            e.setSiteInventaires(siteInventaires);
            e.setIntervenants(intervenants);
            e.setProduitsInventaires(produits);
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
                ArrayList<SiteInventaire> siteInventaires = getSiteInventaire(cur.getInt(cur.getColumnIndex("id")));
                Date date = getDate(cur.getString(cur.getColumnIndex("dateInventaire")));

                Inventaire e = new Inventaire();
                e.setId(cur.getInt(cur.getColumnIndex("id")));
                e.setClient(client);
                e.setIntervenants(intervenants);
                e.setProduitsInventaires(produits);
                e.setSiteInventaires(siteInventaires);
                e.setDateInventaire(date);
                e.setElectriciteSeparerInt(cur.getInt(cur.getColumnIndex("electriciteSepare")));
                e.setOnduleurOperationnelInt(cur.getInt(cur.getColumnIndex("onduleurOperationnel")));
                e.setMajAntivirusInt(cur.getInt(cur.getColumnIndex("majAntivirus")));
                e.setDepoussierageInt(cur.getInt(cur.getColumnIndex("depoussierage")));
                e.setEmpEquipementsInt(cur.getInt(cur.getColumnIndex("empEquipement")));
                e.setEtatCablage(cur.getInt(cur.getColumnIndex("etatCablage")));
                e.setAutreTravauxRealiser(cur.getString(cur.getColumnIndex("autreTravaux")));
                arl.add(e);
                cur.moveToNext();
            }
        cur.close();
        db.close();

        return arl;
    }

    @Override
    public void dropTableInventaire() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("Delete from Inventaire");
        db.close();
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
