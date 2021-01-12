package project.gaycity.ui.health;

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

public class TestResultsFragment extends Fragment {

    private TestResultsViewModel testResultsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        testResultsViewModel =
                new ViewModelProvider(this).get(TestResultsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_test_results, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        testResultsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}