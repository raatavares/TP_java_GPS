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
    requires com.google.gson;


    opens com.example.gps_g33 to javafx.fxml;
    exports com.example.gps_g33;
    exports com.example.gps_g33.controller;
    exports com.example.gps_g33.modelos;
    opens com.example.gps_g33.modelos to javafx.base,com.google.gson;
    exports com.example.gps_g33.controller.depCulinaria;
    exports com.example.gps_g33.controller.depClinico;
    exports com.example.gps_g33.controller.gerencia;
    exports com.example.gps_g33.controller.funcionarios;
}