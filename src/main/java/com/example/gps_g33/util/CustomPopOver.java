package com.example.gps_g33.util;
import com.calendarfx.model.Entry;
import com.example.gps_g33.modelos.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import org.controlsfx.control.PopOver;

public class CustomPopOver extends Region {

    private final PopOver popOver;
    private final Entry entry;

    public CustomPopOver(PopOver popOver, Entry entry) {
        this.popOver = popOver;
        this.entry = entry;

        // Define o título e o texto do botão com base na presença da classe "customStyle"
        String title = entry.getStyleClass().contains("customStyle") ? "Cancelar visita" : "Reservar visita";
        popOver.setTitle(title);

        Button button = new Button(title);

        button.setOnAction(event -> {
            System.out.println("Ação realizada para o evento: " + entry.getTitle());

            Data data = Data.getInstance();
            Familiar familiar = data.getFamiliarPorId(data.getIdFamiliar());
            Visita visita = data.getVisitaPorId(entry.getId());

            if (visita == null) {
                System.out.println("Visita não encontrada");
            } else {
                System.out.println("Visita encontrada");

                if (entry.getStyleClass().contains("customStyle")) {
                    // Cancelar ação
                    visita.removeFamiliarId(familiar.getId());
                    System.out.println("Familiar removido da visita");

                    entry.getStyleClass().remove("customStyle");
                } else {
                    // Reservar ação
                    if (visita.getFamiliares().size() < 5) {
                        visita.addFamiliarId(familiar.getId());
                        System.out.println("Familiar adicionado à visita");
                        entry.getStyleClass().add("customStyle");
                    } else {
                        System.out.println("Limite de visitantes atingido para este horário.");
                    }
                }

            }

            popOver.hide();
        });

        getChildren().add(button);
    }
    public static void exibirPopup(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}