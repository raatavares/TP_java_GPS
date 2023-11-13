package com.example.gps_g33.modelos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    /*@BeforeAll
    static void isValidLogin() {
        Data data = new Data();
        Funcionario funcionario = data.isValidLogin("p", "p");
        assertNotNull(funcionario);
        assertEquals("p", funcionario.getUsername());
        assertEquals("p", funcionario.getPassword());
    }*/

    @Test
    void testClearMedicacoes() {
        Data data = new Data();
        data.clearMedicacoes();
        assertEquals(0, data.getMedicacoes().size());
    }

    @Test
    void testClearRefeicoes() {
        Data data = new Data();
        data.clearRefeicoes();
        assertEquals(0, data.getRefeicoes().size());
    }

    @Test
    void testClearResidentes() {
        Data data = new Data();
        data.clearResidentes();
        assertEquals(0, data.getResidentes().size());
    }

    @Test
    void testClearFuncionarios() {
        Data data = new Data();
        data.clearFuncionarios();
        assertEquals(0, data.getFuncionarios().size());
    }

    @Test
    void testGetAddResidentes() {
        // Criar uma instância da sua classe que contém o método
        Data data = new Data();
        List<Residente> residentes = new ArrayList<>();

        // Criar algumas instâncias de Residente para testar
        Residente residente1 = new Residente(1, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
        Residente residente2 = new Residente(2, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
        Residente residente3 = new Residente(3, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");

        residentes.add(residente1);
        residentes.add(residente2);
        residentes.add(residente3);

        // Adicionar residentes à fonte de dados (substitua isso com sua própria fonte de dados)
        data.clearResidentes();
        data.addResidente(residente1);
        data.addResidente(residente2);
        data.addResidente(residente3);

        assertEquals(residentes, data.getResidentes());

    }
    @Test
    void testGetAddFuncionarios() {
        // Criar uma instância da sua classe que contém o método
        Data data = new Data();
        List<Funcionario> funcionarios = new ArrayList<>();

        // Criar algumas instâncias de Funcionario para testar
        Funcionario funcionario1 = new Funcionario(1, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
        Funcionario funcionario2 = new Funcionario(2, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
        Funcionario funcionario3 = new Funcionario(3, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");

        funcionarios.add(funcionario1);
        funcionarios.add(funcionario2);
        funcionarios.add(funcionario3);
        // Adicionar funcionarios à fonte de dados (substitua isso com sua própria fonte de dados)
        data.clearFuncionarios();
        data.addFuncionario(funcionario1);
        data.addFuncionario(funcionario2);
        data.addFuncionario(funcionario3);

        // Verificar se a lista retornada contém as funcionarios esperadas
        assertEquals(funcionarios, data.getFuncionarios());
    }

    @Test
    void setGetAddRefeicoes() {
        // Criar uma instância da sua classe que contém o método
        Data data = new Data();
        List<Refeicao> refeicoes = new ArrayList<>();

        // Criar algumas instâncias de Refeicao para testar
        Refeicao refeicao1 = new Refeicao(1, "teste", "teste", "teste", "teste", "teste");
        Refeicao refeicao2 = new Refeicao(2, "teste", "teste", "teste", "teste", "teste");
        Refeicao refeicao3 = new Refeicao(3, "teste", "teste", "teste", "teste", "teste");

        refeicoes.add(refeicao1);
        refeicoes.add(refeicao2);
        refeicoes.add(refeicao3);
        // Adicionar refeicoes à fonte de dados (substitua isso com sua própria fonte de dados)
        data.clearRefeicoes();
        data.addRefeicao(refeicao1);
        data.addRefeicao(refeicao2);
        data.addRefeicao(refeicao3);

        // Verificar se a lista retornada contém as refeicoes esperadas
        assertEquals(refeicoes, data.getRefeicoes());
    }


    @Test
    void testGetAddMedicacoes() {
        // Criar uma instância da sua classe que contém o método
        Data data = new Data();
        List<Medicacao> medicacoes = new ArrayList<>();

        // Criar algumas instâncias de Medicacao para testar
        Medicacao medicacao1 = new Medicacao(1, "Comprimido", "28/10/2023", "teste", "123");
        Medicacao medicacao2 = new Medicacao(2, "Comprimido", "29/10/2023", "teste", "123");
        Medicacao medicacao3 = new Medicacao(3, "Xarope", "30/10/2023", "teste", "123");


        medicacoes.add(medicacao1);
        medicacoes.add(medicacao2);
        medicacoes.add(medicacao3);
        // Adicionar medicacoes à fonte de dados (substitua isso com sua própria fonte de dados)
        data.clearMedicacoes();
        data.addMedicacao(medicacao1);
        data.addMedicacao(medicacao2);
        data.addMedicacao(medicacao3);

        // Verificar se a lista retornada contém as medicacoes esperadas
        assertEquals(medicacoes, data.getMedicacoes());
    }



    @Test
    void testSetMedicacoes() {
        // Criar uma instância da sua classe que contém o método
        Data data = new Data();
        List<Medicacao> medicacoes = new ArrayList<>();

        // Criar algumas instâncias de Medicacao para testar
        Medicacao medicacao1 = new Medicacao(1, "Comprimido", "28/10/2023", "teste", "123");
        Medicacao medicacao2 = new Medicacao(2, "Comprimido", "29/10/2023", "teste", "123");
        Medicacao medicacao3 = new Medicacao(3, "Xarope", "30/10/2023", "teste", "123");

        medicacoes.add(medicacao1);
        medicacoes.add(medicacao2);
        medicacoes.add(medicacao3);
        // Adicionar medicacoes à fonte de dados (substitua isso com sua própria fonte de dados)
        data.setMedicacoesData(medicacoes);

        // Verificar se a lista retornada contém as medicacoes esperadas
        assertEquals(medicacoes, data.getMedicacoes());
    }

    @Test
    void testCalcularProximoIdFuncionarios() {
        Data data = new Data();
        Funcionario funcionario = new Funcionario(data.calcularProximoIdFuncionarios(), "teste", "teste", "teste", " ", " ", " ", " ", " ", " ");
        data.addFuncionario(funcionario);
        assertEquals(data.calcularProximoIdFuncionarios() - 1, funcionario.getId());
    }

    @Test
    void testCalcularProximoIdMedicacoes() {
        Data data = new Data();
        Medicacao medicacao = new Medicacao(data.calcularProximoIdMedicacoes(), "teste", "teste", "teste", "teste");
        data.addMedicacao(medicacao);
        assertEquals(data.calcularProximoIdMedicacoes() - 1, medicacao.getId());
    }


    @Test
    void testGetMedicacaoPorId() {
        Data data = new Data();
        Medicacao medicacao = new Medicacao(data.calcularProximoIdMedicacoes(), "teste", "teste", "teste", "teste");
        data.addMedicacao(medicacao);
        assertEquals(medicacao, data.getMedicacaoPorId(medicacao.getId()));
    }

    @Test
    void testGetRefeicaoPorId() {
        Data data = new Data();
        Refeicao refeicao = new Refeicao(data.calcularProximoIdRefeicoes(), "teste", "teste", "teste", "teste", "teste");
        data.addRefeicao(refeicao);
        assertEquals(refeicao, data.getRefeicaoPorId(refeicao.getId()));
    }

    @Test
    void testGetFuncionarioPorId() {
        Data data = new Data();
        Funcionario funcionario = new Funcionario(data.calcularProximoIdFuncionarios(), "teste", "teste", "teste", " ", " ", " ", " ", " ", " ");
        data.addFuncionario(funcionario);
        assertEquals(funcionario, data.getFuncionarioPorId(funcionario.getId()));
    }

    @Test
    void testGetResidentePorId() {
        Data data = new Data();
        Residente residente = new Residente(data.calcularProximoIdResidentes(), "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
        data.addResidente(residente);
        assertEquals(residente, data.getResidentePorId(residente.getId()));
    }

    @Test
    void testRemoveMedicacao() {
        Data data = new Data();
        Medicacao medicacao = new Medicacao(data.calcularProximoIdMedicacoes(), "teste", "teste", "teste", "teste");
        data.addMedicacao(medicacao);
        data.removeMedicacao(medicacao.getId());
        assertNull(data.getMedicacaoPorId(medicacao.getId()));
    }

    @Test
    void testRemoveRefeicao() {
        Data data = new Data();
        Refeicao refeicao = new Refeicao(data.calcularProximoIdRefeicoes(), "teste", "teste", "teste", "teste", "teste");
        data.addRefeicao(refeicao);
        data.removeRefeicao(refeicao.getId());
        assertNull(data.getRefeicaoPorId(refeicao.getId()));
    }

    @Test
    void testRemoveResidente() {
        Data data = new Data();
        Residente residente = new Residente(data.calcularProximoIdResidentes(), "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
        data.addResidente(residente);
        data.removeResidente(residente.getId());
        assertNull(data.getResidentePorId(residente.getId()));
    }

    @Test
    void testRemoveFuncionario() {
        Data data = new Data();
        Funcionario funcionario = new Funcionario(data.calcularProximoIdFuncionarios(), "teste", "teste", "teste", " ", " ", " ", " ", " ", " ");
        data.addFuncionario(funcionario);
        data.removeFuncionario(funcionario.getId());
        assertNull(data.getFuncionarioPorId(funcionario.getId()));
    }
}