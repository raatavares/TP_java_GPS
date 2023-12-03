package com.example.gps_g33.controller.animacao;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Atividade;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class ModalDesassociateResidenteController {
    private Data data;
    private Atividade atividade;

    @FXML
    public Button desassociate_Button;

    @FXML
    public Button cancelar_Button;

    @FXML
    public TextField nome_Atividade;
    @FXML
    public TextArea descricao_Atividade;
    @FXML
    public TextField nome_residenteLabel;
    @FXML
    public TextField nif_residenteLabel;
    @FXML
    public Label labelErros;

    @FXML
    public TableView<Residente> tabela_residentes;

    @FXML
    public TableColumn<Residente,String> nome_residente;

    @FXML
    public TableColumn<Residente,String> nif_residente;

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    public void initializeTable() {
        // Desabilitar a edição das TextField
        nome_residenteLabel.setEditable(false);
        nif_residenteLabel.setEditable(false);

        // Inicializar a tabela de residentes
        // Lógica para atualizar a tabela de residentes
        nome_residente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nif_residente.setCellValueFactory(new PropertyValueFactory<>("nif"));

        updateTable();

        tabela_residentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Quando um residente é selecionado, atualizar as TextField
                nome_residenteLabel.setText(newSelection.getNome());
                nif_residenteLabel.setText(newSelection.getNif());
            }
        });
    }
    @FXML
    public void initialize() {

        // Desabilitar a edição das TextField
        nome_residenteLabel.setEditable(false);
        nif_residenteLabel.setEditable(false);

        //inicializar a tabela de residentes
        //Lógica para atualizar a tabela de residentes
        nome_residente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nif_residente.setCellValueFactory(new PropertyValueFactory<>("nif"));

        updateTable();

        tabela_residentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Quando um residente é selecionado, atualizar as TextField
                nome_residenteLabel.setText(newSelection.getNome());
                nif_residenteLabel.setText(newSelection.getNif());
            }
        });
    }

    public void updateTable() {
        data = Data.getInstance();
        List<Residente> residentes = data.getResidentes();
        Atividade atividadeAtual = this.atividade;

        List<Residente> residentesDaAtividade = residentes.stream()
                .filter(residente -> atividadeAtual != null && atividadeAtual.getNifs().contains(residente.getNif()))
                .collect(Collectors.toList());
        System.out.println("Residentes da Atividade: " + residentesDaAtividade);

        // Converter a lista para uma ObservableList
        ObservableList<Residente> observableList = FXCollections.observableArrayList(residentesDaAtividade);

        tabela_residentes.setItems(observableList);
    }

    @FXML
    public void handleDesassociateButton() {
        // Desassociar o residente da atividade
        if (atividade != null) {
            Residente residente = tabela_residentes.getSelectionModel().getSelectedItem();
            if (residente != null) {
                atividade.removeResidente(residente);
                // Atualizar a tabela de atividades
                updateTable();
                // Fechar o modal
                Stage stage = (Stage) desassociate_Button.getScene().getWindow();
                stage.close();

                if (callback != null) {
                    callback.onAtividadeEditada(atividade);
                }
            } else {
                labelErros.setText("Deve selecionar um residente");
            }
        }

    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }

    public void setAtividadeParaDesassociacao(Atividade atividade) {
        this.atividade = atividade;
        System.out.println("Atividade: " + this.atividade);
        nome_Atividade.setText(atividade.getTipoAtividade());
        descricao_Atividade.setText(atividade.getDescricao());

        // Desabilitar a edição das TextField
        nome_Atividade.setEditable(false);
        descricao_Atividade.setEditable(false);

        // Chamar o método para inicializar a tabela com a atividade
        initializeTable();
    }
}
