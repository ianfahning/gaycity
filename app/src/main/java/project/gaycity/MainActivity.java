package project.gaycity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import project.gaycity.ui.header;
import project.gaycity.ui.home.HomeFragment;
import project.gaycity.ui.subHeader;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recyclerView;
    private static FragmentManager fm;
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        recyclerView = this.findViewById(R.id.recyclerView);
        //stops the nav drawer from scrolling
        recyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        //populates the nav drawer menu with all the pages
        populateMenu();
        //gets the current popup if there is one
        new getPopup().execute();
        System.out.println("google play services = " + String.valueOf(checkGooglePlayServices()));
        FirebaseMessaging.getInstance().getToken();

    }

    private boolean checkGooglePlayServices() {
        // 1
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        // 2
        if (status != ConnectionResult.SUCCESS) {
            Log.e("TAG", "Error");
            // ask user to update google play services and manage the error.
            return false;
        } else {
            // 3
            Log.i("TAG", "Google play services updated");
            return true;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void populateMenu() {
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
        //resources
        ArrayList<subHeader> resourcesSubHeaders = new ArrayList<>();
        resourcesSubHeaders.add(new subHeader("Resource Database",R.id.fragment_resources_database,true));
        resourcesSubHeaders.add(new subHeader("ORCA LIFT",R.id.fragment_orca,true));
        resourcesSubHeaders.add(new subHeader("Outreach",R.id.fragment_outreach,true));
        resourcesSubHeaders.add(new subHeader("Technical Training",R.id.fragment_technical,true));
        resourcesSubHeaders.add(new subHeader("Queer Community Conversations",R.id.fragment_qcc,true));
        headers.add(new header("Resources",resourcesSubHeaders,true, 0));
        //get involved
        ArrayList<subHeader> getInvolvedSubHeaders = new ArrayList<>();
        getInvolvedSubHeaders.add(new subHeader("Volunteer",R.id.fragment_volunteer,true));
        getInvolvedSubHeaders.add(new subHeader("Vote For Visibility",R.id.fragment_vote,true));
        getInvolvedSubHeaders.add(new subHeader("Ways to Give",R.id.fragment_give,true));
        headers.add(new header("Get Involved",getInvolvedSubHeaders,true, 0));
        //connect with us
        headers.add(new header("Connect with Us",none,false, R.id.fragment_connect));
        expandableMenuAdapter adapter = new expandableMenuAdapter(headers, fm, drawer);
        recyclerView.setAdapter(adapter);
    }


    private static class getPopup extends AsyncTask<Void, Void, popupDialogue> {

        @Override
        protected popupDialogue doInBackground(Void... voids) {

            JSONObject json = null;
            try {
                json = new JSONObject(getPopupData());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                //if content_json is an array it means that there are no events
                if (json.get("content_json").getClass() != JSONArray.class) {
                    JSONObject popupData = null;
                    try {
                        popupData = (JSONObject) json.get("content_json");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Iterator<String> keys = popupData.keys();
                    Date currentDate = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    boolean popupFound = false;
                    Date eventStartDate = null;
                    //looks for the current popup if there is one
                    while (keys.hasNext() && !popupFound) {
                        String key = keys.next();
                        try {
                            //converts the key to a date
                            eventStartDate = formatter.parse(key);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //finds the current popup
                        popupFound = findPopup(popupData, key, eventStartDate, currentDate, formatter);
                    }
                    if (popupFound) {
                        return createPopup(popupData, formatter, eventStartDate);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        //checks if the the current date is inbetween the start and end date of the event
        private boolean findPopup(JSONObject popupData, String key, Date eventStartDate, Date currentDate, SimpleDateFormat formatter) {
            //checks if event start date is before current date
            if (eventStartDate.before(currentDate)) {
                Date eventEndDate = null;
                try {
                    //gets the event end date from the data
                    eventEndDate = formatter.parse(((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) popupData.get(key)).get(0)).get("date")).get("end")).getString("date"));
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
                //returns true if the end date is after the current date
                return eventEndDate.after(currentDate);
            }
            return false;
        }

        //creates and returns the popup given the JSON data
        private popupDialogue createPopup(JSONObject popupData, SimpleDateFormat formatter, Date eventStartDate) {
            popupDialogue popup = null;
            try {
                String imageLink = "";
                try {
                    //gets the Image link for a custom image on the popup
                    imageLink = ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) popupData.get(formatter.format(eventStartDate))).get(0)).get("data")).get("featured_image")).getString("full");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Bitmap image = null;
                //if there is a link then get the image
                if (!imageLink.equals("")) {
                    image = getBitmapFromURL(imageLink);
                }
                //creates the popup
                popup = popupDialogue.newInstance((JSONObject) ((JSONArray) popupData.get(formatter.format(eventStartDate))).get(0), fm, image);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return popup;
        }

        //shows the dialogue when everything is done
        @Override
        protected void onPostExecute(popupDialogue popup) {
            if (popup != null) {
                popup.show(fm, "fragment_alert");
            }
        }

        //gets an image from a URL and returns it as a Bitmap
        public Bitmap getBitmapFromURL(String src) {
            try {
                java.net.URL url = new java.net.URL(src);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        //gets the popup data in JSON format and returns it as a String
        private String getPopupData() {
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


                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();

        }
    }

}