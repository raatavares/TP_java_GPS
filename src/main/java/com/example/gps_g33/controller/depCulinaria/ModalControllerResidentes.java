package com.example.gps_g33.controller.depCulinaria;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Refeicao;
import com.example.gps_g33.modelos.Residente;
import com.example.gps_g33.util.InputValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ModalControllerResidentes {

    private Data data;
    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }


    @FXML
    public TextField nomeField;


    @FXML
    public TextField nifField;


    @FXML
    public TextArea descricaoField;

    @FXML
    public RadioButton preferenciaAlimentar;

    @FXML
    public RadioButton restricaoAlimentar;

    @FXML
    public TableView<Residente> tabela_residentes;

    @FXML
    public TableColumn<Residente,String> nome_residente;

    @FXML
    public TableColumn<Residente,String> nif_residente;

    @FXML
    public Button criarButton;

    @FXML
    public Button cancelarButton;

    private int idSelected;

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
                idSelected = newSelection.getId();
            }
        });
    }

    private void updatetable() {
        // Lógica para atualizar a tabela de residentes

        data = Data.getInstance();
        List<Residente> residentes = data.getResidentes();
        // Converter a lista para uma ObservableList
        ObservableList<Residente> observableList = FXCollections.observableArrayList(residentes);

        tabela_residentes.setItems(observableList);
    }


    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    public void handleCriarButton(ActionEvent actionEvent) {
        String nome = nomeField.getText();
        String nif = nifField.getText();
        String descricao = descricaoField.getText();
        String tipoDieta = null;
        if(restricaoAlimentar.isSelected()){
            tipoDieta = "Restrição Alimentar";
        }else if(preferenciaAlimentar.isSelected()){
            tipoDieta = "Preferência Alimentar";
        }

        if(InputValidation.styleTextError(nomeField, !InputValidation.isLengthValid(nome,3))
                && InputValidation.styleTextError(nifField, !InputValidation.isNif(nif))
                && InputValidation.styleTextAreaError(descricaoField, !InputValidation.isDescricaoValid(descricao,3))
                && tipoDieta != null
        ){
            if(tipoDieta.equals("Preferência Alimentar"))
                data.getResidentePorId(idSelected).setPrefAli(descricao);
            else {
                data.getResidentePorId(idSelected).setAlergias(descricao);
            }
            if (callback != null) {
                callback.onResidenteEditado(data.getResidentePorId(idSelected));
            }

            // Fechar o modal
            Stage stage = (Stage) criarButton.getScene().getWindow();
            stage.close();
        }
    }
}
