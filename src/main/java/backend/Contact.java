package backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Contact {
    public final String firstName;
    public final String middleName;
    public final String lastName;
    public final Date birthday;
    public final String address;
    private final ArrayList<String> emails;
    private final ArrayList<String> phoneNumbers;

    public Contact(String firstName, String middleName, String lastName,
                   String address, Date birthday, ArrayList<String> emails,
                   ArrayList<String> phoneNumbers) {
        if(firstName == null || firstName.equals("")) {
            throw new NullPointerException("Имя контакта является налом или" +
                    " пустой строкой.");
        }
        this.firstName = firstName;
        this.middleName = Objects.requireNonNullElse(middleName, "");
        this.lastName = Objects.requireNonNullElse(lastName, "");
        this.address = Objects.requireNonNullElse(address, "");
        this.birthday = birthday;
        this.emails = Objects.requireNonNullElse(emails, new ArrayList<>());
        this.phoneNumbers =
                Objects.requireNonNullElse(phoneNumbers, new ArrayList<>());
    }

    @Override
    public int hashCode() {
        return (firstName + middleName + lastName).hashCode();
    }

    /**
     * Сравнивает на равентство два экземпляра контакта. Контакты равны, если
     * они имеют одинаковые имена, фамилии, отчества и даты рождения. Если
     * у одного из контактов не указана дата рождения, контакты считаются
     * различными.
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Contact)) {
            return false;
        }
        Contact otherContact = (Contact)other;
        return otherContact.firstName.equals(firstName) &&
                otherContact.middleName.equals(middleName) &&
                otherContact.lastName.equals(lastName) &&
                (otherContact.birthday != null && birthday != null &&
                        otherContact.birthday.equals(birthday));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(firstName).append(" ")
                .append(middleName)
                .append(lastName)
                .append(" -> address: ")
                .append(address)
                .append(", birthday: ")
                .append(birthday)
                .append(", emails: ");
        for(int i = 0; i < emails.size(); ++i) {
            if(i != 0) {
                result.append(", ");
            }
            result.append(emails.get(i));
        }
        result.append(", phone numbers: ");
        for(int i = 0; i < phoneNumbers.size(); ++i) {
            if(i != 0) {
                result.append(", ");
            }
            result.append(phoneNumbers.get(i));
        }
        return result.toString();
    }
}
