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

import org.json.JSONException;
import org.json.JSONObject;


public class eventDialogue extends DialogFragment {

    private JSONObject json;
    private static AlertDialog dialog;

    public eventDialogue(JSONObject json){
        this.json = json;
    }

    public static eventDialogue newInstance(JSONObject json){
        eventDialogue frag = new eventDialogue(json);
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialogue_event, container);
        try {
            JSONObject data = ((JSONObject)json.get("data"));
            JSONObject date = ((JSONObject)json.get("date"));
            String title = data.getString("title");
            String startDate = formatDate(((JSONObject)date.get("start")).getString("date"));
            String startTime = ((JSONObject)data.get("time")).getString("start");
            String endTime = ((JSONObject)data.get("time")).getString("end");
           // String content = ((JSONObject)data.get("post")).getString("post_content");
            Spanned content = getContent(data.getString("content"));
            ((TextView) view.findViewById(R.id.title)).setText(title);
            ((TextView) view.findViewById(R.id.content)).setText(content);
            ((TextView) view.findViewById(R.id.date_time)).setText(startDate + "  " + startTime + "-" + endTime);
            String link = ((JSONObject)data.get("meta")).getString("mec_more_info");
            Button registerButton = (Button) view.findViewById(R.id.button_register);
            if(link.equals("")){
                registerButton.setVisibility(View.GONE);
            }else {
                View.OnClickListener registerLink = new View.OnClickListener() {
                    public void onClick(View v) {
                        Uri uriUrl = Uri.parse(link);
                        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                        startActivity(launchBrowser);
                    }
                };
                registerButton.setOnClickListener(registerLink);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private Spanned getContent(String content) {
        return Html.fromHtml(content.substring(content.indexOf("<p>") + 3,content.lastIndexOf("</p>")));
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialog = alertDialogBuilder.create();
        dialog.show();
        return dialog;
    }

    private String formatDate(String date){
        int month = Integer.parseInt(date.substring(5,7));
        System.out.println(date.substring(5,7));
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
