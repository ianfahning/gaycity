package project.gaycity.ui.health;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestResultsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TestResultsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is get test results fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}