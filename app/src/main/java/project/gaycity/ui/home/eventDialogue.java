package project.gaycity.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import project.gaycity.R;


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
            String title = json.getString("title");
            String startDate = json.getString("startDate");
            String startTime = json.getString("startTime");
            ((TextView) view.findViewById(R.id.title)).setText(title);
            ((TextView) view.findViewById(R.id.date_time)).setText(startDate + " " + startTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
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
}
