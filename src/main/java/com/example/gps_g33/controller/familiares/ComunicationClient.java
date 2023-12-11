package com.example.gps_g33.controller.familiares;

import com.example.gps_g33.controller.funcionarios.Server;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ComunicationClient implements Initializable {

    @FXML
    public Button button_send;
    @FXML
    public TextField tf_message;
    @FXML
    public VBox vb_message;
    @FXML
    public ScrollPane sp_main;
    @FXML
    public Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            client = new Client(new Socket("localhost", 8080));
            System.out.println("Cliente criado");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar o cliente");
        }

        vb_message.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        client.receiveMessageFromServer(vb_message);

        button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String messageToSend = tf_message.getText();
                if(!messageToSend.isEmpty()){
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.CENTER_RIGHT);
                    hbox.setPadding(new Insets(5,5,5,10));

                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);

                    textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            " -fx-background-color: rgb(15,125,242);" +
                            " -fx-background-radius: 20px;");
                    textFlow.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0.934,0.945,0.996));

                    hbox.getChildren().add(textFlow);
                    vb_message.getChildren().add(hbox);

                    client.sendMessageToServer(messageToSend);
                    tf_message.clear();
                }
            }
        });
    }


    public static void addLabel(String messageFromServer, VBox vbox) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(5,10,5,10));

        Text text = new Text(messageFromServer);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(233,233,235);"+
                " -fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5,10,5,10));

        hbox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hbox);
            }
        });
    }
}
