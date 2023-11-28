package com.example.gps_g33.controller.familiares;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.FamiliarParaMarcacao;
import com.example.gps_g33.modelos.Visita;
import com.example.gps_g33.modelos.VisitasMarcadas;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class minhasMarcacoesController {
    private ModalCallback callback;

    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    @FXML
    public TableView<VisitasMarcadas> tableView;

    @FXML
    public TableColumn<VisitasMarcadas, String> titleColumn;
    @FXML
    public TableColumn<VisitasMarcadas, String> startDateColumn;
    @FXML
    public TableColumn<VisitasMarcadas, String> startTimeColumn;
    @FXML
    public TableColumn<VisitasMarcadas, String> endDateColumn;
    @FXML
    public TableColumn<VisitasMarcadas, String> endTimeColumn;
    @FXML
    public Label infoLabel;
    public TextField searchField;
    @FXML
    public Button deleteButton;

    @FXML
    public Button btnVoltar;

    public Data data;

    public void initialize() {
        data = Data.getInstance();

        // Definir as propriedades nas colunas fora do loop
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        loadVisitasMarcadas();
    }
    public void loadVisitasMarcadas() {
        List<VisitasMarcadas> visitasFiltradas = data.getVisitasMarcadas().stream()
                .filter(visita -> visita.getFamiliares().stream()
                        .anyMatch(familiar -> familiar.getNif().equals(data.getNifFamiliar())))
                .collect(Collectors.toList());

        tableView.getItems().clear();  // Limpar itens existentes na tabela

        for (VisitasMarcadas visita : visitasFiltradas) {
            // Verificar se o NIF está presente
            boolean nifPresente = visita.getFamiliares().stream()
                    .anyMatch(familiar -> familiar.getNif().equals(data.getNifFamiliar()));

            if (nifPresente) {
                // Adicionar uma nova linha para cada visita com NIF correspondente
                tableView.getItems().add(visita);
            }
        }
    }


    public void onDelete() {
        VisitasMarcadas selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Verificar se é o único familiar na visita
            if (selectedItem.getFamiliares().size() == 1 && selectedItem.getFamiliares().get(0).getNif().equals(data.getNifFamiliar())) {
                // Remover a visita completa
                data.removeVisitaMarcada(selectedItem.getId());
            } else {
                // Remover apenas o nome do familiar
                selectedItem.getFamiliares().removeIf(familiar -> familiar.getNif().equals(data.getNifFamiliar()));
            }

            // Atualizar a tabela após a remoção
            loadVisitasMarcadas();
        } else {
            infoLabel.setText("Nenhum item selecionado.");
            System.out.println("Nenhum item selecionado.");
        }
        loadVisitasMarcadas();
    }

    @FXML
    public void onSearch() {
        loadVisitasMarcadas();
    }

    public void handleVoltar(){
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
}
