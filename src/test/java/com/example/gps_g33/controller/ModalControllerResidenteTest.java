package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Residente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ModalControllerResidenteTest {

    private ModalControllerResidente suaClasse;
    private Residente residente;
    private TextField nomeField;
    private TextField sobrenomeField;
    private TextField nifField;
    private TextField contactoField;
    private TextField emailField;
    private DatePicker dataNascimentoPicker;
    private Stage stage;

    @BeforeEach
    void setUp() {
        // Inicializar campos e classes necessárias para o teste
        nomeField = mock(TextField.class);
        sobrenomeField = mock(TextField.class);
        nifField = mock(TextField.class);
        contactoField = mock(TextField.class);
        emailField = mock(TextField.class);
        dataNascimentoPicker = mock(DatePicker.class);
        stage = mock(Stage.class);

        suaClasse = new ModalControllerResidente();
        suaClasse.setCampos(nomeField, sobrenomeField, nifField, contactoField, emailField, dataNascimentoPicker);
    }
    @Test
    void handleCriarButton() {
        // Configurar mocks para simular campos válidos
        when(nomeField.getText()).thenReturn("John");
        when(sobrenomeField.getText()).thenReturn("Doe");
        when(nifField.getText()).thenReturn("123456789");
        when(contactoField.getText()).thenReturn("123-456-7890");
        when(emailField.getText()).thenReturn("john@example.com");
        when(dataNascimentoPicker.getValue()).thenReturn(LocalDate.of(1990, 1, 1));

        // Chamar a função
        suaClasse.handleCriarButton();

        // Verificar se o modal foi fechado
        verify(stage).close();

    }
}