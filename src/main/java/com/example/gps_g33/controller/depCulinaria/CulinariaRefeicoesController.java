package com.example.gps_g33.controller.depCulinaria;


import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.*;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CulinariaRefeicoesController implements ModalCallback {
    private int id = 0;
    public Button buttonToAddRefeicao;
    public Button buttonToEditRefeicao;
    public StackPane contentArea;
    public TextField searchField;
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
    private Data data;
    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    @FXML
    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataRefeicaoColumn.setCellValueFactory(new PropertyValueFactory<>("dataRefeicao"));
        DescricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        NIF.setCellValueFactory(new PropertyValueFactory<>("nif"));
        tipoDietaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoDieta"));

        updateTable();
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
    public void handleToEditRefeicao(ActionEvent event) {
        Refeicao refeicao = tableViewRefeicao.getSelectionModel().getSelectedItem();
        if(refeicao!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_EditRefeicao.fxml"));
                Parent root = loader.load();

                EditControllerRefeicao editControllerRefeicao = loader.getController();
                editControllerRefeicao.setModalCallback(this);

                editControllerRefeicao.setRefeicaoParaEdicao(refeicao);

                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Editar Refeição");

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
    public boolean usedCredentials(String email, String NIF) {
        return false;
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
    public void onMedicacaoCriado(Medicacao medicacao){

    }

    @Override
    public void onMedicacaoEditado(Medicacao medicacao){

    }

    @Override
    public void onUtensilioCriado(Utensilio utensilio) {

    }

    @Override
    public void onUtensilioEditado(Utensilio utensilio) {

    }

    @Override
    public void onRestrictionEditada(Residente residente) {

    }

    @Override
    public void onRestrictionCriada(Residente residentePorId) {

    }

    @Override
    public void onAtividadeCriada(Atividade atividade) {

    }

    @Override
    public void onAtividadeEditada(Atividade atividade) {

    }

    @Override
    public void onRefeicaoCriado(Refeicao refeicao) {
     refeicao.setId(data.calcularProximoIdRefeicoes());
     data.addRefeicao(refeicao);
     updateTable();
    }

    @Override
    public void onRefeicaoEditado(Refeicao refeicao) {
        for (int i = 0; i < data.getRefeicoes().size(); i++) {
            if(data.getRefeicoes().get(i).getId() == refeicao.getId()){
                data.getRefeicoes().set(i, refeicao);
                break;
            }
        }
        updateTable();
    }

    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Refeicao> refeicoesFiltrados = data.getRefeicoes().stream()
                .filter(refeicao -> refeicao.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());

        tableViewRefeicao.setItems(FXCollections.observableArrayList(refeicoesFiltrados));
    }

    public void onDelete() {
        Refeicao refeicao = tableViewRefeicao.getSelectionModel().getSelectedItem();
        if(refeicao != null){
            data.removeRefeicao(refeicao.getId());
        }
        updateTable();
    }

    public void updateTable() {
        tableViewRefeicao.getItems().clear();
        tableViewRefeicao.getItems().addAll(data.getRefeicoes());
    }
}

