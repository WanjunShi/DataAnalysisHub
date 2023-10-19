package com.wanjunshi.dataanalysishub;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public TextField usernameTF;
    public PasswordField passwordTF;

    public void register(ActionEvent actionEvent) throws IOException {
        Application.navigate("create", 600, 400, (Stage) usernameTF.getScene().getWindow());
    }

    public void login(ActionEvent actionEvent) throws IOException {
        var username = usernameTF.getText();
        var password = passwordTF.getText();
        // This part is about validation.
        if (username.isEmpty() || password.isEmpty()){
            Application.showDialog(Alert.AlertType.ERROR, "Login", "Username or password cannot be empty");
            return;
        }

        if (!Application.db.login(username, password)){
            Application.showDialog(Alert.AlertType.ERROR, "Login", "Username or password is incorrect");
            return;
        }
        Application.navigate("dashboard", 600, 400, (Stage)passwordTF.getScene().getWindow());
    }
}