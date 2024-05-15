package com.example.kids_ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Call extends Activity {

    private Button buttonendcall;
    private Button buttonvolumeup;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        buttonendcall = findViewById(R.id.buttonendcall);
        buttonvolumeup = findViewById(R.id.buttonvolumeup);


        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        ImageView imageView = findViewById(R.id.contact_image);
        imageView.setImageBitmap(imageBitmap);


        String contactName = getIntent().getStringExtra("name");


        TextView contactNameTextView = findViewById(R.id.contact_name);
        contactNameTextView.setText(contactName);

        buttonendcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonvolumeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volumeup = new Intent(Call.this, CameraSelfie.class);
                startActivity(volumeup);
            }
        });


    }
}