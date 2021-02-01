package database;

import backend.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class Database implements AbstractDatabase {

    @NotNull
    ArrayList<Contact> contacts = new ArrayList<>();

    public Database() throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("database.txt"));
        } catch (FileNotFoundException ex) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        String serialized;
        while ((serialized = reader.readLine()) != null) {
            contacts.add(mapper.readValue(serialized, Contact.class));
        }
        reader.close();
    }

    @Override
    public void save() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("database.txt"));
        for(int i = 0; i < contacts.size(); ++i) {
            writer.write(mapper.writeValueAsString(contacts.get(i)));
            writer.newLine();
        }
        writer.close();
    }

    @Override
    @NotNull
    public Collection<Contact> getByPredicate(
            @NotNull Predicate<Contact> predicate) {
        Objects.requireNonNull(predicate, "Предикат поиска является нулевой" +
                " ссылкой.");
        return Collections.unmodifiableCollection(new ArrayList<>(Arrays.asList(
                contacts.stream().filter(predicate).toArray(Contact[]::new))));
    }

    @Override
    public void add(@NotNull Contact contact) {
        contacts.add(Objects.requireNonNull(contact, "Нал при добавлении " +
                "контакта в базу."));
    }

    @Override
    public void remove(Contact contact) {
        contacts.remove(contact);
    }
}
