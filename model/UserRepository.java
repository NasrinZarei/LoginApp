package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;

import loginapp.User;

public class UserRepository {

    List<User> Users = new ArrayList<>();

    URL url = getClass().getResource("../resources/Users.txt");

    File file = new File(url.getPath());
    
     /**
     * Reads the user data from the file and returns a list of users.
     * @throws FileNotFoundException if the file does not exist.
     * @throws ClassNotFoundException if the file contains invalid data.
     * @return a list of users
     */

    public List<User> readRegisterFile() throws FileNotFoundException, ClassNotFoundException {
        try ( ObjectInputStream readobject = new ObjectInputStream(new FileInputStream(file))) {
            Users = (List<User>) readobject.readObject();
            readobject.close();
        } catch (IOException ex) {
        }

        return Users;
    }
    /**
     * Writes the user data to the file.
     * @param nameandfamily
     * @param phone the user's phone number
     * @param email the user's email address
     * @param password the user's password
     */
    public void writeRegisterFile(String nameandfamily, String phone, String email, String password) {

        try ( ObjectOutputStream writeUserobject = new ObjectOutputStream(new FileOutputStream(file))) {
            User NewUser = new User(nameandfamily, phone, email, password);

            NewUser.setId(Users.size());
            Users.add(NewUser);
            writeUserobject.writeObject(Users);

            writeUserobject.close();

            System.out.println(NewUser.getId());
            System.out.println(NewUser.getNameandfamily());
        } catch (Exception ex) {
        }
    }

}
