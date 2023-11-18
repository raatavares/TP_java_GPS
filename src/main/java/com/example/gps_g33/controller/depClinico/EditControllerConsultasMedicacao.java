package com.example.gps_g33.controller.depClinico;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Medicacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditControllerConsultasMedicacao {

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public TextField nomeField;

    @FXML
    public DatePicker dataConsultaField;

    @FXML
    public TextField nifField;

    @FXML
    public TextField medicacaoField;
    @FXML
    public Button editar_Button;

    @FXML
    public Button cancelar_Button;

    private Medicacao medicacao_Consulta;

    @FXML
    public void initialize() {

        // Desabilitar a edição das TextField
        nomeField.setEditable(false);
        nifField.setEditable(false);
    }

    @FXML
    public void onEditarButton() {
        boolean camposValidos = true;

        String nome = nomeField.getText();
        String medicacao = medicacaoField.getText();
        String nif = nifField.getText();
        String dataConsulta = dataConsultaField.getValue().toString();

        medicacao_Consulta.setId(this.medicacao_Consulta.getId());
        medicacao_Consulta.setNome(nome);
        medicacao_Consulta.setMedicacao(medicacao);
        medicacao_Consulta.setNif(nif);
        medicacao_Consulta.setDataConsultaMedicacao(dataConsulta);


        if (callback != null) {
            callback.onMedicacaoEditado(medicacao_Consulta);
        }

        // Fechar o modal
        Stage stage = (Stage) editar_Button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void setMedicacaoParaEdicao(Medicacao medicacao_Consulta) {
        this.medicacao_Consulta = medicacao_Consulta;

        nomeField.setText(medicacao_Consulta.getNome());
        medicacaoField.setText(medicacao_Consulta.getMedicacao());
        nifField.setText(medicacao_Consulta.getNif());

        String dataConsultaMedicacaoString = medicacao_Consulta.getDataConsultaMedicacao();
        LocalDate dataConsultaMedicacao = LocalDate.parse(dataConsultaMedicacaoString);
        dataConsultaField.setValue(dataConsultaMedicacao);
    }

}
