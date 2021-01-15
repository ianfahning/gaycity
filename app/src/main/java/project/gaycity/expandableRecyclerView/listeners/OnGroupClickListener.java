package project.gaycity.expandableRecyclerView.listeners;

import androidx.recyclerview.widget.RecyclerView;
import project.gaycity.expandableRecyclerView.viewholders.GroupViewHolder;

public interface OnGroupClickListener {

    /**
     * @param flatPos the flat position (raw index within the list of visible items in the
     * RecyclerView of a GroupViewHolder)
     * @return false if click expanded group, true if click collapsed group
     */
    boolean onGroupClick(int flatPos);
}
