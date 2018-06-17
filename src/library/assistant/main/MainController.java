/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.assistant.dao.BookDAO;
import library.assistant.dao.IssueInfoDAO;
import library.assistant.dao.MemberDAO;
import library.assistant.model.Book;
import library.assistant.model.IssueInfo;
import library.assistant.model.Member;
import library.assistant.util.MessageBox;

/**
 *
 * @author Zaw Lwin Phyo
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton addBookBtn;
    @FXML
    private JFXButton homeBtn;
    @FXML
    private StackPane centerPane;
    @FXML
    private TabPane homeView;
    @FXML
    private JFXButton addMemberBtn;
    @FXML
    private JFXButton bookListBtn;
    @FXML
    private JFXButton memberListBtn;
    @FXML
    private JFXTextField bookidField;
    @FXML
    private Text titleText;
    @FXML
    private Text authorText;
    @FXML
    private Text publisherText;
 private BookDAO bookDAO;
 private IssueInfoDAO issueInfoDAO;
    @FXML
    private JFXTextField memberidField;
    @FXML
    private Text nameText;
    @FXML
    private Text emailText;
    @FXML
    private Text mobileText;
    @FXML
    private Text addressText;
   private MemberDAO memberDAO; 
    @FXML
    private Text availableText;
    @FXML
    private JFXButton issueBtn;
    @FXML
    private Text availableText1;
    @FXML
    private JFXTextField issueBookIDField;
    @FXML
    private Text mIDText;
    @FXML
    private Text mNameText;
    @FXML
    private Text mEmailText;
    @FXML
    private Text mMoblieText;
    @FXML
    private Text mAddressText;
    @FXML
    private Text bIssueText;
    @FXML
    private Text bAuthorText;
    @FXML
    private Text bPublisherText;
    @FXML
    private Text issueDateText;
    @FXML
    private Text renewCountText;
    @FXML
    private JFXButton renewBtn;
    @FXML
    private JFXButton submissBtn;
    @FXML
    private MenuItem configItem;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       bookDAO=new BookDAO();
       memberDAO=new MemberDAO();
       issueInfoDAO=new IssueInfoDAO();
    }    

    @FXML
    private void loadAddBookView(ActionEvent event) throws IOException{
                Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/addbook/addbook.fxml"));
//
//        Scene sc=new Scene(root);
//Stage stage=new Stage();
//stage.initOwner(addBookBtn.getScene().getWindow());
//stage.initModality(Modality.WINDOW_MODAL);
//stage.setScene(sc);
//stage.show();
centerPane.getChildren().clear();
centerPane.getChildren().add(root);

    }

    @FXML
    private void loadHomeView(ActionEvent event) {
        centerPane.getChildren().clear();
        centerPane.getChildren().add(homeView);
        
    }

    @FXML
    private void loadAddMemberView(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/addmember/addmember.fxml"));

        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);

        
    }

    @FXML
    private void loadBookListView(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/booklist/booklist.fxml"));

        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);

        
    }

    @FXML
    private void loadMemberListView(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/memberlist/memberlist.fxml"));

        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);

        
    }

    @FXML
    private void searchBookInfo(ActionEvent event) {
  clearBookCache();
        
    String id=bookidField.getText();
    if(id.isEmpty()){
    System.out.println("Please enter book ID first...");
    return;
}
        try {
            //Searching book
            Book book=bookDAO.getBook(id);
            if(book!=null){
                titleText.setText(book.getTitle());
                authorText.setText(book.getAuthor());
                publisherText.setText(book.getPublisher());
                boolean available=book.isAvailable();
                if(available){
                    availableText.setText("Available");
                }else{
                    availableText.setText("Not available");
                }
            }else{
                MessageBox.showErrorMessage("cannot find any book");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void searchMemberInfo(ActionEvent event) {
       clearMemberCache();
        
        String id=memberidField.getText();
if(id.isEmpty()){
    System.out.println("Please enter member ID first...");
    return;
}
        try {
            //Searching book
           Member member=memberDAO.getMember(id);
            if(member!=null){
                nameText.setText(member.getName());
                emailText.setText(member.getEmail());
                mobileText.setText(member.getMobile());
                addressText.setText(member.getAddress());

            }else{
              MessageBox.showErrorMessage("Cannot find any member");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    private void clearBookCache() {
titleText.setText("-");
authorText.setText("-");
publisherText.setText("-");
availableText.setText("-");


    }

    private void clearMemberCache() {
nameText.setText("-");
emailText.setText("-");
mobileText.setText("-");
addressText.setText("-");


    }

    @FXML
    private void issueBook(ActionEvent event) {
        String bookId=bookidField.getText();
        String memberId=memberidField.getText();
        if(bookId.isEmpty()|| memberId.isEmpty()){
            System.out.println("Enter book id and member id first.");
            return;
        }
        try {        
            Book book=bookDAO.getBook(bookId);
        if(book.isAvailable()){
        issueInfoDAO.saveIssueInfo(new IssueInfo(memberId,bookId)); 
        bookDAO.updateAvailability(bookId, false);
            System.out.println("Book issue success");
        }else{
            System.out.println("This book is already issue!");
        }
        
        
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchIssueBookInfo(ActionEvent event) {
        clearIssueInfo();
        String bookId=issueBookIDField.getText();
        //issue table
        //book_id,member_id,date,renew_count
        //book table,member table
        if(bookId.isEmpty()){
     Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Please enter book id first!");
          alert.show();
          return;
                  }
    try {
    IssueInfo issueInfo = issueInfoDAO.getIssueInfo(bookId);
    if(issueInfo!=null){
        Book book=bookDAO.getBook(bookId);
        
        bIssueText.setText(book.getTitle());
        bAuthorText.setText(book.getAuthor());
        bPublisherText.setText(book.getPublisher());
        
        Member member=memberDAO.getMember(issueInfo.getMemberId());
        mNameText.setText(member.getName());
        mEmailText.setText(member.getEmail());
        mMoblieText.setText(member.getMobile());
        mAddressText.setText(member.getAddress());
        
    mIDText.setText(issueInfo.getMemberId());
    issueDateText.setText("Issue Date:"+issueInfo.getIssueDate().toString());
    renewCountText.setText("Renew Count:"+issueInfo.getRenewCount());
    
    }else{
        
    System.out.println("Cannot find any issue for this book id");
   
    }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void renewBook(ActionEvent event) {
           String bookId=issueBookIDField.getText();
        if(bookId.isEmpty()){
             Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Please enter book id first!");
          alert.show();
          return;
     }
        try {
            IssueInfo issueInfo=issueInfoDAO.getIssueInfo(bookId);
            if(issueInfo!=null){
                
               issueInfoDAO.updateRenewCount(bookId);
            }else{
                
          Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Cannot find any book!");
          alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void submissBook(ActionEvent event) {
        
        String bookId=issueBookIDField.getText();
        if(bookId.isEmpty()){
             Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Please enter book id first!");
          alert.show();
          return;
     }
        try {
            IssueInfo issueInfo=issueInfoDAO.getIssueInfo(bookId);
            if(issueInfo!=null){
                
               issueInfoDAO.deleteIssueInfo(bookId);
               bookDAO.updateAvailability(bookId, true);
            
            }else{
                
          Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Cannot find any book!");
          alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clearIssueInfo(){
        mIDText.setText("-");
        mNameText.setText("-");
        mEmailText.setText("-");
        mMoblieText.setText("-");
        mAddressText.setText("-");
        
        bIssueText.setText("-");
        bAuthorText.setText("-");
        bPublisherText.setText("-");
        
        issueDateText.setText("-");
        renewCountText.setText("-");
        
        
    }

    @FXML
    private void loadDatabaseConfigView(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/dbconfig/databaseconfig.fxml"));
Scene sc=new Scene(root);
Stage stage=new Stage();
stage.setTitle("Database Configuration");
stage.setScene(sc);

stage.initOwner(centerPane.getScene().getWindow());
stage.initModality(Modality.WINDOW_MODAL);
stage.showAndWait();
MessageBox.showAndWaitErrorMessage("Please restart app");
Stage currentStage=(Stage)centerPane.getScene().getWindow();
currentStage.close();
    }
    
}
