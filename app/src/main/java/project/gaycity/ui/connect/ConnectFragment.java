package project.gaycity.ui.connect;

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

public class ConnectFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_connect, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        //sets the onclick for the buttons
        Button facebookButton = (Button) getView().findViewById(R.id.button_facebook);
        View.OnClickListener facebookLink = v -> {
            Uri uriUrl = Uri.parse("https://www.facebook.com/GayCity/");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        facebookButton.setOnClickListener(facebookLink);

        Button twitterButton = (Button) getView().findViewById(R.id.button_twitter);
        View.OnClickListener twitterLink = v -> {
            Uri uriUrl = Uri.parse("https://twitter.com/gaycityseattle");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        twitterButton.setOnClickListener(twitterLink);

        Button youtubeButton = (Button) getView().findViewById(R.id.button_youtube);
        View.OnClickListener youtubeLink = v -> {
            Uri uriUrl = Uri.parse("https://www.youtube.com/user/GayCityTV");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        youtubeButton.setOnClickListener(youtubeLink);

        Button instagramButton = (Button) getView().findViewById(R.id.button_instagram);
        View.OnClickListener instagramLink = v -> {
            Uri uriUrl = Uri.parse("https://www.instagram.com/gaycity/");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        instagramButton.setOnClickListener(instagramLink);
    }
}