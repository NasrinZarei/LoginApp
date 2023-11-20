package controller;

import java.io.IOException;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginapp.User;
import loginapp.Helper;
import loginapp.Validation;
import model.UserRepository;

public class RegisterController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    TextField nameandfamily;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    @FXML
    private Button save;

    @FXML
    private Button backRegister;

    List<User> Users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    /**
   * Saves a new user to the system.
   *
   * @param event The action event that triggered the save operation.
   * @throws IOException If an error occurs while reading or writing the user data to file.
   * @throws Exception If an unexpected error occurs.
   */
    public void saveUser(ActionEvent event) throws IOException, Exception {
        UserRepository repository = new UserRepository();

        Helper ValidationHelp = new Helper();

        Users = repository.readRegisterFile();

        Validation valid = new Validation();

        List<String> validation = valid.validateUser(Users, nameandfamily.getText(), phone.getText(), email.getText(), password.getText());
         // If the validation fails, display an error message and return.
        if (!validation.isEmpty()) {
            String alert = "Please fix the following errors:\n";
            for (String error : validation) {
                alert += " * " + error + "\n";
            }

            ValidationHelp.help(alert);
            return;
        }
         // Write the new user data to file.
        repository.writeRegisterFile(nameandfamily.getText(), phone.getText(), email.getText(), password.getText());
        ValidationHelp.help("Successfully registered");

    }

    @FXML
    public void backRegister(ActionEvent event) throws IOException {
        Stage stage = (Stage) backRegister.getScene().getWindow();
        stage.close();
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/view.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

        // get a handle to the stage
    }

}
