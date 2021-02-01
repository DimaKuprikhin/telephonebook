package backend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Date  {
    public final int day;
    public final int month;
    public final int year;

    @JsonCreator
    public Date(@JsonProperty("day") int day,
                @JsonProperty("month") int month,
                @JsonProperty("year") int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public int hashCode() {
        return day + 31 * month + 365 * year;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Date)) {
            return false;
        }
        Date otherDate = (Date)other;
        return otherDate.day == day && otherDate.month == month &&
                otherDate.year == year;
    }

    @Override
    public String toString() {
        return day + "." + month + "." + year;
    }
}
