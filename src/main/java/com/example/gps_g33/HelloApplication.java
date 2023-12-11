package com.example.gps_g33;

import com.example.gps_g33.modelos.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    public Data data;

    @Override
    public void start(Stage stage) throws IOException {
        data = Data.getInstance();

        // Verifica se há credenciais salvas
        if (data.loadCredentials() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/views/login/Login.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 500, 250);
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
            stage.setScene(scene);

            LoginController loginController = fxmlLoader.getController();

            // Realiza a verificação das credenciais e faz o login, se aplicável
            loginController.checkAndProcessSavedCredentials();

            stage.setResizable(false);
            stage.show();

            // Fecha a janela antiga usando Platform.runLater
            if (loginController.isLoggedIn() == true) {
                Stage oldStage = (Stage) root.getScene().getWindow();
                oldStage.close();
            }
        } else {
            // Se não há credenciais salvas, exibe a janela de login normalmente
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/login/Login.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 500, 250);
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
            stage.setScene(scene);

            stage.setResizable(false);
            stage.show();
        }
    }



    public static void main(String[] args) {

        launch();
    }

}