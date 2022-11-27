package com.ualr.recyclerviewassignment;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.Utils.Tools;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Inbox> selected = new MutableLiveData<Inbox>();
    public void select(Inbox item){
        selected.setValue(item);
    }
    public LiveData<Inbox> getSelected(){
        return selected;
    }
}