package com.example.myfirstjavafx;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AppController {
    @FXML public GridPane pnLogin;
    @FXML public GridPane pnHomePage;

    @FXML public TextField nameInput;
    @FXML public PasswordField passwordInput;
    @FXML public ColorPicker cpicker;
    public static String currentUser;

    public boolean isValid;
    public Text actiontarget;
    public Button btnLogOut;
    public AnchorPane home;

    HashMap<String, String> mp1 = new HashMap<>(){
        {
            put("user1", "1");
            put("user2", "2");
            put("user3", "3");
        }
    };

    HashMap<String, String> mp2 = new HashMap<>(){
        {
            put("user1", "user1.css");
            put("user2", "user2.css");
            put("user3", "user3.css");
        }
    };



    @FXML protected void onSignIn(ActionEvent event) throws IOException{
        String name = nameInput.getText();
        String password = passwordInput.getText();
        if(mp1.containsKey(name)){
            if(mp1.get(name).equals(password)){
                currentUser = name;
                actiontarget.setText("Access granted.");
                Parent homeView = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("homepage-view.fxml")));
                AnchorPane p = (AnchorPane) pnLogin.getParent();
                p.getChildren().remove(pnLogin);
                p.getChildren().add(homeView);
            }else{
                actiontarget.setText("Incorrect password. Please try again");
            }
        }else{
            actiontarget.setText("User not in database. Pleas try again.");
        }
    }

    @FXML public void onLogOut(ActionEvent actionEvent) throws IOException {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(getClass().getResource(mp2.get(currentUser)).getPath()));
//            bw.write(".root { -fx-background-image: url(\"bg.jpg\"); })");
//            bw.newLine();
            bw.write("#pnHomePage { -fx-background-color: #" + cpicker.getValue().toString().substring(2, 8) + "; }");
            bw.close();

            Parent loginView = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("login-view.fxml")));
            AnchorPane p = (AnchorPane) pnHomePage.getParent();
            p.getChildren().remove(pnHomePage);
            p.getChildren().add(loginView);

//            p.getScene().getStylesheets().clear();
            p.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(mp2.get(currentUser))).toExternalForm());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
