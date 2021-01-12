package project.gaycity.ui.getInvolved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VolunteerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VolunteerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Get Volunteer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}