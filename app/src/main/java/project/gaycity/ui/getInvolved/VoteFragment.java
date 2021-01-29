package project.gaycity.ui.getInvolved;

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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import project.gaycity.R;
import project.gaycity.ui.home.aboutDialogue;

public class VoteFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vote, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button registerButton = (Button) getView().findViewById(R.id.button_register);
        View.OnClickListener registerLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote.aspx");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        registerButton.setOnClickListener(registerLink);
        Button ballotButton = (Button) getView().findViewById(R.id.button_ballot);
        View.OnClickListener ballotLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/ballots.aspx");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        ballotButton.setOnClickListener(ballotLink);
        Button addressButton = (Button) getView().findViewById(R.id.button_address);
        View.OnClickListener addressLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote/change-my-address.aspx");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        addressButton.setOnClickListener(addressLink);
        Button accessibleButton = (Button) getView().findViewById(R.id.button_accessible);
        View.OnClickListener accessibleLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/ballots/accessible-voting-options.aspx");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        accessibleButton.setOnClickListener(accessibleLink);

        Button voteButton = (Button) view.findViewById(R.id.button_vote);
        FragmentManager fm  = getFragmentManager();
        View.OnClickListener dialogue = new View.OnClickListener() {
            public void onClick(View v) {
                voteDialogue alertDialog = voteDialogue.newInstance();
                alertDialog.show(fm, "fragment_alert");
            }
        };
        voteButton.setOnClickListener(dialogue);
    }
}