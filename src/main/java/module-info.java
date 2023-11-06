module com.example.gps_g33
{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires json.simple;
    requires com.google.gson;


    opens com.example.gps_g33 to javafx.fxml;
    exports com.example.gps_g33;
    exports com.example.gps_g33.controller;
    exports com.example.gps_g33.modelos;
    opens com.example.gps_g33.modelos to javafx.base,com.google.gson;
}