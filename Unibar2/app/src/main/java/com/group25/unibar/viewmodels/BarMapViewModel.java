package com.group25.unibar.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.group25.unibar.Db.BarsDb;
import com.group25.unibar.models.Bar;

import java.util.ArrayList;

public class BarMapViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Bar>> barList;

    public BarMapViewModel() {
        super();
    }
}
