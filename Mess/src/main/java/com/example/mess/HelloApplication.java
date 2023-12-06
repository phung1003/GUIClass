package com.example.mess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane stackPane = new StackPane();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("MainScene.fxml"));
        stackPane.getChildren().add(root);
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(800);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}