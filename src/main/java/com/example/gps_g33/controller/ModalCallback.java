package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.*;

public interface ModalCallback {
    void onFuncionarioCriado(Funcionario funcionario);
    void onFuncionarioEditado(Funcionario funcionario);

    void onResidenteEditado(Residente residente);
    void onResidenteCriado(Residente residente);

    void onRefeicaoCriado(Refeicao refeicao);
    void onRefeicaoEditado(Refeicao refeicao);

    void onMedicacaoCriado(Medicacao medicacao);
    void onMedicacaoEditado(Medicacao medicacao);

    void onUtensilioCriado(Utensilio utensilio);
    void onUtensilioEditado(Utensilio utensilio);
}
