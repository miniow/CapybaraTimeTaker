package com.example.projektmobilne;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AlarmViewModel extends AndroidViewModel {

    private final AlarmRepository alarmRepository;
    private final LiveData<List<Alarm>> alarms;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        alarmRepository = new AlarmRepository(application);
        alarms = alarmRepository.findAllAlarms();
    }

    public LiveData<List<Alarm>> findAll(){
        return alarms;
    }
}
