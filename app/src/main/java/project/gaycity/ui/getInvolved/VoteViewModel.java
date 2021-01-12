package project.gaycity.ui.getInvolved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VoteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VoteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Get Vote fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}