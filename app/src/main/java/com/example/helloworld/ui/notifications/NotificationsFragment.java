package com.example.helloworld.ui.notifications;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.R;
import com.example.helloworld.databinding.FragmentNotificationsBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.sql.Time;

public class NotificationsFragment extends Fragment {

    public static final String TAG="Timer";
    private FragmentNotificationsBinding binding;
    MaterialButtonToggleGroup toggleButtons;
    CountDownTimer cdt;
    public long seconds;
    private boolean isRunning;

    TextView remainingTime;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView remainingTime = root.findViewById(R.id.timer);
        NumberPicker numPicker = root.findViewById(R.id.numberPicker);
        numPicker.setMinValue(0);
        numPicker.setMaxValue(60);
        String[] strArray = new String[61];
        for(int i=0; i<strArray.length; i++){
            strArray[i] = i + "s";
        }
        numPicker.setDisplayedValues(strArray);
        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                seconds = numberPicker.getValue();
                Log.i(TAG, seconds + "");
                seconds *= 1000;

            }
        });

        MaterialButtonToggleGroup toggleButtons = root.findViewById(R.id.toggleButtonGroup);

        toggleButtons.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        if (isChecked) {
                            if (checkedId == R.id.timerStart) {
                                cdt = new CountDownTimer(seconds, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        Log.i(TAG, seconds + "s");
                                        seconds = millisUntilFinished;
                                        remainingTime.setText(millisUntilFinished / 1000 + "s");
                                    }
                                    public void onFinish() {
                                        remainingTime.setText("Done");

                                    }
                                }.start();
                                isRunning = true;
                                Log.i(TAG, "start");
                            } else if (checkedId == R.id.timerPause) {
                                Log.i(TAG, "pause");
                                cdt.cancel();

                            } else if (checkedId == R.id.timerStop) {
                                Log.i(TAG, "stop");
                                cdt.cancel();
                                remainingTime.setText("Done");
                            }
                        }
                    }
                });

        //final TextView textView = binding.textNotifications;
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}