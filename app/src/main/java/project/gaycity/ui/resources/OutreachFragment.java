package project.gaycity.ui.resources;

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

public class OutreachFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_outreach, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        //set onclick link for the button
        Button outreachButton = (Button) getView().findViewById(R.id.button_outreach);
        FragmentManager fm = getFragmentManager();
        View.OnClickListener outreachLink = v -> {
            webviewDialogue alertDialog = webviewDialogue.newInstance("https://docs.google.com/forms/d/e/1FAIpQLSdGfvL-DRyYIz7rhoP2lw5Wldlj0XRnkWASHzYK_VoYi_6uXA/viewform");
            alertDialog.show(fm, "fragment_alert");
        };
        outreachButton.setOnClickListener(outreachLink);
    }
}