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

import org.jetbrains.annotations.NotNull;

import project.gaycity.R;

public class voteDialogue extends DialogFragment {

    private static AlertDialog dialog;
    public voteDialogue(){

    }

    public static voteDialogue newInstance(){
        return new voteDialogue();
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.dialogue_vote, container);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //sets the onclick for the buttons
        view.findViewById(R.id.button_back).setOnClickListener(view1 -> dialog.dismiss());
        Button oneButton = (Button) getView().findViewById(R.id.button_one);
        View.OnClickListener oneLink = v -> {
            Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote/who-can-vote.aspx?utm_source=Gay%20City%20LGBTQ%20Center&utm_medium=website&utm_campaign=Election_2020");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        oneButton.setOnClickListener(oneLink);

        Button twoButton = (Button) getView().findViewById(R.id.button_two);
        View.OnClickListener twoLink = v -> {
            Uri uriUrl = Uri.parse("https://info.kingcounty.gov/kcelections/vote/myvoterinfo.aspx?utm_source=Gay%20City%20LGBTQ%20Center&utm_medium=website&utm_campaign=Election_2020");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        twoButton.setOnClickListener(twoLink);

        Button threeButton = (Button) getView().findViewById(R.id.button_three);
        View.OnClickListener threeLink = v -> {
            Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/register-to-vote.aspx%20");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        threeButton.setOnClickListener(threeLink);

        Button sevenButton = (Button) getView().findViewById(R.id.button_seven);
        View.OnClickListener sevenLink = v -> {
            Uri uriUrl = Uri.parse("https://info.kingcounty.gov/kcelections/vote/myvoterinfo.aspx");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        sevenButton.setOnClickListener(sevenLink);

        Button eightButton = (Button) getView().findViewById(R.id.button_eight);
        View.OnClickListener eightLink = v -> {
            Uri uriUrl = Uri.parse("https://www.kingcounty.gov/depts/elections/how-to-vote/ballots/returning-my-ballot/vote-centers.aspx");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        eightButton.setOnClickListener(eightLink);
    }

    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialog = alertDialogBuilder.create();
        dialog.show();
        return dialog;
    }
}
