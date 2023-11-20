package loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
@author [Nasrin Zarei]
*/
public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file that defines the user interface for the login application.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/view.fxml"));
        // Create a new `Parent` object from the FXML file.
        Parent root = loader.load();
        // Create a new `Scene` object from the `Parent` object.
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
