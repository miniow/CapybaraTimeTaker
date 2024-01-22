package com.example.projektmobilne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private AlarmViewModel alarmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.alarm_list_recycler_view);
        final AlarmAdapter adapter = new AlarmAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        alarmViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);
        alarmViewModel.findAll().observe(this,adapter::setAlarms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public class AlarmHolder extends RecyclerView.ViewHolder {
        private Alarm alarm;
        private TextView timeTextView;
        private TextView alarmNameTextView;
        private Switch enableSwitch;

        public AlarmHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.alarm_list_item,parent,false));
            alarm = new Alarm();
            timeTextView = itemView.findViewById(R.id.time_text_view);
            alarmNameTextView = itemView.findViewById(R.id.alarm_name_text_view);
            enableSwitch = itemView.findViewById(R.id.enable_switch);
        }
        public void bind(Alarm alarm){
            if (alarm != null) {
                this.alarm = alarm;
                alarmNameTextView.setText(alarm.getName());
                enableSwitch.setChecked(alarm.isAlarmIsOn());
                timeTextView.setText(formatDate(alarm.getDate()));

            }
        }
    }
    private static String formatDate(long dateInMillis) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return formatter.format(new Date(dateInMillis));
    }
    private class AlarmAdapter extends RecyclerView.Adapter<AlarmHolder>{

        private List<Alarm> alarms;

        @NonNull
        @Override
        public AlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AlarmHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AlarmHolder holder, int position) {
            Alarm alarm = alarms.get(position);
            holder.bind(alarm);
        }

        @Override
        public int getItemCount() {
            return (alarms != null) ? alarms.size() : 0;
        }
        public void setAlarms(List<Alarm> alarms){
            if(alarms!=null){
                this.alarms = alarms;
            }

            notifyDataSetChanged();
        }
    }

}