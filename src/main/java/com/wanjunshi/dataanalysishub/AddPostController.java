package com.wanjunshi.dataanalysishub;

import com.wanjunshi.dataanalysishub.models.PostModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddPostController {
    public TextField idTF;
    public TextField contentTF;
    public TextField sharesTF;
    public TextField likesTF;
    public TextField datetimeTF;

    public void addPost(ActionEvent actionEvent) {
        int id;
        try {
            id = Integer.parseInt(idTF.getText());
            // Handle the exception about that the ID is not integer.
        } catch (NumberFormatException e){
            Application.showDialog(Alert.AlertType.ERROR, "Add Post", "Please input a valid int id");
            return;
        }
        int shares;
        try {
            shares = Integer.parseInt(sharesTF.getText());
        } catch (NumberFormatException e){
            Application.showDialog(Alert.AlertType.ERROR, "Add Post", "Please input a valid int shares");
            return;
        }
        int likes;
        try {
            likes = Integer.parseInt(likesTF.getText());
        } catch (NumberFormatException e){
            Application.showDialog(Alert.AlertType.ERROR, "Add Post", "Please input a valid int likes");
            return;
        }
        String content = contentTF.getText();
        // Handle if the content is empty or containing commas(may result in bad CSV file)
        if(content.isEmpty() || content.contains(",")){
            Application.showDialog(Alert.AlertType.ERROR, "Add Post", "Content must not be empty or containing comma");
            return;
        }

        LocalDateTime dt;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        // Handle the exception for that the time format is not right.
        try {
            dt = LocalDateTime.parse(datetimeTF.getText(), formatter);
        } catch (DateTimeParseException e) {
            Application.showDialog(Alert.AlertType.ERROR, "Add Post", "Date must folow the format");
            return;
        }

        if(!Application.db.addPost(new PostModel(id, content, Application.db.getCurrentUser().getUsername(), likes, shares, dt))){
            Application.showDialog(Alert.AlertType.ERROR, "Add Post", "ID existed");
            return;
        }
        Application.showDialog(Alert.AlertType.INFORMATION, "Add Post", "Post Added");
        ((Stage)datetimeTF.getScene().getWindow()).close();
    }
}
