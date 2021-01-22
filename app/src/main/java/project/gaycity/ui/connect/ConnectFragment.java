package project.gaycity.ui.connect;

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

public class ConnectFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_connect, container, false);
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button facebookButton = (Button) getView().findViewById(R.id.button_facebook);
        View.OnClickListener facebookLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.facebook.com/GayCity/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        facebookButton.setOnClickListener(facebookLink);

        Button twitterButton = (Button) getView().findViewById(R.id.button_twitter);
        View.OnClickListener twitterLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://twitter.com/gaycityseattle");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        twitterButton.setOnClickListener(twitterLink);

        Button youtubeButton = (Button) getView().findViewById(R.id.button_youtube);
        View.OnClickListener youtubeLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.youtube.com/user/GayCityTV");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        youtubeButton.setOnClickListener(youtubeLink);

        Button instagramButton = (Button) getView().findViewById(R.id.button_instagram);
        View.OnClickListener instagramLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.instagram.com/gaycity/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        instagramButton.setOnClickListener(instagramLink);
    }
}