package munisys.net.ma.munisysinventory.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import munisys.net.ma.munisysinventory.R;

/**
 * Created by mehdibouhafs on 07/07/2017.
 */

public class MainMenuAdapter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;

    public MainMenuAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.mainmenu, null);


            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String title = mobileValues[position];


            //Log.e("Title Actual = " , title + " pos " +position);

            switch (title){
                case "New Inventaire" :imageView.setImageResource(R.drawable.listeinventaire); break;
                case "All Inventaire" : imageView.setImageResource(R.drawable.allinventaire);break;
                case "New Intervenant" : imageView.setImageResource(R.drawable.newintervenant);break;
                case "All Intervenant" : imageView.setImageResource(R.drawable.allintervenant);break;
                case "New Client" : imageView.setImageResource(R.drawable.client);break;
                case "Parametres" : imageView.setImageResource(R.drawable.setting256);break;
                case "Help" : imageView.setImageResource(R.drawable.help);break;
                default:
                    break;
            }
            notifyDataSetChanged();




        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return this.mobileValues.length;
    }

    @Override
    public String getItem(int position) {
        return this.mobileValues[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
