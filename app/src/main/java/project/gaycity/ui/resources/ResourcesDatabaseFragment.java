package project.gaycity.ui.resources;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.gaycity.R;

public class ResourcesDatabaseFragment extends Fragment {
    private View root;
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

        //returns the resources in JSON format
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
            //if result is not null, proceed normally, otherwise don't and display network error
            if(result != null){
                try {
                    //removes the loading symbol
                    root.findViewById(R.id.loading).setVisibility(View.GONE);
                    JSONArray resources = (JSONArray) result.get("resources");
                    RecyclerView recyclerView = root.findViewById(R.id.resources);
                    //fade in the recyclerView
                    Animation fadeIn = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
                    recyclerView.startAnimation(fadeIn);
                    recyclerView.setVisibility(View.VISIBLE);
                    int numberOfColumns = 2;
                    GridLayoutManager grid = new GridLayoutManager(root.getContext(), numberOfColumns);

                    //sets up the back to top arrow
                    ImageView topIcon = (ImageView) getView().findViewById(R.id.image_back_to_top);
                    setUpBackToTopArrow(recyclerView, topIcon);

                    recyclerView.setLayoutManager(grid);
                    gridAdapter adapter = new gridAdapter(root.getContext(), resources, getFragmentManager());
                    recyclerView.setAdapter(adapter);
                    //connects the search bar with the filter
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
            } else {
                root.findViewById(R.id.loading).setVisibility(View.GONE);
                root.findViewById(R.id.resource_error).setVisibility(View.VISIBLE);
            }
        }

        //sets up an arrow at the top of the screen that when clicked brings you back to the top
        //the arrow only shows up when you scroll up
        private void setUpBackToTopArrow(RecyclerView recyclerView, ImageView topIcon) {
            View.OnClickListener topLink = v -> {
                doScroll = false; //stop the onScroll from making button visible
                //turn on onScroll again after scrolled to top
                new Handler().postDelayed(() -> doScroll = true, 1000);
                //return to top of view and then fade out the arrow and set it to invisible
                recyclerView.smoothScrollToPosition(0);
                Animation fadeOut = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_out);
                topIcon.startAnimation(fadeOut);
                topIcon.setVisibility(View.INVISIBLE);
                position = 0;
            };
            topIcon.setOnClickListener(topLink);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //if the button has been pressed, doScroll will be false and it wont run this
                    if (doScroll) {
                        position += dy; //keeps track of the position of the view
                        //if scrolling up make it visible
                        if (dy < -2 && !hasApeared && topIcon.getVisibility() != View.VISIBLE) {
                            Animation fadeIn = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
                            topIcon.startAnimation(fadeIn);
                            topIcon.setVisibility(View.VISIBLE);
                            hasApeared = true; //makes sure this only runs once when scrolling up
                        }
                        //if scrolling down or the view is has scrolled to the top set it to invisible
                        if ((dy > 2 && !hasApeared && topIcon.getVisibility() != View.INVISIBLE) || (position <= 0 && topIcon.getVisibility() != View.INVISIBLE)) {
                            Animation fadeOut = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_out);
                            topIcon.startAnimation(fadeOut);
                            topIcon.setVisibility(View.INVISIBLE);
                            hasApeared = true; //makes sure this only runs once when scrolling down
                        }
                    }
                }

                //resets has appeared when ever the scroll changes direction to allow the button to change again
                //this stops the button from fading in or out constantly when scrolling one direction
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    hasApeared = false;
                }
            });
        }

        //returns resources in JSON format as a String
        private String getResources() {
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