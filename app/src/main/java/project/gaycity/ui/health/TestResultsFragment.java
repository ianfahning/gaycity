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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import project.gaycity.R;
import project.gaycity.expandableMenuAdapter;
import project.gaycity.ui.header;
import project.gaycity.ui.subHeader;

public class TestResultsFragment extends Fragment {

    private RecyclerView recylcerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_test_results, container, false);

        return root;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        //set the onclick for the button
        Button resultsButton = (Button) getView().findViewById(R.id.button_results_fragment);
        View.OnClickListener resultsLink = v -> {
            Uri uriUrl = Uri.parse("https://gcscintouch.insynchcs.com/");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        };
        resultsButton.setOnClickListener(resultsLink);
        //sets up the expandable recyclerview with all the data
        recylcerView = root.findViewById(R.id.recyclerView);
        recylcerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        populateMenu();//sets the data for the recycler view
    }

    //populates the expandable recyclerview with all its data
    public void populateMenu() {
        //first time login
        ArrayList<header> headers = new ArrayList<>();
        ArrayList<subHeader> firstSubHeaders = new ArrayList<>();
        firstSubHeaders.add(new subHeader("During your appointment, provide your tester with your email address and you will receive a passcode and temporary password.\n" +
                "\n" +
                "\u2022 Username: Your email address\n" +
                "\u2022 Temporary Password: Provided by tester\n" +
                "\u2022 Passcode: Please use to reset your password by phone.",0,false));
        headers.add(new header("First-Time Login: Acquiring my Username and Password",firstSubHeaders,true,0));
        //logging in
        ArrayList<subHeader> loginsSubHeaders = new ArrayList<>();
        loginsSubHeaders.add(new subHeader("",R.drawable.entry,false));
        loginsSubHeaders.add(new subHeader("First Time User\n" +
                "\n" +
                "Enter your Username (email address) and temporary password. Select the Sign In button. You will be asked to reset your password after logging in.\n" +
                "\n" +
                "Returning User\n" +
                "\n" +
                "Enter your Username (email address) and password. Select the Sign In button.\n" +
                "\n" +
                "*Always use a secure password to ensure the confidentiality of your medical records. Please also remember to sign out of your account, especially if you are on a public or shared device.",0,false));
        headers.add(new header("Logging In",loginsSubHeaders,true,0));
        //forgot password or ID
        ArrayList<subHeader> forgotSubHeaders = new ArrayList<>();
        forgotSubHeaders.add(new subHeader("If you ever forget your password, select “Forgot my Password”.",0,false));
        forgotSubHeaders.add(new subHeader("",R.drawable.forgot_password_red_box,false));
        forgotSubHeaders.add(new subHeader("On the next screen, please complete these four fields:",0,false));
        forgotSubHeaders.add(new subHeader("",R.drawable.i_forgot_my_password,false));
        forgotSubHeaders.add(new subHeader("User Name: Should be the same as your email.\n" +
                "\u2022 Email\n" +
                "\u2022 Date of Birth you gave to the Tester\n" +
                "\u2022 Zip Code you gave to Gay City during Registration.",0,false));
        headers.add(new header("Forgot my Password or Login ID",forgotSubHeaders,true,0));

        //Selecting My Messages
        ArrayList<subHeader> messagesSubHeaders = new ArrayList<>();
        messagesSubHeaders.add(new subHeader("Your Patient portal will include options to see the dashboard and messages from your Gay City tester.",0,false));
        messagesSubHeaders.add(new subHeader("",R.drawable.messages_one,false));
        messagesSubHeaders.add(new subHeader("To check your results, select Messages, and select the blue document link to download your test results.",0,false));
        messagesSubHeaders.add(new subHeader("",R.drawable.messages_two,false));
        messagesSubHeaders.add(new subHeader("You will receive a separate message for every location on your body where specimens were collected and tested (e.g. throat, back hole/rectum, front hole/vagina, blood).",0,false));
        messagesSubHeaders.add(new subHeader("",R.drawable.messages_three,false));
        messagesSubHeaders.add(new subHeader("Keep good track of your results. Do not save the result on a public or shared device.\n" +
                "\n" +
                "Please note: Testers will not respond to messages sent through the Patient Portal. If you have any inquiries about your results, please call 206-860-6969  x718 or email testing@gaycity.org.",0,false));
        headers.add(new header("Selecting My Messages",messagesSubHeaders,true,0));

        //View prior results

        ArrayList<subHeader> priorSubHeaders = new ArrayList<>();
        priorSubHeaders.add(new subHeader("In your Patient Portal, select “Forms/Documents”. You can then view all prior results and client documents (e.g. patient forms, messages).",0,false));
        priorSubHeaders.add(new subHeader("",R.drawable.prior,false));
        headers.add(new header("View Prior Results",priorSubHeaders,true,0));

        //How to read your results
        ArrayList<subHeader> resultsSubHeaders = new ArrayList<>();
        resultsSubHeaders.add(new subHeader("Please note that Gay City generally will not release inconclusive results, nor will we release reactive/positive results without first connecting with clients.\n" +
                "\n" +
                "In the Observation Identifier (i.e. test type), the keywords to look for on your results are:\n" +
                "\n" +
                "\u2022 Positive/reactive: Tested positive\n" +
                "\u2022 Negative/nonreactive: Tested negative\n" +
                "Test Types\n" +
                "\n" +
                "Observation Identifier \tScreens For\n" +
                "RPR (Rapid Plasma Reagin)\tSyphilis\n" +
                "Neisseria Gonorrhoeae\tGonorrhea\n" +
                "Chlamydia Trachomatis\tChlamydia\n" +
                "HIV\tHuman Immunodeficiency Virus\n" +
                "Below is an example of where to view these identifiers on your HIV/STI testing results:",0,false));
        resultsSubHeaders.add(new subHeader("",R.drawable.interpreting_your_test_results,false));
        resultsSubHeaders.add(new subHeader("In the test above, the HIV test indicates a negative result. For the RPR Qualitative (Syphilis), the STI test result is nonreactive (or negative).\n" +
                "\n" +
                "If you have questions about your test results, please contact us at 206-860-6969 x718 or email us at testing@gaycity.org.",0,false));
        headers.add(new header("How to Read Your Results",resultsSubHeaders,true,0));

        //sending results to physician
        ArrayList<subHeader> physicianSubHeaders = new ArrayList<>();
        physicianSubHeaders.add(new subHeader("Gay City will fax your testing results to any physician with your approval. Please visit our Wellness Center to complete the proper documentation with a Wellness Center representative.\n" +
                "Documentation will need to be completed prior to each request to send results to a physician.",0,false));
        physicianSubHeaders.add(new subHeader("",R.drawable.prior,false));
        headers.add(new header("Sending Test Results to My Physician",physicianSubHeaders,true,0));

        //Additional Questions
        ArrayList<subHeader> questionsSubHeaders = new ArrayList<>();
        questionsSubHeaders.add(new subHeader("If you have additional questions about the Patient Portal/results, please contact us at 206-860-6969 x718 or email us at testing@gaycity.org.",0,false));
        headers.add(new header("Additional Questions",questionsSubHeaders,true,0));
        expandableMenuAdapter adapter = new expandableMenuAdapter(headers, null, null);
        recylcerView.setAdapter(adapter);
    }

}
