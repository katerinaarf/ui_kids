package com.example.kids_ui;

import static com.example.kids_ui.Settings.setUp;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.pm.PackageInfo;
import androidx.appcompat.app.AppCompatActivity;

// Main activity of the Kids UI application
public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content view to the main layout

        // Initialize image views for each application
        ImageView appGallery = findViewById(R.id.Gallery);
        ImageView appPainting = findViewById(R.id.Painting);
        ImageView appMusic = findViewById(R.id.Music);
        ImageView appPhone = findViewById(R.id.Phone);
        ImageView appGames = findViewById(R.id.Games);
        ImageView appCamera = findViewById(R.id.Camera);

        ImageButton settingsButton = findViewById(R.id.settingsButton);
        setUp(getApplicationContext()); // Set up application settings

        // Set onClickListener for settings button
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent defaultIntent = new Intent(MainActivity.this, Default.class);
                startActivity(defaultIntent); // Open settings activity
            }
        });

        // Set onClickListener for Gallery application
        appGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(MainActivity.this, Gallery.class);
                startActivity(galleryIntent); // Open gallery activity
            }
        });

        // Set onClickListener for Painting application
        appPainting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPainting(); // Open painting application
            }
        });

        // Set onClickListener for Music application
        appMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeKids(); // Open YouTube Kids application for music
            }
        });

        // Set onClickListener for Phone application
        appPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(MainActivity.this, Contacts.class);
                startActivity(phoneIntent); // Open contacts activity
            }
        });

        // Set onClickListener for Games application
        appGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gamesIntent = new Intent(MainActivity.this, Games.class);
                startActivity(gamesIntent); // Open games activity
            }
        });

        // Set onClickListener for Camera application
        appCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MainActivity.this, Camera.class);
                startActivity(cameraIntent); // Open camera activity
            }
        });
    }

    // Method to open the Painting application
    private void openPainting() {
        String packageName = "com.adsk.sketchbook"; // Package name for the painting application

        PackageManager pm = getPackageManager();
        Intent appIntent = pm.getLaunchIntentForPackage(packageName);

        // Check if the intent to launch the painting application is not null
        if (appIntent != null) {
            startActivity(appIntent); // Start the painting application
        } else {
            // If the painting application is not installed, try to open it in the Play Store
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                // If the Play Store is not available, open the app in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }

    // Method to open YouTube Kids application
    public void openYouTubeKids() {
        // Package name of YouTube Kids application
        String packageName = "com.google.android.apps.youtube.kids";
        PackageManager pm = getPackageManager();

        try {
            // Check if YouTube Kids application is installed on the device
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                // YouTube Kids application is installed, try to get launch intent
                Intent appIntent = pm.getLaunchIntentForPackage(packageName);
                if (appIntent != null) {
                    // YouTube Kids application is installed, open it
                    appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(appIntent);
                } else {
                    // If we couldn't get the launch intent, log the issue
                    Log.e("openYouTubeKids", "Launch intent is null for package: " + packageName);
                    openPlayStore(packageName);
                }
            } else {
                // Package info is null, the app is not installed
                Log.e("openYouTubeKids", "Package info is null for package: " + packageName);
                openPlayStore(packageName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            // YouTube Kids application is not installed
            Log.e("openYouTubeKids", "YouTube Kids application is not installed: " + e.getMessage());
            openPlayStore(packageName);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            Log.e("openYouTubeKids", "Unexpected error: " + e.getMessage());
            openPlayStore(packageName);
        }
    }

    // Method to open the Play Store for installing an application
    private void openPlayStore(String packageName) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException e) {
            // If the Play Store is not available, open a web browser
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }
}
