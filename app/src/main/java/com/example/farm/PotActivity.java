package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PotActivity extends AppCompatActivity {

    private Button CheckPotButton, ControlSensorButton, SelectOptionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pot);

        CheckPotButton = findViewById(R.id.CheckPotButton);
        ControlSensorButton = findViewById(R.id.ControlSensorButton);
        SelectOptionButton = findViewById(R.id.SelectOptionButton);

        CheckPotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.101/serial_monitor.php"));
                startActivity(intent);
            }
        });

        ControlSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PotActivity.this, ControllActivity.class);
                startActivity(intent);
            }
        });

        SelectOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(PotActivity.this, ControllActivity.class);
                //startActivity(intent);
            }
        });


    }
}