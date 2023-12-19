package com.example.gps_g33;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Familiar;
import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.UserCredentials;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    public Label lblError;

    private Data data;

    public CheckBox RememberChoiceBox;
    private boolean loggedIn = false;
    UserCredentials savedCredentials;


    public void initialize(){
        data = Data.getInstance();
        loginButton = new Button();
    }

    @FXML
    public void handleLogin() throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        if (RememberChoiceBox.isSelected()) {
            data.saveCredentials(new UserCredentials(usernameField.getText(), passwordField.getText()));
        }

        if (loggedIn) {
            return;
        }

        // Primeiro, tenta validar como funcionário
        Funcionario validFunc = data.isValidLogin(username, password);
        if (validFunc != null) {
            processLogin(validFunc.getId(), validFunc.getDepartamento());
            return; // Termina a execução se um funcionário válido for encontrado
        }

        // Em seguida, tenta validar como familiar
        Familiar validFam = data.isValidLoginFamiliar(username, password);
        if (validFam != null) {
            processLogin(validFam.getId(), validFam.getDepartamento());
            return; // Termina a execução se um familiar válido for encontrado
        }

        // Se nenhum dos dois for válido, mostra erro
        lblError.setText("Credenciais inválidas");
        lblError.setVisible(true);
    }

    private void exitApp() {
        System.out.println("Dados guardados com sucesso!");

        Data data = Data.getInstance();

        data.saveData();

        Platform.exit();
    }

    private void processLogin(int userId, String departamento) throws IOException {
        String viewPath = getViewPathForUser(departamento);
        if (viewPath == null) {
            lblError.setText("Departamento ainda não implementado");
            lblError.setVisible(true);
        } else {
            data.setIdLogado(userId);
            data.setDepartamentoLogado(departamento);
            loggedIn = true; // Define o status de logado
            loadView(viewPath);
            lblError.setVisible(false);
        }
    }

    public String getViewPathForUser(String departamento) {
        if (departamento.equals("Culinaria")) {
            return "views/depCulinaria/Culinaria_Home.fxml";
        } else if (departamento.equals("Gerencia")) {
            return "views/gerencia/Gerencia_Navbar.fxml";
        }
        else if (departamento.equals("Clinico")) {
            return "views/depClinico/Consultas_Medicaçao.fxml";
        } else if (departamento.equals("Funcionario")) {
            return "views/funcionarios/Funcionários.fxml";
        }
        else if (departamento.equals("Familiares")){
            return "views/familiares/Familiares.fxml";
        }
        else if (departamento.equals("Animacao")){
            return "views/animacao/Animacao_Home.fxml";
        }
        return null;
    }

    public void loadView(String viewPath) throws IOException {

        //Fechar a janela do login
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();


        stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("views/gerencia/Gerencia_Navbar.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1280 , 720);
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            exitApp();
        });

        stage.setResizable(false);
        stage.show();
    }

    public void checkAndProcessSavedCredentials() throws IOException {
        if (data.loadCredentials() != null) {
            savedCredentials = data.loadCredentials();
            System.out.printf("Credentials: " + savedCredentials.getUsername() + " " + savedCredentials.getPassword() + "\n");
            Funcionario validFunc = data.isValidLogin(savedCredentials.getUsername(), savedCredentials.getPassword());
            if (validFunc != null) {
                processLogin(validFunc.getId(), validFunc.getDepartamento());
            }
            Familiar validFam = data.isValidLoginFamiliar(savedCredentials.getUsername(), savedCredentials.getPassword());
            if (validFam != null) {
                processLogin(validFam.getId(), validFam.getDepartamento());
                return; // Termina a execução se um familiar válido for encontrado
            }
        }
    }

    public boolean isLoggedIn() {
        System.out.printf("Logged in: " + loggedIn + "\n");
        return loggedIn;
    }
}
