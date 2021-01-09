package project.gaycity.ui.donate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import project.gaycity.R;

public class DonateFragment extends Fragment {

    private project.gaycity.ui.donate.DonateViewModel donateViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        donateViewModel =
                new ViewModelProvider(this).get(project.gaycity.ui.donate.DonateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_testing, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        donateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}