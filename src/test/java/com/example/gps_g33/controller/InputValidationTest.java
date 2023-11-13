package com.example.gps_g33.controller;

import com.example.gps_g33.util.InputValidation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidationTest {
    @Test
    void validateNif(){

        assertEquals(true, InputValidation.isNif("123456789"));
        assertEquals(false,InputValidation.isNif("1234567"));
        assertEquals(false,InputValidation.isNif("qwertyuio"));
        assertEquals(false,InputValidation.isNif("asasas"));
        assertEquals(false,InputValidation.isNif("1234567890"));
        assertEquals(false,InputValidation.isNif("123456789qwertyuio"));
    }


    @Test
    void validateTelemovel(){

        assertEquals(true,InputValidation.isTelemovel("123456789"));
        assertEquals(false,InputValidation.isTelemovel("1234567"));
        assertEquals(false,InputValidation.isTelemovel("qwertyuio"));
        assertEquals(false,InputValidation.isTelemovel("asasas"));
        assertEquals(false,InputValidation.isTelemovel("1234567890"));
        assertEquals(false,InputValidation.isTelemovel("123456789qwertyuio"));
    }

    @Test
    void validateEmail(){

        assertEquals(true,InputValidation.isEmail("mario@isec"));
        assertEquals(false,InputValidation.isEmail("mario.isec"));

    }

    @Test
    void validateAdulto(){
        assertEquals(false,InputValidation.isAdulto(null));
        assertEquals(true,InputValidation.isAdulto(java.time.LocalDate.now().minusYears(19)));
        assertEquals(false,InputValidation.isAdulto(java.time.LocalDate.now().minusYears(15)));
        assertEquals(false,InputValidation.isAdulto(java.time.LocalDate.now().plusYears(1)));
    }

    @Test
    void validateDataValida(){
        assertEquals(false,InputValidation.isDataValida(null));
        assertEquals(true,InputValidation.isDataValida(java.time.LocalDate.now().minusWeeks(2)));
        assertEquals(false,InputValidation.isDataValida(java.time.LocalDate.now().plusYears(1)));
    }

    @Test
    void validateLength(){
        assertEquals(true,InputValidation.isLengthValid("123456789",3));
        assertEquals(false,InputValidation.isLengthValid("123456789",10));
        assertEquals(true,InputValidation.isLengthValid("123456789",0));
        assertEquals(true,InputValidation.isLengthValid("123456789",-1));
    }




}
