package project.gaycity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import project.gaycity.expandableRecyclerView.ExpandableRecyclerViewAdapter;
import project.gaycity.expandableRecyclerView.models.ExpandableGroup;
import project.gaycity.ui.childViewHolder;
import project.gaycity.ui.header;
import project.gaycity.ui.parentViewHolder;
import project.gaycity.ui.subHeader;

public class expandableMenuAdapter extends ExpandableRecyclerViewAdapter<parentViewHolder, childViewHolder> {
    private final FragmentManager fm;
    private final DrawerLayout drawer;

    public expandableMenuAdapter(List<? extends ExpandableGroup> groups, FragmentManager fm, DrawerLayout drawer) {
        super(groups);
        this.fm = fm;
        this.drawer = drawer;
    }

    @Override
    public parentViewHolder onCreateGroupViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_header, parent, false);
        return new parentViewHolder(v);
    }

    @Override
    public childViewHolder onCreateChildViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_child, parent, false);
        return new childViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(childViewHolder holder, ExpandableGroup group, int childIndex) {
        final subHeader model = (subHeader) group.getItems().get(childIndex);
        holder.bind(model, fm, drawer);
    }

    @Override
    public void onBindGroupViewHolder(parentViewHolder holder, ExpandableGroup group) {
        final header model = (header) group;
        holder.bind(model, fm, drawer);
    }



}
