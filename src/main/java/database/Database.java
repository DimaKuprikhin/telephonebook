package database;

import backend.Contact;

import java.util.ArrayList;

public class Database implements AbstractDatabase {
    ArrayList<Contact> contacts;

    @Override
    public boolean open(String databaseFileName) {
        return false;
    }

    @Override
    public boolean save(String databaseFileName) {
        return false;
    }

    @Override
    public ArrayList<Contact> get() {
        return null;
    }

    @Override
    public void add(Contact contact) {

    }

    @Override
    public void remove(Contact contact) {

    }
}
