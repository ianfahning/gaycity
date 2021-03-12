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
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.NotNull;

import project.gaycity.R;
import project.gaycity.ui.webviewDialogue;

public class AppointmentFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        //set the onclick for the button
        Button appointmentButton = (Button) getView().findViewById(R.id.button_appointment_fragment);
        FragmentManager fm = getFragmentManager();
        View.OnClickListener appointmentLink = v -> {
            webviewDialogue alertDialog = webviewDialogue.newInstance("https://gaycity.as.me/schedule.php?appointmentType=category%3AHIV%2FSTI+Testing");
            alertDialog.show(fm, "fragment_alert");
        };
        appointmentButton.setOnClickListener(appointmentLink);
    }
}