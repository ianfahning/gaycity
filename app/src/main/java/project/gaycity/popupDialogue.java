package project.gaycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;



public class popupDialogue extends DialogFragment {

    private final JSONObject json;
    private static AlertDialog dialog;
    private static FragmentManager fm;
    private Bitmap image;

    public popupDialogue(JSONObject json, FragmentManager fm, Bitmap image) {
        this.json = json;
        this.fm = fm;
        this.image = image;
    }


    public static popupDialogue newInstance(JSONObject json, FragmentManager fm, Bitmap image) {
        return new popupDialogue(json, fm, image);
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialogue_popup, container);
        view.findViewById(R.id.exit).setOnClickListener(view1 -> dialog.dismiss());
        //set the learn more to open a dialogue about the popup
        view.findViewById(R.id.button_learn_more).setOnClickListener(view12 -> {
            eventDialogue alertDialog = eventDialogue.newInstance(json);
            alertDialog.show(fm, "fragment_alert");
        });
        try {
            //set title
            JSONObject data = ((JSONObject) json.get("data"));
            ((TextView) view.findViewById(R.id.popup_title)).setText(data.getString("title"));
            //if there is a custom image set it otherwise it will use the default
            if (image != null) {
                ((ImageView) view.findViewById(R.id.image)).setImageBitmap(image);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }


    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialog = alertDialogBuilder.create();
        dialog.show();
        return dialog;
    }

}
