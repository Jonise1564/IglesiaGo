package com.example.iglesiago.ui.Contacto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ContactoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contacto fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}