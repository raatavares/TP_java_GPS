package com.example.gps_g33.controller;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciaResidentesController implements ModalCallback{
    @FXML
    public TableView<Residente> tableViewResidentes;
    public TableColumn<Residente, Integer> idColumn;
    public TableColumn<Residente, String> nomeColumn;
    public TableColumn<Residente, String> dataNascimentoColumn;
    public TableColumn<Residente, String> nifColumn;
    public TableColumn<Residente, String> contatoColumn;
    public TableColumn<Residente, String> emailColumn;
    public TextField searchField;
    public Button addButtonResidente;
    public Button editButtonResidente;
    public Button deleteButtonResidente;
    private Data data;
    private ModalCallback callback;

    public void initialize(){
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataNascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("contato"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        updateTable();
    }

    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    @FXML
    public void onCreate(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/gerencia/Gerencia_ModalResidentes.fxml"));
            Parent root = loader.load();

            // Definir o callback
            ModalControllerResidente controller = loader.getController();
            controller.setModalCallback(this);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Adicionar Residente");

            // Definir o conteúdo da janela modal
            Scene scene = new Scene(root);
            modalStage.setScene(scene);

            // Mostrar a janela modal
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditButton(ActionEvent event) {
        Residente residente = tableViewResidentes.getSelectionModel().getSelectedItem();
        if(residente!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/Gerencia_EditResidentes.fxml"));
                Parent root = loader.load();

                EditControllerResidente editController = loader.getController();
                editController.setModalCallback(this);

                editController.setResidenteParaEdicao(residente);

                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Editar Funcionário");

                // Definir o conteúdo da janela modal
                Scene scene = new Scene(root);
                modalStage.setScene(scene);

                // Mostrar a janela modal
                modalStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFuncionarioCriado(Funcionario funcionario) {

    }

    @Override
    public void onResidenteEditado(Residente residente) {

        for (int i = 0; i < data.getResidentes().size(); i++) {
            if(data.getResidentes().get(i).getId() == residente.getId()){
                data.getResidentes().set(i, residente);
                break;
            }
        }
        updateTable();

    }
    @Override
    public void onResidenteCriado(Residente residente) {
        residente.setId(data.calcularProximoIdResidentes());
        data.addResidente(residente);
        updateTable();
    }

    @Override
    public void onFuncionarioEditado(Funcionario funcionario) {

    }


    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Residente> residentesFiltrados = data.getResidentes().stream()
                .filter(residente -> residente.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());



        tableViewResidentes.setItems(FXCollections.observableArrayList(residentesFiltrados));
    }


    public void onDeleteButton() {
        Residente residente = tableViewResidentes.getSelectionModel().getSelectedItem();
        if(residente!= null){
            data.removeResidente(residente.getId());
        }
        updateTable();
    }


    public void updateTable() {
        tableViewResidentes.getItems().clear();
        tableViewResidentes.getItems().addAll(data.getResidentes());
    }

}

