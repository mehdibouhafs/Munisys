package munisys.net.ma.munisysinventory.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.R;
import munisys.net.ma.munisysinventory.entities.Intervenant;


/**
 * Created by user on 20/04/2017.
 */

public class Adp_Entities<T> extends ArrayAdapter<T> {
    int res;
    Context con;
    ArrayList<T> arl;


    public Adp_Entities(Context context, int resource, ArrayList<T> objects) {
        super(context, resource, objects);

        this.res=resource;
        this.con=context;
        this.arl=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity) con).getLayoutInflater();

        View v=inflater.inflate(res,parent,false);

        //TextView id=(TextView) v.findViewById(R.id.t_id);
        TextView nom=(TextView) v.findViewById(R.id.t_nom);

        T e=arl.get(position);

        if(e instanceof Intervenant){
            Log.e("Intervenant","");
            //id.setText(String.valueOf(((Intervenant) e).getId()));
            nom.setText(((Intervenant) e).getNomIntervenant());
        }

        return v;
    }
}
