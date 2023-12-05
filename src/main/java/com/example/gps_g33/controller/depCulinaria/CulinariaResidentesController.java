package com.example.gps_g33.controller.depCulinaria;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.controller.gerencia.EditControllerResidente;
import com.example.gps_g33.controller.gerencia.ModalControllerResidente;
import com.example.gps_g33.modelos.*;
import javafx.collections.FXCollections;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CulinariaResidentesController implements ModalCallback {
    public Button buttonToRefeicoes;
    public Button buttonToResidentes;

    public TableView<Residente> tableViewResidentes;
    public TableColumn<Residente, Integer> idColumn;
    public TableColumn<Residente, String> nomeColumn;
    public TableColumn<Residente, String> dataNascimentoColumn;
    public TableColumn<Residente, String> nifColumn;
    public TableColumn<Residente, String> contatoColumn;
    public TableColumn<Residente, String> emailColumn;
    public TableColumn<Residente, String> prefAliColumn;
    public TableColumn<Residente, String> alergiasColumn;
    public TextField searchField;

    private Data data;
    public StackPane contentArea;


    @FXML
    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        prefAliColumn.setCellValueFactory(new PropertyValueFactory<>("prefAli"));
        alergiasColumn.setCellValueFactory(new PropertyValueFactory<>("alergias"));


        updateTable();
    }

    public void updateTable() {
        tableViewResidentes.getItems().clear();
        tableViewResidentes.getItems().addAll(data.getResidentes());
    }



    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Residente> residentesFiltrados = data.getResidentes().stream()
                .filter(residente -> residente.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());



        tableViewResidentes.setItems(FXCollections.observableArrayList(residentesFiltrados));
    }

    @FXML
    public void onCreate(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_CriarInformacao.fxml"));
            Parent root = loader.load();

            // Definir o callback
            ModalControllerResidentes controller = loader.getController();
            controller.setModalCallback(this);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Adicionar Residente");

            // Definir o conteúdo da janela modal
            Scene scene = new Scene(root);
            modalStage.setScene(scene);

            // Mostrar a janela modal
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditButton(ActionEvent event) {
        Residente residente = tableViewResidentes.getSelectionModel().getSelectedItem();
        if(residente!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_EditInformacao.fxml"));
                Parent root = loader.load();

                EditControllerResidenteCulinaria editController = loader.getController();
                editController.setModalCallback(this);

                editController.setResidenteParaEdicao(residente);

                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Editar Funcionário");

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
    public void onRestrictionCriada(Residente residentePorId) {
        data.setResidente(residentePorId);
        updateTable();
    }

    @Override
    public void onAtividadeCriada(Atividade atividade) {

    }

    @Override
    public void onAtividadeEditada(Atividade atividade) {

    }

    @Override
    public void onRestrictionEditada(Residente residente) {
        data.setResidente(residente);
        updateTable();
    }
}
