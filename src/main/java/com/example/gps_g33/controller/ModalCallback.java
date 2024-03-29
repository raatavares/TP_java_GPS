package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.*;

public interface ModalCallback {
    boolean usedCredentials(String email, String NIF);

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

    void onRestrictionEditada(Residente residente);
    void onRestrictionCriada(Residente residentePorId);
    
    void onAtividadeCriada(Atividade atividade);
    void onAtividadeEditada(Atividade atividade);
}
