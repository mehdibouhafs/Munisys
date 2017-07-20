package munisys.net.ma.munisysinventory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.User;

/**
 * Created by mehdibouhafs on 13/07/2017.
 */

public class Db_Client extends Db_IntervenantInventaire implements IClientService {

    public Db_Client(Context context, int version) {

        super(context, version);
    }


    //db.execSQL("Create Table Client(id Integer Primary Key AUTOINCREMENT,client Text)");


    public boolean insererClient(String client,String path_logo)
    {
        if(!getClientBoolean(client)) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues valeurs = new ContentValues();

            //valeurs.put("id",id);
            valeurs.put("client", client);
            valeurs.put("logo",path_logo);
            db.insert("Client", null, valeurs);
            db.close();
            return true;
        }
        return  false;


    }


    public void deleteClient(int id )
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("Client","id=?",new String[]{String.valueOf(id)});
        db.close();

    }

    @Override
    public void majClient(int id, String client,String path_logo) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();
        valeurs.put("id",id);
        valeurs.put("client",client);
        valeurs.put("logo",path_logo);
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
            e.setLogo(cur.getString(cur.getColumnIndex("logo")));

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
                arl.add(new Client(cur.getInt(cur.getColumnIndex("id")),cur.getString(cur.getColumnIndex("client")),
                        cur.getString(cur.getColumnIndex("logo"))));
                cur.moveToNext();
            }
        cur.close();
        db.close();

        return arl;
    }

    @Override
    public int insererClient2(String client,String path_logo) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valeurs=new ContentValues();

        //valeurs.put("id",id);
        valeurs.put("client",client);
        valeurs.put("logo",path_logo);
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
        if(e.getId()!=0){
            return e.getId();
        }else {
            return -1;
        }

    }

    @Override
    public Boolean getClientBoolean(String client) {
        SQLiteDatabase db=getReadableDatabase();
        String selectQuery = "select * from Client where client = '"+client +"'";
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
    public void dropTableClient() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete * from Client");
        db.close();
    }
}
