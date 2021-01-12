package project.gaycity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.gaycity.ui.connect.ConnectFragment;
import project.gaycity.ui.getInvolved.GiveFragment;
import project.gaycity.ui.getInvolved.VolunteerFragment;
import project.gaycity.ui.getInvolved.VoteFragment;
import project.gaycity.ui.health.AppointmentFragment;
import project.gaycity.ui.health.HealthCareFragment;
import project.gaycity.ui.health.PrepFragment;
import project.gaycity.ui.health.TestResultsFragment;
import project.gaycity.ui.home.HomeFragment;
import project.gaycity.ui.resources.ORCAFragment;
import project.gaycity.ui.resources.OutreachFragment;
import project.gaycity.ui.resources.QCCFragment;
import project.gaycity.ui.resources.ResourcesDatabaseFragment;
import project.gaycity.ui.resources.TechnicalFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private List<MenuModel> headerList = new ArrayList<>();
    private HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private FragmentManager fm;
    private DrawerLayout drawer;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.nav_host_fragment, new HomeFragment(), "new").commit();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        expandableListView = findViewById(R.id.expandableListView);
        populateMenu();
        populateExpandableList();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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
        MenuModel menuModel = new MenuModel("Home", R.id.fragment_home,true,false);
        headerList.add(menuModel);
        childList.put(menuModel,null);
        //health
        menuModel = new MenuModel("Health", -1, true,true);
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Make an Appointment", R.id.fragment_appointment,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Get Test Results",R.id.fragment_test_results,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("PrEP", R.id.fragment_prep,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Health Care Enrollment", R.id.fragment_health_care,false,false);
        childModelsList.add(childModel);
        childList.put(menuModel,childModelsList);
        //resources
        menuModel = new MenuModel("Resources", -1, true,true);
        headerList.add(menuModel);
        childModelsList = new ArrayList<>();
        childModel = new MenuModel("Resources Database", R.id.fragment_resources_database,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Queer Community Conversations",R.id.fragment_qcc,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("ORCA LIFT", R.id.fragment_orca,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Outreach", R.id.fragment_outreach,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Technical Assistance", R.id.fragment_technical,false,false);
        childModelsList.add(childModel);
        childList.put(menuModel,childModelsList);
        //Get Involved
        menuModel = new MenuModel("Get Involved", -1, true,true);
        headerList.add(menuModel);
        childModelsList = new ArrayList<>();
        childModel = new MenuModel("Volunteer", R.id.fragment_volunteer,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Vote for Visibility",R.id.fragment_vote,false,false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Ways to Give", R.id.fragment_give,false,false);
        childModelsList.add(childModel);
        childList.put(menuModel,childModelsList);
        //contact us
        menuModel = new MenuModel("Connect with Us", R.id.fragment_connect,true,false);
        headerList.add(menuModel);
        childList.put(menuModel,null);


    }
    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                MenuModel menuItem = headerList.get(groupPosition);
                if (menuItem.isGroup) {
                    if (!menuItem.hasChildren) {
                        fm.beginTransaction().replace(R.id.nav_host_fragment,findFragment(menuItem.view),null).commit();
                        drawer.close();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    fm.beginTransaction().replace(R.id.nav_host_fragment,findFragment(model.view),null).commit();
                    drawer.close();
                }

                return false;
            }
        });
    }

    private void switchFragments(){

    }

    private Fragment findFragment(int num){
        switch(num){
            case R.id.fragment_home:
                return new HomeFragment();
            case R.id.fragment_appointment:
                return new AppointmentFragment();
            case R.id.fragment_test_results:
                return new TestResultsFragment();
            case R.id.fragment_prep:
                return new PrepFragment();
            case R.id.fragment_health_care:
                return new HealthCareFragment();
            case R.id.fragment_resources_database:
                return new ResourcesDatabaseFragment();
            case R.id.fragment_qcc:
                return new QCCFragment();
            case R.id.fragment_orca:
                return new ORCAFragment();
            case R.id.fragment_outreach:
                return new OutreachFragment();
            case R.id.fragment_technical:
                return new TechnicalFragment();
            case R.id.fragment_volunteer:
                return new VolunteerFragment();
            case R.id.fragment_vote:
                return new VoteFragment();
            case R.id.fragment_give:
                return new GiveFragment();
            case R.id.fragment_connect:
                return new ConnectFragment();
        }
        return new HomeFragment();
    }
}