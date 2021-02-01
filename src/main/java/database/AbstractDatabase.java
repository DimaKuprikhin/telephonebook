package database;

import backend.Contact;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Predicate;

public interface AbstractDatabase {

    void save() throws IOException;

    Collection<Contact> getByPredicate(Predicate<Contact> predicate);

    void add(Contact contact);

    void remove(Contact contact);

}
