package backend;

import database.AbstractDatabase;
import database.Database;
import frontend.AbstractUserInterface;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Presenter {
    private AbstractUserInterface userInterface;
    private AbstractDatabase database;

    public Presenter(AbstractUserInterface userInterface) {
        this.userInterface = userInterface;
        database = new Database();
    }

    public ArrayList<Contact> getByPredicate() {
        return null;
    }

    public boolean addContact(Contact contact) {
        return true;
    }

    public void removeContact(Contact contact) {

    }
}
