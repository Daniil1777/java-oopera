package workers;

import enums.Gender;

public class Actor extends Person {
    private int height;

    public Actor(String name, String surname, Gender gender, int height) {
        super(name, surname, gender);
        this.height = height;
    }


    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + height;
        return result;
    }

    public String toString() {
        return name + " " + surname + " (" + height + " см)";
    }
}