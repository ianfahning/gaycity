package project.gaycity.ui.resources;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import project.gaycity.ui.home.HomeFragment;

public class QCCFragment extends Fragment {

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_qcc, container, false);
        new getEvents().execute();
        return root;
    }

    private class getEvents extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... voids) {

            JSONObject json = null;
            try {
                json = new JSONObject(getEvents());
                System.out.println(json);
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
            if(events == null){
                root.findViewById(R.id.text_no_events).setVisibility(View.VISIBLE);
            }
            eventAdapter adapter = new eventAdapter(root.getContext(), events, getFragmentManager());
            recyclerView.setAdapter(adapter);
            Animation fadeIn = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
            recyclerView.startAnimation(fadeIn);
            recyclerView.setVisibility(View.VISIBLE);

        }

        private String getEvents(){
            StringBuilder sb = null;
            try {
                URL url = new URL("https://www.gaycity.org/wp-json/mecexternal/v1/calendar/12160");

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