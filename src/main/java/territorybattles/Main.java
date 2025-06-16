package territorybattles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Attempting to load mainView.fxml...");
            Parent root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
            System.out.println("FXML loaded successfully.");
            primaryStage.setTitle("Territory Battles Calculator");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Startup Exception: " + e);
            e.printStackTrace();
            try (java.io.FileWriter fw = new java.io.FileWriter(System.getProperty("user.home") + "/Desktop/debug.log", true)) {
                fw.write("Startup Exception: " + e + System.lineSeparator());
            } catch (Exception ignored) {}
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
