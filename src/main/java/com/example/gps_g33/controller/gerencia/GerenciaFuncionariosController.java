package com.example.gps_g33.controller.gerencia;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class GerenciaFuncionariosController implements ModalCallback {
    private Data data;

    @FXML
    public TableView<Funcionario> tableView;
    public TableColumn<Funcionario, Integer> idColumn;
    public TableColumn<Funcionario, String> nomeColumn;
    public TableColumn<Funcionario, String> sobrenomeColumn;
    public TableColumn<Funcionario, String> dataNascimentoColumn;
    public TableColumn<Funcionario, String> nifColumn;
    public TableColumn<Funcionario, String> contatoColumn;
    public TableColumn<Funcionario, String> emailColumn;
    public TextField searchField;
    public TextField searchFieldResidentes;
    public Button addButton;
    public Button editButton;
    public Button deleteButton;
    private ModalCallback callback;

    public void initialize(){
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        sobrenomeColumn.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        dataNascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("contato"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        updateTable();
    }

    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    @FXML
    public void handleAddButton(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/gerencia/Gerencia_ModalFuncionarios.fxml"));
            Parent root = loader.load();

            // Definir o callback
            ModalController controller = loader.getController();
            controller.setModalCallback(this);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Adicionar Funcionário");

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
        Funcionario funcionario = tableView.getSelectionModel().getSelectedItem();
        if(funcionario!= null){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/gerencia/Gerencia_EditFuncionarios.fxml"));
                Parent root = loader.load();



                EditController editController = loader.getController();
                editController.setModalCallback(this);

                editController.setFuncionarioParaEdicao(funcionario);

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
        funcionario.setId(data.calcularProximoIdFuncionarios());
        data.addFuncionario(funcionario);
        updateTable();
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

    }

    @Override
    public void onVisitasMarcadasCriada(VisitasMarcadas visitasMarcadas) {

    }

    @Override
    public void onFuncionarioEditado(Funcionario funcionario) {

        for (int i = 0; i < data.getFuncionarios().size(); i++) {
            if(data.getFuncionarios().get(i).getId() == funcionario.getId()){
                data.getFuncionarios().set(i, funcionario);
                break;
            }
        }
        updateTable();

    }


    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Funcionario> funcionariosFiltrados = data.getFuncionarios().stream()
                .filter(funcionario -> funcionario.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());

        tableView.setItems(FXCollections.observableArrayList(funcionariosFiltrados));
    }

    public void onDelete() {
        Funcionario funcionario = tableView.getSelectionModel().getSelectedItem();
        if(funcionario != null){
            data.removeFuncionario(funcionario.getId());
        }
        updateTable();
    }

    public void updateTable() {
        tableView.getItems().clear();
        tableView.getItems().addAll(data.getFuncionarios());
    }

}
