package com.example.walden;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.walden.MainActivity.display_mode;
import static com.example.walden.MainActivity.edit_mode;
import static com.example.walden.MainActivity.hour_waldenZone;
import static com.example.walden.MainActivity.interval_waldenZone;
import static com.example.walden.MainActivity.minute_waldenZone;
import static com.example.walden.MainActivity.turned_on;

public class gridAdaptor extends ArrayAdapter<MainSetting> {

    private Context context;
    private int resource;
    private List<MainSetting> settingList;

    public gridAdaptor(@NonNull Context context, int resource, @NonNull List<MainSetting> restaurantList) {
        super(context, resource, restaurantList);
        this.context = context;
        this.resource = resource;
        this.settingList = restaurantList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview, parent, false);
        }

        final RelativeLayout container = convertView.findViewById(R.id.container);
        TextView name = convertView.findViewById(R.id.item_name);
        final ToggleButton on = convertView.findViewById(R.id.item_on);
        final Button editButton = convertView.findViewById(R.id.button);
        final Drawable edit_icon = editButton.getBackground();
        final LinearLayout edit = convertView.findViewById(R.id.linear_edit);
        final LinearLayout display = convertView.findViewById(R.id.linear_info);
        final TimePicker timePicker = convertView.findViewById(R.id.time_picker);
        final NumberPicker numberPicker = convertView.findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(18);
        final TextView timeSetting = convertView.findViewById(R.id.textview_timeSetting);
        final TextView modeSetting = convertView.findViewById(R.id.textview_modeSetting);
        final TextView countdown = convertView.findViewById(R.id.textview_countdown);


        final MainSetting currentSetting = settingList.get(position);
        name.setText(currentSetting.getName());
        on.setChecked(currentSetting.getOn());
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turned_on[0] == false) {
                    editButton.setVisibility(View.VISIBLE);
                    editButton.setEnabled(true);
                    turned_on[0] = true;
                } else {
                    if (edit_mode[0] == false) {
                        editButton.setVisibility(View.INVISIBLE);
                        editButton.setEnabled(false);
                        turned_on[0] = false;
                    } else {
                        turned_on[0] = true;
                    }
                }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display_mode[0] == true) {
                    display_mode[0] = false;
                    display.setVisibility(View.GONE);
                }
                if (edit_mode[0] == false) {
                    edit.setVisibility(View.VISIBLE);
                    edit_mode[0] = true;
                    editButton.setBackgroundColor(Color.LTGRAY);
                    editButton.setText("finish");
                    interval_waldenZone = numberPicker.getValue();
                    hour_waldenZone = timePicker.getHour();
                    minute_waldenZone = timePicker.getMinute();
                    timeSetting.setText("Time Set at: " + hour_waldenZone+"h"+minute_waldenZone+"m,  "+"Interval: " + interval_waldenZone + "h");
                } else {
                    edit.setVisibility(View.GONE);
                    edit_mode[0] = false;
                    editButton.setBackground(edit_icon);
                    editButton.setText(" ");
                }
            }
        });

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display_mode[0] == false && edit_mode[0] == false && turned_on[0] == true) {
                    display_mode[0] = true;
                    display.setVisibility(View.VISIBLE);
                    if (hour_waldenZone == 0 && minute_waldenZone == 0 && interval_waldenZone == 0) {
                        modeSetting.setText("You haven't set up a time yet");
                        timeSetting.setText("Click the Edit button to get started...");
                        countdown.setText(" ");
                    } else {
                        modeSetting.setText("Mode: ");
                        timeSetting.setText("Time Set at: " + hour_waldenZone+"h"+minute_waldenZone+"m,  "+"Interval: " + interval_waldenZone + "h");
                        countdown.setText("Time till next walden zone: ");
                    }
                } else {
                    display_mode[0] = false;
                    display.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }
}
