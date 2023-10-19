package com.wanjunshi.dataanalysishub;

import com.wanjunshi.dataanalysishub.models.UserModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateController {
    public TextField usernameTF;
    public TextField fnameTF;
    public TextField lNameTF;
    public PasswordField passwordTF;

    public void login(ActionEvent actionEvent) throws IOException {
        Application.navigate("login", 600, 400, (Stage) usernameTF.getScene().getWindow());
    }

    public void create(ActionEvent actionEvent) throws IOException {
        var username = usernameTF.getText();
        var pasword = passwordTF.getText();
        var fname = fnameTF.getText();
        var lname = lNameTF.getText();

        if(username.length() < 4){
            Application.showDialog(Alert.AlertType.ERROR, "Registration", "Username must be at least 4 characters");
            return;
        }
        if(fname.isEmpty()){
            Application.showDialog(Alert.AlertType.ERROR, "Registration", "First name must not be empty");
            return;
        }
        if(lname.isEmpty()){
            Application.showDialog(Alert.AlertType.ERROR, "Registration", "Last name must not be empty");
            return;
        }
        if(pasword.length() < 8){
            Application.showDialog(Alert.AlertType.ERROR, "Registration", "Password must be 8 characters");
            return;
        }

        // if it returns false is because that the username is already exists.
        if(!Application.db.register(new UserModel(username,  fname, lname, pasword))){
            Application.showDialog(Alert.AlertType.ERROR, "Registration", "Username exists");
            return;
        }

        Application.navigate("dashboard", 600, 400, (Stage)passwordTF.getScene().getWindow());
    }
}
