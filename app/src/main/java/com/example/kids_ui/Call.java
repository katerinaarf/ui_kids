package com.example.kids_ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Call extends Activity {

    private Button buttonEndCall;
    private Button buttonVolumeUp;

    // MediaPlayer instance to play tap sound
    private MediaPlayer tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Initialize buttons
        buttonEndCall = findViewById(R.id.buttonendcall);
        buttonVolumeUp = findViewById(R.id.buttonvolumeup);

        // Get the image byte array from the intent
        // Decode the byte array into a Bitmap
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        // Find the ImageView and set the decoded Bitmap
        ImageView imageView = findViewById(R.id.contact_image);
        imageView.setImageBitmap(imageBitmap);

        // Get the contact name from the intent
        String contactName = getIntent().getStringExtra("name");

        // Find the TextView and set the contact name
        TextView contactNameTextView = findViewById(R.id.contact_name);
        contactNameTextView.setText(contactName);

        // Set the onClick listener for the end call button
        buttonEndCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // End the activity when the end call button is pressed
                finish();
            }
        });

        // Set the onClick listener for the volume up button
        buttonVolumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Play the click sound when the volume up button is pressed
                clickSound();
            }
        });
    }

    // Method to play the click sound
    private void clickSound() {
        if (tap == null) {
            // Create MediaPlayer instance if not already created
            tap = MediaPlayer.create(this, R.raw.tap);
        }
        // Start playing the sound
        tap.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Release MediaPlayer resources when the activity stops
        if (tap != null) {
            tap.release();
            tap = null;
        }
    }
}
