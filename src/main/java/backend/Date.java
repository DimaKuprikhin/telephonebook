package backend;

public class Date {
    public final int day;
    public final int month;
    public final int year;

    public Date(int day, int month, int year) {
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
