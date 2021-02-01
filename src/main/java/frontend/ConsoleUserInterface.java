package frontend;

import backend.Contact;
import backend.Date;
import backend.Presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class ConsoleUserInterface {

    private final Presenter presenter;

    public ConsoleUserInterface(Presenter presenter) {
        this.presenter = presenter;
    }

    public void findAll() {
        Collection<Contact> contacts =
                presenter.getByPredicate(contact -> true);
        System.out.println("Список всех контактов:");
        for(Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void findByName(BufferedReader reader) {
        System.out.println("Введите строку для поиска по фио");
        try {
            String searchString = reader.readLine();
            Collection<Contact> foundContacts = presenter.getByPredicate(
                    contact -> (contact.firstName + " " +
                            contact.middleName + " " +
                            contact.lastName).contains(searchString));
            System.out.println("Результаты поиска по имени " + searchString);
            for(Contact contact : foundContacts) {
                System.out.println(contact);
            }
        }
        catch (IOException ex) {
            System.out.println("Ошибка ввода при вводе поисковой строки");
        }
    }

    private void findByBirthday(BufferedReader reader) {
        try {
            System.out.println("Введите число месяца рождения");
            int day = Integer.parseInt(reader.readLine());
            System.out.println("Введите месяц рождения");
            int month = Integer.parseInt(reader.readLine());
            System.out.println("Введите год рождения");
            int year = Integer.parseInt(reader.readLine());
            Date searchDate = new Date(day, month, year);
            Collection<Contact> foundContacts = presenter.getByPredicate(
                    contact -> contact.birthday.equals(searchDate));
            System.out.println("Результаты поиска по дате рожедния");
            for(Contact contact : foundContacts) {
                System.out.println(contact);
            }
        }
        catch (IOException ex) {
            System.out.println("Ошибка ввода при вводе поисковой строки");
        }
    }

    public void findByNumber(BufferedReader reader) {
        try {
            System.out.println("Введите номер");
            String searchedNumber = reader.readLine();
            Collection<Contact> foundContacts = presenter.getByPredicate(
                    contact -> contact.getPhoneNumbers().stream()
                            .anyMatch(number -> number.contains(searchedNumber)));
            System.out.println("Результаты поиска по номеру " + searchedNumber);
            for(Contact contact : foundContacts) {
                System.out.println(contact);
            }
        }
        catch (IOException ex) {
            System.out.println("Ошибка ввода при вводе поисковой строки");
        }
    }

    public void findByEmail(BufferedReader reader) {
        try {
            System.out.println("Введите email");
            String searchedEmail = reader.readLine();
            Collection<Contact> foundContacts = presenter.getByPredicate(
                    contact -> contact.getEmails().stream()
                            .anyMatch(email -> email.contains(searchedEmail)));
            System.out.println("Результаты поиска по email " + searchedEmail);
            for(Contact contact : foundContacts) {
                System.out.println(contact);
            }
        }
        catch (IOException ex) {
            System.out.println("Ошибка ввода при вводе поисковой строки");
        }
    }

    public void findByAddress(BufferedReader reader) {
        try {
            System.out.println("Введите адрес");
            String searchedAddress = reader.readLine();
            Collection<Contact> foundContacts = presenter.getByPredicate(
                    contact -> contact.address.contains(searchedAddress));
            System.out.println("Результаты поиска по адресу " + searchedAddress);
            for(Contact contact : foundContacts) {
                System.out.println(contact);
            }
        }
        catch (IOException ex) {
            System.out.println("Ошибка ввода при вводе поисковой строки");
        }
    }

    private void addContact(BufferedReader reader) {
        try {
            System.out.println("Введите имя");
            String firstName = reader.readLine();
            if(firstName.trim().length() == 0) {
                System.out.println("Имя не иожет быть пустой строкой");
                return;
            }

            System.out.println("Введите отчество или пустую строку");
            String middleName = reader.readLine();

            System.out.println("Введите фамилию или пустую строку");
            String lastName = reader.readLine();

            System.out.println("Введите адрес");
            String address = reader.readLine();

            System.out.println("Введите число месяца рождения");
            int day = Integer.parseInt(reader.readLine());
            System.out.println("Введите номер месяца рождения");
            int month = Integer.parseInt(reader.readLine());
            System.out.println("Введите год рождения");
            int year = Integer.parseInt(reader.readLine());
            Date birthday;
            try {
                birthday = new Date(day, month, year);
            }
            catch(IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }

            System.out.println("Введите количество номеров контакта");
            int phoneNumbersNumber = Integer.parseInt(reader.readLine());
            ArrayList<String> phoneNumbers = new ArrayList<>();
            for(int i = 0; i < phoneNumbersNumber; ++i) {
                System.out.println("Введите " + (i + 1) + " номер контакта");
                phoneNumbers.add(reader.readLine());
            }

            System.out.println("Введите количество email'ов контакта");
            int emailsNumber = Integer.parseInt(reader.readLine());
            ArrayList<String> emails = new ArrayList<>();
            for(int i = 0; i < emailsNumber; ++i) {
                System.out.println("Введите " + (i + 1) + " email контакта");
                emails.add(reader.readLine());
            }

            boolean addResult = presenter.addContact(
                        new Contact(firstName, middleName,
                                lastName, birthday, address,
                                emails, phoneNumbers));
            if(addResult) {
                System.out.println("Контакт успешно добавлен");
            }
            else {
                System.out.println("Контакт не добавлен - контакт с таким " +
                        "именем и датой рождения уже существуюет");
            }
        }
        catch(IOException ex) {
            System.out.println("Ошибка ввода информации о контакте");
        }
        catch(NumberFormatException ex) {
            System.out.println("Некорректный формат введеного числа");
        }
    }

    private void removeContact(BufferedReader reader) {
        try {
            System.out.println("Введите имя");
            String firstName = reader.readLine();

            System.out.println("Введите отчество или пустую строку");
            String middleName = reader.readLine();

            System.out.println("Введите фамилию или пустую строку");
            String lastName = reader.readLine();

            System.out.println("Введите число месяца рождения");
            int day = Integer.parseInt(reader.readLine());
            System.out.println("Введите номер месяца рождения");
            int month = Integer.parseInt(reader.readLine());
            System.out.println("Введите год рождения");
            int year = Integer.parseInt(reader.readLine());
            Date birthday = new Date(day, month, year);

            Contact removedContact = new Contact(firstName, middleName,
                    lastName, birthday, "",
                    new ArrayList<>(), new ArrayList<>());
            boolean removeResult = presenter.removeContact(removedContact);
            if(removeResult) {
                System.out.println("Контакт успешно удален");
            }
            else {
                System.out.println("Не найдено подходящего контакта для " +
                        "удаления");
            }
        }
        catch(IOException ex) {
            System.out.println("Ошибка при вводе информации о контакте");
        }
        catch(NumberFormatException ex) {
            System.out.println("Некорректный формат введеного числа");
        }
    }

    public boolean close() {
        try {
            presenter.saveDatabase();
            return true;
        }
        catch(IOException ex) {
            System.out.println("Не удалось сохранить базу данных в" +
                    " файл." + ex.getMessage());
            return false;
        }
    }

    public void run() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("1 -> показать все контакты");
            System.out.println("2 -> поиск по фио");
            System.out.println("3 -> поиск по дате рождения");
            System.out.println("4 -> поиск по номеру");
            System.out.println("5 -> поиск по email");
            System.out.println("6 -> поиск по адресу");
            System.out.println("7 -> добавить контакт");
            System.out.println("8 -> удалить контакт");
            System.out.println("0 -> выход из программы");

            int command;
            try {
                command = Integer.parseInt(reader.readLine());
            }
            catch(IOException ex) {
                System.out.println("Ошибка ввода команды");
                continue;
            }
            catch(NumberFormatException ex) {
                System.out.println("Некорректный формат введеного числа");
                continue;
            }

            if(command == 0) {
                if(close()) {
                    break;
                }
            }
            if(command == 1) {
                findAll();
            }
            if(command == 2) {
                findByName(reader);
            }
            if(command == 3) {
                findByBirthday(reader);
            }
            if(command == 4) {
                findByNumber(reader);
            }
            if(command == 5) {
                findByEmail(reader);
            }
            if(command == 6) {
                findByAddress(reader);
            }
            if(command == 7) {
                addContact(reader);
            }
            if(command == 8) {
                removeContact(reader);
            }
        }
    }
}
