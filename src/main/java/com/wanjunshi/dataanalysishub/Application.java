package com.wanjunshi.dataanalysishub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
// This is an entry point.

public class Application extends javafx.application.Application {

    public static Database db = new Database();
    @Override
    public void start(Stage stage) throws IOException {
        navigate("login", 600, 400, stage);
    }

    public static void main(String[] args) {
        launch();
    }
    public static <T>void navigate(String name, int w, int h, String title,T controller ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(name+"-view.fxml"));
        fxmlLoader.setControllerFactory(aClass -> controller);
        Scene scene = new Scene(fxmlLoader.load(), w, h);
        var s = new Stage();
        s.setTitle(title);
        s.setScene(scene);
        s.setResizable(false);
        s.show();
    }

    public static void navigate(String name, int w, int h, Stage s) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(name+"-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), w, h);
        s.setTitle("Data Analytics Hub");
        s.setScene(scene);
        s.setResizable(false);
        s.show();
    }

    public static void showDialog(Alert.AlertType type, String title, String desc){
        var alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(desc);
        alert.showAndWait();
    }
}