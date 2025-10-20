package shows;

import workers.Actor;
import workers.Director;
import java.util.ArrayList;
import java.util.List;

public class Show {
    protected String title;
    protected int duration;
    protected Director director;
    protected List<Actor> listOfActors;

    public Show(String title, int duration, Director director) {
        this.title = title;
        this.duration = duration;
        this.director = director;
        this.listOfActors = new ArrayList<>();
    }

    public void addActor(Actor actor) {
        if (listOfActors.contains(actor)) {
            System.out.println("Предупреждение: Актер " + actor + " уже участвует в спектакле!");
            return;
        }
        listOfActors.add(actor);
        System.out.println("Актер " + actor.getName() + " " + actor.getSurname() + " добавлен в спектакль");
    }

    public void replaceActor(Actor newActor, String surnameToReplace) {
        for (int i = 0; i < listOfActors.size(); i++) {
            if (listOfActors.get(i).getSurname().equals(surnameToReplace)) {
                Actor oldActor = listOfActors.set(i, newActor);
                System.out.println("Актер " + oldActor + " заменен на " + newActor);
                return;
            }
        }
        System.out.println("Предупреждение: Актер с фамилией " + surnameToReplace + " не найден в спектакле!");
    }

    public void printActors() {
        System.out.println("Актеры в спектакле '" + title + "':");
        for (Actor actor : listOfActors) {
            System.out.println("  - " + actor);
        }
    }

    public void printDirectorInfo() {
        System.out.println("Режиссер спектакля '" + title + "': " + director);
    }

    public String getTitle() {
        return title;
    }

    public List<Actor> getListOfActors() {
        return new ArrayList<>(listOfActors);
    }
    public boolean hasActorWithSurname(String surname) {
        for (Actor actor : listOfActors) {
            if (actor.getSurname().equals(surname)) {
                return true;
            }
        }
        return false;
    }
}

