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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;


public class GerenciaFuncionariosController implements ModalCallback{

    private int id = 0;
    @FXML
    public TableView<Funcionario> tableView;
    @FXML
    public TableColumn<Funcionario, Integer> idColumn;
    @FXML
    public TableColumn<Funcionario, String> nomeColumn;

    @FXML
    public TableColumn<Funcionario, String> sobrenomeColumn;

    @FXML
    public TableColumn<Funcionario, String> dataNascimentoColumn;

    @FXML
    public TableColumn<Funcionario, String> nifColumn;

    @FXML
    public TableColumn<Funcionario, String> contatoColumn;

    @FXML
    public TableColumn<Funcionario, String> emailColumn;

    private ObservableList<Funcionario> listaDeFuncionarios = FXCollections.observableArrayList();

    @FXML
    public TextField searchField;

    @FXML
    public TextField searchFieldResidentes;

    @FXML
    public Button addButton;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;


    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    @FXML
    public void handleAddButton(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/gerencia/Gerencia_ModalFuncionarios.fxml"));
            Parent root = loader.load();

            // Definir o callback
            ModalController controller = loader.getController();
            controller.setModalCallback(this);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Adicionar Funcionário");

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
        Funcionario funcionario = tableView.getSelectionModel().getSelectedItem();
        if(funcionario!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/Gerencia_EditFuncionarios.fxml"));
                Parent root = loader.load();



                EditController editController = loader.getController();
                editController.setModalCallback(this);

                editController.setFuncionarioParaEdicao(funcionario);

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
        funcionario.setId(++id);
        listaDeFuncionarios.add(funcionario);
        tableView.setItems(listaDeFuncionarios);
    }

    @Override
    public void onResidenteEditado(Residente residente) {
    }

    @Override
    public void onResidenteCriado(Residente residente) {

    }

    @Override
    public void onFuncionarioEditado(Funcionario funcionario) {
        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            if(listaDeFuncionarios.get(i).getId() == funcionario.getId()){
                listaDeFuncionarios.set(i, funcionario);
                break;
            }
        }
    }


    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Funcionario> funcionariosFiltrados = listaDeFuncionarios.stream()
                .filter(funcionario -> funcionario.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());

        tableView.setItems(FXCollections.observableArrayList(funcionariosFiltrados));
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        sobrenomeColumn.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        dataNascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("contato"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.setItems(listaDeFuncionarios);
    }

    public void onDelete(ActionEvent actionEvent) {
        Funcionario funcionario = tableView.getSelectionModel().getSelectedItem();
        if(funcionario!= null){
            listaDeFuncionarios.remove(funcionario);
        }
    }

}
