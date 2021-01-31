package application;

import backend.Presenter;
import database.Database;
import frontend.ConsoleUserInterface;
import org.json.JSONException;

import java.io.IOException;

public class Application {
    public static void main(String[] argv) {
        Database database;
        try {
            database = new Database();
        }
        catch(IOException | JSONException ex) {
            System.out.println("Ошибка при загрузке базы данных");
            return;
        }
        Presenter presenter = new Presenter(database);
        ConsoleUserInterface userInterface =
                new ConsoleUserInterface(presenter);
        userInterface.run();
    }
}
