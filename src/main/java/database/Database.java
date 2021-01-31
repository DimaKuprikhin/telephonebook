package database;

import backend.Contact;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class Database implements AbstractDatabase {
    ArrayList<Contact> contacts;

    public Database() throws IOException, JSONException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("database.txt"));
        }
        catch(FileNotFoundException ex) {
            contacts = new ArrayList<>();
            return;
        }

        ArrayList<Contact> newContacts = new ArrayList<>();
        String serialized;
        while ((serialized = reader.readLine()) != null) {
            newContacts.add((Contact) JSONObject.stringToValue(serialized));
        }
        reader.close();

        contacts = newContacts;
    }

    @Override
    public void save() throws IOException, JSONException {
        FileWriter writer = new FileWriter("database.txt");
        for (int i = 0; i < contacts.size(); ++i) {
            writer.write(JSONObject.valueToString(contacts.get(i)) + "\n");
        }
        writer.close();
    }

    @Override
    public ArrayList<Contact> getByPredicate(Predicate<Contact> predicate) {
        return new ArrayList<>(Arrays.asList(
                contacts.stream().filter(predicate).toArray(Contact[]::new)));
    }

    @Override
    public void add(Contact contact) {
        contacts.add(Objects.requireNonNull(contact, "Нал при добавлении " +
                "контакта в базу."));
    }

    @Override
    public void remove(Contact contact) {
        contacts.remove(contact);
    }
}
