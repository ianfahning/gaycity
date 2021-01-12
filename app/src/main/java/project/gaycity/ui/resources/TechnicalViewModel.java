package project.gaycity.ui.resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TechnicalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TechnicalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Technical assistance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}