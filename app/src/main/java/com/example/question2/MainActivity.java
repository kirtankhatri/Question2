package com.example.question2;

import android.content.SharedPreferences;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    Switch soundSwitch,vibrationSwitch,LEDSwitch,showBannersSwitch,showContentSwitch,showOnLockScreenSwitch;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        soundSwitch = findViewById(R.id.soundSwitch);
        vibrationSwitch = findViewById(R.id.vibrationSwitch);
        LEDSwitch = findViewById(R.id.ledSwitch);
        showBannersSwitch = findViewById(R.id.showBannersSwitch);
        showContentSwitch = findViewById(R.id.showContentSwitch);
        showOnLockScreenSwitch = findViewById(R.id.showOnLockScreenSwitch);

        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener((e)->{
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.confirmation_dialog,null);
            Button yesBtn = v.findViewById(R.id.yesBtn);
            Button noBtn = v.findViewById(R.id.noBtn);
            bottomSheetDialog.setContentView(v);

            yesBtn.setOnClickListener((e2)->{
                saveToSharedPreference();
                bottomSheetDialog.hide();
                Toast.makeText(MainActivity.this, "Preferences stored successfully", Toast.LENGTH_SHORT).show();
            });

            noBtn.setOnClickListener((e2)->{
                bottomSheetDialog.hide();
            });

            bottomSheetDialog.show();
        });

        readSharedPreferences();
    }

    public void saveToSharedPreference(){
        SharedPreferences sf = getSharedPreferences("localStorage",MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean("sound",soundSwitch.isChecked());
        editor.putBoolean("vibration",vibrationSwitch.isChecked());
        editor.putBoolean("led",LEDSwitch.isChecked());
        editor.putBoolean("show_banners",showBannersSwitch.isChecked());
        editor.putBoolean("show_content",showContentSwitch.isChecked());
        editor.putBoolean("show_on_lock_screen",showOnLockScreenSwitch.isChecked());
        editor.commit();
    }

    public void readSharedPreferences(){
        SharedPreferences sf = getSharedPreferences("localStorage",MODE_PRIVATE);
        soundSwitch.setChecked(sf.getBoolean("sound",false));
        vibrationSwitch.setChecked(sf.getBoolean("vibration",false));
        LEDSwitch.setChecked(sf.getBoolean("led",false));
        showBannersSwitch.setChecked(sf.getBoolean("show_banners",false));
        showContentSwitch.setChecked(sf.getBoolean("show_content",false));
        showOnLockScreenSwitch.setChecked(sf.getBoolean("show_on_lock_Screen",false));
    }

}