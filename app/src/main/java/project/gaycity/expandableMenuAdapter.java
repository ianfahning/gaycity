package project.gaycity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.gaycity.expandableRecyclerView.ExpandableRecyclerViewAdapter;
import project.gaycity.expandableRecyclerView.models.ExpandableGroup;
import project.gaycity.ui.childViewHolder;
import project.gaycity.ui.header;
import project.gaycity.ui.parentViewHolder;
import project.gaycity.ui.subHeader;

public class expandableMenuAdapter extends ExpandableRecyclerViewAdapter<parentViewHolder, childViewHolder>{
    private FragmentManager fm;
    private RecyclerView recyclerView;
    private DrawerLayout drawer;

    public expandableMenuAdapter(List<? extends ExpandableGroup> groups, FragmentManager fm, RecyclerView recyclerView, DrawerLayout drawer){
        super(groups);
        this.fm = fm;
        this.recyclerView = recyclerView;
        this.drawer = drawer;
    }

    @Override
    public parentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_header,parent,false);
        return new parentViewHolder(v);
    }

    @Override
    public childViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_child,parent,false);
        return new childViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(childViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final subHeader model = (subHeader) group.getItems().get(childIndex);
        holder.bind(model,fm,drawer);
    }

    @Override
    public void onBindGroupViewHolder(parentViewHolder holder, int flatPosition, ExpandableGroup group) {
        final header model = (header) group;
        holder.bind(model,fm,drawer);
    }



}
