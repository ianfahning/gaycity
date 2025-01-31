package project.gaycity.ui;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import project.gaycity.R;
import project.gaycity.expandableRecyclerView.viewholders.GroupViewHolder;
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

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class parentViewHolder extends GroupViewHolder {
    private final TextView textView;
    private final ImageView arrow;
    private boolean isArrow;

    public parentViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.lblListHeader);
        arrow = itemView.findViewById(R.id.arrow);
    }

    public void bind(header model, FragmentManager fm, DrawerLayout drawer) {
        this.isArrow = model.isArrow;
        //sets arrow to visible if the model is expandable
        if(this.isArrow){
            arrow.setVisibility(View.VISIBLE);
        }
        //if there is a value for fragment it means that when you click it, it should open a new fragment
        if(model.fragment != 0) {
            //set the onclick to open the new fragment
            View.OnClickListener changeFragment = v -> {
                fm.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.nav_host_fragment, findFragment(model.fragment), null).commit();
                drawer.close();
            };
            textView.setOnClickListener(changeFragment);
        }
        textView.setText(model.getTitle());
    }

    @Override
    public void expand() {
        if(isArrow) {animateExpand();}
    }

    @Override
    public void collapse() {
        if(isArrow) {animateCollapse();}
    }

    //animation for the arrow when the element expands
    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    //animation for the arrow when the element collapse
    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    //returns a new fragment based on the number passed to it
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
