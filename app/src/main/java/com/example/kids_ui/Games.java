package com.example.kids_ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Games extends Activity {

    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        ImageView gameSlitherio = findViewById(R.id.Slitherio);
        ImageView gameTetris = findViewById(R.id.Tetris);
        ImageView gamePuzzle = findViewById(R.id.Puzzle);

        backbutton = findViewById(R.id.backbutton);


        gameSlitherio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSlitherio();
            }
        });

        gameTetris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTetris();
            }
        });

        gamePuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPuzzle();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void openSlitherio() {
        String packageName = "air.com.hypah.io.slither";

        PackageManager pm = getPackageManager();
        Intent slitIntent = pm.getLaunchIntentForPackage(packageName);

        if (slitIntent != null) {
            startActivity(slitIntent);
        } else {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }

    private void openTetris() {
        String packageName = "com.n3twork.tetris";

        PackageManager pm = getPackageManager();
        Intent tetrisIntent = pm.getLaunchIntentForPackage(packageName);

        if (tetrisIntent != null) {
            startActivity(tetrisIntent);
        } else {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }

    private void openPuzzle() {
        String packageName = "linkdesks.jigsaw.puzzle.classic";

        PackageManager pm = getPackageManager();
        Intent puzIntent = pm.getLaunchIntentForPackage(packageName);

        if (puzIntent != null) {
            startActivity(puzIntent);
        } else {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }
}