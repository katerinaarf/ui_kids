package com.example.kids_ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Contacts extends Activity {
    // List to hold dynamically created buttons
    ArrayList<Button> buttons = new ArrayList<Button>();
    Context myContext;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Initialize home button and set its click listener to finish the activity
        button = findViewById(R.id.homebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Get contacts from Settings
        ArrayList<Settings.Contact> contacts = Settings.getContacts();
        for (int i = 0; i < contacts.size(); i++) {
            // Retrieve each contact and add to the layout
            Settings.Contact temp = contacts.get(i);
            System.out.println(temp.name + " " + temp.number + " " + temp.path);
            addContact(temp.name, temp.number, temp.path, i);
        }
    }

    // Method to add contact views dynamically to the layout
    private void addContact(String name, String number, Drawable bmp, int pos) {
        RelativeLayout table = null;
        // Select the appropriate RelativeLayout based on the position
        switch (pos) {
            case 0:
                table = findViewById(R.id.contact_2);
                break;
            case 1:
                table = findViewById(R.id.contact_3);
                break;
            case 2:
                table = findViewById(R.id.contact_1);
                break;
            case 3:
                table = findViewById(R.id.contact_4);
                break;
        }

        // Inflate the single_person layout and populate it with contact details
        View items1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.single_person, null);
        TextView names = items1.findViewById(R.id.Name);
        names.setText(name);
        ImageView image = items1.findViewById(R.id.Picture);
        image.setImageDrawable(bmp);
        items1.setTag(name);

        // Set click listener for the contact view
        items1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initiate call when the contact is clicked
                callNumber((String) view.getTag(), image.getDrawable());
            }
        });

        // Add the contact view to the appropriate RelativeLayout
        if (table != null) table.addView(items1);
    }

    // Method to start Call activity with the contact's name and image
    public void callNumber(String name, Drawable image) {
        Intent callIntent = new Intent(Contacts.this, Call.class);
        callIntent.putExtra("name", name);

        // Convert the Drawable to a byte array to pass to the Call activity
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ((BitmapDrawable) image).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        callIntent.putExtra("image", byteArray);

        // Start the Call activity
        startActivity(callIntent);
    }
}
