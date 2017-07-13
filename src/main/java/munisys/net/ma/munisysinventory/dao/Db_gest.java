package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Collaborateur;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.ProduitInventaire;
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.User;

/**
 * Created by user on 20/04/2017.
 */

public class Db_gest extends SQLiteOpenHelper implements IClientService,IIntervenantService,IIventaireService,IProduitInventaireService,ICollaborateurService,IProduitService,ISiteService,IUserService {
     final static String Db_name="INVENTAIRE.db";



    public Db_gest(Context context, int version) {
        super(context, Db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table Client(id Integer Primary Key AUTOINCREMENT,client Text,site Text,direction Text,ville Text,burreauEtage Text,serviceOrCentre Text,telephone Text,contact Text)");
        db.execSQL("Create Table Site(id Integer Primary Key AUTOINCREMENT,site Text,ville Text,clientId Integer)");
        db.execSQL("Create Table User(id Integer Primary Key AUTOINCREMENT,name Text,email Text,password Text)");
        db.execSQL("Create Table Collaborateur(id Integer Primary Key AUTOINCREMENT,nomCollaborateur Text)");
        db.execSQL("Create Table Intervenant(id Integer Primary Key AUTOINCREMENT,nomIntervenant Text)");
        db.execSQL("Create Table ProduitInventaire( id Integer Primary Key AUTOINCREMENT,idProduit Integer,idIventaire Text,nInventaire Text,SN Text,nomPoste Text,addIp Text,dhcp Integer,electriciteSepare Integer,onduleurOperationnel Integer,collaborateur Text)");
        db.execSQL("Create Table Produit(modele String Primary Key,equipement Text,marque Text,matricule Text)");
        db.execSQL("Create Table Inventaire(id Integer Primary Key AUTOINCREMENT,clientId Integer,intervenantId Integer,dateInventaire Datetime DEFAULT CURRENT_DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table Produit");
        db.execSQL("Drop Table Site");
        //db.execSQL("Drop Table User");
        db.execSQL("Drop Table ProduitInventaire");
        db.execSQL("Drop Table Inventaire");
        db.execSQL("Drop Table Client");
        db.execSQL("Drop Table Collaborateur");
        db.execSQL("Drop Table Intervenant");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        db.execSQL("Drop Table Produit");
        db.execSQL("Drop Table Site");
        db.execSQL("Drop Table User");
        db.execSQL("Drop Table Inventaire");
        db.execSQL("Drop Table Client");
        db.execSQL("Drop Table Collaborateur");
        db.execSQL("Drop Table Intervenant");

    }

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
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setIdProduit(cur.getString(cur.getColumnIndex("idProduit")));
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




    public void insererClient(String client,String site,String direction,String ville,String burreauEtage,
                              String ServiceCentre,String telephone,String contact)
    {
         SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("client",client);
        valeurs.put("direction",direction);
        valeurs.put("ville",ville);valeurs.put("burreauEtage",burreauEtage);
        valeurs.put("serviceOrCentre",ServiceCentre);
        valeurs.put("telephone",telephone);
        valeurs.put("contact",contact);
        db.insert("Client",null,valeurs);
        db.close();

    }


    public void deleteClient(int id )
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Client","id=?",new String[]{String.valueOf(id)});
        db.close();

    }

