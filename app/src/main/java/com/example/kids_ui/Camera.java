package com.example.kids_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;

public class Camera extends AppCompatActivity {

    private Button buttonForPhotos;
    private Button buttonForView;
    private Button backButton;
    private Button buttonTakePhoto;

    // MediaPlayer instance to play click sound
    private MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        buttonForPhotos = findViewById(R.id.buttonforphotos);
        buttonForView = findViewById(R.id.buttonforview);
        backButton = findViewById(R.id.backbutton);
        buttonTakePhoto = findViewById(R.id.buttontakephoto);

        // Set onClick listener for button to open Gallery activity
        buttonForPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Camera.this, Gallery.class);
                startActivity(gallery);
            }
        });

        // Set onClick listener for button to open CameraSelfie activity
        buttonForView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selfie = new Intent(Camera.this, CameraSelfie.class);
                startActivity(selfie);
            }
        });

        // Set onClick listener for back button to finish the activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Set onClick listener for button to take photo and play click sound
        buttonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound();
            }
        });
    }

    // Method to play the click sound
    private void clickSound() {
        if (click == null) {
            // Create MediaPlayer instance if not already created
            click = MediaPlayer.create(this, R.raw.click);
        }
        // Start playing the sound
        click.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Release MediaPlayer resources when the activity stops
        if (click != null) {
            click.release();
            click = null;
        }
    }
}
