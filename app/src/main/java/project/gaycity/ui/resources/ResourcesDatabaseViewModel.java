package project.gaycity.ui.resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResourcesDatabaseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ResourcesDatabaseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is resources database fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}