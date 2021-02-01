package backend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Contact {
    public final String firstName;
    public final String middleName;
    public final String lastName;
    public final Date birthday;
    public final String address;
    private final ArrayList<String> emails;
    private final ArrayList<String> phoneNumbers;

    @JsonCreator
    public Contact(@JsonProperty("firstName") String firstName,
                   @JsonProperty("middleName") String middleName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("birthday") Date birthday,
                   @JsonProperty("address") String address,
                   @JsonProperty("emails") ArrayList<String> emails,
                   @JsonProperty("phoneNumbers") ArrayList<String> phoneNumbers)
                    throws IllegalArgumentException {
        if(firstName.trim().length() == 0) {
            throw new IllegalArgumentException("Имя не может быть пустой" +
                    " строкой");
        }
        this.firstName = Objects.requireNonNull(firstName);
        this.middleName = Objects.requireNonNullElse(middleName, "");
        this.lastName = Objects.requireNonNullElse(lastName, "");
        this.birthday = Objects.requireNonNull(birthday);
        this.address = Objects.requireNonNullElse(address, "");
        this.emails = Objects.requireNonNullElse(emails, new ArrayList<>());
        this.phoneNumbers =
                Objects.requireNonNullElse(phoneNumbers, new ArrayList<>());
    }

    public Collection<String> getEmails() {
        return Collections.unmodifiableCollection(emails);
    }

    public Collection<String> getPhoneNumbers() {
        return Collections.unmodifiableCollection(phoneNumbers);
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
     * @param other Сравниваемый объект.
     * @return True, если объекты равны, false - иначе.
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
                .append(middleName).append(" ")
                .append(lastName)
                .append(", address: ")
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
