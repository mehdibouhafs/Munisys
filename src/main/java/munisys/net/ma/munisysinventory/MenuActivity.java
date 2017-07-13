package munisys.net.ma.munisysinventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import munisys.net.ma.munisysinventory.HomeActivity;
import munisys.net.ma.munisysinventory.R;
import munisys.net.ma.munisysinventory.SecondeActivity;
import munisys.net.ma.munisysinventory.adapters.MainMenuAdapter;

public class MenuActivity extends HomeActivity {

    private Button confirmerIntervenant;
    private SearchableSpinner selectorIntervenants;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_first);

       View view = getLayoutInflater().inflate(R.layout.content_main, frameLayout);
        setTitle("Home");

        GridView grid = (GridView) view.findViewById(R.id.grid);
        grid.setAdapter(new MainMenuAdapter(this,titles));


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {

                //startActivity(i); // Specify activity through Intent i
                Intent intent;
                switch (position){
                    case 0 : intent = new Intent(getApplicationContext(),FirstActivity.class);startActivity(intent); break;
                    case 1 : intent = new Intent(getApplicationContext(),InventoriesActivity.class);startActivity(intent);break;
                    case 2 : intent = new Intent(getApplicationContext(),FirstActivity.class);startActivity(intent);break;
                    case 3 :intent = new Intent(getApplicationContext(),FirstActivity.class);startActivity(intent);break;
                    case 4 : intent = new Intent(getApplicationContext(),FirstActivity.class);startActivity(intent);break;
                    case 5 :intent = new Intent(getApplicationContext(),FirstActivity.class);startActivity(intent);break;
                    case 6 : intent = new Intent(getApplicationContext(),FirstActivity.class);startActivity(intent);break;

                }

            }
        });


    }




}
