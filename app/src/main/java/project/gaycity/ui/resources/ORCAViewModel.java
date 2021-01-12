package project.gaycity.ui.resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ORCAViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ORCAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ORCA fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}