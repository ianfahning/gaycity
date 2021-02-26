package project.gaycity.ui.health;

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

public class AppointmentFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        //set the onclick for the button
        Button appointmentButton = (Button) getView().findViewById(R.id.button_appointment_fragment);
        View.OnClickListener appointmentLink = v -> {
            Uri uriUrl = Uri.parse("https://gaycity.as.me/schedule.php?appointmentType=category%3AHIV%2FSTI+Testing");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        appointmentButton.setOnClickListener(appointmentLink);
    }
}