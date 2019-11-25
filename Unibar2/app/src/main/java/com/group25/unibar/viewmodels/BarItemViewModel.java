package com.group25.unibar.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.group25.unibar.models.BarInfo;

import java.util.List;

 // https://developer.android.com/topic/libraries/architecture/viewmodel#sharing

public class BarItemViewModel extends ViewModel {

    private final MutableLiveData<BarInfo> barInfo = new MutableLiveData<>();


    public void select(BarInfo item) {
        barInfo.setValue(item);
    }

    public LiveData<BarInfo> getSelected() {
        return barInfo;
    }

    public void getBarList(){
        // Return bar list for sharing between fragments

    }

}
