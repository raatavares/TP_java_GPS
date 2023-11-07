package com.example.gps_g33.controller;


import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Refeicao;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CulinariaRefeicoesController implements ModalCallback{

    private int id = 0;

    public Button buttonSair;
    public Button buttonToAddRefeicao;
    public Button buttonToEditRefeicao;
    public Button buttonToRefeicoes;
    public Button buttonToResidentes;
    public StackPane contentArea;
    public Text lblDate;

    
    @FXML
    public TableView<Refeicao> tableViewRefeicao;

    @FXML
    public TableColumn<Refeicao, Integer> idColumn;

    @FXML
    public TableColumn<Refeicao, String> nomeColumn;

    @FXML
    public TableColumn<Refeicao, String> dataRefeicaoColumn;

    @FXML
    public TableColumn<Refeicao, String> DescricaoColumn;

    @FXML
    public TableColumn<Refeicao, String> NIF;

    @FXML
    public TableColumn<Refeicao, String> tipoDietaColumn;

    private ObservableList<Refeicao> listaDeRefeicoes = FXCollections.observableArrayList();

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    @FXML
    public void initialize() {
        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String data = formato.format(dataAtual);
        lblDate.setText(data);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataRefeicaoColumn.setCellValueFactory(new PropertyValueFactory<>("dataRefeicao"));
        DescricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        NIF.setCellValueFactory(new PropertyValueFactory<>("nif"));
        tipoDietaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoDieta"));

        tableViewRefeicao.setItems(listaDeRefeicoes);

        buttonSair.setOnAction(event -> {
            Stage stage = (Stage) buttonSair.getScene().getWindow();
            stage.close();
        });
    }

    public void switchToRefeicoes() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depCulinaria/Culinaria_Refeicoes.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    public void switchToResidentes() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depCulinaria/Culinaria_Residentes.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }


    public void handleToAddRefeicao() {
        try {
            // Carregar o FXML da janela pop-up
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_CriarRefeicao.fxml"));
            Parent popupRoot = loader.load();


            ModalControllerRefeicoes controller = loader.getController();
            controller.setModalCallback(this);
            // Criar uma nova cena com a janela pop-up

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Adicionar Refeição");

            // Definir o conteúdo da janela modal
            Scene scene = new Scene(popupRoot);
            modalStage.setScene(scene);

            // Mostrar a janela modal
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleToEditRefeicao() {
        try {
            // Carregar o FXML da janela pop-up
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_EditRefeicao.fxml"));
            Parent popupRoot = loader.load();

            // Criar uma nova cena com a janela pop-up
            Scene popupScene = new Scene(popupRoot);

            // Criar um novo palco (Stage) para a janela pop-up
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Torna a janela pop-up modal
            popupStage.setTitle("Editar Refeição"); // Defina o título da janela pop-up
            popupStage.setScene(popupScene);

            // Mostrar a janela pop-up
            popupStage.showAndWait(); // Espere até que a janela pop-up seja fechada

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 @Override
    public void onFuncionarioCriado(Funcionario funcionario) {

    }

    @Override
    public void onFuncionarioEditado(Funcionario funcionario) {

    }

    @Override
    public void onResidenteEditado(Residente residente) {

    }

    @Override
    public void onResidenteCriado(Residente residente) {

    }

    @Override
    public void onRefeicaoCriado(Refeicao refeicao) {
        refeicao.setId(++id);
        listaDeRefeicoes.add(refeicao);
        System.out.println(refeicao);
        tableViewRefeicao.setItems(listaDeRefeicoes);

    }

    @Override
    public void onRefeicaoEditado(Funcionario funcionario) {

    }
}

