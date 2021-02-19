package project.gaycity.ui.resources;

import android.app.Activity;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.gaycity.R;

public class ResourcesDatabaseFragment extends Fragment {
    private View root;
    private ImageView topIcon;
    private boolean hasApeared = true;
    private boolean doScroll = true;
    private double position = 0;

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
            try {
                json = new JSONObject(getResources());
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
                    Animation fadeIn = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
                    recyclerView.startAnimation(fadeIn);
                    recyclerView.setVisibility(View.VISIBLE);
                    int numberOfColumns = 2;
                    GridLayoutManager grid = new GridLayoutManager(root.getContext(), numberOfColumns);
                    topIcon = (ImageView) getView().findViewById(R.id.image_back_to_top);
                    View.OnClickListener topLink = new View.OnClickListener() {
                        public void onClick(View v) {
                            doScroll = false; //stop the onScroll from making button visible
                            new Handler().postDelayed(new Runnable() { //turn on onScroll again after scrolled to top
                                @Override
                                public void run() {
                                    doScroll = true;
                                }
                            }, 1000);
                            recyclerView.smoothScrollToPosition(0);
                            Animation fadeOut = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_out);
                            topIcon.startAnimation(fadeOut);
                            topIcon.setVisibility(View.INVISIBLE);
                            position = 0;
                        }
                    };
                    topIcon.setOnClickListener(topLink);
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (doScroll) {
                                position += dy;
                                if (dy < -2 && !hasApeared && topIcon.getVisibility() != View.VISIBLE) {
                                    Animation fadeIn = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
                                    topIcon.startAnimation(fadeIn);
                                    topIcon.setVisibility(View.VISIBLE);
                                    hasApeared = true;
                                }
                                if ((dy > 2 && !hasApeared && topIcon.getVisibility() != View.INVISIBLE) || (position <= 0 && topIcon.getVisibility() != View.INVISIBLE)) {
                                    Animation fadeOut = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_out);
                                    topIcon.startAnimation(fadeOut);
                                    topIcon.setVisibility(View.INVISIBLE);
                                    hasApeared = true;
                                }
                            }
                        }

                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            hasApeared = false;
                        }
                    });
                    recyclerView.setLayoutManager(grid);
                    gridAdapter adapter = new gridAdapter(root.getContext(), resources, getFragmentManager());
                    recyclerView.setAdapter(adapter);
                    SearchView search = root.findViewById(R.id.search);
                    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            adapter.filter(query);
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            adapter.filter(newText);
                            return true;
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                root.findViewById(R.id.loading).setVisibility(View.GONE);
                root.findViewById(R.id.resource_error).setVisibility(View.VISIBLE);
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

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

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