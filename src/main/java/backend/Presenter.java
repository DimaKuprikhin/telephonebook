package backend;

import database.AbstractDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Presenter {
    private final AbstractDatabase database;

    public Presenter(AbstractDatabase database) {
        this.database = database;
    }

    public boolean saveDatabase() {
        try {
            database.save();
            return true;
        }
        catch(IOException ex) {
            return false;
        }
    }

    public ArrayList<Contact> getByPredicate(Predicate<Contact> predicate) {
        return database.getByPredicate(predicate);
    }

    public boolean addContact(Contact contact) {
        if(database.getByPredicate(
                contactInContacts -> contactInContacts.equals(contact))
                .size() > 0) {
            return false;
        }
        database.add(contact);
        return true;
    }

    public boolean removeContact(Contact contact) {
        if(database.getByPredicate(
                contactInContacts -> contactInContacts.equals(contact))
                .size() != 1) {
            return false;
        }
        database.remove(contact);
        return true;
    }
}
