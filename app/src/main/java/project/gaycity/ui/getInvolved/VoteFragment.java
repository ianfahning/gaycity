package project.gaycity.ui.getInvolved;

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

import project.gaycity.R;

public class VoteFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vote, container, false);
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //sets the onclick for the buttons
        Button registerButton = (Button) getView().findViewById(R.id.button_register);
        View.OnClickListener registerLink = v -> {
            Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote.aspx");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        registerButton.setOnClickListener(registerLink);
        Button ballotButton = (Button) getView().findViewById(R.id.button_ballot);
        View.OnClickListener ballotLink = v -> {
            Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/ballots.aspx");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        ballotButton.setOnClickListener(ballotLink);
        Button addressButton = (Button) getView().findViewById(R.id.button_address);
        View.OnClickListener addressLink = v -> {
            Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote/change-my-address.aspx");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        addressButton.setOnClickListener(addressLink);
        Button accessibleButton = (Button) getView().findViewById(R.id.button_accessible);
        View.OnClickListener accessibleLink = v -> {
            Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/ballots/accessible-voting-options.aspx");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        accessibleButton.setOnClickListener(accessibleLink);

        Button voteButton = (Button) view.findViewById(R.id.button_vote);
        FragmentManager fm = getFragmentManager();
        View.OnClickListener dialogue = v -> {
            voteDialogue alertDialog = voteDialogue.newInstance();
            alertDialog.show(fm, "fragment_alert");
        };
        voteButton.setOnClickListener(dialogue);
    }
}