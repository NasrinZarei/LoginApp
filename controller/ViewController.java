
package controller;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void loginWindows(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginView.fxml"));
        Parent root = loader.load();
        newStage.setScene(new Scene(root));

        // Show the stage
        newStage.show();
        
         // get a handle to the stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    public void registerButtonClick(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/register.fxml"));
        Parent root = loader.load();
        newStage.setScene(new Scene(root));

        // Show the stage
        newStage.show();
        
         // get a handle to the stage
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
        
    }
    
}
