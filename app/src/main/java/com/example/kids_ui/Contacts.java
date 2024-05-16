package com.example.kids_ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
    ArrayList<Button> buttons = new ArrayList<Button>();
    Context myContext;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        button = findViewById(R.id.homebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ArrayList<Settings.Contact> contacts = Settings.getContacts();
        for (int i = 0; i < contacts.size(); i++) {
            Settings.Contact temp = contacts.get(i);
            System.out.println(temp.name + " " + temp.number + " " + temp.path);
            addContact(temp.name, temp.number, temp.path, i);
        }

    }

    private void addContact(String name, String number, Drawable bmp, int pos) {
        RelativeLayout table = null;
        switch (pos) {
            case 0:
                table = (RelativeLayout) findViewById(R.id.contact_2);
                break;
            case 1:
                table = (RelativeLayout) findViewById(R.id.contact_3);
                break;
            case 2:
                table = (RelativeLayout) findViewById(R.id.contact_1);
                break;
            case 3:
                table = (RelativeLayout) findViewById(R.id.contact_4);
                break;
        }
        View items1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.single_person, null);
        TextView names = (TextView) items1.findViewById(R.id.Name);
        names.setText(name);
        ImageView image = (ImageView) items1.findViewById(R.id.Picture);
        image.setImageDrawable(bmp);
        items1.setTag(number);

        items1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callnumber((String) view.getTag(), image.getDrawable());
            }
        });
        if (table != null) table.addView(items1);

    }


    public void callnumber(String name, Drawable image) {
        Intent callIntent = new Intent(Contacts.this, Call.class);
        callIntent.putExtra("name", name);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ((BitmapDrawable)image).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        callIntent.putExtra("image", byteArray);

        startActivity(callIntent);
    }

}
