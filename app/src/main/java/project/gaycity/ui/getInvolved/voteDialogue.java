package project.gaycity.ui.getInvolved;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import project.gaycity.R;
import project.gaycity.ui.home.aboutDialogue;

public class voteDialogue extends DialogFragment {

    private static AlertDialog dialog;
    public voteDialogue(){

    }

    public static voteDialogue newInstance(){
        voteDialogue frag = new voteDialogue();
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialogue_vote, container);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button oneButton = (Button) getView().findViewById(R.id.button_one);
        View.OnClickListener oneLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote/who-can-vote.aspx?utm_source=Gay%20City%20LGBTQ%20Center&utm_medium=website&utm_campaign=Election_2020");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        oneButton.setOnClickListener(oneLink);

        Button twoButton = (Button) getView().findViewById(R.id.button_two);
        View.OnClickListener twoLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://info.kingcounty.gov/kcelections/vote/myvoterinfo.aspx?utm_source=Gay%20City%20LGBTQ%20Center&utm_medium=website&utm_campaign=Election_2020");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        twoButton.setOnClickListener(twoLink);

        Button threeButton = (Button) getView().findViewById(R.id.button_three);
        View.OnClickListener threeLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote.aspx%20");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        threeButton.setOnClickListener(threeLink);

        Button sevenButton = (Button) getView().findViewById(R.id.button_seven);
        View.OnClickListener sevenLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://info.kingcounty.gov/kcelections/vote/myvoterinfo.aspx");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        sevenButton.setOnClickListener(sevenLink);

        Button eightButton = (Button) getView().findViewById(R.id.button_eight);
        View.OnClickListener eightLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/ballots/returning-my-ballot/vote-centers.aspx");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        eightButton.setOnClickListener(eightLink);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialog = alertDialogBuilder.create();
        dialog.show();
        return dialog;
    }
}
