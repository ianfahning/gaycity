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
import project.gaycity.ui.home.aboutDialogue;

public class gridAdapter extends RecyclerView.Adapter<gridAdapter.ViewHolder> {

    private JSONArray mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private FragmentManager fm;

    // data is passed into the constructor
    gridAdapter(Context context, JSONArray data,FragmentManager fm) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.fm = fm;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_resource_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject resource = ((JSONObject)mData.get(position));
            holder.addResource(resource);
            String Organization = resource.getString("Organization");
            String Communities = resource.getString("Communities");
            String basicNeeds = resource.getString("basicNeeds");
            String website = resource.getString("website");
            if(Organization.equals("undefined")){
                holder.website.setText("-");
            }else{
                holder.title.setText(shortenTitle(resource.getString("Organization")));
            }
            if(Communities.equals("undefined")){
                holder.website.setText("-");
            }else{
                holder.communities.setText(shortenTitle(resource.getString("Communities")));
            }
            if(basicNeeds.equals("undefined")){
                holder.website.setText("-");
            }else{
                holder.basicNeeds.setText(shortenTitle(resource.getString("basicNeeds")));
            }
            if(website.equals("undefined")){
                holder.website.setText("-");
            }else{
                holder.website.setText(shortenTitle(resource.getString("website")));
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
            resourceDialogue alertDialog = resourceDialogue.newInstance(resource);
            alertDialog.show(fm, "fragment_alert");
        }

        public void addResource(JSONObject resource) {
            this.resource = resource;
        }
    }

    // convenience method for getting data at click position
    JSONObject getItem(int id) {
        try {
            return (JSONObject) mData.get(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
