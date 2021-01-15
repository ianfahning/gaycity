package project.gaycity.ui.health;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppointmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AppointmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Appointment Hours");
    }

    public LiveData<String> getText() {
        return mText;
    }
}