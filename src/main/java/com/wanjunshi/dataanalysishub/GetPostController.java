package com.wanjunshi.dataanalysishub;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GetPostController {
    final int id;
    public GetPostController(int id) {
        this.id = id;
    }

    public void initialize() {
        var post = Application.db.getPost(id);
        if (post == null){
            Application.showDialog(Alert.AlertType.ERROR, "Get Post", "Post not found");
            ((Stage)idTF.getScene().getWindow()).close();
            return;
        }
        idTF.setText(String.valueOf(post.getId()));
        contentTF.setText(post.getContent());
        sharesTF.setText(String.valueOf(post.getShares()));
        likesTF.setText(String.valueOf(post.getLikes()));
        datetimeTF.setText(post.getTime().toString());
    }

    public TextField idTF;
    public TextField contentTF;
    public TextField sharesTF;
    public TextField likesTF;
    public TextField datetimeTF;
}
