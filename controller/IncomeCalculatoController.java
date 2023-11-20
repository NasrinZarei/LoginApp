package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginapp.User;
import loginapp.Helper;
import loginapp.Income;
import loginapp.IncomeValidation;
import model.IncomeRepository;

public class IncomeCalculatoController implements Initializable {

    @FXML
    private TextField costSubject;

    @FXML
    private DatePicker date;

    @FXML
    private Button incomeBack;

    @FXML
    private TextField incomePrice;

    @FXML
    private TextField incomeSubject;

    @FXML
    private TextField priceCost;

    @FXML
    private Button submit;

    @FXML
    private TextField incomeuserId;

    @FXML
    private ChoiceBox<String> typeCost;

    List<Income> incomes;
    List<User> Users;
    private String userId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Preferences preferences = Preferences.userRoot();
        userId = preferences.get("usersId", "empty");
        System.out.println("user id equall Preferences " + userId);
        incomeuserId.setText(userId);
        typeCost.getItems().addAll("rent", "Grocery", "water bill", "Electric bill");
    }

    @FXML
    public void submit(ActionEvent event) throws IOException, Exception {

        // Date date = selectedDate.atStartOfDay();
        IncomeRepository repository = new IncomeRepository();
        incomes = repository.readIncomeFile();

        Helper IncomeValidationHelp = new Helper();
        IncomeValidation incomvalid = new IncomeValidation();
        List<String> incomevalidation = incomvalid.validateIncome(priceCost.getText(), incomePrice.getText(), costSubject.getText(), incomeSubject.getText());
        if (!incomevalidation.isEmpty()) {
            String alert = "Please fix the following errors:\n";
            for (String incomeerror : incomevalidation) {
                alert += " * " + incomeerror + "\n";
            }
            IncomeValidationHelp.help(alert);
            return;
        }

        String UseridText = incomeuserId.getText();

        String incomePriceText = incomePrice.getText();
        if (incomePriceText.length() == 0) {
            incomePriceText = "0";
        }
        Float incomePricFloat = Float.parseFloat(incomePriceText);

        String priceCostText = priceCost.getText();
        Float priceCostFloat = Float.parseFloat(priceCostText);

        LocalDate selectedDate = date.getValue();

        String selectedItem = typeCost.getValue();

        Map<String, Object> incomeMap = new HashMap<>();
        incomeMap.put("incomeUserId", UseridText);
        incomeMap.put("selectedDate", selectedDate);
        incomeMap.put("incomePric", incomePricFloat);
        incomeMap.put("incomeSubject", incomeSubject.getText());
        incomeMap.put("selectedItem", selectedItem);
        incomeMap.put("priceCost", priceCostFloat);
        incomeMap.put("costSubject", costSubject.getText());
        incomeMap.get("selectedDate");

        repository.writeIncomeFile(incomeMap);

        IncomeValidationHelp.help("Successfully registered");
    }

    @FXML
    public void incomeBack(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/profile.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
