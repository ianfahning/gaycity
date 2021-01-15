package project.gaycity.ui.resources;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import project.gaycity.R;

public class ORCAFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_orca, container, false);
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button collegeButton = (Button) getView().findViewById(R.id.button_College_ORCA);
        View.OnClickListener collegeLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://orcalift.dynamics365portals.us/orca-lift-form/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        collegeButton.setOnClickListener(collegeLink);
        Button nonCollegeButton = (Button) getView().findViewById(R.id.button_Non_College_ORCA);
        View.OnClickListener nonCollegeLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.surveymonkey.com/r/LIFTrenewal");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        nonCollegeButton.setOnClickListener(nonCollegeLink);
    }
}