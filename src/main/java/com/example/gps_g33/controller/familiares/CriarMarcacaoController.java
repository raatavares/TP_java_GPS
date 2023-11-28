package com.example.gps_g33.controller.familiares;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.FamiliarParaMarcacao;
import com.example.gps_g33.modelos.Visita;
import com.example.gps_g33.modelos.VisitasMarcadas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CriarMarcacaoController {
    private ModalCallback callback;

    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public TextField nomeField;
    @FXML
    public TextField nifField;
    @FXML
    public TextField horaInicioField;
    @FXML
    public TextField horaFimField;
    @FXML
    public TextField diaInicioField;
    @FXML
    public TextField diaFimField;
    @FXML
    public Button criarButton;

    @FXML
    public Button cancelarButton;

    public Data data;
    public String titulo;

    public void initialize() {
        data = Data.getInstance();
    }

    public void setSelectedVisita(Visita selectedVisita) {
        if (selectedVisita != null) {
            titulo = selectedVisita.getTitle();
            horaInicioField.setText(selectedVisita.getStartTime());
            horaFimField.setText(selectedVisita.getEndTime());
            diaInicioField.setText(selectedVisita.getStartDate());
            diaFimField.setText(selectedVisita.getEndDate());
            nomeField.setText(data.getNomeFamiliar());
            nifField.setText(data.getNifFamiliar());
        }
    }

    public void handleCriarButton() {
        FamiliarParaMarcacao familiarParaMarcacao = new FamiliarParaMarcacao(nomeField.getText(), nifField.getText());
        if (nomeField != null) {
            // Verificar se já existe uma reserva para o horário selecionado
            VisitasMarcadas visitaExistente = data.getVisitasMarcadas().stream()
                    .filter(visita -> visita.getStartTime().equals(horaInicioField.getText())
                            && visita.getEndTime().equals(horaFimField.getText())
                            && visita.getStartDate().equals(diaInicioField.getText())
                            && visita.getEndDate().equals(diaFimField.getText()))
                    .findFirst()
                    .orElse(null);

            if (visitaExistente == null) {
                System.out.println("Visita não encontrada");
            } else {
                System.out.println("Visita encontrada");
            }

            if (visitaExistente != null) {
                // Adicionar nome ao array de nomes se ainda houver espaço
                if (visitaExistente.getFamiliares().size() < 5) {

                    for (int i = 0; i < data.getVisitasMarcadas().size(); i++) {
                        if (data.getVisitasMarcadas().get(i).getId() == visitaExistente.getId()) {
                            visitaExistente.adicionarNomeVisitante(familiarParaMarcacao);
                            data.getVisitasMarcadas().set(i, visitaExistente);
                            break;
                        }
                    }
                } else {
                    System.out.println("Limite de visitantes atingido para este horário.");
                }
            } else {
                // Criar uma nova reserva se não existir uma para o horário selecionado
                VisitasMarcadas visitasMarcadas = new VisitasMarcadas(0, familiarParaMarcacao, titulo, diaInicioField.getText(), diaFimField.getText(), horaInicioField.getText(), horaFimField.getText());
                if (callback != null) {
                    callback.onVisitasMarcadasCriada(visitasMarcadas);
                }
            }
        }
        Stage stage = (Stage) criarButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancelarButton(){
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
