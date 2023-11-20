package loginapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IncomeValidation {
    
    // The class also provides a method to validate all of the income data at once. This method returns 
    
    //a list of error messages if any of the validations fail, or an empty list if all of the validations pass.
    public boolean Requirement(String priceCost) {
          // Returns true if the price cost is not empty, false otherwise.
        return priceCost.length() != 0;
    }

    public boolean incomepriceValid(String incomePricFloat) {
        Pattern PricPatern = Pattern.compile("^([+-]?\\d*\\.?\\d*)$");
        Matcher nameMatcher = PricPatern.matcher(incomePricFloat);
        return nameMatcher.matches();
    }

    public boolean priceCostValid(String priceCost) {
        Pattern PricPatern = Pattern.compile("^([+-]?\\d*\\.?\\d*)$");
        Matcher priceCostMatcher = PricPatern.matcher(priceCost);
        return priceCostMatcher.matches();
    }

    public boolean costSubjectValid(String costSubject) {
        Pattern PricPatern = Pattern.compile("^[a-zA-Z]+$");
        Matcher costSubjectMatcher = PricPatern.matcher(costSubject);
        return costSubjectMatcher.matches();
    }

    public boolean incomeSubjectValid(String incomeSubject) {
        Pattern PricPatern = Pattern.compile("^[a-zA-Z]+$");
        Matcher incomeSubjectMatcher = PricPatern.matcher(incomeSubject);
        return incomeSubjectMatcher.matches();
    }

    public List<String> validateIncome(String priceCost, String incomePrice, String costSubject, String incomeSubject) {
        IncomeValidation incomeValid = new IncomeValidation();
        boolean checkRequirement = incomeValid.Requirement(priceCost);
        boolean checkIncomeprice = incomeValid.incomepriceValid(incomePrice);
        boolean checkPriceCost = incomeValid.priceCostValid(priceCost);
        boolean checkcostSubject = incomeValid.costSubjectValid(costSubject);
        boolean checkincomeSubject = incomeValid.incomeSubjectValid(incomeSubject);

        List<String> validationIncomeErrors = new ArrayList<>();
        if (!checkRequirement) {
            validationIncomeErrors.add("Please fill in the cost price section");
        }
        if (!checkIncomeprice) {
            validationIncomeErrors.add("Incomeprice must be 0-9");
        }
        if (!checkPriceCost) {
            validationIncomeErrors.add("CostPrice must be 0-9");
        }
        if (!checkcostSubject) {
            validationIncomeErrors.add("costSubject must be a-z or A-Z");
        }
        if (!checkincomeSubject) {
            validationIncomeErrors.add("incomeSubject must be a-z or A-Z");
        }
        return validationIncomeErrors;
    }
}
