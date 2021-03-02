package project.gaycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class eventDialogue extends DialogFragment {

    private final JSONObject json;
    private static AlertDialog dialog;

    public eventDialogue(JSONObject json) {
        this.json = json;
    }

    public static eventDialogue newInstance(JSONObject json){
        return new eventDialogue(json);
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialogue_event, container);
        try {
            //pull all the data from the JSON
            JSONObject data = ((JSONObject) json.get("data"));
            JSONObject date = ((JSONObject) json.get("date"));
            String title = data.getString("title");
            String startDate = ((JSONObject) date.get("start")).getString("date");
            String formattedStartDate = formatDate(startDate);
            String startTime = ((JSONObject) data.get("time")).getString("start");
            String endDate = ((JSONObject) date.get("end")).getString("date");
            String endTime = ((JSONObject) data.get("time")).getString("end");
            Spanned content = getContent(data.getString("content"));
            String eventDate = formattedStartDate + "  " + startTime + "-" + endTime;

            //set all the text views with the data
            ((TextView) view.findViewById(R.id.title)).setText(title);
            ((TextView) view.findViewById(R.id.content)).setText(content);
            ((TextView) view.findViewById(R.id.date_time)).setText(eventDate);

            //create onclick to set a reminder in the users calender
            Button reminderButton = view.findViewById(R.id.button_reminder);
            View.OnClickListener calenderEvent = v -> {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                //process start and end times
                //i didn't use a Date object because it always gave me a year in the early 1900s
                int[] eventStartDate = getTimeAndDate(startDate, startTime);
                int[] eventEndDate = getTimeAndDate(endDate, endTime);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(eventStartDate[0], eventStartDate[1], eventStartDate[2], eventStartDate[3], eventStartDate[4]);
                Calendar finishTime = Calendar.getInstance();
                finishTime.set(eventEndDate[0], eventEndDate[1], eventEndDate[2], eventEndDate[3], eventEndDate[4]);
                Intent intent = new Intent(Intent.ACTION_EDIT);
                //set the data
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", beginTime.getTimeInMillis());
                intent.putExtra("rrule", "FREQ=YEARLY");
                intent.putExtra("endTime", finishTime.getTimeInMillis());
                intent.putExtra("title", title);
                startActivity(intent);
            };
            reminderButton.setOnClickListener(calenderEvent);

            //get the link to register for the event
            String link = ((JSONObject) data.get("meta")).getString("mec_more_info");
            Button registerButton = view.findViewById(R.id.button_register);
            //if there is no link remove the register button, otherwise set the onclick
            if (link.equals("")) {
                registerButton.setVisibility(View.GONE);
            } else {
                View.OnClickListener registerLink = v -> {
                    Uri uriUrl = Uri.parse(link);
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                };
                registerButton.setOnClickListener(registerLink);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    //give a date formatted like "yyyy-mm-dd" and a time formatted like "hh:mm pm" it will return an
    //array of those numbers in order
    private int[] getTimeAndDate(String Date, String Time) {
        int year = Integer.parseInt(Date.substring(0, 4));
        int month = Integer.parseInt(Date.substring(5, 7)) - 1;
        int day = Integer.parseInt(Date.substring(8, 10));
        int hour = 0;
        if (Time.substring(5, 7).toLowerCase().equals("pm")) {
            hour = Integer.parseInt(Time.substring(0, 1)) + 12;
        } else {
            hour = Integer.parseInt(Time.substring(0, 1));
        }
        int minute = Integer.parseInt(Time.substring(2, 4));
        return new int[]{year, month, day, hour, minute};
    }

    //the text is surrounded by HTML stuff that causes lots of white space, this will just get the text
    private Spanned getContent(String content) {
        //if the <p> tag is the very first thing, we need to add 1 otherwise it will be out of bounds
        if (content.indexOf("<p>") == -1) {
            return Html.fromHtml(content.substring(content.indexOf("<p>") + 1, content.lastIndexOf("</p>")));
        } else {
            return Html.fromHtml(content.substring(content.indexOf("<p>"), content.lastIndexOf("</p>")));
        }

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.button_back).setOnClickListener(view1 -> dialog.dismiss());
    }

    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialog = alertDialogBuilder.create();
        dialog.show();
        return dialog;
    }

    //returns the 3 letter abbreviation based of the month number in the data
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
