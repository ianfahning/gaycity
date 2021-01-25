package project.gaycity.ui.resources;

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



public class resourceDialogue extends DialogFragment {

    private JSONObject json;
    private static AlertDialog dialog;

    public resourceDialogue(JSONObject json){
        this.json = json;
    }

    public static project.gaycity.ui.resources.resourceDialogue newInstance(JSONObject json){
        project.gaycity.ui.resources.resourceDialogue frag = new project.gaycity.ui.resources.resourceDialogue(json);
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialogue_resource, container);
        try {
            String organization = json.getString("Organization");
            String mission = json.getString("mission");
            String vision = json.getString("vision");
            String values = json.getString("values");
            String services = json.getString("services");
            String serviceAvailablility = json.getString("serviceAvailablility");
            String resourceAvailablility = json.getString("resourceAvailablility");
            String website = json.getString("website");
            String phone = json.getString("phone");
            String email = json.getString("email");
            String Communities = json.getString("Communities");
            String basicNeeds = json.getString("basicNeeds");
            String accessNeeds = json.getString("accessNeeds");
            if(organization.equals("undefined")){
                ((TextView) view.findViewById(R.id.title)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.title)).setText(organization);
            }

            if(mission.equals("undefined")){
                ((TextView) view.findViewById(R.id.mission)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.mission)).setText(mission);
            }

            if(vision.equals("undefined")){
                ((TextView) view.findViewById(R.id.vision)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.vision)).setText(vision);
            }

            if(values.equals("undefined")){
                ((TextView) view.findViewById(R.id.values)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.values)).setText(values);
            }

            if(services.equals("undefined")){
                ((TextView) view.findViewById(R.id.services)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.services)).setText(services);
            }

            if(serviceAvailablility.equals("undefined")){
                ((TextView) view.findViewById(R.id.servicesAvailablility)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.servicesAvailablility)).setText(serviceAvailablility);
            }

            if(resourceAvailablility.equals("undefined")){
                ((TextView) view.findViewById(R.id.resourcesAvailablility)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.resourcesAvailablility)).setText(resourceAvailablility);
            }

            if(website.equals("undefined")){
                ((TextView) view.findViewById(R.id.website)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.website)).setText(website);
            }

            if(phone.equals("undefined")){
                ((TextView) view.findViewById(R.id.phone)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.phone)).setText(phone);
            }

            if(email.equals("undefined")){
                ((TextView) view.findViewById(R.id.email)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.email)).setText(email);
            }

            if(Communities.equals("undefined")){
                ((TextView) view.findViewById(R.id.communites)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.communites)).setText(Communities);
            }

            if(basicNeeds.equals("undefined")){
                ((TextView) view.findViewById(R.id.basicNeeds)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.basicNeeds)).setText(basicNeeds);
            }

            if(accessNeeds.equals("undefined")){
                ((TextView) view.findViewById(R.id.access)).setText("-");
            }else{
                ((TextView) view.findViewById(R.id.access)).setText(accessNeeds);
            }

          //  ((TextView) view.findViewById(R.id.transServices)).setText(json.getString("transServices"));
           // ((TextView) view.findViewById(R.id.bipocServices)).setText(json.getString("bipocServices"));
           // ((TextView) view.findViewById(R.id.lgtbqYouthServices)).setText(json.getString("lgbtqYouthServices"));
           // ((TextView) view.findViewById(R.id.lgtbqAdultServices)).setText(json.getString("lgbtqAdultServices"));


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
