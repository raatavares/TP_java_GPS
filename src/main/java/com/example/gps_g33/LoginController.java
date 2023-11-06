package com.example.gps_g33;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public Button loginButton;

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(username, password)) {
            String viewPath = getViewPathForUser(username);
            loadView(viewPath);
        } else {
            // Tratamento de erro para credenciais inválidas
        }
    }

    @FXML
    public void initialize(){
        loginButton = new Button();
    }

    public boolean isValidLogin(String username, String password) {
        return username.equals("culinaria") && password.equals("culinaria") ||
                username.equals("gerencia") && password.equals("gerencia");
    }

    public String getViewPathForUser(String username) {
        if (username.equals("culinaria")) {
            return "views/depCulinaria/Culinaria_Home.fxml";
        } else if (username.equals("gerencia")) {
            return "views/gerencia/Gerencia_Navbar.fxml";
        }
        return null;
    }

    public void loadView(String viewPath) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource(viewPath));
        Scene scene = new Scene(fxmlLoader.load(), 1200 , 800);
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }
}
