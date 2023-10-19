package com.wanjunshi.dataanalysishub;

import com.wanjunshi.dataanalysishub.models.UserModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    public TextField usernameTF;
    public TextField fnameTF;
    public TextField lNameTF;
    public PasswordField passwordTF;

    public void initialize(){
        var user = Application.db.getCurrentUser();
        usernameTF.setText(user.getUsername());
        fnameTF.setText(user.getFname());
        lNameTF.setText(user.getLname());
    }

    public void create(ActionEvent actionEvent) {
        var username = usernameTF.getText();
        var pasword = passwordTF.getText();
        var fname = fnameTF.getText();
        var lname = lNameTF.getText();

        if(username.length() < 4){
            Application.showDialog(Alert.AlertType.ERROR, "Edit Profile", "Username must be at least 4 characters");
            return;
        }
        if(fname.isEmpty()){
            Application.showDialog(Alert.AlertType.ERROR, "Edit Profile", "First name must not be empty");
            return;
        }
        if(lname.isEmpty()){
            Application.showDialog(Alert.AlertType.ERROR, "Edit Profile", "Last name must not be empty");
            return;
        }
        if(pasword.length() < 8){
            Application.showDialog(Alert.AlertType.ERROR, "Edit Profile", "Password must be 8 characters");
            return;
        }

        if(!Application.db.editProfile(new UserModel(username,  fname, lname, pasword))){
            Application.showDialog(Alert.AlertType.ERROR, "Edit Profile", "Username exists");
            return;
        }

        Application.showDialog(Alert.AlertType.INFORMATION, "Edit Profile", "Profile edited successfully");
        ((Stage)fnameTF.getScene().getWindow()).close();
    }
}
