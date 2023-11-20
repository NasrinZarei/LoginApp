package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import loginapp.Income;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.IncomeRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListController implements Initializable {

    URL incomeUrl = getClass().getResource("../resources/income.txt");
    File incomeFile = new File(incomeUrl.getPath());
    ObservableList<IncomeTable> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<IncomeTable, String> costPrice;

    @FXML
    private TableColumn<IncomeTable, String> costSubject;

    @FXML
    private TableColumn<IncomeTable, String> date;

    @FXML
    private TableColumn<IncomeTable, String> incomePrice;

    @FXML
    private TableColumn<IncomeTable, String> incomeSubject;

    @FXML
    private TableColumn<IncomeTable, String> id;

    @FXML
    private TableView<IncomeTable> tableView;
    @FXML
    private Button backList;

    private String userId;

    List<Income> incomes;
    String formattedString;
    @FXML
    private Button delete;

    String setUserId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences preferences = Preferences.userRoot();
        userId = preferences.get("usersId", "empty");

        initiateCode();
        try {
            loadDate();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
        }
    }

    private void initiateCode() {
         // Set the cell value factories for the table view columns.
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        incomePrice.setCellValueFactory(new PropertyValueFactory<>("incomePrice"));
        incomeSubject.setCellValueFactory(new PropertyValueFactory<>("incomeSubject"));
        costPrice.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        costSubject.setCellValueFactory(new PropertyValueFactory<>("costSubject"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

    }
      /**
     * Loads the income data from file.
     *
     * @throws FileNotFoundException If the income data file cannot be found.
     * @throws ClassNotFoundException If the income data file cannot be read.
      */
    private void loadDate() throws FileNotFoundException, ClassNotFoundException {

        IncomeRepository incomerepository = new IncomeRepository();
        incomes = incomerepository.readIncomeFile();
        
        
        for (Income item : incomes) {
            IncomeTable income = new IncomeTable();
            setUserId = item.getUserid();
            
             // If the user ID matches the current user ID, add the income record to the table view.
             if (userId.equals(setUserId)) {
                LocalDate getDate = item.getDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                formattedString = getDate.format(formatter);
                // Set the values of the IncomeTable object.
                income.setDate(formattedString);
                income.setIncomePrice(Float.toString(item.getIncomePrice()));
                income.setIncomeSubject(item.getIncomSubject());
                income.setCostPrice(Float.toString(item.getPriceCost()));
                income.setCostSubject(item.getCostSubject());
                income.setId(Integer.toString(item.getId()));
                 // Refresh the table view.
                list.addAll(income);

            }

        }
        tableView.getItems().addAll(list);
    }


    @FXML
      /**
     * Deletes the selected income record from the table view and saves the changes to the income data file.
     *
     * @param event The action event that triggered the delete operation.
     * @throws FileNotFoundException If the income data file cannot be found.
     * @throws IOException If an error occurs while writing to the income data file.
     */
    private void deleteItem(ActionEvent event) throws FileNotFoundException, IOException {

        IncomeTable incomeUser = tableView.getSelectionModel().getSelectedItem();
        String incomeUserid = incomeUser.getId();
        // Remove the selected income record from the table view.
        tableView.getItems().remove(incomeUser);

        List<Income> newIncomes = new ArrayList<>();

        // Iterate over the existing income data and add all of the income records except for the selected income record to the new list.
        for (Income item : incomes) {
            String setid = Integer.toString(item.getId());
            if (!setid.equals(incomeUserid)) {
                newIncomes.add(item);
            }
        }
         // Clear the existing income data.
        incomes.clear();
        incomes = newIncomes;
        System.out.println(incomes);
        
         // Overwrite the income data file with the updated income data.
        FileWriter writer = new FileWriter(incomeFile);
        writer.write("");
        writer.close();
        
        // Overwrite the income data file with the updated income data.
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(incomeFile));
        out.writeObject(incomes);
        out.close();
    }

    public static class IncomeTable {

        private StringBuilder sb = new StringBuilder();
        private SimpleStringProperty id;
        private SimpleStringProperty date;
        private SimpleStringProperty incomePrice;
        private SimpleStringProperty incomeSubject;
        private SimpleStringProperty costPrice;
        private SimpleStringProperty costSubject;

        public String getId() {
            return id.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getIncomePrice() {
            return incomePrice.get();
        }

        public String getIncomeSubject() {
            return incomeSubject.get();
        }

        public String getCostPrice() {
            return costPrice.get();
        }

        public String getCostSubject() {
            return costSubject.get();
        }

        public void setId(String id) {

            this.id = new SimpleStringProperty(id);
        }

        public void setDate(String date) {

            this.date = new SimpleStringProperty(date);
        }

        public void setIncomePrice(String incomePrice) {
            this.incomePrice = new SimpleStringProperty(incomePrice);
        }

        public void setIncomeSubject(String incomeSubject) {
            this.incomeSubject = new SimpleStringProperty(incomeSubject);
        }

        public void setCostPrice(String costPrice) {
            this.costPrice = new SimpleStringProperty(costPrice);
        }

        public void setCostSubject(String costSubject) {
            this.costSubject = new SimpleStringProperty(costSubject);
        }

    }

    @FXML
    void backList(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../view/profile.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
