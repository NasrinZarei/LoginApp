
package loginapp;

import javafx.scene.control.Alert;
/**
 * Displays an information alert dialog with the given text.
 *
 */

public class Helper {
    
    public void help(String txt){
         Alert alertName = new Alert(Alert.AlertType.INFORMATION, txt);
         alertName.show();
    }
}
