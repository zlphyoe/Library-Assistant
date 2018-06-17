/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.editbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.assistant.dao.BookDAO;
import library.assistant.model.Book;

/**
 * FXML Controller class
 *
 * @author Zaw Lwin Phyo
 */
public class EditbookController implements Initializable {

    @FXML
    private JFXTextField bookFeild;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField authorField;
    @FXML
    private JFXTextField publisherField;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton cancelBtn;
BookDAO bookDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     bookFeild.setDisable(true);
     bookDAO=new BookDAO();
    }    

    @FXML
    private void updateBook(ActionEvent event) {
        String id=bookFeild.getText();
        String title=titleField.getText();
        String author=authorField.getText();
        String publisher=publisherField.getText();
        if(id.isEmpty()|| title.isEmpty()||author.isEmpty()||publisher.isEmpty()){
           Alert alert=new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warining");
          alert.setHeaderText(null);
          alert.setContentText("Please fill!");
          alert.show();
            return;
        }
        Book book=new Book (id,title,author,publisher);
        try {
           bookDAO.updateBook(book);
           Stage currentStage=(Stage)updateBtn.getScene().getWindow();
        currentStage.close();

       } catch (SQLException ex) {
            Logger.getLogger(EditbookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage currentStage=(Stage)updateBtn.getScene().getWindow();
        currentStage.close();
    }

    public void setBookInfo(Book selectedBook) {
bookFeild.setText(selectedBook.getId());
titleField.setText(selectedBook.getTitle());
authorField.setText(selectedBook.getAuthor());
publisherField.setText(selectedBook.getPublisher());
    }
    
}
