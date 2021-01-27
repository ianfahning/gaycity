package project.gaycity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.gaycity.ui.header;
import project.gaycity.ui.home.HomeFragment;
import project.gaycity.ui.subHeader;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private FragmentManager fm;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.nav_host_fragment, new HomeFragment(), "new").commit();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        recyclerView = this.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        populateMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void populateMenu(){
        //home
        ArrayList<header> headers = new ArrayList<>();
        ArrayList<subHeader> none = new ArrayList<>();
        headers.add(new header("Home",none,false, R.id.fragment_home));
        //health
        ArrayList<subHeader> healthSubHeaders = new ArrayList<>();
        healthSubHeaders.add(new subHeader("Make and Appointment",R.id.fragment_appointment,true));
        healthSubHeaders.add(new subHeader("Get Test Results",R.id.fragment_test_results,true));
        healthSubHeaders.add(new subHeader("Health Care Enrollment",R.id.fragment_health_care,true));
        healthSubHeaders.add(new subHeader("PrEP",R.id.fragment_prep,true));
        headers.add(new header("Health",healthSubHeaders,true, 0));

        ArrayList<subHeader> resourcesSubHeaders = new ArrayList<>();
        resourcesSubHeaders.add(new subHeader("Resource Database",R.id.fragment_resources_database,true));
        resourcesSubHeaders.add(new subHeader("ORCA LIFT",R.id.fragment_orca,true));
        resourcesSubHeaders.add(new subHeader("Outreach",R.id.fragment_outreach,true));
        resourcesSubHeaders.add(new subHeader("Technical Training",R.id.fragment_technical,true));
        resourcesSubHeaders.add(new subHeader("Queer Community Conversations",R.id.fragment_qcc,true));
        headers.add(new header("Resources",resourcesSubHeaders,true, 0));

        ArrayList<subHeader> getInvolvedSubHeaders = new ArrayList<>();
        getInvolvedSubHeaders.add(new subHeader("Volunteer",R.id.fragment_volunteer,true));
        getInvolvedSubHeaders.add(new subHeader("Vote For Visibility",R.id.fragment_vote,true));
        getInvolvedSubHeaders.add(new subHeader("Ways to Give",R.id.fragment_give,true));
        headers.add(new header("Get Involved",getInvolvedSubHeaders,true, 0));

        headers.add(new header("Connect with Us",none,false, R.id.fragment_connect));
        expandableMenuAdapter adapter = new expandableMenuAdapter(headers,fm,recyclerView,drawer);
        recyclerView.setAdapter(adapter);
    }

}