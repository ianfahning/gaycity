package project.gaycity.ui.health;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrepViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrepViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is prep fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}