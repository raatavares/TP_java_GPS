package com.example.gps_g33.util;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
public class InputValidation {

    public static boolean isEmail(String input) {
        return input.contains("@");
    }

    public static boolean isTelemovel(String input) {
        return input.matches("\\d{9}");
    }

    public static boolean isNif(String input) {
        return input.matches("\\d{9}");
    }

    public static boolean isAdulto(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        if(!isDataNotNull(dataNascimento))
            return false;

        return dataNascimento.plusYears(18).isBefore(dataAtual);
    }

    public static boolean isDataValida(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        if(!isDataNotNull(dataNascimento))
            return false;


        return dataNascimento.isBefore(dataAtual);
    }

    public static boolean isDataValidaRefeicoes(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        if(!isDataNotNull(dataNascimento))
            return false;


        return dataNascimento.isAfter(dataAtual);
    }

    public static boolean isDataNotNull(LocalDate dataNascimento) {
        return dataNascimento != null;
    }

    public static boolean isLengthValid(String input, int length) {
        return input.length() >= length;
    }

    public static boolean isDescricaoValid(String input, int length) {
        return input.length() >= length;
    }


    public static boolean styleTextError(TextField aux, boolean mostrarErro) {
        if (mostrarErro) {
            aux.setStyle("-fx-border-color: red");
            return false;
        } else {
            aux.setStyle("");
            return true;
        }
    }

    public static boolean styleTextAreaError(TextArea aux, boolean mostrarErro) {
        if (mostrarErro) {
            aux.setStyle("-fx-border-color: red");
            return false;
        } else {
            aux.setStyle("");
            return true;
        }
    }

    public static boolean styleDataError(DatePicker aux, boolean mostrarErro) {
        if (mostrarErro) {
            aux.setStyle("-fx-border-color: red");
            return false;
        } else {
            aux.setStyle("");
            return true;
        }
    }
}
