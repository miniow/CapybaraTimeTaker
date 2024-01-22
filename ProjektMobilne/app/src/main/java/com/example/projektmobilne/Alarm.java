package com.example.projektmobilne;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Nullable;

@Entity(tableName = "alarm_table")
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private long date;
    private float soundVolume;
    private boolean alarmIsOn;
    private int alarmSoundId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isAlarmIsOn() {
        return alarmIsOn;
    }

    public void setAlarmIsOn(boolean alarmIsOn) {
        this.alarmIsOn = alarmIsOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    public int getAlarmSoundId() {
        return alarmSoundId;
    }

    public void setAlarmSoundId(int alarmSoundId) {
        this.alarmSoundId = alarmSoundId;
    }
}