package munisys.net.ma.munisysinventory;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import munisys.net.ma.munisysinventory.adapters.MainMenuAdapter;
import munisys.net.ma.munisysinventory.entities.Session;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    protected FrameLayout frameLayout;
    protected int activityId;
    protected Session session;

    static final String[] titles = new String[] {
            "New Inventaire","All Inventaire",
            "New Intervenant","All Intervenant",
            "New Client","Parametres","Help"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        session = new Session(this);
        if(!session.loggedIn()){
            logout();
        }

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        activityId = R.layout.content_main;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();



    }

    private void logout() {
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent intent;
                switch (id){
                    case R.id.home:

                        //Toast.makeText(getApplicationContext(),"We are already in home",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);

                        break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.newInventaire:
                        Toast.makeText(getApplicationContext(),"new inventaire",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        intent = new Intent(getApplicationContext(),FirstActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.allInventaire:
                        Toast.makeText(getApplicationContext(),"all inventaire",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.newIntervenant:
                        Toast.makeText(getApplicationContext(),"new Intervenants",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.allintervenant:
                        Toast.makeText(getApplicationContext(),"All intervenants",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.help:
                        Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        finish();
                }
                return true;
            }
        });


        View header = navigationView.getHeaderView(0);


        TextView name_user = (TextView)header.findViewById(R.id.name_user);
        name_user.setText(session.getNameUser());
        Button logout = (Button) header.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            /*case R.id.action_save:
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

  /*  @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_home, menu);

        MenuItem setting = menu.findItem(R.id.action_settings);

        return true;
    }
}
