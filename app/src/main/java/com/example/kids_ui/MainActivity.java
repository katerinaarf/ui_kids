package com.example.kids_ui;

import static com.example.kids_ui.Settings.setUp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView appGallery = findViewById(R.id.Gallery);
        ImageView appPainting = findViewById(R.id.Painting);
        ImageView appMusic = findViewById(R.id.Music);
        ImageView appPhone = findViewById(R.id.Phone);
        ImageView appGames = findViewById(R.id.Games);
        ImageView appCamera = findViewById(R.id.Camera);

        ImageButton settingsButton = findViewById(R.id.settingsButton);
        setUp(getApplicationContext());


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent defaultIntent = new Intent(MainActivity.this, Default.class);
                startActivity(defaultIntent);
            }
        });


        appGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(MainActivity.this, Gallery.class);
                startActivity(galleryIntent);
            }
        });

        appPainting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPainting();
            }
        });

        appMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeKids();
            }
        });


        appPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(MainActivity.this, Contacts.class);
                startActivity(phoneIntent);
            }
        });

        appGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gamesIntent = new Intent(MainActivity.this, Games.class);
                startActivity(gamesIntent);
            }
        });


        appCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MainActivity.this, Camera.class);
                startActivity(cameraIntent);
            }
        });
    }

    private void openPainting(){
        String packageName = "com.adsk.sketchbook";

        PackageManager pm = getPackageManager();
        Intent appIntent = pm.getLaunchIntentForPackage(packageName);

        if(appIntent != null)
        {
            startActivity(appIntent);
        } else{
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            }catch (android.content.ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }



    private void openYouTubeKids() {
        // Package name of YouTube Kids app
        String packageName = "com.google.android.apps.youtube.kids";

        // Check if YouTube Kids app is installed on the device
        PackageManager pm = getPackageManager();
        Intent appIntent = pm.getLaunchIntentForPackage(packageName);

        if (appIntent != null) {
            // YouTube Kids app is installed, open it
            startActivity(appIntent);
        } else {
            // YouTube Kids app is not installed, open Play Store to install it
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                // If Play Store is N/A, open browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }



}


   
