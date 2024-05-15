package com.example.kids_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;


import androidx.appcompat.app.AppCompatActivity;

public class Camera extends AppCompatActivity {

    private Button buttonforphotos;
    private Button buttonforview;
    private Button backbutton;
    private  Button buttontakephoto;
    private MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        buttonforphotos = findViewById(R.id.buttonforphotos);
        buttonforview = findViewById(R.id.buttonforview);
        backbutton = findViewById(R.id.backbutton);
        buttontakephoto = findViewById(R.id.buttontakephoto);

        buttonforphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Camera.this, Gallery.class);
                startActivity(gallery);            }
        });

        buttonforview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selfie = new Intent(Camera.this, CameraSelfie.class);
                startActivity(selfie);            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttontakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound();
            }
        });

    }
    private void clickSound() {
        if (click == null) {
            click = MediaPlayer.create(this, R.raw.click);
        }
        click.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (click != null) {
            click.release();
            click = null;
        }
    }
}