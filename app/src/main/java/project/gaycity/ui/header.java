package project.gaycity.ui;

import java.util.List;

import project.gaycity.expandableRecyclerView.models.ExpandableGroup;

public class header extends ExpandableGroup<subHeader> {
    boolean isArrow;
    int fragment;
    public header(String name, List<subHeader> items,boolean isArrow, int fragment) {
        super(name, items);
        this.isArrow = isArrow;
        this.fragment = fragment;
    }
}
