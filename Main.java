import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author Tegneryadnov_VI
 * @version 11
 * @Java Установка
 */

public class Main {
    private final static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {
        try {
            createDir("C:/temp/Game/src");
            createDir("C:/temp/Game/src/main");
            createFile("C:/temp/Game/src/main/Main.java");
            createFile("C:/temp/Game/src/main/Utils.java");
            createDir("C:/temp/Game/src/test");
            createDir("C:/temp/Game/res");
            createDir("C:/temp/Game/res/drawables");
            createDir("C:/temp/Game/res/vectors");
            createDir("C:/temp/Game/res/icons");
            createDir("C:/temp/Game/savegames");
            createDir("C:/temp/Game/temp");
        } finally {
            logToFile("C:/temp/Game/temp/temp.txt");
        }
    }

    public static void createDir(String path) {
        try {
            File dir = new File(path);
            if (dir.exists()) {
                toLog(String.format("Каталог %s уже cоздан ранее", path));
            } else if (dir.mkdir()) {
                toLog(String.format("Каталог %s cоздан", path));
            } else {
                toLog(String.format("Не удалось создать каталог %s", path));
            }
        } catch (Exception e) {
            toLog(String.format("Ошибка создания каталога %s:%s", path, e.getStackTrace()));
        }
    }

    public static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                toLog(String.format("Файл %s уже создан ранее", fileName));
            } else if (file.createNewFile()) {
                toLog(String.format("Файл %s cоздан", fileName));
            } else {
                toLog(String.format("Не удалось создать файл %s", fileName));
            }
        } catch (Exception e) {
            toLog(String.format("Ошибка создания файла %s:%s ", fileName, e.getStackTrace()));
        }
    }

    public static void toLog(String message) {
        System.out.println(message);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        log.append(String.format("%s: %s\n", LocalDateTime.now().format(formatter), message));
    }

    public static void logToFile(String fileName) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(log.toString());
        } catch (IOException ex) {
            System.out.printf("Ошибка сохранения лога %s:", ex.getStackTrace());
        }
    }
}
