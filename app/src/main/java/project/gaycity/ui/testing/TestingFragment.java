package project.gaycity.ui.testing;

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

public class TestingFragment extends Fragment {

    private project.gaycity.ui.testing.TestingViewModel testingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        testingViewModel =
                new ViewModelProvider(this).get(project.gaycity.ui.testing.TestingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_testing, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        testingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}