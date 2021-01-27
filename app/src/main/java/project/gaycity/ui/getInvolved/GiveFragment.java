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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.gaycity.R;
import project.gaycity.expandableMenuAdapter;
import project.gaycity.ui.header;
import project.gaycity.ui.subHeader;

public class GiveFragment extends Fragment {


    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_give, container, false);
        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button donateButton = (Button) getView().findViewById(R.id.button_donate);
        View.OnClickListener donateLink = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.flipcause.com/hosted_widget/hostedWidgetHome/MjQyMzE=");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        };
        donateButton.setOnClickListener(donateLink);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        populateMenu();
    }

    public void populateMenu(){
        ArrayList<header> headers = new ArrayList<>();
        //mail a check
        ArrayList<subHeader> mailSubHeaders = new ArrayList<>();
        mailSubHeaders.add(new subHeader("Mail a donation check to us through the following address:\n" +
                "Gay City: Seattle’s LGBTQ Center\n" +
                "ATTN: Development Department\n" +
                "517 E. Pike St.\n" +
                "Seattle, WA 98122",0,false));
        headers.add(new header("Mail a Check",mailSubHeaders,true,0));
        //planned Giving(Bequests & Stocks)
        ArrayList<subHeader> plannedSubHeaders = new ArrayList<>();
        plannedSubHeaders.add(new subHeader("Support our work for LGBTQ communities while also fulfilling your own financial goals and objectives. For more information, contact Director of Development Bekah Telew at bekah@gaycity.org ",0,false));
        headers.add(new header("Planned Giving(Bequests & Stocks)",plannedSubHeaders,true,0));
        //Host a Fundraiser for Gay City
        ArrayList<subHeader> fundraiserSubHeaders = new ArrayList<>();
        fundraiserSubHeaders.add(new subHeader("We have all the tools and tips to help you host a fabulous fundraiser. For more details, contact us at bekah@gaycity.org ",0,false));
        headers.add(new header("Host a Fundraiser for Gay City",fundraiserSubHeaders,true,0));
        //Workplace Giving
        ArrayList<subHeader> workplaceSubHeaders = new ArrayList<>();
        workplaceSubHeaders.add(new subHeader("Did you know that you can donate to Gay City seamlessly through payroll deductions? Since companies often match employee contributions, workplace giving provides you with the opportunity to directly influence your company’s philanthropic endeavors.\n" +
                "Contact your Human Resources team today.",0,false));
        headers.add(new header("Workplace Giving",workplaceSubHeaders,true,0));
        //Gifts-in-Kind
        ArrayList<subHeader> giftsSubHeaders = new ArrayList<>();
        giftsSubHeaders.add(new subHeader("Do you have a business you’d like to promote? Consider an in-kind donation to Gay City, it’s tax-deductible and a great way to show off your goods to Gay City supporters. For more information, contact Director of Development Bekah Telew at bekah@gaycity.org ",0,false));
        headers.add(new header("Gifts-in-Kind",giftsSubHeaders,true,0));
        //AmazonSmile
        ArrayList<subHeader> amazonSubHeaders = new ArrayList<>();
        amazonSubHeaders.add(new subHeader("Amazon Smile is a quick and automated way for you to support your favorite charitable organization every time you shop, at no cost to you. Select Gay City as a charitable recipient through smile.amazon.com, and Amazon will donate a portion of the funds they receive from your purchase.",0,false));
        headers.add(new header("AmazonSmile",amazonSubHeaders,true,0));
        //Car Donations
        //Kroger Co.Family of Stores
        ArrayList<subHeader> krogerSubHeaders = new ArrayList<>();
        krogerSubHeaders.add(new subHeader("Kroger and its family of stores, like Fred Meyer, donate more than $2 million per year to non-profits across the Pacific Northwest with recommendations from their customers. Sign up for the Community Rewards program and link your Fred Meyer Rewards Card to Gay City Health Project (QI274) at fredmeyer.com/communityrewards. Every time you shop, Fred Meyer will donate a portion of its proceeds back to Seattle’s only LGBTQ center.",0,false));
        headers.add(new header("Kroger Co. Family of Stores (Fred Meyer/QFC/Fry’s)",krogerSubHeaders,true,0));

        expandableMenuAdapter adapter = new expandableMenuAdapter(headers,null,recyclerView, null);
        recyclerView.setAdapter(adapter);
    }

}