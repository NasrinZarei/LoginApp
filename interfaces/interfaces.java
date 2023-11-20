package interfaces;


import java.util.List;

import loginapp.User;

public interface interfaces {

    boolean validRequirements(String nameandfamily, String email, String password);

    boolean validateDuplicateEmail(List<User> users, String email);

    boolean validateEmail(String email);

    boolean validName(String nameandfamily);

    boolean validPassword(String password);
}
