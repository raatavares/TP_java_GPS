package com.example.gps_g33.controller;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private int id = 0;
    @FXML
    public TableView<Residente> tableViewResidentes;

    @FXML
    public TableColumn<Residente, Integer> idColumn;
    @FXML
    public TableColumn<Residente, String> nomeColumn;

    @FXML
    public TableColumn<Residente, String> dataNascimentoColumn;

    @FXML
    public TableColumn<Residente, String> nifColumn;

    @FXML
    public TableColumn<Residente, String> contatoColumn;

    @FXML
    public TableColumn<Residente, String> emailColumn;

    private ObservableList<Residente> listaDeResidentes = FXCollections.observableArrayList();

    @FXML
    public TextField searchField;

    @FXML
    public Button addButtonResidente;

    @FXML
    public Button editButtonResidente;

    @FXML
    public Button deleteButtonResidente;


    private ModalCallback callback;
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
        for (int i = 0; i < listaDeResidentes.size(); i++) {
            if(listaDeResidentes.get(i).getId() == residente.getId()){
                listaDeResidentes.set(i, residente);
                break;
            }
        }
    }
    @Override
    public void onResidenteCriado(Residente residente) {
        residente.setId(++id);
        listaDeResidentes.add(residente);
        tableViewResidentes.setItems(listaDeResidentes);
    }

    @Override
    public void onFuncionarioEditado(Funcionario funcionario) {

    }


    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Residente> residentesFiltrados = listaDeResidentes.stream()
                .filter(residente -> residente.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());



        tableViewResidentes.setItems(FXCollections.observableArrayList(residentesFiltrados));
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataNascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("contato"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableViewResidentes.setItems(listaDeResidentes);
    }

    public void onDeleteButton(ActionEvent actionEvent) {
        Residente residente = tableViewResidentes.getSelectionModel().getSelectedItem();
        if(residente!= null){
            listaDeResidentes.remove(residente);
        }
    }

}

