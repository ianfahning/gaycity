package project.gaycity.ui.health;

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

public class PrepFragment extends Fragment {

    private PrepViewModel prepViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //prepViewModel =
               // new ViewModelProvider(this).get(PrepViewModel.class);
        View root = inflater.inflate(R.layout.fragment_prep, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        /*prepViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button appointmentButton = (Button) getView().findViewById(R.id.button_prep_fragment);
        View.OnClickListener appointmentLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfopcWuPbAVYLac2w-7zRaKbvTN_TpY_v_0hE1WIzaZAMwBFQ/viewform");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        appointmentButton.setOnClickListener(appointmentLink);
    }
}