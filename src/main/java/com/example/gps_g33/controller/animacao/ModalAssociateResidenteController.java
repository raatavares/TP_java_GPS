package com.example.gps_g33.controller.animacao;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.*;
import com.example.gps_g33.util.InputValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ModalAssociateResidenteController {
    private Data data;
    private boolean adicionarParaTodos = false;
    private Atividade atividade;

    @FXML
    public Button associate_Button;

    @FXML
    public Button cancelar_Button;

    @FXML
    public TextField nome_Atividade;
    @FXML
    public TextArea descricao_Atividade;
    @FXML
    public TextField nome_residenteLabel;
    @FXML
    public TextField nif_residenteLabel;
    @FXML
    public Label labelErros;

    @FXML
    public TableView<Residente> tabela_residentes;

    @FXML
    public TableColumn<Residente,String> nome_residente;

    @FXML
    public TableColumn<Residente,String> nif_residente;

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }



    @FXML
    public void initialize() {
        // Desabilitar a edição das TextField
        nome_residenteLabel.setEditable(false);
        nif_residenteLabel.setEditable(false);

        // Lógica para atualizar a tabela de residentes
        nome_residente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nif_residente.setCellValueFactory(new PropertyValueFactory<>("nif"));

        // Adiciona o row factory para destacar os residentes associados à atividade
        tabela_residentes.setRowFactory(tv -> new TableRow<Residente>() {
            @Override
            protected void updateItem(Residente residente, boolean empty) {
                super.updateItem(residente, empty);

                if (empty || residente == null) {
                    setStyle(""); // Limpa qualquer estilo
                } else {
                    // Verifica se o NIF do residente está associado à atividade
                    List<String> nifsAtividade = atividade != null ? atividade.getNifs() : null;
                    if (nifsAtividade != null && nifsAtividade.contains(residente.getNif())) {
                        // Se associado, define o estilo de fundo para vermelho
                        setStyle("-fx-background-color: #FF0000;");
                    } else {
                        // Se não associado, não há estilo de fundo
                        setStyle("");
                    }
                }
            }
        });

        updatetable();

        tabela_residentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                adicionarParaTodos = false;
                // Quando um residente é selecionado, atualizar as TextField
                nome_residenteLabel.setText(newSelection.getNome());
                nif_residenteLabel.setText(newSelection.getNif());
            }
        });
    }


    public void updatetable() {
        // Lógica para atualizar a tabela de residentes

        data = Data.getInstance();
        List<Residente> residentes = data.getResidentes();
        // Converter a lista para uma ObservableList
        ObservableList<Residente> observableList = FXCollections.observableArrayList(residentes);

        tabela_residentes.setItems(observableList);
    }

    @FXML
    public void handleAssociateButton() {
        boolean camposValidos = true;
        String mensagemErro = "";

            // Verifique se um residente foi selecionado na tabela
            Residente residenteSelecionado = tabela_residentes.getSelectionModel().getSelectedItem();

            if (residenteSelecionado != null) {
                Atividade atividades = data.getAtividades().stream()
                        .filter(ativ -> ativ.getId() == atividade.getId())
                        .findFirst()
                        .orElse(null);


                if (atividades != null) {
                    for (int i = 0; i < data.getAtividades().size(); i++) {
                        if (data.getAtividades().get(i).getId() == atividades.getId()) {
                            // Verifique se o NIF do residente já está associado à atividade
                            List<String> nifsAtividade = atividade.getNifs();
                            if (nifsAtividade != null && nifsAtividade.contains(residenteSelecionado.getNif())) {
                                camposValidos = false;
                                mensagemErro = "Residente com NIF " + residenteSelecionado.getNif() + " já está associado à atividade.";
                                break;
                            } else {
                                atividades.adicionarNomeResidente(residenteSelecionado);
                                atividades.adicionarNifResidente(residenteSelecionado);
                                data.getAtividades().set(i, atividades);

                                if (callback != null) {
                                    callback.onAtividadeEditada(atividade);
                                }

                                break;
                            }
                        }
                    }
                }

            } else {
                camposValidos = false;
                mensagemErro = "Por favor, selecione um residente da tabela.";
            }


        // Verifique se há erros
        if (camposValidos) {
            // Fechar o modal
            Stage stage = (Stage) associate_Button.getScene().getWindow();
            stage.close();
        } else {
            // Exiba a mensagem de erro na labelErros
            labelErros.setText(mensagemErro);
        }
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }

    public void setAtividadeParaAssociacao(Atividade atividade) {
        this.atividade = atividade;
        System.out.println("Atividade: " + this.atividade);
        nome_Atividade.setText(atividade.getTipoAtividade());
        descricao_Atividade.setText(atividade.getDescricao());

        // Desabilitar a edição das TextField
        nome_Atividade.setEditable(false);
        descricao_Atividade.setEditable(false);
    }
}
