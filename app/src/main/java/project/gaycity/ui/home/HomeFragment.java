package project.gaycity.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import project.gaycity.R;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button aboutButton = (Button) view.findViewById(R.id.button_mission);
        FragmentManager fm  = getFragmentManager();
        View.OnClickListener dialogue = new View.OnClickListener() {
            public void onClick(View v) {
                aboutDialogue alertDialog = aboutDialogue.newInstance();
                alertDialog.show(fm, "fragment_alert");
            }
        };
        aboutButton.setOnClickListener(dialogue);
        Button donateButton = (Button) getView().findViewById(R.id.button_donate);
        View.OnClickListener donateLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.flipcause.com/hosted_widget/hostedWidgetHome/MjQyMzE=");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        donateButton.setOnClickListener(donateLink);
        Button appointmentButton = (Button) getView().findViewById(R.id.button_appointment);
        View.OnClickListener appointmentLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://gaycity.as.me/schedule.php?appointmentType=category%3AHIV%2FSTI+Testing");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        appointmentButton.setOnClickListener(appointmentLink);
    }

}