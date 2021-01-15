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

public class TechnicalFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_technical, container, false);

        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button technicalButton = (Button) getView().findViewById(R.id.button_outreach);
        View.OnClickListener technicalLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdGfvL-DRyYIz7rhoP2lw5Wldlj0XRnkWASHzYK_VoYi_6uXA/viewform");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        technicalButton.setOnClickListener(technicalLink);
    }
}