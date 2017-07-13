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

public class Db_User extends Db_gest implements IUserService{

    //db.execSQL("Create Table User(id Integer Primary Key AUTOINCREMENT,name Text,email Text,password Text)");

    public Db_User(Context context, int version)
    {
        super(context, version);
    }


    @Override
    public boolean insererUser(String name, String email, String password) {

        if(!getUserBoolean(email)) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues valeurs = new ContentValues();

            valeurs.put("name", name);
            valeurs.put("email", email);
            valeurs.put("password", password);


            db.insert("User", null, valeurs);
            db.close();
            return true;
        }
        return false;
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
    public Boolean getUserBoolean(String email) {
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
