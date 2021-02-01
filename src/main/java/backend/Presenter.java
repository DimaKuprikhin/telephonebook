package backend;

import database.AbstractDatabase;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Predicate;

public class Presenter {
    private final AbstractDatabase database;

    public Presenter(AbstractDatabase database) {
        this.database = database;
    }

    public void saveDatabase() throws IOException {
        database.save();
    }

    public Collection<Contact> getByPredicate(Predicate<Contact> predicate) {
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
