package backend;

import database.AbstractDatabase;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class Presenter {

    @NotNull
    private final AbstractDatabase database;

    public Presenter(@NotNull AbstractDatabase database) {
        this.database = Objects.requireNonNull(database, "База данных" +
                " является налом.");
    }

    public void saveDatabase() throws IOException {
        database.save();
    }

    @NotNull
    public Collection<Contact> getByPredicate(
            @NotNull Predicate<Contact> predicate) {
        Objects.requireNonNull(predicate, "Ссылка не предикат поиска является" +
                " налом.");
        return database.getByPredicate(predicate);
    }

    public boolean addContact(@NotNull Contact contact) {
        Objects.requireNonNull(contact, "Ссылка на добавляемый контакт" +
                " является налом.");
        if(database.getByPredicate(
                contactInContacts -> contactInContacts.equals(contact))
                .size() > 0) {
            return false;
        }
        database.add(contact);
        return true;
    }

    public boolean removeContact(@NotNull Contact contact) {
        Objects.requireNonNull(contact, "Ссылка на удаляемый контакт" +
                " является налом.");
        if(database.getByPredicate(
                contactInContacts -> contactInContacts.equals(contact))
                .size() != 1) {
            return false;
        }
        database.remove(contact);
        return true;
    }
}
