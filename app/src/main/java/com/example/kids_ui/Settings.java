package com.example.kids_ui;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;


public class Settings {

    public static class App {

        public String name;
        public String desc;
        public String path;
        public boolean active;
        public int category;

        public App(String name, String desc, String path, int category) {
            this.name = name;
            this.desc = desc;
            this.path = path;
            this.category = category;
            this.active = true;
        }

    }

    public static class Contact {
        public String name;
        public String number;
        public Drawable path;

        public Contact(String name, String number, Drawable path) {
            this.name = name;
            this.number = number;
            this.path = path;
        }
    }



    public static Context myContext;

    private static ArrayList<App> apps;
    private static ArrayList<Contact> contacts;

    private static boolean[] abilities = {true, true, true, true};

    public static ArrayList<Contact> getContacts() {
        return contacts;
    }



    public static ArrayList<App> getApps() {
        return apps;
    }

    public static void setUp(Context context) {
        myContext = context;
        contacts = new ArrayList<Contact>();
//
    }

    public static boolean[] getAbilities() {
        return abilities;
    }

}
