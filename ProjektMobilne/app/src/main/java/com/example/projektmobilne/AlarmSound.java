package com.example.projektmobilne;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alarm_sound_table")
public class AlarmSound {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Uri ringtoneUri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uri getRingtoneUri() {
        return ringtoneUri;
    }

    public void setRingtoneUri(Uri ringtoneUri) {
        this.ringtoneUri = ringtoneUri;
    }
}
