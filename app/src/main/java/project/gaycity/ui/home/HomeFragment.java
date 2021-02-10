package project.gaycity.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.gaycity.R;
import project.gaycity.ui.resources.ResourcesDatabaseFragment;
import project.gaycity.ui.resources.gridAdapter;

public class HomeFragment extends Fragment {

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        new getEvents().execute();
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button aboutButton = (Button) view.findViewById(R.id.button_mission);
        FragmentManager fm  = getFragmentManager();
        View.OnClickListener dialogue = new View.OnClickListener() {
            public void onClick(View v) {
                aboutDialogue alertDialog = aboutDialogue.newInstance();
                alertDialog.show(fm, "fragment_alert");
            }
        };
        aboutButton.setOnClickListener(dialogue);
        Button donateButton = (Button) getView().findViewById(R.id.button_donate);
        View.OnClickListener donateLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.flipcause.com/hosted_widget/hostedWidgetHome/MjQyMzE=");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        donateButton.setOnClickListener(donateLink);
        Button appointmentButton = (Button) getView().findViewById(R.id.button_appointment);
        View.OnClickListener appointmentLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://gaycity.as.me/schedule.php?appointmentType=category%3AHIV%2FSTI+Testing");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        appointmentButton.setOnClickListener(appointmentLink);
    }

    private class getEvents extends AsyncTask<Void, Void, JSONObject> {

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
            JSONArray events = null;
            try {
                events = (JSONArray) result.get("Events");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RecyclerView recyclerView = root.findViewById(R.id.eventRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            eventAdapter adapter = new eventAdapter(root.getContext(), events, getFragmentManager());
            recyclerView.setAdapter(adapter);
            Animation fadeIn = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
            recyclerView.startAnimation(fadeIn);
            recyclerView.setVisibility(View.VISIBLE);

        }

        private String getEvents(){
            //when events are added this function will bve filled out, for now it just returns a set string
            String json = "{\"Events\":[{\"title\": \"neat event\", \"startDate\": \"February 17\",\"startTime\": \"5:00 PM\"},{\"title\": \"QCC: Using Self-Determination and Peer-Led Advocacy to Improve Health Equity Outcomes for BIPOC LGTBQ Youth\", \"startDate\": \"February 17\",\"startTime\": \"5:00 PM\"}]}";
            return json;
        }
    }

}