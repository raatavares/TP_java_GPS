package com.example.gps_g33;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    public Label lblError;

    private Data data;


    public void initialize(){
        data = Data.getInstance();
        loginButton = new Button();
    }

    @FXML
    public void handleLogin() throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        Funcionario validFunc = data.isValidLogin(username, password);

        if (validFunc != null) {
            String viewPath = getViewPathForUser(validFunc.getDepartamento());
            if(viewPath == null) {
                lblError.setText("Departamento ainda não implementado");
                lblError.setVisible(true);
            }else {
                data.setIdLogado(validFunc.getId());
                loadView(viewPath);
                lblError.setVisible(false);
            }

        } else {
            lblError.setText("Credenciais inválidas");
            lblError.setVisible(true);
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

        stage.setResizable(false);
        stage.show();
    }
}
