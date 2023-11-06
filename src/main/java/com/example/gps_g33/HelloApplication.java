package com.example.gps_g33;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Funcionario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Hashtable;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/gerencia/Gerencia_Navbar.fxml"));
//
//       Scene scene = new Scene(fxmlLoader.load(), 1280 , 720);
//        stage.setScene(scene);
//
//        stage.setResizable(false);
//        stage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_Home.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load(), 1280 , 720);
//        stage.setScene(scene);
//
//        stage.setResizable(false);
//        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/login/Login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600 , 600);
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

}