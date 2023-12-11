package com.example.gps_g33.controller.animacao;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.controller.depCulinaria.EditControllerRefeicao;
import com.example.gps_g33.controller.depCulinaria.ModalControllerRefeicoes;
import com.example.gps_g33.modelos.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnimacaoHomeController implements ModalCallback {
    private Data data;

    @FXML
    public TableView<Atividade> tableViewAtividades;
    @FXML
    public TableColumn<Atividade, Integer> idColumn;

    @FXML
    public TableColumn<Atividade, String> nomeColumn;

    @FXML
    public TableColumn<Atividade, String> dataAtividadeColumn;

    @FXML
    public TableColumn<Atividade, String> descricaoColumn;

    @FXML
    public TableColumn<Atividade, String> nifColumn;

    @FXML
    public TableColumn<Atividade, String> tipoAtividadeColumn;

    @FXML
    public DatePicker datePicker;

    @FXML
    public Button buttonToAddAtividade;

    @FXML
    public Button buttonToEditAtividade;

    @FXML
    public Button deleteButton;

    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dataAtividadeColumn.setCellValueFactory(new PropertyValueFactory<>("dataAtividade"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tipoAtividadeColumn.setCellValueFactory(new PropertyValueFactory<>("tipoAtividade"));
        tableViewAtividades.setPlaceholder(new Label("Deve inserir o nome do residente para pesquisar"));
        TableColumn<Atividade, Boolean> colBtn = new TableColumn<>("Participantes");
        colBtn.setMinWidth(60);
        colBtn.setCellFactory(new Callback<TableColumn<Atividade, Boolean>, TableCell<Atividade, Boolean>>() {
            @Override
            public TableCell<Atividade, Boolean> call(TableColumn<Atividade, Boolean> p) {
                return new EditButton();
            }
        });
        tableViewAtividades.getColumns().add(colBtn);


        updateTable();
    }


    public class EditButton extends TableCell<Atividade, Boolean> {
        final Button colBtn = new Button("Participantes");
        EditButton() {
            colBtn.setOnAction(event -> {
                //Quero mostrar os participantes da atividade selecionada na tabela de atividades e permitir a sua edição (adicionar ou remover participantes)
                // Ao clicar no botão "Participantes" deve abrir uma janela pop-up com a lista de participantes da atividade selecionada na tabela de atividades
                // E permitir a sua edição (adicionar ou remover participantes)
                Atividade atividade = tableViewAtividades.getSelectionModel().getSelectedItem();

                if(atividade != null){
                    try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/animacao/Animacao_AtividadeParticipantes.fxml"));

                        Parent popupRoot = loader.load();

                        ParticipantesController participantesController = loader.getController();

                        participantesController.setAtividade(atividade);
                        Stage modalStage = new Stage();
                        modalStage.initModality(Modality.APPLICATION_MODAL);
                        modalStage.setTitle("Participantes da Atividade");
                        Scene scene = new Scene(popupRoot);
                        modalStage.setScene(scene);

                        // Mostra a janela modal
                        modalStage.showAndWait();

                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });


        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(colBtn);
            } else {
                setGraphic(null);
            }
        }
    }




    public void updateTable() {
        tableViewAtividades.getItems().clear();
        Collection<Atividade> atividades = data.getAtividades();
        if (atividades != null) {
            tableViewAtividades.getItems().addAll(atividades);
        }
    }

    public void onSearchData() {
        if(datePicker.getValue() == null){
            updateTable();
            return;
        }
        String dataPicker = datePicker.getValue().toString();

        Collection<Atividade> atividades = data.getAtividades().stream().filter(atividade -> atividade.getDataAtividade().equals(dataPicker)).collect(Collectors.toList());

        tableViewAtividades.getItems().clear();
        tableViewAtividades.getItems().addAll(atividades);


    }

    public void handleToAddAtividade() {
        try {
            // Carregar o FXML da janela pop-up
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/animacao/Animacao_AddAtividade.fxml"));
            Parent popupRoot = loader.load();


            ModalAddAtividadeController controller = loader.getController();
            controller.setModalCallback(this);
            // Criar uma nova cena com a janela pop-up

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Associar á Atividade");

            // Definir o conteúdo da janela modal
            Scene scene = new Scene(popupRoot);
            modalStage.setScene(scene);

            // Mostrar a janela modal
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleToEditAtividade() {
        Atividade atividade = tableViewAtividades.getSelectionModel().getSelectedItem();
        if(atividade!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/animacao/Animacao_EditAtividade.fxml"));
                Parent root = loader.load();


                ModalEditAtividadeController editControllerAtividade= loader.getController();
                editControllerAtividade.setModalCallback(this);

                editControllerAtividade.setAtividadeParaEdicao(atividade);

                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Editar Atividade");

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

    public void onDelete() {
        Atividade atividade = tableViewAtividades.getSelectionModel().getSelectedItem();
        if(atividade != null){
            data.removeAtividade(atividade.getId());
        }
        updateTable();
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
        atividade.setId(data.calcularProximoIdAtividades());
        data.addAtividade(atividade);
        updateTable();
    }

    @Override
    public void onAtividadeEditada(Atividade atividade) {
        for (int i = 0; i < data.getAtividades().size(); i++) {
            if(data.getAtividades().get(i).getId() == atividade.getId()){
                data.getAtividades().set(i, atividade);
                break;
            }
        }
        updateTable();
    }

}
