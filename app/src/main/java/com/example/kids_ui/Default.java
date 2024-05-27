package com.example.kids_ui;

// Import necessary libraries
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

    // ArrayLists to store buttons and views
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<View> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Check if certain ability is enabled, if not, return
        if (!Settings.getAbilities()[0]) {
            return;
        }

        // Set the content view to the default activity layout
        setContentView(R.layout.activity_default);

        // Add back button functionality
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // This will simulate the back button press
            }
        });

        // Add functionality to add contact button
        Button button = (Button) findViewById(R.id.add_contact);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newContact(); // Call method to create a new contact
            }
        });
        buttons.add(button); // Add the button to the list of buttons
        items = new ArrayList<View>(); // Initialize the list of views
        ArrayList<Settings.Contact> contacts = Settings.getContacts(); // Get existing contacts from settings
        // Loop through existing contacts and add them to the UI
        for (int i = 0; i < contacts.size(); i++) {
            addContact(contacts.get(i).name, contacts.get(i).number, contacts.get(i).path, i);
        }
    }

    // Method to edit a contact
    private void editContact(int id) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(
                R.layout.activity_contact_form, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        // Get references to input fields and set previous values
        EditText name = (EditText) deleteDialogView.findViewById(R.id.input_name);
        EditText number = (EditText) deleteDialogView.findViewById(R.id.input_number);
        String prevName = Settings.getContacts().get(id).name;
        String prevNumber = Settings.getContacts().get(id).number;
        name.setText(prevName);
        number.setText(prevNumber);
        deleteDialogView.setTag(id); // Set tag to identify the contact
        // Set onClickListener for save button
        deleteDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                EditText name = (EditText) deleteDialog.findViewById(R.id.input_name);
                EditText number = (EditText) deleteDialog.findViewById(R.id.input_number);
                RadioButton rMale = (RadioButton) deleteDialog.findViewById(R.id.male);
                boolean male = rMale.isChecked();
                Drawable icon;
                int randomIconIndex;
                // Choose icon based on gender
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
                // Update contact in settings
                Settings.getContacts().get((int) deleteDialogView.getTag()).name = name.getText().toString();
                Settings.getContacts().get((int) deleteDialogView.getTag()).number = number.getText().toString();
                Settings.getContacts().get((int) deleteDialogView.getTag()).path = icon;
                // Add updated contact to UI
                addContact(name.getText().toString(), number.getText().toString(), icon, (int) deleteDialogView.getTag());
                deleteDialog.dismiss(); // Dismiss dialog
            }
        });

        // Set onClickListener for cancel button
        deleteDialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss(); // Dismiss dialog
            }
        });
        deleteDialog.show(); // Show dialog
    }

    // Method to create a new contact
    private void newContact() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(
                R.layout.activity_contact_form, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        RadioButton rMale = (RadioButton) deleteDialogView.findViewById(R.id.male);
        rMale.setChecked(true); // Set default gender to male

        // Set onClickListener for save button
        deleteDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                EditText name = (EditText) deleteDialog.findViewById(R.id.input_name);
                EditText number = (EditText) deleteDialog.findViewById(R.id.input_number);
                RadioButton rMale = (RadioButton) deleteDialog.findViewById(R.id.male);
                boolean male = rMale.isChecked();
                Drawable icon;
                int randomIconIndex;
                // Choose icon based on gender
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
                // Add new contact to settings
                Settings.getContacts().add(new Settings.Contact(name.getText().toString(), number.getText().toString(), icon));
                // Add new contact to UI
                addContact(name.getText().toString(), number.getText().toString(), icon, items.size());
                deleteDialog.dismiss(); // Dismiss dialog
            }
        });

        // Set onClickListener for cancel button
        deleteDialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss(); // Dismiss dialog
            }
        });

        deleteDialog.show(); // Show dialog
    }

    // Method to add a contact to the UI
    private int addContact(String name, String number, Drawable pathToImage, int id) {
        // Get table layout
        TableLayout table = (TableLayout) findViewById(R.id.contact_list);
        // If the contact ID is beyond the current list
        if (id >= items.size()) {
            // Inflate contact row layout
            View items1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_contact_row, null);
            TextView nameV = (TextView) items1.findViewById(R.id.contact_name);
            TextView numberV = (TextView) items1.findViewById(R.id.contact_number);
            // Set name and number text
            nameV.setText(name);
            numberV.setText(number);

            ImageView image = (ImageView) items1.findViewById(R.id.contact_image);
            image.setImageDrawable(pathToImage); // Set contact image
            ImageView edit = (ImageView) items1.findViewById(R.id.edit_contact);
            edit.setTag(id);
            // Set onClickListener for edit contact button
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    editContact(items.indexOf(view.getParent().getParent().getParent()));
                }
            });
            ImageView delete = (ImageView) items1.findViewById(R.id.delete_contact);
            delete.setTag(id);
            // Set onClickListener for delete contact button
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove(items.indexOf(view.getParent().getParent().getParent()));
                }
            });
            items.add(items1); // Add the contact view to the list of items
            table.addView(items1); // Add the contact view to the table layout
            TextView counter = (TextView) findViewById(R.id.manage_contacts);
            counter.setText("Manage Contacts:(" + items.size() + "/3)"); // Update counter text
            if (items.size() == 3) {
                buttons.get(0).setVisibility(View.INVISIBLE); // Hide add contact button if max contacts reached
            }
        } else {
            // If the contact ID is within the current list, update the existing contact
            View items1 = table.getChildAt(id);
            TextView nameV = (TextView) items1.findViewById(R.id.contact_name);
            TextView numberV = (TextView) items1.findViewById(R.id.contact_number);
            nameV.setText(name); // Update name text
            numberV.setText(number); // Update number text
            ImageView image = (ImageView) items1.findViewById(R.id.contact_image);
            image.setImageDrawable(pathToImage); // Update contact image
        }

        return id;

    }

    // Method to remove a contact from the UI
    private void remove(int i) {
        TableLayout table = (TableLayout) findViewById(R.id.contact_list);
        table.removeView(items.get(i)); // Remove the contact view from the table layout
        items.remove(i); // Remove the contact view from the list of items
        Settings.getContacts().remove(i); // Remove the contact from settings
        TextView counter = (TextView) findViewById(R.id.manage_contacts);
        counter.setText("Manage Contacts:(" + items.size() + "/3)"); // Update counter text

        // If only two contacts are remaining, make the add contact button visible
        if (items.size() == 2) {
            buttons.get(0).setVisibility(View.VISIBLE);
        }
    }
}
