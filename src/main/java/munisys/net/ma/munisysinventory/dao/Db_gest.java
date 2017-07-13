package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.SiteInventaire;
import munisys.net.ma.munisysinventory.entities.User;

/**
 * Created by user on 20/04/2017.
 */

public class Db_gest extends SQLiteOpenHelper {
    final static String Db_name = "Munisys.db";


    public Db_gest(Context context, int version) {
        super(context, Db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


            db.execSQL("Create Table Cliente(id Integer Primary Key AUTOINCREMENT,client Text)");
            db.execSQL("Create Table User(id Integer Primary Key AUTOINCREMENT,name Text,email Text,password Text)");
            db.execSQL("Create Table Intervenant(id Integer Primary Key AUTOINCREMENT,nomIntervenant Text)");
            db.execSQL("Create Table Site(id Integer Primary Key AUTOINCREMENT,clientId Integer,site Text,ville Text)");
            db.execSQL("Create Table SiteInventaire(id Integer Primary Key AUTOINCREMENT,siteId Integer,direction Text,ville Text,burreauEtage Text,serviceOrCentre Text,telephone Text,contact Text)");
            db.execSQL("Create Table IntervenantInventaire(idInventaire Integer,idIntervenant Integer)");
            db.execSQL("Create Table ProduitInventaire( id Integer Primary Key AUTOINCREMENT,idProduit String,idIventaire Text,nInventaire Text,SN Text,nomPoste Text,addIp Text,dhcp Integer,electriciteSepare Integer,onduleurOperationnel Integer,collaborateur Text)");
            db.execSQL("Create Table Produit(modele String Primary Key,equipement Text,marque Text,matricule Text)");
            db.execSQL("Create Table Inventaire(id Integer Primary Key AUTOINCREMENT,clientId Integer,siteIventaireId Integer," +
                    "intervenantIventaireId Integer,dateInventaire Datetime DEFAULT CURRENT_DATE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* db.execSQL("Drop Table User");
        db.execSQL("Drop Table Intervenant");
        db.execSQL("Drop Table Client");
        db.execSQL("Drop Table Site");
        db.execSQL("Drop Table SiteInventaire");
        db.execSQL("Drop Table IntervenantInventaire");
        db.execSQL("Drop Table ProduitInventaire");
        db.execSQL("Drop Table Produit");
        db.execSQL("Drop Table Inventaire");*/
        db.execSQL("Drop Table Intervenant");
        db.execSQL("Drop Table Site");
        db.execSQL("Drop Table IntervenantInventaire");
        db.execSQL("Drop Table SiteInventaire");
       // db.execSQL("Drop Table Client1");
        db.execSQL("Drop Table ProduitInventaire");
        db.execSQL("Drop Table Produit");
        db.execSQL("Drop Table Inventaire");
        db.execSQL("Drop Table User");
        onCreate(db);
    }


}

















