package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author morteza
 */
public class ProfileController implements Initializable {

    @FXML
    private Button list;

    @FXML
    private Button newIncome;

    @FXML
    private Button backProfile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void calculationWindows(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/incomeCalculator.fxml"));
        Parent root = loader.load();
        newStage.setScene(new Scene(root));

        // Show the stage
        newStage.show();
        Stage stage = (Stage) newIncome.getScene().getWindow();
        stage.close();
    }

    @FXML
    void listWindows(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/list.fxml"));

        Parent root = loader.load();
        newStage.setScene(new Scene(root));

        // Show the stage
        newStage.show();

        Stage stage = (Stage) list.getScene().getWindow();
        stage.close();
    }

    @FXML
    void backProfile(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/loginView.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
