package munisys.net.ma.munisysinventory.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import munisys.net.ma.munisysinventory.R;
import munisys.net.ma.munisysinventory.entities.Inventaire;
import munisys.net.ma.munisysinventory.entities.Produit;

/**
 * Created by mehdibouhafs on 28/03/2017.
 */

public class AdaptorForInventory extends RecyclerView.Adapter<AdaptorForInventory.MyViewHolder> implements Filterable {

    ArrayList<Inventaire> list_don;
    ArrayList<Inventaire>  list_don_filtred;
    Context ctx;


    public AdaptorForInventory(ArrayList<Inventaire> list_don,Context ctx) {

        this.list_don = list_don;
        this.ctx = ctx;
        this.list_don_filtred = list_don;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.modelinventory,
                parent,
                false);
        MyViewHolder vh = new MyViewHolder(v);

        return  vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Inventaire inventaire = list_don_filtred.get(position);
        holder.client.setText("Client : "+inventaire.getClient().getClient());
        holder.lastUpdate.setText("Last update :"+inventaire.getDateInventaire());
        try {
            fileToImageView(inventaire.getClient().getLogo(),holder.logo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.lastUpdate.setText("Date: "+dateFormat.format(inventaire.getDateInventaire()));
    }

    @Override
    public int getItemCount() {
        return list_don_filtred.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {

                    list_don_filtred = list_don;
                } else {

                    ArrayList<Inventaire> filteredList = new ArrayList<>();

                    for (Inventaire inventaire : list_don) {

                        if (inventaire.getClient().getClient().toLowerCase().contains(charString)) {
                            filteredList.add(inventaire);
                        }
                    }

                    list_don_filtred = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list_don_filtred;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    list_don_filtred = (ArrayList<Inventaire>) results.values;
                    notifyDataSetChanged();
            }
        };
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView client;
        TextView lastUpdate;
        ImageView logo;



        public MyViewHolder(View v) {
            super(v); // done this way instead of view tagging
             this.client = (TextView) v.findViewById(R.id.client);
             this.lastUpdate = (TextView) v.findViewById(R.id.lastupdate);
             this.logo = (ImageView) v.findViewById(R.id.logo);

        }
    }

    public void fileToImageView(String filePath,ImageView logo) throws IOException {

        InputStream inputStream = ctx.getContentResolver().openInputStream(Uri.parse(filePath));
        Bitmap bmp = BitmapFactory.decodeStream(inputStream);
        if( inputStream != null ) inputStream.close();
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        logo.setImageBitmap(bmp);

    }
}
