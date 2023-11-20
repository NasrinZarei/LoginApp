package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginapp.User;
import loginapp.Validation;
import model.UserRepository;

public class LoginViewController implements Initializable {

    @FXML
    private Button Login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Button closeButton;

    List<User> Users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
      /**
   * Opens the profile window if the login credentials are valid.
   *
   * @param event The action event that triggered the profile window opening.
   * @throws IOException If an error occurs while loading the profile window FXML file.
   * @throws ClassNotFoundException If the profile window FXML file cannot be found.
     */
    @FXML
    public void profileWindows(ActionEvent event) throws IOException, ClassNotFoundException {

        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/profile.fxml"));
        Parent root = loader.load();
        newStage.setScene(new Scene(root));

        if ((this.loginValidation(email.getText(), password.getText()) == true)) {

            newStage.show();
        } else {
            Alert emailAlert = new Alert(Alert.AlertType.INFORMATION, "Email or passsword is not coorect");
            emailAlert.show();

        }
        Stage stage = (Stage) Login.getScene().getWindow();
        stage.close();

    }
     /**
   * Validates the login credentials.
   *
   * @param email The user's email address.
   * @param password The user's password.
   * @return `true` if the login credentials are valid, `false` otherwise.
   * @throws ClassNotFoundException If the user data file cannot be found.
   * @throws FileNotFoundException If the user data file cannot be opened.
     */
    private boolean loginValidation(String email, String password) throws ClassNotFoundException, FileNotFoundException {

        Validation validemails = new Validation();
        validemails.validateEmail(email);
        UserRepository read = new UserRepository();
        Users = read.readRegisterFile();
        for (User item : Users) {
            if (item.getEmail().equals(email) && (item.getPasword().equals(password))) {
                String usersId = Integer.toString(item.getId());
                Preferences preferences = Preferences.userRoot();
                preferences.put("usersId", usersId);

                return true;

            }
        }
        // If the email address and password do not match any of the users, return `false`.
        return false;
    }

    @FXML
    public void backLogin(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/view.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
