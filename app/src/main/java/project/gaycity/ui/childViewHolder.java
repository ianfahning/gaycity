package project.gaycity.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import project.gaycity.R;
import project.gaycity.expandableRecyclerView.viewholders.ChildViewHolder;
import project.gaycity.ui.connect.ConnectFragment;
import project.gaycity.ui.getInvolved.GiveFragment;
import project.gaycity.ui.getInvolved.VolunteerFragment;
import project.gaycity.ui.getInvolved.VoteFragment;
import project.gaycity.ui.health.AppointmentFragment;
import project.gaycity.ui.health.HealthCareFragment;
import project.gaycity.ui.health.PrepFragment;
import project.gaycity.ui.health.TestResultsFragment;
import project.gaycity.ui.home.HomeFragment;
import project.gaycity.ui.resources.ORCAFragment;
import project.gaycity.ui.resources.OutreachFragment;
import project.gaycity.ui.resources.QCCFragment;
import project.gaycity.ui.resources.ResourcesDatabaseFragment;
import project.gaycity.ui.resources.TechnicalFragment;

public class childViewHolder extends ChildViewHolder {
    public TextView textView;
    private final ImageView imageView;

    public childViewHolder(View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.lblListItem);
        imageView = itemView.findViewById(R.id.imgView);
    }

    public void bind(subHeader model, FragmentManager fm, DrawerLayout drawer) {
        textView.setText(model.name);
        //if the model is not supposed change fragments and the image or fragment in non zero that means
        //that it is supposed to show an image and the value in the imageOrFragment is an image
        if (!model.isFragment && model.imageOrFragment != 0) {
            imageView.setImageResource(model.imageOrFragment);
            imageView.setVisibility(View.VISIBLE);
            //if the model is supposed to change fragments then set the on click. this means that the
            //imageOrFragment value is a fragment
        } else if (model.isFragment) {
            View.OnClickListener changeFragment = v -> {
                fm.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.nav_host_fragment, findFragment(model.imageOrFragment), null).commit();
                drawer.close();
            };
            textView.setOnClickListener(changeFragment);
        }

    }

    private Fragment findFragment(int num){
        switch(num){
            case R.id.fragment_home:
                return new HomeFragment();
            case R.id.fragment_appointment:
                return new AppointmentFragment();
            case R.id.fragment_test_results:
                return new TestResultsFragment();
            case R.id.fragment_prep:
                return new PrepFragment();
            case R.id.fragment_health_care:
                return new HealthCareFragment();
            case R.id.fragment_resources_database:
                return new ResourcesDatabaseFragment();
            case R.id.fragment_qcc:
                return new QCCFragment();
            case R.id.fragment_orca:
                return new ORCAFragment();
            case R.id.fragment_outreach:
                return new OutreachFragment();
            case R.id.fragment_technical:
                return new TechnicalFragment();
            case R.id.fragment_volunteer:
                return new VolunteerFragment();
            case R.id.fragment_vote:
                return new VoteFragment();
            case R.id.fragment_give:
                return new GiveFragment();
            case R.id.fragment_connect:
                return new ConnectFragment();
        }
        return new HomeFragment();
    }

}
