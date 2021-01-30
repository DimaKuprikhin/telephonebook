package telephonebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Subscriber {
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String email;
    private Date birthday;
    private ArrayList<String> phoneNumbers;

    public Subscriber(String firstName, String lastName) {
        firstName = Objects.requireNonNullElse(firstName, "");
    }
}
