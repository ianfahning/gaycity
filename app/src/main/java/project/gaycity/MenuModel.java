package project.gaycity;

import android.view.View;

import androidx.fragment.app.Fragment;

public class MenuModel {

    public String name;
    public boolean hasChildren, isGroup;
    public int view;

    public MenuModel(String name, int view, boolean isGroup, boolean hasChildren) {

        this.name = name;
        this.view = view;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }
}
