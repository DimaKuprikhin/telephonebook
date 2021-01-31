package database;

import backend.Contact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public interface AbstractDatabase {

    void save() throws IOException;

    ArrayList<Contact> getByPredicate(Predicate<Contact> predicate);

    void add(Contact contact);

    void remove(Contact contact);

}
