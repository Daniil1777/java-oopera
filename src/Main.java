import workers.Actor;
import workers.Director;
import enums.Gender;
import shows.Show;
import shows.Opera;
import shows.Ballet;

import java.util.*;

public class Main {
    private static  Scanner scanner = new Scanner(System.in);
    private static  List<Actor> actors = new ArrayList<>();
    private static List<Director> directors = new ArrayList<>();
    private static List<Show> shows = new ArrayList<>();
    private static List<Opera> operas = new ArrayList<>();
    private static List<Ballet> ballets = new ArrayList<>();

    public static void main(String[] args) {

        createWorkers();

        showMainMenu();
    }

    private static void createWorkers() {

        actors.add(new Actor("Иван", "Петров", Gender.MALE, 185));
        actors.add(new Actor("Мария", "Сидорова", Gender.FEMALE, 170));
        actors.add(new Actor("Алексей", "Иванов", Gender.MALE, 178));
        actors.add(new Actor("Елена", "Кузнецова", Gender.FEMALE, 165));
        actors.add(new Actor("Дмитрий", "Смирнов", Gender.MALE, 182));


        directors.add(new Director("Сергей", "Режиссеров", Gender.MALE, 15));
        directors.add(new Director("Анна", "Постановщикова", Gender.FEMALE, 8));
        directors.add(new Director("Петр", "Театралов", Gender.MALE, 12));
    }

