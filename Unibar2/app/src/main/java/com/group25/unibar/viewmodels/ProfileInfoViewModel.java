package com.group25.unibar.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.group25.unibar.models.User;

public class ProfileInfoViewModel extends ViewModel {

    private MutableLiveData<User> user;

    public LiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<User>();
            loadUser();
        }
        return user;
    }

    private void loadUser() {
        // Do an asynchronous operation to fetch user.
    }

    private void saveUserChanges() {
        // Do an asynchronous operation to save user changes.
    }

}
