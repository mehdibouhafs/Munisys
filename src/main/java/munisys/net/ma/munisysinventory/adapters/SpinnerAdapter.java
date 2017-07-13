package munisys.net.ma.munisysinventory.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.R;
import munisys.net.ma.munisysinventory.entities.Client;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.Produit;
import munisys.net.ma.munisysinventory.entities.Site;
import munisys.net.ma.munisysinventory.entities.User;

/**
 * Created by mehdibouhafs on 10/07/2017.
 */
public class SpinnerAdapter<T> extends ArrayAdapter<T> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<T> values;
    private int res;

    public SpinnerAdapter(Context context, int textViewResourceId,
                          ArrayList<T> values) {
        super(context, textViewResourceId, values);
        this.res=textViewResourceId;
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.size();
    }

    public T getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View v=inflater.inflate(res,parent,false);
        TextView nom=(TextView) v.findViewById(R.id.t_nom);
        nom.setTextColor(Color.BLACK);
        T e=values.get(position);
        if(e instanceof Intervenant){
            Log.e("Intervenant","");
            //id.setText(String.valueOf(((Intervenant) e).getId()));
            nom.setText(((Intervenant) e).getNomIntervenant());
        }else if(e instanceof Client){
            nom.setText(((Client) e).getClient());
        }else if(e instanceof Produit){
            nom.setText(((Produit) e).getModele());
        }else if(e instanceof Site) {
            nom.setText(((Site) e).getSite());
        }else if(e instanceof User) {
            nom.setText(((User) e).getName());
        }


        return nom;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(res, parent, false);
        TextView nom = (TextView) v.findViewById(R.id.t_nom);
        T e = values.get(position);
        if (e instanceof Intervenant) {
            Log.e("Intervenant", "");
            //id.setText(String.valueOf(((Intervenant) e).getId()));
            nom.setText(((Intervenant) e).getNomIntervenant() + "SITE EEEEE");
        } else if (e instanceof Client) {
            nom.setText(((Client) e).getClient() + "SITE EEEEE");
        } else if (e instanceof Produit) {
            nom.setText(((Produit) e).getModele()+ "SITE EEEEE");
        } else if (e instanceof Site) {
            nom.setText(((Site) e).getSite() + "SITE EEEEE");

        }
        return nom;
    }



}