    private static void showMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("ГЛАВНОЕ МЕНЮ:");
            System.out.println("1. Создать мероприятие");
            System.out.println("2. Управление актерами в спектаклях");
            System.out.println("3. Просмотр информации");
            System.out.println("4. Показать либретто");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    createShow();
                    break;
                case "2":
                    manageActors();
                    break;
                case "3":
                    viewInformation();
                    break;
                case "4":
                    showLibretto();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор!");
                    break;
            }
        }
    }

    private static void createShow() {
        System.out.println("Создание мероприятия:");
        System.out.println("1. Обычный спектакль");
        System.out.println("2. Опера");
        System.out.println("3. Балет");
        System.out.print("Выберите тип мероприятия: ");

        String typeInput = scanner.nextLine();

        switch (typeInput) {
            case "1":
                createRegularShow();
                break;
            case "2":
                createOpera();
                break;
            case "3":
                createBallet();
                break;
            default:
                System.out.println("Неверно введен номер!");
                break;
        }
    }

    private static void createRegularShow() {
        System.out.print("Название спектакля: ");
        String title = scanner.nextLine();

        System.out.print("Длительность (минут): ");
        String durationInput = scanner.nextLine();
        int duration = parseNumber(durationInput);

        Director director = selectDirector();
        if (director == null) return;

        Show show = new Show(title, duration, director);
        shows.add(show);
        System.out.println("Обычный спектакль " + title + " создан!");
    }

    private static void createOpera() {
        System.out.print("Название оперы: ");
        String title = scanner.nextLine();

        System.out.print("Длительность (минут): ");
        String durationInput = scanner.nextLine();
        int duration = parseNumber(durationInput);

        Director director = selectDirector();
        if (director == null) return;

        System.out.print("Автор музыки: ");
        String musicAuthor = scanner.nextLine();
        System.out.print("Текст либретто: ");
        String libretto = scanner.nextLine();

        System.out.print("Количество человек в хоре: ");
        String choirInput = scanner.nextLine();
        int choirSize = parseNumber(choirInput);

        Opera opera = new Opera(title, duration, director, musicAuthor, libretto, choirSize);
        shows.add(opera);
        operas.add(opera);
        System.out.println("Опера '" + title + "' создана!");
    }

    private static void createBallet() {
        System.out.print("Название балета: ");
        String title = scanner.nextLine();

        System.out.print("Длительность (минут): ");
        String durationInput = scanner.nextLine();
        int duration = parseNumber(durationInput);

        Director director = selectDirector();
        if (director == null) return;

        System.out.print("Автор музыки: ");
        String musicAuthor = scanner.nextLine();
        System.out.print("Текст либретто: ");
        String libretto = scanner.nextLine();
        System.out.print("Хореограф: ");
        String choreographer = scanner.nextLine();

        Ballet ballet = new Ballet(title, duration, director, musicAuthor, libretto, choreographer);
        shows.add(ballet);
        ballets.add(ballet);
        System.out.println("Балет " + title + " создан!");
    }

    private static void manageActors() {
        if (shows.isEmpty()) {
            System.out.println("Необходимо минимум одно мероприятие!");
            return;
        }

        System.out.println("Управление актерами в мепроприятиях:");
        System.out.println("1. Добавить актера");
        System.out.println("2. Заменить актера");
        System.out.println("3. Удалить актера");
        System.out.print("Выберите действие: ");

        String choice = scanner.nextLine();
        Show show = selectShow();
        if (show == null) return;

        switch (choice) {
            case "1":
                addActorToShow(show);
                break;
            case "2":
                replaceActorInShow(show);
                break;
            case "3":
                removeActorFromShow(show);
                break;
            default:
                System.out.println("Неверный выбор!");
                break;
        }
    }

    private static void addActorToShow(Show show) {
        System.out.println("Добавление актера в мероприятии:");
        Actor actor = selectActor();
        if (actor != null) {
            show.addActor(actor);
        }
    }

    private static void replaceActorInShow(Show show) {
        System.out.println("Замена актера в мероприятии :");

        System.out.print("Введите фамилию актера для замены: ");
        String surnameToReplace = scanner.nextLine();

        if (!show.hasActorWithSurname(surnameToReplace)) {
            System.out.println("Актер с фамилией " + surnameToReplace + " не найден в мероприятии!");
            return;
        }

        System.out.println("Выберите нового актера:");
        Actor newActor = selectActor();
        if (newActor != null) {
            show.replaceActor(newActor, surnameToReplace);
        }
    }

    private static void removeActorFromShow(Show show) {
        System.out.println("Удаление актера из мероприятия:");

        System.out.print("Введите фамилию актера для удаления: ");
        String surnameToRemove = scanner.nextLine();

        List<Actor> actorsInShow = show.getListOfActors();
        for (int i = 0; i < actorsInShow.size(); i++) {
            if (actorsInShow.get(i).getSurname().equals(surnameToRemove)) {
                Actor removedActor = actorsInShow.get(i);
                actorsInShow.remove(i);
                System.out.println("Актер " + removedActor + " удален из мероприятия!");
                return;
            }
        }
        System.out.println("Актер с фамилией '" + surnameToRemove + " не найден в мероприятии!");
    }

    private static void viewInformation() {
        if (shows.isEmpty()) {
            System.out.println("Нет созданных мероприятий!");
            return;
        }

        System.out.println("Просмотр информации:");


        System.out.println("Доступные актеры:");
        for (Actor actor : actors) {
            System.out.println("  - " + actor);
        }


        System.out.println("Доступные режиссеры:");
        for (Director director : directors) {
            System.out.println("  - " + director);
        }

        // Выводим информацию о спектаклях
        System.out.println("Мероприятия");
        for (Show show : shows) {
            boolean isOpera = false;
            boolean isBallet = false;

            for (Opera opera : operas) {
                if (opera == show) {
                    isOpera = true;
                    break;
                }
            }

            for (Ballet ballet : ballets) {
                if (ballet == show) {
                    isBallet = true;
                    break;
                }
            }

            if (isOpera) {
                System.out.println("ОПЕРА: " + show.getTitle());
            } else if (isBallet) {
                System.out.println("БАЛЕТ: " + show.getTitle());
            } else {
                System.out.println("СПЕКТАКЛЬ: " + show.getTitle());
            }

            show.printDirectorInfo();
            show.printActors();

            if (isOpera) {
                Opera opera = (Opera) show;
                opera.printChoirInfo();
            } else if (isBallet) {
                Ballet ballet = (Ballet) show;
                ballet.printChoreographerInfo();
            }
        }
    }

    private static void showLibretto() {
        if (operas.isEmpty() && ballets.isEmpty()) {
            System.out.println("Нет музыкальных спектаклей (опер или балетов)!");
            return;
        }

        System.out.println("Либретто музыкальный спектаклей:");

        int index = 1;
        for (Opera opera : operas) {
            System.out.println(index + ". " + opera.getTitle());
            index++;
        }

        for (Ballet ballet : ballets) {
            System.out.println(index + ". " + ballet.getTitle());
            index++;
        }

        System.out.print("Выберите спектакль: ");
        String choiceInput = scanner.nextLine();
        int choice = parseNumber(choiceInput) - 1;

        int totalMusicalShows = operas.size() + ballets.size();
        if (choice >= 0 && choice < totalMusicalShows) {
            if (choice < operas.size()) {
                Opera selectedOpera = operas.get(choice);
                selectedOpera.printLibretto();
            } else {
                int balletIndex = choice - operas.size();
                Ballet selectedBallet = ballets.get(balletIndex);
                selectedBallet.printLibretto();
            }
        } else {
            System.out.println("Неверный выбор!");
        }
    }

    private static Director selectDirector() {
        System.out.println("Выберите режиссера;");
        for (int i = 0; i < directors.size(); i++) {
            System.out.println((i + 1) + ". " + directors.get(i));
        }

        System.out.print("Выберите режиссера: ");
        String choiceInput = scanner.nextLine();
        int choice = parseNumber(choiceInput) - 1;

        if (choice >= 0 && choice < directors.size()) {
            return directors.get(choice);
        } else {
            System.out.println("Неверный выбор!");
            return null;
        }
    }

    private static Show selectShow() {
        if (shows.isEmpty()) {
            System.out.println("Нет доступных спектаклей!");
            return null;
        }

        System.out.println("Выберите мероприятие:");
        for (int i = 0; i < shows.size(); i++) {
            System.out.println((i + 1) + ". " + shows.get(i).getTitle());
        }

        System.out.print("Выберите спектакль: ");
        String choiceInput = scanner.nextLine();
        int choice = parseNumber(choiceInput) - 1;

        if (choice >= 0 && choice < shows.size()) {
            return shows.get(choice);
        } else {
            System.out.println("Неверный выбор!");
            return null;
        }
    }

    private static Actor selectActor() {
        System.out.println("Выберите актера;");
        for (int i = 0; i < actors.size(); i++) {
            System.out.println((i + 1) + ". " + actors.get(i));
        }

        System.out.print("Выберите актера: ");
        String choiceInput = scanner.nextLine();
        int choice = parseNumber(choiceInput) - 1;

        if (choice >= 0 && choice < actors.size()) {
            return actors.get(choice);
        } else {
            System.out.println("Неверный выбор!");
            return null;
        }
    }

    private static int parseNumber(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        Scanner sc = new Scanner(input);
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            return 0;
        }
    }
}