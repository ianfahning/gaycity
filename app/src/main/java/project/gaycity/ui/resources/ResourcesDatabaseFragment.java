package project.gaycity.ui.resources;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import project.gaycity.R;

public class ResourcesDatabaseFragment extends Fragment {
    private JSONArray resources;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_resources_database, container, false);
        new getResources().execute();
        return root;
    }

    private class getResources extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... voids) {

            JSONObject json = null;
            URL url = null;
            try {
                String resourceString = "";
                int i = 0;
              while(resourceString.length() < 15){
                  resourceString = getResources();
                  i++;
              }
                System.out.println(i);

                json = new JSONObject(resourceString);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            if(result != null){
                try {
                    root.findViewById(R.id.loading).setVisibility(View.GONE);
                    JSONArray resources = (JSONArray) result.get("resources");
                    RecyclerView recyclerView = root.findViewById(R.id.resources);
                    recyclerView.setVisibility(View.VISIBLE);
                    int numberOfColumns = 2;
                    recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), numberOfColumns));
                    gridAdapter adapter = new gridAdapter(root.getContext(), resources, getFragmentManager());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        private String getResources(){
            StringBuilder sb = null;
            try {
            URL url = new URL("https://gay-city-server.herokuapp.com/");

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