package project.gaycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONException;
import org.json.JSONObject;


public class popupDialogue extends DialogFragment {

    private JSONObject json;
    private static AlertDialog dialog;
    private static FragmentManager fm;

    public popupDialogue(JSONObject json, FragmentManager fm){
        this.json = json;
        this.fm = fm;
    }


    public static popupDialogue newInstance(JSONObject json, FragmentManager fm){
        popupDialogue frag = new popupDialogue(json,fm);
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialogue_popup, container);
        view.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.button_learn_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventDialogue alertDialog = eventDialogue.newInstance(json);
                alertDialog.show(fm, "fragment_alert");
            }
        });
        try {
            JSONObject data = ((JSONObject)json.get("data"));
            ((TextView) view.findViewById(R.id.popup_title)).setText(data.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private Spanned getContent(String content) {
        return Html.fromHtml(content.substring(content.indexOf("<p>") + 3,content.lastIndexOf("</p>")));
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
       /* view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialog = alertDialogBuilder.create();
        dialog.show();
        return dialog;
    }
}
