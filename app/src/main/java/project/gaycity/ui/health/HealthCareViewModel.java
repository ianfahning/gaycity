package project.gaycity.ui.health;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HealthCareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HealthCareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is health care fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}