package project.gaycity.ui.resources;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import project.gaycity.R;

public class ORCAFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_orca, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        //set onclick links for the buttons
        Button collegeButton = (Button) getView().findViewById(R.id.button_College_ORCA);
        View.OnClickListener collegeLink = v -> {
            Uri uriUrl = Uri.parse("https://orcalift.dynamics365portals.us/orca-lift-form/");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        collegeButton.setOnClickListener(collegeLink);
        Button nonCollegeButton = (Button) getView().findViewById(R.id.button_Non_College_ORCA);
        View.OnClickListener nonCollegeLink = v -> {
            Uri uriUrl = Uri.parse("https://www.surveymonkey.com/r/LIFTrenewal");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        nonCollegeButton.setOnClickListener(nonCollegeLink);
    }
}