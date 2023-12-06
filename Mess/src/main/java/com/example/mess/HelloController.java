package com.example.mess;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {
    @FXML
    Label welcomeText;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        changeScreen();
    }

    public void changeScreen() throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("MainScene.fxml"));
        Scene scene = anchorPane.getScene();
        StackPane stackPane = (StackPane) scene.getRoot();
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        root.translateYProperty().set(scene.getHeight());
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.25), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> stackPane.getChildren().remove(anchorPane));
        timeline.play();
    }
}