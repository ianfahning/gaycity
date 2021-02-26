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

public class OutreachFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_outreach, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        //set onclick link for the button
        Button outreachButton = (Button) getView().findViewById(R.id.button_outreach);
        View.OnClickListener outreachLink = v -> {
            Uri uriUrl = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdGfvL-DRyYIz7rhoP2lw5Wldlj0XRnkWASHzYK_VoYi_6uXA/viewform");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        outreachButton.setOnClickListener(outreachLink);
    }
}