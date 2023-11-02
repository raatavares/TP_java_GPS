package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Funcionario;

public interface ModalCallback {
    void onFuncionarioCriado(Funcionario funcionario);
    void onFuncionarioEditado(Funcionario funcionario);
}
