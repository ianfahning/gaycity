package project.gaycity.ui.resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OutreachViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OutreachViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Outreach fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}