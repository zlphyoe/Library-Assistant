/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.dbconfig;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.assistant.util.DatabaseConfigLoader;
import library.assistant.util.DatabaseProperty;

/**
 * FXML Controller class
 *
 * @author Zaw Lwin Phyo
 */
public class DatabaseconfigController implements Initializable {

    @FXML
    private Spinner<Integer> portSpinner;
    @FXML
    private TextField hostField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    private JFXButton saveBtn;
private DatabaseConfigLoader dbLoader;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbLoader=new DatabaseConfigLoader();
        DatabaseProperty dbProp=dbLoader.getDatabaseProperties();
        hostField.setText(dbProp.getHost());
        nameField.setText(dbProp.getUser());
        passwordField.setText(dbProp.getPassword());
        SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(3300,3320, Integer.parseInt(dbProp.getPort()));
       portSpinner.setValueFactory(valueFactory);
    }
    @FXML
    private void saveDatabaseConfig(ActionEvent event) {
        String host=hostField.getText();
        String port=portSpinner.getValue().toString();
        String user=nameField.getText();
        String password=passwordField.getText();
        
        DatabaseProperty dbProp=new DatabaseProperty(host, user, password, port);
        
        dbLoader.saveDatabaseProperties(dbProp);
        Stage stage=(Stage)saveBtn.getScene().getWindow();
        stage.close();
    }
    
}
