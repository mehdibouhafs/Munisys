package munisys.net.ma.munisysinventory;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import munisys.net.ma.munisysinventory.adapters.SpinnerAdapter;
import munisys.net.ma.munisysinventory.dao.Db_gest;
import munisys.net.ma.munisysinventory.entities.Intervenant;
import munisys.net.ma.munisysinventory.entities.User;

public class FirstActivity extends HomeActivity {

    private Button confirmerIntervenant;
    private SearchableSpinner selectorIntervenants;
    private ArrayList<User> users;
    private Db_gest db;
    private Toolbar toolbar;
    private ImageButton newIntervenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_first);

       View view = getLayoutInflater().inflate(R.layout.content_first, frameLayout);
        setTitle("Select intervenant");
        activityId = R.layout.content_first;




        newIntervenant = (ImageButton) findViewById(R.id.newIntervenant);
        newIntervenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(FirstActivity.this);
                dialog.setContentView(R.layout.add_invetervenant);

                EditText input_client = (EditText) dialog.findViewById(R.id.input_intervenant);
                Button btn_creer = (Button)dialog.findViewById(R.id.btn_create_intervenant);
                Button btn_annuler = (Button)dialog.findViewById(R.id.btn_annuler);
                btn_annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        db = new Db_gest(this,16);


        users = db.getALLUser();

        confirmerIntervenant = (Button) view.findViewById(R.id.validerIntervenant);
        selectorIntervenants = (SearchableSpinner) view.findViewById(R.id.selectedIntervenant);
        selectorIntervenants.setAdapter(new SpinnerAdapter(this,R.layout.model,users));


       confirmerIntervenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondeActivity.class);
                Bundle bundle = new Bundle();
                Intervenant intervenant = new Intervenant("mehdi");
                bundle.putSerializable("intervenant",intervenant);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_home, menu);

        MenuItem search = menu.findItem(R.id.action_settings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
           /* case R.id.action_save:
                save();
                return true;
            case R.id.action_delete:
                delete();
                return true;
            case R.id.action_settings:
                return true;*/
        }

        return super.onOptionsItemSelected(item);
    }


}
