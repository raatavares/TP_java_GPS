package com.example.gps_g33.controller.depClinico;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.*;
import com.example.gps_g33.modelos.Data;
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

public class ConsultasMedicacaoController implements ModalCallback {
    private Data data;
    @FXML
    public Button buttonToAddMedicacao;

    public Button buttonToEditMedicacao;

    public StackPane contentArea;
    public TextField searchField;

    public TableView<Medicacao> tableViewMedicacao;

    public TableColumn<Medicacao, Integer> idColumn;

    public TableColumn<Medicacao, String> nomeColumn;

    public TableColumn<Medicacao, String> dataConsultasColumn;

    public TableColumn<Medicacao, String> medicacaoColumn;

    private ObservableList<Medicacao> listaDeMedicacoes = FXCollections.observableArrayList();


    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataConsultasColumn.setCellValueFactory(new PropertyValueFactory<>("dataConsultaMedicacao"));
        medicacaoColumn.setCellValueFactory(new PropertyValueFactory<>("medicacao"));

        updateTable();
    }

    public void handleToAddMedicacao() {
        try {
            // Carregar o FXML da janela pop-up
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depClinico/Consultas_Medicaçao_Modal.fxml"));
            Parent popupRoot = loader.load();


            ModalControllerConsultasMedicacao controller = loader.getController();
            controller.setModalCallback(this);
            // Criar uma nova cena com a janela pop-up

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Adicionar Medicacao");

            // Definir o conteúdo da janela modal
            Scene scene = new Scene(popupRoot);
            modalStage.setScene(scene);

            // Mostrar a janela modal
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleToEditMedicacao(ActionEvent event) {
        Medicacao medicacao = tableViewMedicacao.getSelectionModel().getSelectedItem();
        if(medicacao!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depClinico/Consultas_Medicaçao_Editar.fxml"));
                Parent root = loader.load();


                EditControllerConsultasMedicacao editControllerConsultasMedicacao = loader.getController();
                editControllerConsultasMedicacao.setModalCallback(this);

                editControllerConsultasMedicacao.setMedicacaoParaEdicao(medicacao);

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
        List<Medicacao> medicacoesFiltradas = data.getMedicacoes().stream()
                .filter(medicacao -> medicacao.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());

        tableViewMedicacao.setItems(FXCollections.observableArrayList(medicacoesFiltradas));
    }

    @Override
    public void onMedicacaoCriado(Medicacao medicacao) {
        medicacao.setId(data.calcularProximoIdMedicacoes());
        data.addMedicacao(medicacao);
        updateTable();
    }

    @Override
    public void onMedicacaoEditado(Medicacao medicacao) {
        for (int i = 0; i < data.getMedicacoes().size(); i++) {
            if(data.getMedicacoes().get(i).getId() == medicacao.getId()){
                data.getMedicacoes().set(i, medicacao);
                break;
            }
        }
        updateTable();
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

    }

    @Override
    public void onVisitasMarcadasCriada(VisitasMarcadas visitasMarcadas) {

    }

    @Override
    public void onAtividadeCriada(Atividade atividade) {

    }

    @Override
    public void onAtividadeEditada(Atividade atividade) {

    }

    public void onDelete() {
        Medicacao medicacao = tableViewMedicacao.getSelectionModel().getSelectedItem();
        if(medicacao != null){
            data.removeMedicacao(medicacao.getId());
        }
        updateTable();
    }

    public void updateTable() {
        tableViewMedicacao.getItems().clear();
        tableViewMedicacao.getItems().addAll(data.getMedicacoes());
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
    public void onRefeicaoEditado(Refeicao refeicao) {

    }

    @Override
    public void onRefeicaoCriado(Refeicao refeicao) {

    }

}
