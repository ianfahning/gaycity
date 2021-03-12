package project.gaycity.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.gaycity.R;
import project.gaycity.eventAdapter;
import project.gaycity.ui.webviewDialogue;

public class HomeFragment extends Fragment {

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        new getEvents().execute();
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //set the onclick for the buttons
        Button aboutButton = (Button) view.findViewById(R.id.button_mission);
        FragmentManager fm = getFragmentManager();
        View.OnClickListener dialogue = v -> {
            aboutDialogue alertDialog = aboutDialogue.newInstance();
            alertDialog.show(fm, "fragment_alert");
        };
        aboutButton.setOnClickListener(dialogue);
        Button donateButton = (Button) getView().findViewById(R.id.button_donate);
        View.OnClickListener donateLink = v -> {
            Uri uriUrl = Uri.parse("https://www.flipcause.com/hosted_widget/hostedWidgetHome/MjQyMzE=");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        donateButton.setOnClickListener(donateLink);
        Button appointmentButton = (Button) getView().findViewById(R.id.button_appointment);
        View.OnClickListener appointmentLink = v -> {
            webviewDialogue alertDialog = webviewDialogue.newInstance("https://gaycity.as.me/schedule.php?appointmentType=category%3AHIV%2FSTI+Testing");
            alertDialog.show(fm, "fragment_alert");
        };
        appointmentButton.setOnClickListener(appointmentLink);
    }

    private class getEvents extends AsyncTask<Void, Void, JSONObject> {

        //returns the event in JSON format
        @Override
        protected JSONObject doInBackground(Void... voids) {

            JSONObject json = null;
            try {
                json = new JSONObject(getEvents());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            JSONObject events = null;
            try {
                events = (JSONObject) result.get("content_json");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            root.findViewById(R.id.loading).setVisibility(View.GONE);
            RecyclerView recyclerView = root.findViewById(R.id.eventRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            //if there are no events display the no events nexts
            if(events == null){
                root.findViewById(R.id.text_no_events).setVisibility(View.VISIBLE);
            }
            eventAdapter adapter = new eventAdapter(root.getContext(), events, getFragmentManager());
            recyclerView.setAdapter(adapter);
            Animation fadeIn = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
            recyclerView.startAnimation(fadeIn);
            recyclerView.setVisibility(View.VISIBLE);
        }

        //gets and returns the events in JSON format as a String
        private String getEvents(){
            StringBuilder sb = null;
            try {
                URL url = new URL("https://www.gaycity.org/wp-json/mecexternal/v1/calendar/12175");

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