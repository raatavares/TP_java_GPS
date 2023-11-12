package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Medicacao;
import com.example.gps_g33.modelos.Refeicao;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class ModalControllerConsultasMedicacao {
    private Data data;

    @FXML
    public Button criar_Button;

    @FXML
    public Button cancelar_Button;


    @FXML
    public TableView<Residente> tabela_residentes;

    @FXML
    public TableColumn<Residente,String> nome_residente;

    @FXML
    public TableColumn<Residente,String> nif_residente;

    @FXML
    public DatePicker dataConsulta;

    @FXML
    public TextField medicacaoField;

    @FXML
    public TextField nomeField;

    @FXML
    public TextField nifField;

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public void initialize() {

        // Desabilitar a edição das TextField
        nomeField.setEditable(false);
        nifField.setEditable(false);

        //inicializar a tabela de residentes
        //Lógica para atualizar a tabela de residentes
        nome_residente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nif_residente.setCellValueFactory(new PropertyValueFactory<>("nif"));

        updatetable();

        tabela_residentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Quando um residente é selecionado, atualizar as TextField
                nomeField.setText(newSelection.getNome());
                nifField.setText(newSelection.getNif());
            }
        });
    }

    public void updatetable() {
        // Lógica para atualizar a tabela de residentes

        data = Data.getInstance();
        List<Residente> residentes = data.getResidentes();
        // Converter a lista para uma ObservableList
        ObservableList<Residente> observableList = FXCollections.observableArrayList(residentes);

        tabela_residentes.setItems(observableList);
    }

    @FXML
    public void handleCriarButton() {

            // Lógica para adicionar medicacao para um residente
            String nome = nomeField.getText();
            String nif = nifField.getText();
            String medicacao = medicacaoField.getText();
            String data = dataConsulta.getValue().toString();

            Medicacao medicacao1 = new Medicacao(0, nome, data, medicacao, nif);

            if (callback != null) {
                callback.onMedicacaoCriado(medicacao1);
            }
        

        // Fechar o modal
        Stage stage = (Stage) criar_Button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }


}
