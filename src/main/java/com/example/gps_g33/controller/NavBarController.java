package com.example.gps_g33.controller;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.LoginController;
import com.example.gps_g33.controller.depCulinaria.EditControllerRefeicao;
import com.example.gps_g33.controller.familiares.marcarHorarioVisitaController;
import com.example.gps_g33.controller.funcionarios.ComunicationServer;
import com.example.gps_g33.controller.gerencia.EditController;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Funcionario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NavBarController {

    public StackPane contentArea;
    public Text lblDate;


    //Gerencia
    @FXML
    public Button buttonFuncionarios;


    //Culinaria
    public Button buttonGerirStock;

    public Button buttonRefeicoes;
    //Ambos
    public Button buttonAvisos;
    public Button buttonResidentes;

    //Clinic
    public Button buttonConsultas;

    public Button buttonMedicamentos;

    //Funcionario
    public Button buttonInfoResidentes;
    public Button buttonChat;
    public Button buttonCriarHorario;

    //Familiares
    public Button buttonMarcarHorario;
    public Button buttonInfoFamiliares;

    //Animacao
    public Button buttonAtividades;
    private Data data;

    @FXML
    public void initialize() {
        data = Data.getInstance();
        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String data = formato.format(dataAtual);
        lblDate.setText(data);
        setButtonsView();
    }

    public void setButtonsView(){
        //String departamento = (data.getFuncionarioPorId(data.getIdLogado())).getDepartamento();
        String departamento = data.getDepartamentoLogado();
        if(departamento.equals("Culinaria")){
            buttonResidentes.setVisible(true);
            buttonGerirStock.setVisible(true);
            buttonRefeicoes.setVisible(true);

            buttonAvisos.setVisible(false);
            buttonAvisos.setVisible(false);
            buttonFuncionarios.setVisible(false);
            buttonFuncionarios.setManaged(false);
            buttonConsultas.setVisible(false);
            buttonMedicamentos.setVisible(false);
            buttonInfoResidentes.setVisible(false);
            buttonInfoResidentes.setManaged(false);
            buttonCriarHorario.setVisible(false);
            buttonCriarHorario.setManaged(false);
            buttonChat.setVisible(false);
            buttonChat.setManaged(false);
            buttonMarcarHorario.setVisible(false);
            buttonMarcarHorario.setManaged(false);
            buttonInfoFamiliares.setVisible(false);
            buttonInfoFamiliares.setManaged(false);
            buttonAtividades.setVisible(false);
            buttonAtividades.setManaged(false);


        } else if (departamento.equals("Gerencia")) {

            buttonAvisos.setVisible(true);
            buttonFuncionarios.setVisible(true);
            buttonResidentes.setVisible(true);

            buttonGerirStock.setVisible(false);
            buttonGerirStock.setManaged(false);
            buttonRefeicoes.setVisible(false);
            buttonRefeicoes.setManaged(false);
            buttonConsultas.setVisible(false);
            buttonMedicamentos.setVisible(false);
            buttonInfoResidentes.setVisible(false);
            buttonInfoResidentes.setManaged(false);
            buttonCriarHorario.setVisible(false);
            buttonCriarHorario.setManaged(false);
            buttonChat.setVisible(false);
            buttonChat.setManaged(false);
            buttonMarcarHorario.setVisible(false);
            buttonMarcarHorario.setManaged(false);
            buttonInfoFamiliares.setVisible(false);
            buttonInfoFamiliares.setManaged(false);
            buttonAtividades.setVisible(false);
            buttonAtividades.setManaged(false);
        }
        else if (departamento.equals("Clinico")) {

            buttonConsultas.setVisible(true);
            buttonMedicamentos.setVisible(true);

            buttonAvisos.setVisible(false);
            buttonAvisos.setManaged(false);
            buttonFuncionarios.setVisible(false);
            buttonFuncionarios.setManaged(false);
            buttonResidentes.setVisible(false);
            buttonResidentes.setManaged(false);
            buttonGerirStock.setVisible(false);
            buttonGerirStock.setManaged(false);
            buttonRefeicoes.setVisible(false);
            buttonRefeicoes.setManaged(false);
            buttonInfoResidentes.setVisible(false);
            buttonInfoResidentes.setManaged(false);
            buttonCriarHorario.setVisible(false);
            buttonCriarHorario.setManaged(false);
            buttonChat.setVisible(false);
            buttonChat.setManaged(false);
            buttonMarcarHorario.setVisible(false);
            buttonMarcarHorario.setManaged(false);
            buttonInfoFamiliares.setVisible(false);
            buttonInfoFamiliares.setManaged(false);
            buttonAtividades.setVisible(false);
            buttonAtividades.setManaged(false);
        }
        else if(departamento.equals("Funcionario")){
            buttonInfoResidentes.setVisible(true);
            buttonCriarHorario.setVisible(true);
            buttonChat.setVisible(true);

            buttonConsultas.setVisible(false);
            buttonConsultas.setManaged(false);
            buttonMedicamentos.setVisible(false);
            buttonMedicamentos.setManaged(false);
            buttonAvisos.setVisible(false);
            buttonAvisos.setManaged(false);
            buttonFuncionarios.setVisible(false);
            buttonFuncionarios.setManaged(false);
            buttonResidentes.setVisible(false);
            buttonResidentes.setManaged(false);
            buttonGerirStock.setVisible(false);
            buttonGerirStock.setManaged(false);
            buttonRefeicoes.setVisible(false);
            buttonRefeicoes.setManaged(false);
            buttonMarcarHorario.setVisible(false);
            buttonMarcarHorario.setManaged(false);
            buttonInfoFamiliares.setVisible(false);
            buttonInfoFamiliares.setManaged(false);
            buttonAtividades.setVisible(false);
            buttonAtividades.setManaged(false);
        }
        else if(departamento.equals("Familiares")){

            buttonMarcarHorario.setVisible(true);
            buttonInfoFamiliares.setVisible(true);
            buttonChat.setVisible(true);

            buttonInfoResidentes.setVisible(false);
            buttonInfoResidentes.setManaged(false);
            buttonCriarHorario.setVisible(false);
            buttonCriarHorario.setManaged(false);
            buttonConsultas.setVisible(false);
            buttonConsultas.setManaged(false);
            buttonMedicamentos.setVisible(false);
            buttonMedicamentos.setManaged(false);
            buttonAvisos.setVisible(false);
            buttonAvisos.setManaged(false);
            buttonFuncionarios.setVisible(false);
            buttonFuncionarios.setManaged(false);
            buttonResidentes.setVisible(false);
            buttonResidentes.setManaged(false);
            buttonGerirStock.setVisible(false);
            buttonGerirStock.setManaged(false);
            buttonRefeicoes.setVisible(false);
            buttonRefeicoes.setManaged(false);
            buttonAtividades.setVisible(false);
            buttonAtividades.setManaged(false);
        }
        else if(departamento.equals("Animacao")){
            buttonAtividades.setVisible(true);

            buttonMarcarHorario.setVisible(false);
            buttonInfoFamiliares.setVisible(false);
            buttonInfoResidentes.setVisible(false);
            buttonInfoResidentes.setManaged(false);
            buttonCriarHorario.setVisible(false);
            buttonCriarHorario.setManaged(false);
            buttonChat.setVisible(false);
            buttonChat.setManaged(false);
            buttonConsultas.setVisible(false);
            buttonConsultas.setManaged(false);
            buttonMedicamentos.setVisible(false);
            buttonMedicamentos.setManaged(false);
            buttonAvisos.setVisible(false);
            buttonAvisos.setManaged(false);
            buttonFuncionarios.setVisible(false);
            buttonFuncionarios.setManaged(false);
            buttonResidentes.setVisible(false);
            buttonResidentes.setManaged(false);
            buttonGerirStock.setVisible(false);
            buttonGerirStock.setManaged(false);
            buttonRefeicoes.setVisible(false);
            buttonRefeicoes.setManaged(false);
        }
    }

    public void switchToFuncionarios(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/gerencia/Gerencia_Funcionarios.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void switchToResidentes() throws IOException {
        String departamento = (data.getFuncionarioPorId(data.getIdLogado())).getDepartamento();
        if(departamento.equals("Culinaria")){
            Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depCulinaria/Culinaria_Residentes.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } else if (departamento.equals("Gerencia")) {
            Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/gerencia/Gerencia_Residentes.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        }
    }

    public void switchToChat() throws IOException {
        String departamento = (data.getFuncionarioPorId(data.getIdLogado())).getDepartamento();
        if(departamento.equals("Funcionario")){
            Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/funcionarios/ComunicationServer.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } else {
            System.out.println("Familiares");
            Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/familiares/ComunicationClient.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        }
    }

    public void switchToAvisos() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/gerencia/Gerencia_Avisos.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void switchToRefeicoes() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depCulinaria/Culinaria_Refeicoes.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void switchToConsultas() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depClinico/Consultas_Medicaçao.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void switchToMedicamentos() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depClinico/medicamentos&utensilios.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void switchToInfoResidentes() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/funcionarios/InformacoesDoResidente.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void switchToCriarHorario() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/funcionarios/CriarHorasVisita.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void switchToInfoFamiliares() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/familiares/InformacoesDoResidente.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    //TODO: criar horario
    public void switchToMarcarHorario() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/familiares/MarcarVisita.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void switchToAtividades() throws IOException{
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/animacao/Animacao_Home.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void buttonSair() throws IOException {
        Stage stage = (Stage) contentArea.getScene().getWindow();
        stage.close();
        data.saveData();

        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("views/login/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500 , 250);
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            exitApp();
        });

        stage.setResizable(false);
        stage.show();
    }
    private void exitApp() {
        if(data.getServer() != null){
            System.out.println("Server != null");
            data.getServer().closeEverything(data.getServer().getSocket(), data.getServer().getBufferedReader(), data.getServer().getBufferedWriter());
        }
        if(data.getClient() != null){
            System.out.println("Client != null");
            data.getClient().closeEverything(data.getClient().getSocket(), data.getClient().getBufferedReader(), data.getClient().getBufferedWriter());
        }
        System.out.println("Dados guardados com sucesso!");

        Data data = Data.getInstance();

        data.saveData();

        Platform.exit();
    }
}
