package com.example.amal.esa.ui.admarket;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdMarketViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdMarketViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Ad Market fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}