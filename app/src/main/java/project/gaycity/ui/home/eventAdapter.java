package project.gaycity.ui.home;

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
import project.gaycity.ui.resources.resourceDialogue;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.ViewHolder> {

    private JSONArray mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private FragmentManager fm;

    // data is passed into the constructor
    eventAdapter(Context context, JSONArray data, FragmentManager fm) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.fm = fm;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.event_layout, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject event = ((JSONObject)mData.get(position));
            holder.addEvent(event);
            String title = event.getString("title");
            String startDate = event.getString("startDate");
            String startTime = event.getString("startTime");
            holder.title.setText(title);
            holder.date.setText(startDate + " " + startTime);
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
        TextView date;
        JSONObject event;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.event_title);
            date = itemView.findViewById(R.id.event_start);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            eventDialogue alertDialog = eventDialogue.newInstance(event);
            alertDialog.show(fm, "fragment_alert");
        }

        public void addEvent(JSONObject event) {
            this.event = event;
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
