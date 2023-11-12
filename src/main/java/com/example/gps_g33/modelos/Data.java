package com.example.gps_g33.modelos;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.*;
import java.io.FileReader;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
public class Data {
    public String PATH_RESIDENTES = "Dados\\residentes.json";
    public String PATH_FUNCIONARIOS = "Dados\\funcionarios.json";
    public String PATH_FAMILIARES = "./Dados/familiares.json";
    public String PATH_REFEICOES = "Dados\\refeicoes.json";

    public String PATH_MEDICACOES = "Dados\\medicacoes.json";

    private static Data instance;
    private List<Funcionario> funcionariosData;
    private List<Residente> residentesData;
    private List<Refeicao> refeicoesData;

    private List<Medicacao> medicacoesData;

    private int idLogado;

    public Data(){
        loadData();
    }


    public List<Funcionario> getFuncionarios() {
        return funcionariosData;
    }

    public Funcionario getFuncionarioPorId(int id) {
        for (Funcionario funcionario : funcionariosData) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null; // Retornar null se o funcionário não for encontrado
    }

    public Funcionario isValidLogin(String username, String password) {

        for (Funcionario funcionario : funcionariosData) {
            if (funcionario.getUsername().equals(username) && funcionario.getPassword().equals(password)) {
                return funcionario;
            }
        }

        return null;
    }


    public void addFuncionario(Funcionario funcionario) {
        funcionariosData.add(funcionario);
        saveData();
    }

    public void removeFuncionario(int id) {
        Funcionario funcionarioToRemove = getFuncionarioPorId(id);
        if (funcionarioToRemove != null) {
            funcionariosData.remove(funcionarioToRemove);
        }
        saveData();
    }




    public List<Residente> getResidentes() {
        return residentesData;
    }

    public Residente getResidentePorId(int id) {
        for (Residente residente : residentesData) {
            if (residente.getId() == id) {
                return residente;
            }
        }
        return null; // Retornar null se o residente não for encontrado
    }

    public void addResidente(Residente residente) {
        residentesData.add(residente);
        saveData();
    }

    public void removeResidente(int id) {
        Residente residenteToRemove = getResidentePorId(id);
        if (residenteToRemove != null) {
            residentesData.remove(residenteToRemove);
        }
        saveData();
    }



    public List<Refeicao> getRefeicoes() {
        return refeicoesData;
    }

    public Refeicao getRefeicaoPorId(int id) {
        for (Refeicao refeicao : refeicoesData) {
            if (refeicao.getId() == id) {
                return refeicao;
            }
        }
        return null; // Retornar null se o residente não for encontrado
    }

    public void addRefeicao(Refeicao refeicao) {
        refeicoesData.add(refeicao);
        saveData();
    }

    public void removeRefeicao(int id) {
        Refeicao refeicaoToRemove = getRefeicaoPorId(id);
        if (refeicaoToRemove != null) {
            refeicoesData.remove(refeicaoToRemove);
        }
        saveData();
    }

    public List<Medicacao> getMedicacoes() {
        return medicacoesData;
    }

    public void addMedicacao(Medicacao medicacao) {
        medicacoesData.add(medicacao);
        saveData();
    }

    public void removeMedicacao(int id) {
        Medicacao medicacaoToRemove = getMedicacaoPorId(id);
        if (medicacaoToRemove != null) {
            medicacoesData.remove(medicacaoToRemove);
        }
        saveData();
    }

    public Medicacao getMedicacaoPorId(int id) {
        for (Medicacao medicacao : medicacoesData) {
            if (medicacao.getId() == id) {
                return medicacao;
            }
        }
        return null;
    }

    public void loadData() {
        try {
            Gson gson = new Gson();
            FileReader readerResi = new FileReader(PATH_RESIDENTES);
            FileReader readerFunc = new FileReader(PATH_FUNCIONARIOS);
            FileReader readerRefei = new FileReader(PATH_REFEICOES);
            FileReader readerMedi = new FileReader(PATH_MEDICACOES);

            Type residenteListType = new TypeToken<List<Residente>>() {}.getType();
            Type funcionarioListType = new TypeToken<List<Funcionario>>() {}.getType();
            Type refeicaoListType = new TypeToken<List<Refeicao>>() {}.getType();

            Type medicacaoListType = new TypeToken<List<Medicacao>>() {}.getType();

            residentesData = gson.fromJson(readerResi, residenteListType);
            funcionariosData = gson.fromJson(readerFunc, funcionarioListType);
            refeicoesData = gson.fromJson(readerRefei, refeicaoListType);
            medicacoesData = gson.fromJson(readerMedi, medicacaoListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveData() {
        try {
            Gson gson = new Gson();
            FileWriter writerResi = new FileWriter(PATH_RESIDENTES);
            FileWriter writerFunc = new FileWriter(PATH_FUNCIONARIOS);
            FileWriter writerRefei = new FileWriter(PATH_REFEICOES);
            FileWriter writerMedi = new FileWriter(PATH_MEDICACOES);

            gson.toJson(residentesData, writerResi);
            gson.toJson(funcionariosData, writerFunc);
            gson.toJson(refeicoesData, writerRefei);
            gson.toJson(medicacoesData, writerMedi);

            writerResi.close();
            writerFunc.close();
            writerRefei.close();
            writerMedi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calcularProximoIdFuncionarios() {
        int maiorId = funcionariosData.stream()
                .map(Funcionario::getId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return maiorId + 1;
    }

    public int calcularProximoIdResidentes() {
        int maiorId = residentesData.stream()
                .map(Residente::getId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return maiorId + 1;
    }


    public int calcularProximoIdRefeicoes() {
        int maiorId = refeicoesData.stream()
                .map(Refeicao::getId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return maiorId + 1;
    }

    public int calcularProximoIdMedicacoes() {
        int maiorId = medicacoesData.stream()
                .map(Medicacao::getId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return maiorId + 1;
    }

    public int getIdLogado() {
        return idLogado;
    }

    public void setIdLogado(int idLogado) {
        this.idLogado = idLogado;
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

}
