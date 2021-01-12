package project.gaycity.ui.getInvolved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GiveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GiveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ways to give fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}