package project.gaycity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import project.gaycity.ui.header;
import project.gaycity.ui.home.HomeFragment;
import project.gaycity.ui.subHeader;

import static java.text.DateFormat.getDateTimeInstance;

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
        new getPopup().execute();
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


    private class getPopup extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... voids) {

            JSONObject json = null;
            try {
                json = new JSONObject(getPopup());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            JSONObject popup = null;
            try {
                popup = (JSONObject) result.get("content_json");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Iterator<String> keys = popup.keys();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = formatter.format(date);
            System.out.println(currentDate);
            boolean popupFound = false;
            while(keys.hasNext() && !popupFound){
                String key = keys.next();
                if(key.equals(currentDate)){
                    popupFound = true;
                }
            }
            if(popupFound){
                popupDialogue popupDialog = null;
                try {
                    popupDialog = popupDialogue.newInstance((JSONObject) ((JSONArray)popup.get(currentDate)).get(0),fm);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                popupDialog.show(fm, "fragment_alert");
            }
        }

        private String getPopup(){
            StringBuilder sb = null;
            try {
                URL url = new URL("https://www.gaycity.org/wp-json/mecexternal/v1/calendar/12426");

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);

                urlConnection.setDoOutput(true);

                urlConnection.connect();

                BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

                char[] buffer = new char[1024];


                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();

        }
    }

}