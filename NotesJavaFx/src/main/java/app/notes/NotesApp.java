package app.notes;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NotesApp extends Application {

	private static final Logger LOGGER = Logger.getLogger(NotesApp.class.getName());

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/app/notes/NotesApp.fxml"));
			primaryStage.setTitle("Notes");
			Scene scene = new Scene(root, 800, 600);
			// primaryStage.getIcons().add(new Image("javafx.png"));
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
