package com.example.kids_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// Activity for displaying the gallery
public class Gallery extends Activity {

    private Button backbutton; // Button to navigate back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery); // Set the content view to gallery layout

        backbutton = findViewById(R.id.backbutton); // Initialize back button

        // Set onClickListener for the back button
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Finish the current activity and return to the previous one
            }
        });
    }
}
