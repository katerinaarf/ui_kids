package com.example.kids_ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CameraSelfie extends AppCompatActivity {

    private Button buttonforphotos;
    private Button buttonforview;
    private Button backbutton;

    private  Button buttontakephoto;

    private MediaPlayer click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerselfie);

        buttonforphotos = findViewById(R.id.buttonforphotos);
        buttonforview = findViewById(R.id.buttonforview);
        backbutton = findViewById(R.id.backbutton);
        buttontakephoto = findViewById(R.id.buttontakephoto);

        buttonforphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(CameraSelfie.this, Gallery.class);
                startActivity(gallery);            }
        });

        buttonforview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(CameraSelfie.this, Camera.class);
                startActivity(camera);            }
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