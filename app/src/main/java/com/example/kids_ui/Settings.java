package com.example.kids_ui;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

// Class containing settings and configurations for the Kids UI application
public class Settings {

    // Inner class representing an installed application
    public static class App {

        public String name;      // Name of the application
        public String desc;      // Description of the application
        public String path;      // File path of the application
        public boolean active;   // Whether the application is active or not
        public int category;     // Category of the application

        // Constructor for creating an App object
        public App(String name, String desc, String path, int category) {
            this.name = name;
            this.desc = desc;
            this.path = path;
            this.category = category;
            this.active = true;  // By default, an app is set to active
        }
    }

    // Inner class representing a contact in the address book
    public static class Contact {
        public String name;      // Name of the contact
        public String number;    // Phone number of the contact
        public Drawable path;    // Image representing the contact

        // Constructor for creating a Contact object
        public Contact(String name, String number, Drawable path) {
            this.name = name;
            this.number = number;
            this.path = path;
        }
    }

    public static Context myContext;   // Context of the application

    private static ArrayList<App> apps;        // List of installed applications
    private static ArrayList<Contact> contacts; // List of contacts in the address book

    private static boolean[] abilities = {true, true, true, true}; // Array indicating the abilities of the application

    // Method to get the list of contacts
    public static ArrayList<Contact> getContacts() {
        return contacts;
    }

    // Method to get the list of installed applications
    public static ArrayList<App> getApps() {
        return apps;
    }

    // Method to set up the application settings
    public static void setUp(Context context) {
        myContext = context;
        contacts = new ArrayList<Contact>(); // Initialize the contacts list
    }

    // Method to get the abilities of the application
    public static boolean[] getAbilities() {
        return abilities;
    }
}
