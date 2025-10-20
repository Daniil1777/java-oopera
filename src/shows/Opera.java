package shows;

import workers.Director;

public class Opera extends MusicalShow {
    private int choirSize;

    public Opera(String title, int duration, Director director,
                 String musicAuthor, String librettoText, int choirSize) {
        super(title, duration, director, musicAuthor, librettoText);
        this.choirSize = choirSize;
    }

    public void printChoirInfo() {
        System.out.println("В опере '" + title + "' участвует хор из " + choirSize + " человек");
    }
}
