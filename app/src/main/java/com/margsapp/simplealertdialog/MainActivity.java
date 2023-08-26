package com.margsapp.simplealertdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    AppCompatButton show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = findViewById(R.id.show);


        show.setOnClickListener(v -> {
            SimpleAlertDialog.Builder
                    .with(MainActivity.this)
                    .setTitle("Testing")
                    .setDescription("This is a test message")
                    .setBackground(getDrawable(R.drawable.popup_back))
                    .setDescriptionColor(getResources().getColor(R.color.black))
                    .setNegativeButtonBack(getDrawable(R.drawable.negative_btn_back))
                    .setNegativeText("No")
                    .setNegativeButtonTextColor(getResources().getColor(R.color.black))
                    .setPositiveButtonBack(getDrawable(R.drawable.positive_btn_back))
                    .setPositiveText("Yes")
                    .setTitleColor(getResources().getColor(R.color.black))
                    .setPositiveButtonTextColor(getResources().getColor(R.color.white))
                    .isCancellable(true)
                    .onNegativeClicked(dialog -> Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show())
                    .onPositiveClicked(dialog -> Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show())
                    .build();

            Toast.makeText(MainActivity.this, "Popup Show", Toast.LENGTH_SHORT).show();
        });
    }
}