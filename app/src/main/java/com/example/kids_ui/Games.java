package com.example.kids_ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

// Activity for displaying games
public class Games extends Activity {

    private Button backbutton; // Button to navigate back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games); // Set the content view to games layout

        // Initialize image views for each game
        ImageView gameSlitherio = findViewById(R.id.Slitherio);
        ImageView gameTetris = findViewById(R.id.Tetris);
        ImageView gamePuzzle = findViewById(R.id.Puzzle);

        backbutton = findViewById(R.id.backbutton); // Initialize back button

        // Set onClickListener for Slitherio game
        gameSlitherio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSlitherio(); // Open Slitherio game
            }
        });

        // Set onClickListener for Tetris game
        gameTetris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTetris(); // Open Tetris game
            }
        });

        // Set onClickListener for Puzzle game
        gamePuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPuzzle(); // Open Puzzle game
            }
        });

        // Set onClickListener for the back button
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Finish the current activity and return to the previous one
            }
        });
    }

    // Method to open Slitherio game
    private void openSlitherio() {
        String packageName = "air.com.hypah.io.slither"; // Package name for Slitherio game

        // Get package manager
        PackageManager pm = getPackageManager();
        // Get intent to launch Slitherio game
        Intent slitIntent = pm.getLaunchIntentForPackage(packageName);

        // Check if the intent is not null
        if (slitIntent != null) {
            startActivity(slitIntent); // Start Slitherio game
        } else {
            // If intent is null, try opening the game in Google Play Store
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                // If Google Play Store is not available, open the game in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }

    // Method to open Tetris game
    private void openTetris() {
        String packageName = "com.n3twork.tetris"; // Package name for Tetris game

        // Get package manager
        PackageManager pm = getPackageManager();
        // Get intent to launch Tetris game
        Intent tetrisIntent = pm.getLaunchIntentForPackage(packageName);

        // Check if the intent is not null
        if (tetrisIntent != null) {
            startActivity(tetrisIntent); // Start Tetris game
        } else {
            // If intent is null, try opening the game in Google Play Store
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                // If Google Play Store is not available, open the game in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }

    // Method to open Puzzle game
    private void openPuzzle() {
        String packageName = "linkdesks.jigsaw.puzzle.classic"; // Package name for Puzzle game

        // Get package manager
        PackageManager pm = getPackageManager();
        // Get intent to launch Puzzle game
        Intent puzIntent = pm.getLaunchIntentForPackage(packageName);

        // Check if the intent is not null
        if (puzIntent != null) {
            startActivity(puzIntent); // Start Puzzle game
        } else {
            // If intent is null, try opening the game in Google Play Store
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                // If Google Play Store is not available, open the game in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }
}
