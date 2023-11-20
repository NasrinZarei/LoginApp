package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import loginapp.Income;

public class IncomeRepository {

    List<Income> incomes = new ArrayList<>();

    URL incomeUrl = getClass().getResource("../resources/income.txt");
    File incomeFile = new File(incomeUrl.getPath());
    
     /**
     * Reads the income data from the file and returns a list of incomes.
     *
     * @throws FileNotFoundException if the file does not exist.
     * @throws ClassNotFoundException if the file contains invalid data.
     * @return a list of incomes
     */

    public List<Income> readIncomeFile() throws FileNotFoundException, ClassNotFoundException {

        try ( ObjectInputStream readobject = new ObjectInputStream(new FileInputStream(incomeFile))) {
            incomes = (List<Income>) readobject.readObject();
            readobject.close();
        } catch (IOException ex) {
        }
        return incomes;

    }
    /**
     * Writes the income data to the file.
     *
     * @param incomeMap a map containing the income data
     * @throws FileNotFoundException if the file does not exist.
     */
    public void writeIncomeFile(Map<String, Object> incomeMap) throws FileNotFoundException {
        try ( ObjectOutputStream writeIncomeobject = new ObjectOutputStream(new FileOutputStream(incomeFile))) {

            Income NewIncome = new Income((String) incomeMap.get("incomeUserId"), (LocalDate) incomeMap.get("selectedDate"), (Float) incomeMap.get("incomePric"),
                    (String) incomeMap.get("incomeSubject"), (String) incomeMap.get("selectedItem"),
                    (Float) incomeMap.get("priceCost"), (String) incomeMap.get("costSubject"));

            NewIncome.setId(incomes.size());
            incomes.add(NewIncome);
            writeIncomeobject.writeObject(incomes);

            writeIncomeobject.close();
            System.out.println("user id incomer" + NewIncome.getUserid());
        } catch (Exception ex) {
        }
    }
}
