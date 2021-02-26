package project.gaycity.ui.resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.gaycity.R;

public class gridAdapter extends RecyclerView.Adapter<gridAdapter.ViewHolder> {

    private final JSONArray mDataCopy; //copy of the data so it can be restored when there is a filter
    private final LayoutInflater mInflater;
    private final FragmentManager fm;
    private JSONArray mData;//the data

    // data is passed into the constructor
    gridAdapter(Context context, JSONArray data, FragmentManager fm) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mDataCopy = data;
        this.fm = fm;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_resource_layout, parent, false);
        return new ViewHolder(view);
    }

    //filters the data based on the query
    public void filter(String query) {
        //clear the current data
        mData = new JSONArray();
        //if there is no query then replace the data with the copy
        if(query.isEmpty()){
            mData = mDataCopy;
        } else{
            query = query.toLowerCase();
            //find if there is an occurrence of the query in either organization, communities, or basic needs
            for(int i = 0; i < mDataCopy.length(); i++){
                try {
                    if(((JSONObject)mDataCopy.get(i)).getString("Organization").toLowerCase().contains(query)){
                        mData.put(mDataCopy.get(i));
                    }
                    if(((JSONObject)mDataCopy.get(i)).getString("Communities").toLowerCase().contains(query)){
                        mData.put(((JSONObject)mDataCopy.get(i)));
                    }
                    if(((JSONObject)mDataCopy.get(i)).getString("basicNeeds").toLowerCase().contains(query)){
                        mData.put(((JSONObject)mDataCopy.get(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        notifyDataSetChanged();
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject resource = ((JSONObject)mData.get(position));
            //hand the holder the data for when it is clicked and needs to make a dialog
            holder.addResource(resource);
            //get all data from the JSON
            String Organization = resource.getString("Organization");
            String Communities = resource.getString("Communities");
            String basicNeeds = resource.getString("basicNeeds");
            String website = resource.getString("website");
            //if the data does not exist make make it a -, otherwise shorten it
            if(Organization.equals("undefined")){
                holder.title.setText("-");
            }else{
                holder.title.setText(shortenTitle(Organization));
            }
            if(Communities.equals("undefined")){
                holder.communities.setText("-");
            }else{
                holder.communities.setText(shortenTitle(Communities));
            }
            if(basicNeeds.equals("undefined")){
                holder.basicNeeds.setText("-");
            }else{
                holder.basicNeeds.setText(shortenTitle(basicNeeds));
            }
            if(website.equals("undefined")){
                holder.website.setText("-");
            }else{
                holder.website.setText(shortenTitle(website));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length();
    }

    //shortens a string if it needs to and adds an ellipses
    public String shortenTitle(String title){
        if(title.length() > 18){
            title = title.substring(0,15) + "...";
        }else{
            return title;
        }
        return title;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView communities;
        TextView basicNeeds;
        TextView website;
        JSONObject resource;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.resource_title);
            communities = itemView.findViewById(R.id.resource_Communities);
            basicNeeds = itemView.findViewById(R.id.resource_basic);
            website = itemView.findViewById(R.id.resource_website);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //created a dialog to display more information
            resourceDialogue alertDialog = resourceDialogue.newInstance(resource);
            alertDialog.show(fm, "fragment_alert");
        }

        public void addResource(JSONObject resource) {
            this.resource = resource;
        }
    }

}
