package database;

import backend.Contact;

import java.util.ArrayList;

public interface AbstractDatabase {

    boolean open(String databaseFileName);

    boolean save(String databaseFileName);

    ArrayList<Contact> get();

    void add(Contact contact);

    void remove(Contact contact);

}
