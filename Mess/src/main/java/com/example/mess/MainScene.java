package com.example.mess;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScene implements Initializable {
    private int count = 2;

    public static final int left = 1;
    public static final int right = 0;

    @FXML
    private Circle avatar;

    @FXML
    private Button call;

    @FXML
    private Button image;

    @FXML
    private TextArea message;

    @FXML
    private Label name;

    @FXML
    private Button send;

    @FXML
    private Label status;

    @FXML
    private Button video;

    @FXML
    private ScrollPane pane;

    @FXML
    private VBox content;

    @FXML
    private HBox hBox;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            avatar.setFill(new ImagePattern(new Image(getClass().getResource("Avatar.jpeg").toURI().toString())));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        pane.setFitToWidth(true);


        createButton("Send.png", send);
        createButton("Call.png", call);
        createButton("Video.png", video);
        createButton("Image.png", image);

        content.heightProperty().addListener(observable -> pane.setVvalue(1));

        hBox.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                send();
                System.out.println(1);
            }
        });
    }

    public void createButton(String image, Button button) {
        ImageView imageView = new ImageView();
        try {
            imageView.setImage(new Image(getClass().getResource(image).toURI().toString()));
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(30);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        button.setGraphic(imageView);
    }

    @FXML
    void call(ActionEvent event) {
        try {
            changeScreen("hello-view.fxml", "call screen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void image(ActionEvent event) {
        try {
            changeScreen("hello-view.fxml", "image screen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void send() {
        if (!message.getText().isEmpty()) {
            HBox hBox = gethBox();
            content.getChildren().add(hBox);
            count++;
            message.setText("");
        }
    }

    private HBox gethBox() {
        HBox hBox = new HBox();
        hBox.setPrefWidth(content.getWidth());

        setText(hBox);
        return hBox;
    }

    private void setText(HBox hBox) {
        Text text = new Text(message.getText());
        text.setStyle("-fx-font: 24 arial; ");
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(content.getWidth() / 1.2);

        textFlow.setPadding(new Insets(10));
        text.setFill(Color.WHITE);
        textFlow.setStyle("-fx-background-color: #3978F7; -fx-background-radius: 10");

        hBox.getChildren().add(textFlow);
        chooseMode(count % 2, hBox, text, textFlow);
    }

    public void chooseMode(int mode, HBox hBox, Text text, TextFlow textFlow) {
        switch (mode) {
            case left:
                hBox.setAlignment(Pos.CENTER_LEFT);
                text.setFill(Color.BLACK);
                textFlow.setStyle("-fx-background-color: #F0F0F0; -fx-background-radius: 10");
                break;
            case right:
                hBox.setAlignment(Pos.CENTER_RIGHT);
                text.setFill(Color.WHITE);
                textFlow.setStyle("-fx-background-color: #3978F7; -fx-background-radius: 10");
                break;
        }
    }

    @FXML
    void video(ActionEvent event) {
        try {
            changeScreen("hello-view.fxml", "video screen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Circle getAvatar() {
        return avatar;
    }

    public void setAvatar(Circle avatar) {
        this.avatar = avatar;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getStatus() {
        return status;
    }

    public void setStatus(Label status) {
        this.status = status;
    }



    public void changeScreen(String sceneName, String usage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = anchorPane.getScene();
        Parent root = loader.load();
        HelloController controller = loader.getController();

        controller.welcomeText.setText(usage);
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