    @Override
    public void majClient(int id, String client, String site, String direction, String ville,
                          String burreauEtage, String ServiceCentre, String telephone, String contact) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("id",id);
        valeurs.put("client",client);
        valeurs.put("site",site);
        valeurs.put("direction",client);
        valeurs.put("ville",ville);
        valeurs.put("burreauEtage",burreauEtage);
        valeurs.put("serviceOrCentre",ServiceCentre);
        valeurs.put("telephone",telephone);
        valeurs.put("contact",contact);
        db.update("Client",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public Client getClient(int id) {
        SQLiteDatabase db=getReadableDatabase();
        Client e=new Client();
        Cursor cur=db.rawQuery("select * from Client where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setClient(cur.getString(cur.getColumnIndex("client")));
            e.setDirection(cur.getString(cur.getColumnIndex("direction")));
            e.setServiceCentre(cur.getString(cur.getColumnIndex("serviceOrCentre")));
            e.setSite(cur.getString(cur.getColumnIndex("site")));
            e.setContact(cur.getString(cur.getColumnIndex("contact")));
            e.setVille(cur.getString(cur.getColumnIndex("ville")));
            e.setBurreauEtage(cur.getString(cur.getColumnIndex("burreauEtage")));
            e.setTelephone(cur.getString(cur.getColumnIndex("telephone")));

        }
        cur.close();
        db.close();
        return e;
    }

    @Override
    public ArrayList<Client> getAllClients() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Client> arl=new ArrayList<Client>();
        Cursor cur=db.rawQuery("select * from Client",null);
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                arl.add(new Client(cur.getInt(cur.getColumnIndex("id")),cur.getString(cur.getColumnIndex("client")), cur.getString(cur.getColumnIndex("site")),
                        cur.getString(cur.getColumnIndex("direction")),cur.getString(cur.getColumnIndex("ville")),cur.getString(cur.getColumnIndex("burreauEtage")),
                        cur.getString(cur.getColumnIndex("serviceOrCentre")),cur.getString(cur.getColumnIndex("contact")),cur.getString(cur.getColumnIndex("telephone"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();

        return arl;
    }

    @Override
    public int insererClient2(String client, String site, String ville, String telephone, String contact) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("client",client);
        valeurs.put("client",client);
        valeurs.put("ville",ville);
        valeurs.put("telephone",telephone);
        valeurs.put("contact",contact);
        Long id =db.insert("Client",null,valeurs);
        db.close();
        return (int)(long)id;
    }

    @Override
    public int getClient(String client) {
        SQLiteDatabase db=getReadableDatabase();
        Client e=new Client();
        Cursor cur=db.rawQuery("select * from Client where client=?",new String[]{String.valueOf(client)} );

        if(cur.moveToFirst())
        {
            e.setId(cur.getInt(cur.getColumnIndex("id")));
        }
        cur.close();
        db.close();
        return e.getId();
    }

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
                e.setId(cur.getInt(cur.getColumnIndex("id")));
                e.setClientId(cur.getInt(cur.getColumnIndex("clientId")));
                e.setVille(cur.getString(cur.getColumnIndex("ville")));
                e.setSite(cur.getString(cur.getColumnIndex("site")));
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
                arl.add(new Site(cur.getInt(cur.getColumnIndex("id")),cur.getString(cur.getColumnIndex("site")),
                        cur.getString(cur.getColumnIndex("ville")),cur.getInt(cur.getColumnIndex("clientId"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();
        return arl;
    }

    @Override
    public void insererSite(String site, String ville, int cliendId) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("site",site);
        valeurs.put("ville",ville);
        valeurs.put("clientId",cliendId);

        db.insert("Site",null,valeurs);
        db.close();
    }

    @Override
    public void deleteSite(int id) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Site","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void majSite(int id, String site, String ville, int clientId) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        //valeurs.put("id",id);
        valeurs.put("site",site);
        valeurs.put("ville",ville);
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
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setSite(cur.getString(cur.getColumnIndex("site")));
            e.setVille(cur.getString(cur.getColumnIndex("ville")));
            e.setId(cur.getInt(cur.getColumnIndex("clientId")));


        }
        cur.close();
        db.close();
        return e;
    }





    public void insererCollaborateur(String nomCollaborateur)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("nomCollaborateur",nomCollaborateur);

        db.insert("Collaborateur",null,valeurs);
        db.close();
    }

    public void deleteCollaborateur(int id )
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Client","id=?",new String[]{String.valueOf(id)});
        db.close();

    }


    public void majCollaborateur(int id,String client)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("id",id);
        valeurs.put("client",client);

        db.update("Client",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public Collaborateur getCollaborateur(int id) {
        SQLiteDatabase db=getReadableDatabase();
        Collaborateur e=new Collaborateur();
        Cursor cur=db.rawQuery("select * from Collaborateur where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setNomCollaborateur(cur.getString(cur.getColumnIndex("nomCollaborateur")));


        }
        cur.close();
        db.close();
        return e;
    }

    @Override
    public ArrayList<Collaborateur> getAllCollaborateurs() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Collaborateur> arl=new ArrayList<Collaborateur>();
        Cursor cur=db.rawQuery("select * from Collaborateur",null);
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                arl.add(new Collaborateur(cur.getInt(cur.getColumnIndex("id")),cur.getString(cur.getColumnIndex("nomCollaborateur"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();

        return arl;
    }

    public void insererIntervenant(String nomIntervenant)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("nomIntervenant",nomIntervenant);

        db.insert("Intervenant",null,valeurs);
        db.close();
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
    public void insererInventaire(int idClient, int idIntervenant, Date dateInventaire) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        valeurs.put("clientId",idClient);
        valeurs.put("intervenantID",idIntervenant);
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

    @Override
    public void majInventaire(int id, int idClient, int idIntervenant, Date dateInventaire) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        //valeurs.put("id",id);
        valeurs.put("clientId",idClient);
        valeurs.put("intervenantId",idIntervenant);
        valeurs.put("dateInventaire",getDateTime(dateInventaire));

        db.update("Inventaire",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public Inventaire getInventaire(int id) {
        SQLiteDatabase db=getReadableDatabase();
        Inventaire e=new Inventaire();
        Cursor cur=db.rawQuery("select * from Inventaire where id=?",new String[]{String.valueOf(id)} );

        if(cur.moveToFirst())
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setIntervenant(getIntervenant(cur.getInt(cur.getColumnIndex("intervenantId"))));
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
        Intervenant intervenant;
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                client = getClient(cur.getInt(cur.getColumnIndex("id")));
                produits = getAllProduitsInventaire(cur.getInt(cur.getColumnIndex("id")));
                intervenant = getIntervenant(cur.getInt(cur.getColumnIndex("id")));
                arl.add(new Inventaire(cur.getInt(cur.getColumnIndex("id")),client,produits,intervenant,getDate(cur.getString(cur.getColumnIndex("dateInventaire")))));
                cur.moveToNext();
            }
        cur.close();
        db.close();

        return arl;
    }

    @Override
    public void insererProduit(String modele, String equipement, String marque, String matricule) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        valeurs.put("modele",modele);
        valeurs.put("equipement",equipement);
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
    public void majProduit(String modele1, String modele, String equipement, String marque, String matricule) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("modele",modele);
        valeurs.put("equipement",equipement);
        valeurs.put("marque",marque);
        //valeurs.put("idClient",idClient);

        db.update("Produit",valeurs,"modele=?",new String[]{String.valueOf(modele1)});
        db.close();
    }

    @Override
    public Produit getProduit(String modele) {
        SQLiteDatabase db=getReadableDatabase();
        Produit e=new Produit();
        Cursor cur=db.rawQuery("select * from Produit where modele=?",new String[]{String.valueOf(modele)} );

        if(cur.moveToFirst())
        {   e.setModele(cur.getString(cur.getColumnIndex("modele")));
            e.setEquipement(cur.getString(cur.getColumnIndex("equipement")));
            e.setMarque(cur.getString(cur.getColumnIndex("marque")));
            e.setMatricule(cur.getString(cur.getColumnIndex("matricule")));

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
                        cur.getString(cur.getColumnIndex("matricule"))));
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
    public void insererUser(String name, String email, String password) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        valeurs.put("name",name);
        valeurs.put("email",email);
        valeurs.put("password",password);


        db.insert("User",null,valeurs);
        db.close();
    }

    @Override
    public void deleteUser(int id) {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("User","id=?",new String[]{String.valueOf(id)});
        db.close();

    }

    @Override
    public void majUser(int id, String name, String email, String password) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("name",name);
        valeurs.put("email",email);
        valeurs.put("password",password);
        //valeurs.put("idClient",idClient);

        db.update("User",valeurs,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public User getUser(String email, String password) {
        SQLiteDatabase db=getReadableDatabase();
        User e=new User();
        String selectQuery = "select * from User where email = '"+email +"' and password = '"+password+"'";
        Cursor cur=db.rawQuery(selectQuery,null);

        if(cur.moveToFirst())
        {   e.setId(cur.getInt(cur.getColumnIndex("id")));
            e.setName(cur.getString(cur.getColumnIndex("name")));
            e.setEmail(cur.getString(cur.getColumnIndex("email")));
            e.setPassword(cur.getString(cur.getColumnIndex("password")));
        }
        cur.close();
        db.close();
        return e;
    }

    @Override
    public Boolean getUserBoolean(String email, String password) {
        SQLiteDatabase db=getReadableDatabase();
        User e=new User();
        String selectQuery = "select * from User where email = '"+email +"' and password = '"+password+"'";
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
    public Boolean getUserBooleanEmail(String email) {
        SQLiteDatabase db=getReadableDatabase();
        User e=new User();
        String selectQuery = "select * from User where email = '"+email +"'";
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
    public ArrayList<User> getALLUser() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<User> arl=new ArrayList<User>();
        Cursor cur=db.rawQuery("select * from User",null);
        if(cur.moveToFirst())
            while(cur.isAfterLast()==false)
            {
                arl.add(new User(cur.getInt(cur.getColumnIndex("id")),
                        cur.getString(cur.getColumnIndex("name")),cur.getString(cur.getColumnIndex("email")),cur.getString(cur.getColumnIndex("password"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();

        return arl;
    }
}

