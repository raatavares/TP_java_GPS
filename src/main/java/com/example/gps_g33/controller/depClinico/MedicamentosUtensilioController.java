package com.example.gps_g33.controller.depClinico;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MedicamentosUtensilioController implements ModalCallback {
    private Data data;
    @FXML
    public Button buttonToAddUtensilio;

    public Button buttonToEditUtensilio;

    public StackPane contentArea;
    public TextField searchField;

    public TableView<Utensilio> tableViewUtensilio;

    public TableColumn<Utensilio, Integer> idColumn;

    public TableColumn<Utensilio, String> nomeColumn;

    public TableColumn<Utensilio, Boolean> utensilioColumn;

    private ObservableList<Utensilio> listaDeUtensilios = FXCollections.observableArrayList();

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        utensilioColumn.setCellValueFactory(new PropertyValueFactory<>("falta"));
        utensilioColumn.setCellFactory(createUtensilioCellFactory());

        updateTable();
    }

    private Callback<TableColumn<Utensilio, Boolean>, TableCell<Utensilio, Boolean>> createUtensilioCellFactory() {
        return column -> new TableCell<Utensilio, Boolean>() {
            @Override
            protected void updateItem(Boolean falta, boolean empty) {
                super.updateItem(falta, empty);

                if (empty || falta == null) {
                    setText(null);
                } else {
                    setText(falta ? "Sim" : "Não");
                }
            }
        };
    }

    public void handleToAddUtensilio() {
        try {
            // Carregar o FXML da janela pop-up
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depClinico/medicamentos&utensilios_Criar.fxml"));
            Parent popupRoot = loader.load();


            ModalCriarMedicamentosUtensilios controller = loader.getController();
            controller.setModalCallback(this);
            // Criar uma nova cena com a janela pop-up

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Adicionar Utensilio");

            // Definir o conteúdo da janela modal
            Scene scene = new Scene(popupRoot);
            modalStage.setScene(scene);

            // Mostrar a janela modal
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleToEditUtensilio(ActionEvent event) {
        Utensilio utensilio = tableViewUtensilio.getSelectionModel().getSelectedItem();
        if(utensilio!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depClinico/medicamentos&utensilios_Editar.fxml"));
                Parent root = loader.load();


                EditControllerMedicamentosUtensilios editControllerMedicamentosUtensilios = loader.getController();
                editControllerMedicamentosUtensilios.setModalCallback(this);

                editControllerMedicamentosUtensilios.setUtensilioParaEdicao(utensilio);

                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Editar Consulta e Medicacao");

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

    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Utensilio> UtensiliosFiltradas = data.getUtensilios().stream()
                .filter(utensilio -> utensilio.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());

        tableViewUtensilio.setItems(FXCollections.observableArrayList(UtensiliosFiltradas));
    }

    public void onDelete() {
        Utensilio utensilio = tableViewUtensilio.getSelectionModel().getSelectedItem();
        if(utensilio != null){
            data.removeUtensilio(utensilio.getId());
        }
        updateTable();
    }

    public void updateTable() {
        tableViewUtensilio.getItems().clear();
        tableViewUtensilio.getItems().addAll(data.getUtensilios());
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
        utensilio.setId(data.calcularProximoIdUtensilio());
        data.addUtensilio(utensilio);
        updateTable();
    }

    @Override
    public void onUtensilioEditado(Utensilio utensilio) {
        for (int i = 0; i < data.getUtensilios().size(); i++) {
            if(data.getUtensilios().get(i).getId() == utensilio.getId()){
                data.getUtensilios().set(i, utensilio);
                break;
            }
        }
        updateTable();
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
}
