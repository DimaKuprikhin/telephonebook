package database;

import backend.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * Класс, реализующий базу для хранения списка контактов в текстовом файле в
 * формате JSON.
 */
public class Database implements AbstractDatabase {

    /**
     * Хранимые контакты. Ни один элемент не равен null.
     */
    @NotNull
    private final ArrayList<Contact> contacts = new ArrayList<>();

    /**
     * Строковая константа имени файла, хранящего базу контактов.
     */
    private final String DATABASE_FILE_NAME = "database.txt";

    /**
     * Конструктор базы. Десериализует контакты из файла database.txt, если
     * возможно открыть файл.
     * @throws IOException При ошибке чтения из файла или ошибке десериализации.
     */
    public Database() throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(DATABASE_FILE_NAME));
        } catch (FileNotFoundException ex) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        String serialized;
        // Построчно считываем из файла и десериализуем каждую строку.
        while ((serialized = reader.readLine()) != null) {
            contacts.add(mapper.readValue(serialized, Contact.class));
        }
        reader.close();
    }

    /**
     * Сохраняет изменеия в базе.
     * @throws IOException При ошибке записи изменений.
     */
    @Override
    public void save() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter(DATABASE_FILE_NAME));
        // Записываем сериализованные элементы построчно в файл.
        for(int i = 0; i < contacts.size(); ++i) {
            writer.write(mapper.writeValueAsString(contacts.get(i)));
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Получение коллекции контактов по запросу.
     * @param predicate Предикат, указывающий, какие элементы необходимо
     *                  вернуть.
     * @return Unmodifiable коллекцию контактов, удовлетворяющих предикату.
     */
    @Override
    @NotNull
    public Collection<Contact> getByPredicate(
            @NotNull Predicate<Contact> predicate) {
        Objects.requireNonNull(predicate, "Предикат поиска является нулевой" +
                " ссылкой.");
        return Collections.unmodifiableCollection(new ArrayList<>(Arrays.asList(
                contacts.stream().filter(predicate).toArray(Contact[]::new))));
    }

    /**
     * Безусловно добавляет контакт в базу.
     * @param contact Добавляемый контакт.
     */
    @Override
    public void add(@NotNull Contact contact) {
        contacts.add(Objects.requireNonNull(contact, "Нал при добавлении " +
                "контакта в базу."));
    }

    /**
     * Удаляет контакт из базы. При отсутствии удаляемого контакта в базе,
     * состояние базы не меняется.
     * @param contact Удаляемый контакт.
     */
    @Override
    public void remove(@NotNull Contact contact) {
        contacts.remove(Objects.requireNonNull(contact, "Нал при удалении " +
                "контакта из базы."));
    }
}
