package com.example.gps_g33.controller.familiares;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.controller.depClinico.ModalControllerConsultasMedicacao;
import com.example.gps_g33.modelos.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class marcarHorarioVisitaController implements ModalCallback {
    @FXML
    public TableView<Visita> tableView;

    @FXML
    public TableColumn<Visita, String> titleColumn;
    @FXML
    public TableColumn<Visita, String> startDateColumn;
    @FXML
    public TableColumn<Visita, String> startTimeColumn;
    @FXML
    public TableColumn<Visita, String> endDateColumn;
    @FXML
    public TableColumn<Visita, String> endTimeColumn;
    @FXML
    public Label infoLabel;
    public TextField searchField;

    public LocalTime minTime = LocalTime.MAX;
    public LocalTime maxTime = LocalTime.MIN;

    public Data data;
    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    public void initialize() {
        data = Data.getInstance();
        callback = this;

        configureTableColumns();
        loadVisitSchedules();

    }

    public void configureTableColumns() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    public List<Visita> horariosOriginais;
    public void loadVisitSchedules() {
        try {
            Gson gson = new Gson();
            Type horarioVisitaListType = new TypeToken<List<Visita>>(){}.getType();
            horariosOriginais = gson.fromJson(new FileReader("Dados/visitas.json"), horarioVisitaListType);
            List<Visita> horariosProcessados = new ArrayList<>();

            for (Visita horario : horariosOriginais) {
                LocalTime startTime = LocalTime.parse(horario.getStartTime());
                LocalTime endTime = LocalTime.parse(horario.getEndTime());
            // Verifica se a visita precisa ser dividida
            if (startTime.plusHours(1).isBefore(endTime)) {
                // Divide a visita em intervalos de 1 hora
                while (startTime.plusHours(1).isBefore(endTime)) {
                    Visita novaVisita = new Visita(horario.getTitle(), startTime.toString(), startTime.plusHours(1).toString(), horario.getStartDate(), horario.getEndDate());
                    horariosProcessados.add(novaVisita);
                    startTime = startTime.plusHours(1);

                }
                // Adiciona o último intervalo
                horariosProcessados.add(new Visita(horario.getTitle(), startTime.toString(), endTime.toString(), horario.getStartDate(), horario.getEndDate()));
            } else {
                // Adiciona a visita original
                horariosProcessados.add(horario);
            }

            // Atualiza os horários mínimos e máximos
            if (startTime.isBefore(minTime)) {
                minTime = startTime;
            }
            if (endTime.isAfter(maxTime)) {
                maxTime = endTime;
            }
        }

        tableView.getItems().setAll(horariosProcessados);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    public void handleBook() {
        try {
            Visita selectedItem = tableView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Carregar o FXML da janela pop-up
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/familiares/CriarMarcacao.fxml"));
                Parent popupRoot = loader.load();

                CriarMarcacaoController controller = loader.getController();
                controller.setModalCallback(this);
                controller.setSelectedVisita(selectedItem);

                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Adicionar Marcação");

                Scene scene = new Scene(popupRoot);
                modalStage.setScene(scene);

                modalStage.showAndWait();
            } else {
                infoLabel.setText("Nenhum item selecionado.");
                System.out.println("Nenhum item selecionado.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSearch() {
        String termoPesquisa = searchField.getText().toLowerCase();

        List<Visita> visitasFiltradas = horariosOriginais.stream()
                .filter(visita ->
                        visita.getStartDate().toLowerCase().contains(termoPesquisa) ||
                                visita.getTitle().toLowerCase().contains(termoPesquisa) ||
                                visita.getStartTime().toLowerCase().contains(termoPesquisa)
                )
                .collect(Collectors.toList());

        tableView.setItems(FXCollections.observableArrayList(visitasFiltradas));
    }


    @FXML
    public void handleMinhasMarcacoes() {
        try {
             // Carregar o FXML da janela pop-up
             FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/familiares/MinhasMarcacoes.fxml"));
             Parent popupRoot = loader.load();

             minhasMarcacoesController controller = loader.getController();
             controller.setModalCallback(this);

             Stage modalStage = new Stage();
             modalStage.initModality(Modality.APPLICATION_MODAL);
             modalStage.setTitle("Adicionar Marcação");

             Scene scene = new Scene(popupRoot);
             modalStage.setScene(scene);

             modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void onRefeicaoCriado(Refeicao refeicao) {

    }

    @Override
    public void onRefeicaoEditado(Refeicao refeicao) {

    }

    @Override
    public void onMedicacaoCriado(Medicacao medicacao) {

    }

    @Override
    public void onMedicacaoEditado(Medicacao medicacao) {

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
    public void onVisitasMarcadasEditada(VisitasMarcadas visitasMarcadas) {
        for (int i = 0; i < data.getVisitasMarcadas().size(); i++) {
            if(data.getVisitasMarcadas().get(i).getId() == visitasMarcadas.getId()){
                data.getVisitasMarcadas().set(i, visitasMarcadas);
                break;
            }
        }
    }

    @Override
    public void onVisitasMarcadasCriada(VisitasMarcadas visitasMarcadas) {
        visitasMarcadas.setId(data.calcularProximoIdVisitaMarcada());
        data.addVisitaMarcada(visitasMarcadas);
    }

    @Override
    public void onAtividadeCriada(Atividade atividade) {

    }

    @Override
    public void onAtividadeEditada(Atividade atividade) {

    }
}
