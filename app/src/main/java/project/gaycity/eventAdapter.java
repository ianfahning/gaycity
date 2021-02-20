package project.gaycity;

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

import java.util.ArrayList;
import java.util.Iterator;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.ViewHolder> {

    private JSONArray mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private FragmentManager fm;
    private ArrayList<String> JSONkeys = new ArrayList<>();

    // data is passed into the constructor
    public eventAdapter(Context context, JSONObject data, FragmentManager fm) {
        this.mInflater = LayoutInflater.from(context);
        this.fm = fm;
        if(data == null){
            try {
                this.mData = new JSONArray("[]");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            //events are organized in an array of events for each date, this takes all events on every date
            //and puts them into a single array
            mData = new JSONArray();
            Iterator<String> keys = data.keys();
            while(keys.hasNext()) {
                String key = keys.next();
                try {
                    JSONArray day = (JSONArray) data.get(key);
                    for(int i = 0; i < day.length(); i++){
                        mData.put(day.get(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

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

            JSONObject event = (JSONObject) mData.get(position);
            holder.addEvent(event);
            JSONObject data = ((JSONObject)event.get("data"));
            JSONObject date = ((JSONObject)event.get("date"));
            String title = data.getString("title");
            String startDate = formatDate(((JSONObject)date.get("start")).getString("date"));
            String startTime = ((JSONObject)data.get("time")).getString("start");
            String endTime = ((JSONObject)data.get("time")).getString("end");
            holder.title.setText(title);
            holder.date.setText(startDate + "  " + startTime + "-" + endTime);
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

    private String formatDate(String date){
        int month = Integer.parseInt(date.substring(5,7));
        String monthName = "";
        switch(month){
            case 1:
                monthName = "Jan";
                break;
            case 2:
                monthName = "Feb";
                break;
            case 3:
                monthName = "Mar";
                break;
            case 4:
                monthName = "Apr";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "Jun";
                break;
            case 7:
                monthName = "Jul";
                break;
            case 8:
                monthName = "Aug";
                break;
            case 9:
                monthName = "Sep";
                break;
            case 10:
                monthName = "Oct";
                break;
            case 11:
                monthName = "Nov";
                break;
            case 12:
                monthName = "Dec";
                break;
        }
        return date.substring(0,5) + monthName + date.substring(7);
    }
}
