package com.example.projektmobilne;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlarmRepository {
    private final AlarmDao alarmDao;
    private final LiveData<List<Alarm>> alarms;

    AlarmRepository(Application application){
        AlarmDatabase database = AlarmDatabase.getDatabaseInstance(application);
        alarmDao = database.alarmDao();
        alarms = alarmDao.findAll();
    }

    LiveData<List<Alarm>> findAllAlarms(){
        return alarms;
    }

    void insert(Alarm alarm){
        AlarmDatabase.databaseWriteExecutor.execute(()-> alarmDao.insertAlarm(alarm));
    }
}
