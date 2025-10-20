package workers;

import enums.Gender;

public class Director extends Person {
    private int numberOfShows;

    public Director(String name, String surname, Gender gender, int numberOfShows) {
        super(name, surname, gender);
        this.numberOfShows = numberOfShows;
    }

    public String toString() {
        return name + " " + surname + " (поставлено спектаклей: " + numberOfShows + ")";
    }
}
