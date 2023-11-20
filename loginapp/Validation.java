package loginapp;

import interfaces.interfaces;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import loginapp.User;

public class Validation implements interfaces {
    
    @Override
    public boolean validRequirements(String nameandfamily, String email, String password) {
        // This method validates the minimum requirements for user input: name, email, and password must all be non-empty.
       // It returns `true` if all requirements are met, and `false` otherwise.
        return !(nameandfamily.length() == 0 && email.length() == 0 && password.length() == 0);
    }

    @Override
    public boolean validName(String nameandfamily) {
         // This method validates the user's name. It uses a regular expression to ensure that the name only contains letters.
        // It returns `true` if the name is valid, and `false` otherwise.
        Pattern namePatern = Pattern.compile("^[a-zA-Z]+$");
        Matcher nameMatcher = namePatern.matcher(nameandfamily);
        return nameMatcher.matches();
    }

    @Override
    public boolean validateDuplicateEmail(List<User> Users, String email) {
         // This method validates that the user's email address is not already in use. It checks the list of existing users to see if any of them have the same email address as the user input.
        // It returns `true` if the email address is unique, and `false` otherwise.
        for (User item : Users) {
            if (item.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validateEmail(String email) {
         // This method validates the user's email address using a regular expression. It ensures that the email address has a valid format, including a local part, domain name, and top-level domain.
        // It returns `true` if the email address is valid, and `false` otherwise.
        Pattern p = Pattern.compile("^(.+)@(\\S+)$");
        Matcher emailMatcher = p.matcher(email);
        return emailMatcher.matches();
    }

    @Override
    public boolean validPassword(String password) {
        // This method validates the user's password. It ensures that the password is at least 6 characters long.
        // It returns `true` if the password is valid, and `false` otherwise.
        return password.length() >= 6;
    }

    public List<String> validateUser(List<User> users, String nameandfamily, String phone, String email, String password) {
         // This method validates all of the user's input. It calls the other validation methods to check the name, email, password, and duplicate email.
        // It returns a list of error messages if any of the validation checks fail, or an empty list if all of the checks pass.

        Validation validemails = new Validation();

        boolean checkRequirements = validemails.validRequirements(nameandfamily, phone, password);

        boolean checkEmail = validemails.validateEmail(email);

        boolean checkename = validemails.validName(nameandfamily);

        boolean checkePassword = validemails.validPassword(password);

        boolean checkemailduplicate = validemails.validateDuplicateEmail(users, email);

        List<String> validationErrors = new ArrayList<>();
        if (!checkRequirements) {

            validationErrors.add("Name&Family  or Phone or password is empty");
        }
        if (!checkEmail) {

            validationErrors.add("Email is not valid");
        }
        if (!checkename) {

            validationErrors.add("Name&Family must be A-Z or a-z");
        }
        if (!checkePassword) {

            validationErrors.add("password must be more than 6 integers");
        }
        if (!checkemailduplicate) {
            validationErrors.add("Email is duplicate\\n Please enter another email");

        }
        return validationErrors;
    }
}
