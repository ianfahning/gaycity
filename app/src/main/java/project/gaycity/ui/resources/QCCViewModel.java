package project.gaycity.ui.resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QCCViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QCCViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is QCC fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}