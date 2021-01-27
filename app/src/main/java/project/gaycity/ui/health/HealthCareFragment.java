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

import java.util.ArrayList;

import project.gaycity.R;
import project.gaycity.expandableMenuAdapter;
import project.gaycity.ui.header;
import project.gaycity.ui.subHeader;

public class HealthCareFragment extends Fragment {

    RecyclerView recylcerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_health_care, container, false);
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button appointmentButton = (Button) getView().findViewById(R.id.button_health_fragment);
        View.OnClickListener appointmentLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://gaycity.as.me/schedule.php?appointmentType=category%3AHealthcare+Navigation");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        appointmentButton.setOnClickListener(appointmentLink);
        recylcerView = root.findViewById(R.id.recyclerView);
        recylcerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        populateMenu();
    }

    public void populateMenu(){
        //Types of Health Plans
        ArrayList<header> headers = new ArrayList<>();
        ArrayList<subHeader> plansSubHeaders = new ArrayList<>();
        plansSubHeaders.add(new subHeader("We enroll individuals in the following plans based on their eligibility:\n" +
                "\u2022 Apple Health (Medicaid)\n" +
                "\u2022 Qualified Health Plans (Private Care)",0,false));
        headers.add(new header("Types of Health Plans",plansSubHeaders,true,0));
        //Eligibility
        ArrayList<subHeader> eligibilitySubHeaders = new ArrayList<>();
        eligibilitySubHeaders.add(new subHeader("Eligibility\n" +
                "\n" +
                "To be eligible to enroll in health coverage, you:\n" +
                "\n" +
                "\u2022 Must live in the United States*\n" +
                "\u2022 Must be a U.S. citizen or national** (or be lawfully present)*** \n" +
                "\u2022 Can’t be incarcerated\n" +
                "\u2022 Have a qualifying event (if outside of special enrollment period)\n\n" +
                "*Must be considered a “resident” of the United States for tax purposes. Generally, health insurance covers health care provided by doctors, hospitals, and other providers within the United States. If you’re living abroad, it’s important to know this before you consider signing up for insurance.\n" +
                "\n" +
                "**A U.S. national is someone who’s a U.S. citizen or a person who isn’t a U.S. citizen but owes permanent allegiance to the U.S. With extremely limited exception by which he or she is entitled to be protected, all non-citizen U.S. nationals are people born in American Samoa or abroad with one or more American Samoan parents under certain conditions.\n" +
                "\n" +
                "***Immigrants with the following statuses qualify:\n" +
                "\n" +
                "\u2022 Lawful Permanent Resident (LPR/Green Card holder)\n" +
                "\u2022 Asylee\n" +
                "\u2022 Refugee\n" +
                "\u2022 Cuban/Haitian Entrant\n" +
                "\u2022 Paroled into the U.S.\n" +
                "\u2022 Conditional Entrant Granted before 1980\n" +
                "\u2022 Battered Spouse, Child and Parent\n" +
                "\u2022 Victim of Trafficking and his/her Spouse, Child, Sibling or Parent\n" +
                "\u2022 Granted Withholding of Deportation or Withholding of Removal, under the immigration laws or under the Convention against Torture (CAT)\n" +
                "\u2022 Individual with Non-immigrant Status, includes worker visas (such as H1, H-2A, H-2B), student visas, U-visa, T-visa, and other visas, and citizens of Micronesia, the Marshall Islands, and Palau\n" +
                "\u2022 Temporary Protected Status (TPS)\n" +
                "\u2022 Deferred Enforced Departure (DED)\n" +
                "\u2022 Deferred Action Status (Exception: Deferred Action for Childhood Arrivals (DACA) is not an eligible immigration status for applying for health insurance)\n" +
                "\u2022 Lawful Temporary Resident\n" +
                "\u2022 Administrative order staying removal issued by the Department of Homeland Security\n" +
                "\u2022 Member of a federally-recognized Indian tribe or American Indian Born in Canada\n" +
                "\u2022 Resident of American Samoa\n\n" +
                "Applicants for any of these statuses qualify:\n" +
                "\n" +
                "\u2022 Temporary Protected Status with Employment Authorization\n" +
                "\u2022 Special Immigrant Juvenile Status\n" +
                "\u2022 Victim of Trafficking Visa\n" +
                "\u2022 Adjustment to LPR Status\n" +
                "\u2022 Asylum (see note below)\n" +
                "\u2022 Withholding of Deportation, or Withholding of Removal, under the immigration laws or under the Convention against Torture (CAT): Applicants for asylum are eligible for coverage only if they’ve been granted employment authorization or are under the age of 14 and have had an application pending for at least 180 days.\n\n" +
                "People with the following statuses and who have employment authorization qualify:\n" +
                "\n" +
                "\u2022 Registry Applicants\n" +
                "\u2022 Order of Supervision\n" +
                "\u2022 Applicant for Cancellation of Removal or Suspension of Deportation\n" +
                "\u2022 Applicant for Legalization under Immigration Reform and Control Act (IRCA)\n" +
                "\u2022 Legalization under the LIFE Act\n\n" +
                "Remember: Information about immigration status will be used only to determine eligibility for coverage and not for immigration enforcement.",0,false));
        headers.add(new header("Eligibility",eligibilitySubHeaders,true,0));
        //Qualifying Events
        ArrayList<subHeader> eventsSubHeaders = new ArrayList<>();
        eventsSubHeaders.add(new subHeader("Qualifying Events\n\n" +
                "You may be eligible to enroll outside of open enrollment only if you have a qualifying life event. Meet with our affirming navigators today to find the best options for you.\n" +
                "\n" +
                "Qualifying Event Examples\n" +
                "\n" +
                "\u2022 Experience a decrease in income\n" +
                "\u2022 Loss of other coverage\n" +
                "\u2022 Getting married\n" +
                "\u2022 Deceased person on coverage\n" +
                "\u2022 Child Birth\n" +
                "\u2022 Eligible for subsidies (tax credits and/or cost-sharing reductions)\n" +
                "\u2022 Had Minimum Essential Coverage for at least 1 day during the 60 days prior to\n" +
                "income change^\n\n" +
                "^Customers must report this qualifying life event within 60 days of newly gaining tax credit eligibility.",0,false));
        headers.add(new header("Qualifying Events",eventsSubHeaders,true,0));
        //cancel plan
        ArrayList<subHeader> cancelSubHeaders = new ArrayList<>();
        cancelSubHeaders.add(new subHeader("Our health navigators will help you cancel your plan.\n" +
                "\n" +
                "Ensure you are eligible for your employer’s health coverage at this time of the year and confirm your enrollment." +"\n" +
                "\n\n" +
                "Please note: If you end all health coverage (both your insurance plan and employer-provided health insurance) and don’t replace it, the government may require you to pay a fee.",0,false));
        headers.add(new header("Canceling a Plan",cancelSubHeaders,true,0));
        expandableMenuAdapter adapter = new expandableMenuAdapter(headers,null,recylcerView, null);
        recylcerView.setAdapter(adapter);
    }

}