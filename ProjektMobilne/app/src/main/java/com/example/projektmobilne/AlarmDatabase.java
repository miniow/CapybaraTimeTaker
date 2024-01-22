package com.example.projektmobilne;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Database(entities = {Alarm.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AlarmDatabase extends RoomDatabase {
    private static AlarmDatabase databaseInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract AlarmDao alarmDao();
    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{

                AlarmDao dao = databaseInstance.alarmDao();
                Date data = new Date();

                long randomValue = ThreadLocalRandom.current().nextLong();
                data.setTime(565555);

                Alarm alarm1 = new Alarm();
                alarm1.setDate(data.getTime());
                alarm1.setName("Alarm 1");
                alarm1.setAlarmIsOn(true);

                randomValue = ThreadLocalRandom.current().nextLong();
                data.setTime(randomValue);

                Alarm alarm2 = new Alarm();
                alarm2.setDate(data.getTime());
                alarm2.setName("Alarm 2");
                alarm2.setAlarmIsOn(false);

                randomValue = ThreadLocalRandom.current().nextLong();
                data.setTime(randomValue);
                Alarm alarm3 = new Alarm();
                alarm3.setDate(data.getTime());
                alarm3.setName("Alarm 1");
                alarm3.setAlarmIsOn(true);

                dao.insertAlarm(alarm1);
                dao.insertAlarm(alarm2);
                dao.insertAlarm(alarm3);
            });
        }
    };

    static AlarmDatabase getDatabaseInstance(final Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AlarmDatabase.class, "alarm_database")
                    .addCallback(roomDatabaseCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databaseInstance;
    }

}
