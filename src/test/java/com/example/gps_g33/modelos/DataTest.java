package com.example.gps_g33.modelos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DataTest {

//    /*@BeforeAll
//    static void isValidLogin() {
//        Data data = new Data();
//        Funcionario funcionario = data.isValidLogin("p", "p");
//        assertNotNull(funcionario);
//        assertEquals("p", funcionario.getUsername());
//        assertEquals("p", funcionario.getPassword());
//    }*/
//    @Test
//    void testClearUtensilios() {
//        Data data = new Data();
//        data.clearUtensilios();
//        assertEquals(0, data.getUtensilios().size());
//    }
//
//    @Test
//    void testClearMedicacoes() {
//        Data data = new Data();
//        data.clearMedicacoes();
//        assertEquals(0, data.getMedicacoes().size());
//    }
//
//    @Test
//    void testClearRefeicoes() {
//        Data data = new Data();
//        data.clearRefeicoes();
//        assertEquals(0, data.getRefeicoes().size());
//    }
//
//    @Test
//    void testClearResidentes() {
//        Data data = new Data();
//        data.clearResidentes();
//        assertEquals(0, data.getResidentes().size());
//    }
//
//    @Test
//    void testClearFuncionarios() {
//        Data data = new Data();
//        data.clearFuncionarios();
//        assertEquals(0, data.getFuncionarios().size());
//    }
//
//    @Test
//    void testGetAddResidentes() {
//        // Criar uma instância da sua classe que contém o método
//        Data data = new Data();
//        List<Residente> residentes = new ArrayList<>();
//
//        // Criar algumas instâncias de Residente para testar
//        Residente residente1 = new Residente(1, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//        Residente residente2 = new Residente(2, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//        Residente residente3 = new Residente(3, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//
//        residentes.add(residente1);
//        residentes.add(residente2);
//        residentes.add(residente3);
//
//        // Adicionar residentes à fonte de dados (substitua isso com sua própria fonte de dados)
//        data.clearResidentes();
//        data.addResidente(residente1);
//        data.addResidente(residente2);
//        data.addResidente(residente3);
//
//        assertEquals(residentes, data.getResidentes());
//
//    }
//    @Test
//    void testGetAddFuncionarios() {
//        // Criar uma instância da sua classe que contém o método
//        Data data = new Data();
//        List<Funcionario> funcionarios = new ArrayList<>();
//
//        // Criar algumas instâncias de Funcionario para testar
//        Funcionario funcionario1 = new Funcionario(1, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//        Funcionario funcionario2 = new Funcionario(2, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//        Funcionario funcionario3 = new Funcionario(3, "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//
//        funcionarios.add(funcionario1);
//        funcionarios.add(funcionario2);
//        funcionarios.add(funcionario3);
//        // Adicionar funcionarios à fonte de dados (substitua isso com sua própria fonte de dados)
//        data.clearFuncionarios();
//        data.addFuncionario(funcionario1);
//        data.addFuncionario(funcionario2);
//        data.addFuncionario(funcionario3);
//
//        // Verificar se a lista retornada contém as funcionarios esperadas
//        assertEquals(funcionarios, data.getFuncionarios());
//    }
//
//    @Test
//    void setGetAddRefeicoes() {
//        // Criar uma instância da sua classe que contém o método
//        Data data = new Data();
//        List<Refeicao> refeicoes = new ArrayList<>();
//
//        // Criar algumas instâncias de Refeicao para testar
//        Refeicao refeicao1 = new Refeicao(1, "teste", "teste", "teste", "teste", "teste");
//        Refeicao refeicao2 = new Refeicao(2, "teste", "teste", "teste", "teste", "teste");
//        Refeicao refeicao3 = new Refeicao(3, "teste", "teste", "teste", "teste", "teste");
//
//        refeicoes.add(refeicao1);
//        refeicoes.add(refeicao2);
//        refeicoes.add(refeicao3);
//        // Adicionar refeicoes à fonte de dados (substitua isso com sua própria fonte de dados)
//        data.clearRefeicoes();
//        data.addRefeicao(refeicao1);
//        data.addRefeicao(refeicao2);
//        data.addRefeicao(refeicao3);
//
//        // Verificar se a lista retornada contém as refeicoes esperadas
//        assertEquals(refeicoes, data.getRefeicoes());
//    }
//
//
//    @Test
//    void testGetAddMedicacoes() {
//        // Criar uma instância da sua classe que contém o método
//        Data data = new Data();
//        List<Medicacao> medicacoes = new ArrayList<>();
//
//        // Criar algumas instâncias de Medicacao para testar
//        Medicacao medicacao1 = new Medicacao(1, "Comprimido", "28/10/2023", "teste", "123");
//        Medicacao medicacao2 = new Medicacao(2, "Comprimido", "29/10/2023", "teste", "123");
//        Medicacao medicacao3 = new Medicacao(3, "Xarope", "30/10/2023", "teste", "123");
//
//
//        medicacoes.add(medicacao1);
//        medicacoes.add(medicacao2);
//        medicacoes.add(medicacao3);
//        // Adicionar medicacoes à fonte de dados (substitua isso com sua própria fonte de dados)
//        data.clearMedicacoes();
//        data.addMedicacao(medicacao1);
//        data.addMedicacao(medicacao2);
//        data.addMedicacao(medicacao3);
//
//        // Verificar se a lista retornada contém as medicacoes esperadas
//        assertEquals(medicacoes, data.getMedicacoes());
//    }
//
//
//    @Test
//    void testGetAddUtensilios() {
//        // Criar uma instância da sua classe que contém o método
//        Data data = new Data();
//        List<Utensilio> utensilios = new ArrayList<>();
//
//        // Criar algumas instâncias de utensilios para testar
//        Utensilio utensilio1 = new Utensilio(1, "Equipamentos de Proteção", false);
//        Utensilio utensilio2 = new Utensilio(2, "Gaze", true);
//        Utensilio utensilio3 = new Utensilio(3, "Anti-inflamatórios", true);
//
//
//        utensilios.add(utensilio1);
//        utensilios.add(utensilio2);
//        utensilios.add(utensilio3);
//        // Adicionar utensilios à fonte de dados (substitua isso com sua própria fonte de dados)
//        data.clearUtensilios();
//        data.addUtensilio(utensilio1);
//        data.addUtensilio(utensilio2);
//        data.addUtensilio(utensilio3);
//
//        // Verificar se a lista retornada contém os utensilios esperadas
//        assertEquals(utensilios, data.getUtensilios());
//    }
//
//    @Test
//    void testUsedNif(){
//        //TODO: Quando familiares for implementado, adicionar testes para familiares
//        Data data = new Data();
//
//        // Criar algumas instâncias de Funcionario para testar
//        Residente residente_1=new Residente(1,"Joao","Ze","12/12/1969","923456789","123456789","joao@isec.pt","","","asma");
//        Residente residente_2=new Residente(2,"Ze","Antonio","12/10/1959","823446729","923456789","ze@isec.pt","","","asma");
//        Residente residente_3=new Residente(3,"Antonio","Silva","19/05/1949","823446729","975456789","Antonio@isec.pt","","","asma");
//
//        data.addResidente(residente_1);
//        assertFalse(data.usedNif(residente_2.getNif()));
//        data.addResidente(residente_2);
//        assertTrue(data.usedNif(residente_3.getNif()));
//        data.addResidente(residente_3);
//
//        Funcionario funcionario_1=new Funcionario(1,"Joao","Ze","12/12/1969","923456789","123456789","joao@isec.pt","","","asma");
//        assertTrue(data.usedNif(funcionario_1.getNif()));
//        data.addFuncionario(funcionario_1);
//        Funcionario funcionario_2=new Funcionario(1,"Joao","Ze","12/12/1969","563456789","123456789","joao@isec.pt","","","asma");
//        assertFalse(data.usedNif(funcionario_2.getNif()));
//        data.addFuncionario(funcionario_2);
//    }
//
//    @Test
//    void testUsedEmail(){
//        //TODO: Quando familiares for implementado, adicionar testes para familiares
//        Data data = new Data();
//
//        Residente residente_1=new Residente(1,"Joao","Ze","12/12/1969","923456789","123456789","joao@isec.pt","","","asma");
//        Residente residente_2=new Residente(2,"Ze","Antonio","12/10/1959","823446729","923456789","ze@isec.pt","","","asma");
//        Residente residente_3=new Residente(3,"Antonio","Silva","19/05/1949","823446729","975456789","ze@isec.pt","","","asma");
//
//        data.addResidente(residente_1);
//        assertFalse(data.usedNif(residente_2.getNif()));
//        data.addResidente(residente_2);
//        assertTrue(data.usedNif(residente_3.getNif()));
//        data.addResidente(residente_3);
//
//        Funcionario funcionario_1=new Funcionario(1,"Joao","Ze","12/12/1969","923456789","123456789","joao@isec.pt","","","asma");
//        assertTrue(data.usedNif(funcionario_1.getNif()));
//        data.addFuncionario(funcionario_1);
//        Funcionario funcionario_2=new Funcionario(1,"Joao","Ze","12/12/1969","563456089","123776789","jodfao@isec.pt","","","asma");
//        assertFalse(data.usedNif(funcionario_2.getNif()));
//        data.addFuncionario(funcionario_2);
//
//
//    }
//
//@Test
//    void testSetResidentes(){
//        Data data = new Data();
//        List<Residente> residentes = new ArrayList<>();
//
//        Residente residente_1=new Residente(1,"Joao","Ze","12/12/1969","123456789","123456789","joao@isec.pt","","","asma");
//        Residente residente_2=new Residente(2,"Ze","Antonio","12/10/1959","123446729","923456789","ze@isec.pt","","","asma");
//        Residente residente_3=new Residente(3,"Antonio","Silva","19/05/1949","1234342729","975456789","Antonio@isec.pt","","","asma");
//
//        Residente residente1=new Residente(1,"Joao","Ze","12/12/1969","123456789","123456789","joao@isec.pt","Salada","polen","asma");
//        Residente residente2=new Residente(2,"Ze","Antonio","12/10/1959","123446729","923456789","ze@isec.pt","","sopa","asma");
//        Residente residente3=new Residente(3,"Antonio","Silva","19/05/1949","1234342729","975456789","Antonio@isec.pt","bolonhesa","","asma");
//
//        residentes.add(residente1);
//        residentes.add(residente2);
//        residentes.add(residente3);
//        // Adicionar medicacoes à fonte de dados (substitua isso com sua própria fonte de dados)
//        data.addResidente(residente_1);
//        data.addResidente(residente_2);
//        data.addResidente(residente_3);
//
//
//        data.setResidente(residente1);
//        data.setResidente(residente2);
//        data.setResidente(residente3);
//
//        assertEquals(residente1.getPrefAli(), data.getResidentePorId(1).getPrefAli());
//        assertEquals(residente1.getAlergias(), data.getResidentePorId(1).getAlergias());
//        assertEquals(residente2.getPrefAli(), data.getResidentePorId(2).getPrefAli());
//        assertEquals(residente2.getAlergias(), data.getResidentePorId(2).getAlergias());
//        assertEquals(residente3.getPrefAli(), data.getResidentePorId(3).getPrefAli());
//        assertEquals(residente3.getAlergias(), data.getResidentePorId(3).getAlergias());
//
//    }
//
//    @Test
//    void testSetMedicacoes() {
//        // Criar uma instância da sua classe que contém o método
//        Data data = new Data();
//        List<Medicacao> medicacoes = new ArrayList<>();
//
//        // Criar algumas instâncias de Medicacao para testar
//        Medicacao medicacao1 = new Medicacao(1, "Comprimido", "28/10/2023", "teste", "123");
//        Medicacao medicacao2 = new Medicacao(2, "Comprimido", "29/10/2023", "teste", "123");
//        Medicacao medicacao3 = new Medicacao(3, "Xarope", "30/10/2023", "teste", "123");
//
//        medicacoes.add(medicacao1);
//        medicacoes.add(medicacao2);
//        medicacoes.add(medicacao3);
//        // Adicionar medicacoes à fonte de dados (substitua isso com sua própria fonte de dados)
//        data.setMedicacoesData(medicacoes);
//
//        // Verificar se a lista retornada contém as medicacoes esperadas
//        assertEquals(medicacoes, data.getMedicacoes());
//    }
//
//    @Test
//    void testCalcularProximoIdFuncionarios() {
//        Data data = new Data();
//        Funcionario funcionario = new Funcionario(data.calcularProximoIdFuncionarios(), "teste", "teste", "teste", " ", " ", " ", " ", " ", " ");
//        data.addFuncionario(funcionario);
//        assertEquals(data.calcularProximoIdFuncionarios() - 1, funcionario.getId());
//    }
//
//    @Test
//    void testCalcularProximoIdMedicacoes() {
//        Data data = new Data();
//        Medicacao medicacao = new Medicacao(data.calcularProximoIdMedicacoes(), "teste", "teste", "teste", "teste");
//        data.addMedicacao(medicacao);
//        assertEquals(data.calcularProximoIdMedicacoes() - 1, medicacao.getId());
//    }
//
//
//    @Test
//    void testGetMedicacaoPorId() {
//        Data data = new Data();
//        Medicacao medicacao = new Medicacao(data.calcularProximoIdMedicacoes(), "teste", "teste", "teste", "teste");
//        data.addMedicacao(medicacao);
//        assertEquals(medicacao, data.getMedicacaoPorId(medicacao.getId()));
//    }
//
//    @Test
//    void testGetRefeicaoPorId() {
//        Data data = new Data();
//        Refeicao refeicao = new Refeicao(data.calcularProximoIdRefeicoes(), "teste", "teste", "teste", "teste", "teste");
//        data.addRefeicao(refeicao);
//        assertEquals(refeicao, data.getRefeicaoPorId(refeicao.getId()));
//    }
//
//    @Test
//    void testGetFuncionarioPorId() {
//        Data data = new Data();
//        Funcionario funcionario = new Funcionario(data.calcularProximoIdFuncionarios(), "teste", "teste", "teste", " ", " ", " ", " ", " ", " ");
//        data.addFuncionario(funcionario);
//        assertEquals(funcionario, data.getFuncionarioPorId(funcionario.getId()));
//    }
//
//    @Test
//    void testGetResidentePorId() {
//        Data data = new Data();
//        Residente residente = new Residente(data.calcularProximoIdResidentes(), "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//        data.addResidente(residente);
//        assertEquals(residente, data.getResidentePorId(residente.getId()));
//    }
//    @Test
//    void testGetUtensilioPorId() {
//        Data data = new Data();
//        Utensilio utensilio = new Utensilio(data.calcularProximoIdUtensilio(), "teste",true);
//        data.addUtensilio(utensilio);
//        assertEquals(utensilio, data.getUtensilioPorId(utensilio.getId()));
//    }
//
//    @Test
//    void testRemoveMedicacao() {
//        Data data = new Data();
//        Medicacao medicacao = new Medicacao(data.calcularProximoIdMedicacoes(), "teste", "teste", "teste", "teste");
//        data.addMedicacao(medicacao);
//        data.removeMedicacao(medicacao.getId());
//        assertNull(data.getMedicacaoPorId(medicacao.getId()));
//    }
//
//    @Test
//    void testRemoveRefeicao() {
//        Data data = new Data();
//        Refeicao refeicao = new Refeicao(data.calcularProximoIdRefeicoes(), "teste", "teste", "teste", "teste", "teste");
//        data.addRefeicao(refeicao);
//        data.removeRefeicao(refeicao.getId());
//        assertNull(data.getRefeicaoPorId(refeicao.getId()));
//    }
//
//    @Test
//    void testRemoveResidente() {
//        Data data = new Data();
//        Residente residente = new Residente(data.calcularProximoIdResidentes(), "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste", "teste");
//        data.addResidente(residente);
//        data.removeResidente(residente.getId());
//        assertNull(data.getResidentePorId(residente.getId()));
//    }
//
//    @Test
//    void testRemoveFuncionario() {
//        Data data = new Data();
//        Funcionario funcionario = new Funcionario(data.calcularProximoIdFuncionarios(), "teste", "teste", "teste", " ", " ", " ", " ", " ", " ");
//        data.addFuncionario(funcionario);
//        data.removeFuncionario(funcionario.getId());
//        assertNull(data.getFuncionarioPorId(funcionario.getId()));
//    }
//    @Test
//    void testRemoveUtensilio() {
//        Data data = new Data();
//        Utensilio utensilio = new Utensilio(data.calcularProximoIdUtensilio(), "teste", true);
//        data.addUtensilio(utensilio);
//        data.removeUtensilio(utensilio.getId());
//        assertNull(data.getUtensilioPorId(utensilio.getId()));
//    }
}
