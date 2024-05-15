package com.example.kids_ui;
import com.example.kids_ui.Settings;
import com.example.kids_ui.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;




public class Default extends Activity {

    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<View> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (!Settings.getAbilities()[0]) {

            return;
        }

        setContentView(R.layout.activity_default);

        // Add back button
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // This will simulate the back button press
            }
        });


        Button button = (Button) findViewById(R.id.add_contact);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newContact();
            }
        });
        buttons.add(button);
        items = new ArrayList<View>();
        ArrayList<Settings.Contact> contacts = Settings.getContacts();
        for (int i = 0; i < contacts.size(); i++) {
            addContact(contacts.get(i).name, contacts.get(i).number, contacts.get(i).path, i);
        }



    }


    private void editContact(int id) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(
                R.layout.activity_contact_form, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        EditText name = (EditText) deleteDialogView.findViewById(R.id.input_name);
        EditText number = (EditText) deleteDialogView.findViewById(R.id.input_number);

        String prevName = Settings.getContacts().get(id).name;
        String prevNumber = Settings.getContacts().get(id).number;

        RadioButton rMale = (RadioButton) deleteDialogView.findViewById(R.id.male);
        rMale.setChecked(true);

        name.setText(prevName);
        number.setText(prevNumber);
        deleteDialogView.setTag(id);
        deleteDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) deleteDialog.findViewById(R.id.input_name);
                EditText number = (EditText) deleteDialog.findViewById(R.id.input_number);
                RadioButton rMale = (RadioButton) deleteDialog.findViewById(R.id.male);

                boolean male = rMale.isChecked();
                Drawable icon;
                int randomIconIndex;
                if (male) {
                    randomIconIndex = (int) (Math.random() * 2) + 1;
                    if (randomIconIndex == 1) {
                        icon = getResources().getDrawable(R.drawable.ic_contact1);
                    } else {
                        icon = getResources().getDrawable(R.drawable.ic_contact3);
                    }
                } else {
                    randomIconIndex = (int) (Math.random() * 2) + 1;
                    if (randomIconIndex == 1) {
                        icon = getResources().getDrawable(R.drawable.ic_contact2);
                    } else {
                        icon = getResources().getDrawable(R.drawable.ic_contact4);
                    }
                }
                Settings.getContacts().get((int) deleteDialogView.getTag()).name = name.getText().toString();
                Settings.getContacts().get((int) deleteDialogView.getTag()).number = number.getText().toString();
                Settings.getContacts().get((int) deleteDialogView.getTag()).path = icon;
                addContact(name.getText().toString(), number.getText().toString(), icon, (int) deleteDialogView.getTag());
                deleteDialog.dismiss();
            }
        });

        deleteDialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();

            }
        });
        deleteDialog.show();
    }

    private void newContact() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(
                R.layout.activity_contact_form, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        RadioButton rMale = (RadioButton) deleteDialogView.findViewById(R.id.male);

        rMale.setChecked(true);
        deleteDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) deleteDialog.findViewById(R.id.input_name);
                EditText number = (EditText) deleteDialog.findViewById(R.id.input_number);
                RadioButton rMale = (RadioButton) deleteDialog.findViewById(R.id.male);

                boolean male = rMale.isChecked();
                Drawable icon;
                int randomIconIndex;
                if (male) {
                    randomIconIndex = (int) (Math.random() * 2) + 1;
                    if (randomIconIndex == 1) {
                        icon = getResources().getDrawable(R.drawable.ic_contact1);
                    } else {
                        icon = getResources().getDrawable(R.drawable.ic_contact3);
                    }
                } else {
                    randomIconIndex = (int) (Math.random() * 2) + 1;
                    if (randomIconIndex == 1) {
                        icon = getResources().getDrawable(R.drawable.ic_contact2);
                    } else {
                        icon = getResources().getDrawable(R.drawable.ic_contact4);
                    }
                }
                Settings.getContacts().add(new Settings.Contact(name.getText().toString(), number.getText().toString(), icon));
                addContact(name.getText().toString(), number.getText().toString(), icon, items.size());

                deleteDialog.dismiss();
            }
        });

        deleteDialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();

            }
        });

        deleteDialog.show();
    }

    private int addContact(String name, String number, Drawable pathToImage, int id) {

        TableLayout table = (TableLayout) findViewById(R.id.contact_list);
        if (id >= items.size()) {
            View items1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_contact_row, null);
            TextView nameV = (TextView) items1.findViewById(R.id.contact_name);
            TextView numberV = (TextView) items1.findViewById(R.id.contact_number);
            nameV.setText(name);
            numberV.setText(number);

            ImageView image = (ImageView) items1.findViewById(R.id.contact_image);
            image.setImageDrawable(pathToImage);
            ImageView edit = (ImageView) items1.findViewById(R.id.edit_contact);
            edit.setTag(id);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    editContact(items.indexOf(view.getParent().getParent().getParent()));
                }
            });
            ImageView delete = (ImageView) items1.findViewById(R.id.delete_contact);
            delete.setTag(id);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove(items.indexOf(view.getParent().getParent().getParent()));
                }
            });
            items.add(items1);
            table.addView(items1);
            TextView counter = (TextView) findViewById(R.id.manage_contacts);
            counter.setText("Manage Contacts:(" + items.size() + "/3)");
            if (items.size() == 3) {
                buttons.get(0).setVisibility(View.INVISIBLE);
            }
        } else {
            View items1 = table.getChildAt(id);
            TextView nameV = (TextView) items1.findViewById(R.id.contact_name);
            TextView numberV = (TextView) items1.findViewById(R.id.contact_number);
            nameV.setText(name);
            numberV.setText(number);
            ImageView image = (ImageView) items1.findViewById(R.id.contact_image);
            image.setImageDrawable(pathToImage);
        }

        return id;

    }

    private void remove(int i) {
        TableLayout table = (TableLayout) findViewById(R.id.contact_list);
        table.removeView(items.get(i));
        items.remove(i);
        Settings.getContacts().remove(i);
        TextView counter = (TextView) findViewById(R.id.manage_contacts);
        counter.setText("Manage Contacts:(" + items.size() + "/3)");

        if (items.size() == 2) {
            buttons.get(0).setVisibility(View.VISIBLE);
        }
    }



}