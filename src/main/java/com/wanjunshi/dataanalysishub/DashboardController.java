package com.wanjunshi.dataanalysishub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DashboardController {
    public Text welcomeText;
    public TextField postIDTF;
    public Button upgradeVIPButton;
    public Button dataVisButton;
    public Button bulkImportButton;

    public void initialize(){
        if(Application.db.getCurrentUser().isVip()){
            upgradeVIPButton.setDisable(true);
        } else{
            dataVisButton.setDisable(true);
            bulkImportButton.setDisable(true);
        }

        welcomeText.setText("Welcome " + Application.db.getCurrentUser().getUsername());
    }

    private int getPostID(){
        try {
            var id = Integer.parseInt(postIDTF.getText());
            return id;
        }catch (NumberFormatException e){
            Application.showDialog(Alert.AlertType.ERROR, "Error", "Post ID must be a number");
        }
        return -1;
    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Application.navigate("edit", 600, 400, "Edit Profile", new EditController());
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Application.db.logout();
        Application.navigate("login", 600, 400, (Stage) welcomeText.getScene().getWindow());
    }

    public void upgradeVIP(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Upgrade to VIP");
        alert.setHeaderText("Do you want to subscribe to the application for a monthly fee of $0");
        alert.setContentText("Select yes or no");

        // Set the buttons for Yes and No
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Show the dialog and wait for the user's choice
        Optional<ButtonType> result = alert.showAndWait();

        // Process the user's choice
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Application.db.upgradeToVIP();
            Application.showDialog(Alert.AlertType.INFORMATION, "VIP", "Upgrade success, please logout and log in again to access VIP functionalities");
        }
    }

    public void dataVis(ActionEvent actionEvent) throws IOException {
        Application.navigate("vis", 600, 400, "Visualization", new VisController());
    }

    public void bulkImport(ActionEvent actionEvent) {
        Application.showDialog(Alert.AlertType.ERROR, "Error", "Not implemented yet");
    }

    public void getN(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Get top N likes");
        dialog.setHeaderText("Enter number of posts to fetch");
        dialog.setContentText("Number:");

        // Show the dialog and wait for user input
        Optional<String> result = dialog.showAndWait();

        // Process the user input
        result.ifPresent(number -> {
            try {
                int num = Integer.parseInt(number);
                var posts = Application.db.getTopNLikes(num);
                Application.navigate("top-n", 600, 400, "Top N likes", new TopNController(posts));
            } catch (NumberFormatException e) {
                Application.showDialog(Alert.AlertType.ERROR, "Error", "Must be a number");
            } catch (IOException e) {
                Application.showDialog(Alert.AlertType.ERROR, "Error", "Unable to load data");
            }
        });

    }

    public void addPost(ActionEvent actionEvent) throws IOException {
        Application.navigate("add-post", 600, 400, "Add Post", new AddPostController());
    }

    public void deletePost(ActionEvent actionEvent) {
        var id = getPostID();
        if(id != -1){
           if(Application.db.deletePost(id)){
               Application.showDialog(Alert.AlertType.INFORMATION, "Delete Post", "Post deleted");
               return;
           }
            Application.showDialog(Alert.AlertType.ERROR, "Delete Post", "Post not found");
        }
    }

    
    public void exportPost(ActionEvent actionEvent) {
        var id = getPostID();
        if(id != -1){
            var post = Application.db.getPost(id);
            if (post == null){
                Application.showDialog(Alert.AlertType.ERROR, "Export Post", "Post not found");
                return;
            }
            if(Application.db.exportPost(post, (Stage) welcomeText.getScene().getWindow())){
                Application.showDialog(Alert.AlertType.INFORMATION, "Export Post", "Post successfully exported");
                return;
            }
            Application.showDialog(Alert.AlertType.ERROR, "Delete Post", "Post not found");
        }
    }

    public void getPost(ActionEvent actionEvent) throws IOException {
        var id = getPostID();
        if(id != -1){
            Application.navigate("get-post", 600, 400, "Get Post by ID", new GetPostController(id));
        }
    }
}
