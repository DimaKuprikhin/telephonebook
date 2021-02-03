package backend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Date  {
    public final int day;
    public final int month;
    public final int year;

    @JsonCreator
    public Date(@JsonProperty("day") int day,
                @JsonProperty("month") int month,
                @JsonProperty("year") int year) {
        if(day < 1 || day > 31) {
            throw new IllegalArgumentException("Неккоректное значение дня " +
                    "месяца. Должно быть в отрезке [1, 31].");
        }
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Неккоректное значение номера " +
                    "месяца. Должно быть в отрезке [1, 12].");
        }
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
