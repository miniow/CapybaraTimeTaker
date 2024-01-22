package com.example.projektmobilne;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlarmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlarm(Alarm alarm);

    @Query("SELECT * FROM alarm_table ")
    LiveData<List<Alarm>> findAll();


}
