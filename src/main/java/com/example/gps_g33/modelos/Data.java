package com.example.gps_g33.modelos;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.*;
import java.io.FileReader;

import com.calendarfx.model.Entry;
import com.example.gps_g33.controller.familiares.Client;
import com.example.gps_g33.controller.funcionarios.Server;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
public class Data {
    public String PATH_RESIDENTES = "Dados\\residentes.json";
    public String PATH_FUNCIONARIOS = "Dados\\funcionarios.json";
    public String PATH_FAMILIARES = "Dados\\familiares.json";
    public String PATH_REFEICOES = "Dados\\refeicoes.json";

    public String PATH_MEDICACOES = "Dados\\medicacoes.json";
    public String PATH_UTENSILIOS = "Dados\\Medicamentos&Utensilios.json";
    public String PATH_VISITAS = "Dados\\visitas.json";
    public String PATH_ATIVIDADES = "Dados\\atividades.json";

    public String PATH_LOGIN = "Dados\\logincredentials.json";


    private static Data instance;
    private List<Funcionario> funcionariosData;
    private List<Residente> residentesData;
    private List<Refeicao> refeicoesData;

    private List<Medicacao> medicacoesData;
    private List<Utensilio> utensiliosData;
    private List<Familiar> familiaresData;

    public List<Visita> calendarData;
    public List<Atividade> atividadesData;
    public List<UserCredentials> userCredentialsData;

    private int idLogado;

    private String departamentoLogado;

    public Data(){
        loadData();
    }

    public Server server;
    public Client client;
    public void setServer(Server server){
        this.server = server;
    }
    public void setClient(Client client){
        this.client = client;
    }
    public Server getServer(){
        return server;
    }
    public Client getClient(){
        return client;
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

    public Familiar isValidLoginFamiliar(String username, String password) {

        for (Familiar familiar : familiaresData) {
            if (familiar.getUsername().equals(username) && familiar.getPassword().equals(password)) {
                return familiar;
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
    public boolean usedNif(String Nif) {
        for (Residente residente1 : residentesData) {
            if (residente1.getNif().equals(Nif)) {
                return true;
            }
        }
        for (Funcionario funcionario : funcionariosData) {
            if (funcionario.getNif().equals(Nif)) {
                return true;
            }
        }
        for (Familiar familiar : familiaresData) {
            if (familiar.getNif().equals(Nif)) {
                return true;
            }
        }
        return false; // Retornar false se NIF não for usado
    }
    public boolean usedEmail(String email) {
        for (Residente residente1 : residentesData) {
            if (residente1.getEmail().equals(email)) {
                return true;
            }
        }
        for (Funcionario funcionario : funcionariosData) {
            if (funcionario.getEmail().equals(email)) {
                return true;
            }
        }
        for (Familiar familiar : familiaresData) {
            if (familiar.getEmail().equals(email)) {
                return true;
            }
        }
        return false; // Retornar false se email não for usado
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

    public List<Utensilio> getUtensilios() {
        return utensiliosData;
    }

    public void addUtensilio(Utensilio utensilio) {
        utensiliosData.add(utensilio);
        saveData();
    }

    public void removeUtensilio(int id) {
        Utensilio utensilioToRemove = getUtensilioPorId(id);
        if (utensilioToRemove != null) {
            utensiliosData.remove(utensilioToRemove);
        }
        saveData();
    }

    public Utensilio getUtensilioPorId(int id) {
        for (Utensilio utensilio : utensiliosData) {
            if (utensilio.getId() == id) {
                return utensilio;
            }
        }
        return null;
    }

    public List<Visita> getCalendarData() {
        return calendarData;
    }

    public Visita getVisitaPorId(String id){
        for (Visita Visita : calendarData) {
            if (Visita.getId().equals(id)) {
                return Visita;
            }
        }
        return null;

    }

    public void removeCalendarEvent(String id) {
        Visita visitaRemove = getVisitaPorId(id);
        if (visitaRemove != null) {
            calendarData.remove(visitaRemove);
        }
        System.out.println("Removido do ficheiro json");
        saveData();
    }
    public boolean addCalendarEvent(Entry calendarEvent) {
        Visita visita = new Visita(calendarEvent);
        //Verificar que ainda nao existe nenhuma no mesmo dia e hora
        for (Visita v:calendarData){
            if(v.getStartDate().equals(visita.getStartDate()) && v.getStartTime().equals(visita.getStartTime())){
                System.out.println("Ja existe uma visita nesse dia e hora\n");
                return false;
            }
        }
        System.out.println("Adicionado ao ficheiro json");
        saveData();
        calendarData.add(visita);
        return true;
    }

    public boolean updateCalendarEvent(Entry calendarEvent) {
        Visita visita = new Visita(calendarEvent);
        Visita visitaToUpdate = getVisitaPorId(visita.getId());
        if (visitaToUpdate != null) {
            for (Visita v:calendarData){
                if(v.getStartDate().equals(visita.getStartDate()) && v.getStartTime().equals(visita.getStartTime()) && !v.getId().equals(visita.getId())){
                    System.out.println("Ja existe uma visita nesse dia e hora\n");
                    return false;
                }
            }

            calendarData.remove(visitaToUpdate);
            calendarData.add(visita);
            System.out.println("Atualizado no ficheiro json");
            return true;
        }else{
            System.out.println("Ouve um erro ao dar update ao evento");
            return false;
        }
        
    }

    public List<Atividade> getAtividades() {
        return atividadesData;
    }




    public void addAtividade(Atividade atividade) {
        atividadesData.add(atividade);
        saveData();
    }

    public void removeAtividade(int id) {
        Atividade atividadeToRemove = getAtividadePorId(id);
        if (atividadeToRemove != null) {
            atividadesData.remove(atividadeToRemove);
        }
        saveData();
    }

    public Atividade getAtividadePorId(int id) {
        for (Atividade atividade : atividadesData) {
            if (atividade.getId() == id) {
                return atividade;
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
            FileReader readerUtensi = new FileReader(PATH_UTENSILIOS);
            FileReader readerVisitas = new FileReader(PATH_VISITAS);
            FileReader readerFamiliares = new FileReader(PATH_FAMILIARES);
            FileReader readerAtividades = new FileReader(PATH_ATIVIDADES);
            FileReader readerLoginCredentials = new FileReader(PATH_LOGIN);

            Type residenteListType = new TypeToken<List<Residente>>() {}.getType();
            Type funcionarioListType = new TypeToken<List<Funcionario>>() {}.getType();
            Type refeicaoListType = new TypeToken<List<Refeicao>>() {}.getType();

            Type medicacaoListType = new TypeToken<List<Medicacao>>() {}.getType();
            Type utensilioListType = new TypeToken<List<Utensilio>>() {}.getType();
            Type calendarListType = new TypeToken<List<Visita>>() {}.getType();

            Type familiarListType = new TypeToken<List<Familiar>>() {}.getType();
            Type atividadesListType = new TypeToken<List<Atividade>>() {}.getType();

            Type loginCredentialsListType = new TypeToken<List<UserCredentials>>() {}.getType();

            residentesData = gson.fromJson(readerResi, residenteListType);
            funcionariosData = gson.fromJson(readerFunc, funcionarioListType);
            refeicoesData = gson.fromJson(readerRefei, refeicaoListType);
            medicacoesData = gson.fromJson(readerMedi, medicacaoListType);
            utensiliosData = gson.fromJson(readerUtensi, utensilioListType);
            familiaresData = gson.fromJson(readerFamiliares, familiarListType);
            calendarData = gson.fromJson(readerVisitas, calendarListType);
            atividadesData = gson.fromJson(readerAtividades, atividadesListType);
            userCredentialsData = gson.fromJson(readerLoginCredentials, loginCredentialsListType);
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
            FileWriter writerUtensi = new FileWriter(PATH_UTENSILIOS);
            FileWriter writeVisitas = new FileWriter(PATH_VISITAS);
            FileWriter writerFamiliares = new FileWriter(PATH_FAMILIARES);
            FileWriter writerAtividades = new FileWriter(PATH_ATIVIDADES);
            FileWriter writerUserCredentials = new FileWriter(PATH_LOGIN);

            gson.toJson(residentesData, writerResi);
            gson.toJson(funcionariosData, writerFunc);
            gson.toJson(refeicoesData, writerRefei);
            gson.toJson(medicacoesData, writerMedi);
            gson.toJson(utensiliosData, writerUtensi);
            gson.toJson(calendarData, writeVisitas);
            gson.toJson(familiaresData, writerFamiliares);
            gson.toJson(atividadesData, writerAtividades);
            gson.toJson(userCredentialsData, writerUserCredentials);


            writerResi.close();
            writerFunc.close();
            writerRefei.close();
            writerMedi.close();
            writerUtensi.close();
            writeVisitas.close();
            writerFamiliares.close();
            writerAtividades.close();
            writerUserCredentials.close();
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

    public void setResidente(Residente residente){
        Residente residente1 = getResidentePorId(residente.getId());
        residente1.setPrefAli(residente.getPrefAli());
        residente1.setAlergias(residente.getAlergias());
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


    public int calcularProximoIdUtensilio() {
        int maiorId = utensiliosData.stream()
                .map(Utensilio::getId)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return maiorId + 1;
    }

    public int calcularProximoIdAtividades() {

        if (atividadesData == null) {
            atividadesData = new ArrayList<>(); // ou outra inicialização adequada
        }

        int maiorId = atividadesData.stream()
                .map(Atividade::getId)
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

    public String getDepartamentoLogado() {
        return departamentoLogado;
    }

    public void setDepartamentoLogado(String departamentoLogado) {
        this.departamentoLogado = departamentoLogado;
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }
    public void clearUtensilios() {
        utensiliosData.clear();
        saveData();
    }

    public void clearFuncionarios() {
        funcionariosData.clear();
        saveData();
    }

    public void clearCredentials() {
        userCredentialsData.clear();
        userCredentialsData.add(new UserCredentials("",""));
        saveData();
    }


    public void clearResidentes() {
        residentesData.clear();
        saveData();
    }

    public void clearRefeicoes() {
        refeicoesData.clear();
        saveData();
    }

    public void clearMedicacoes() {
        medicacoesData.clear();
        saveData();
    }

    public void clearAtividades() {
        atividadesData.clear();
        saveData();
    }

    public void setMedicacoesData(List<Medicacao> medicacoes) {
        medicacoesData = medicacoes;
    }

    public String getNomeFamiliar() {
        Funcionario funcionario = getFuncionarioPorId(idLogado);
        return funcionario.getNome();
    }
    public String getNifFamiliar() {
        Funcionario funcionario = getFuncionarioPorId(idLogado);
        return funcionario.getNif();
    }

    public Familiar getFamiliarPorId(int id) {
        for (Familiar familiar : familiaresData) {
            if (familiar.getId() == id) {
                return familiar;
            }
        }
        return null; // Retornar null se o familiar não for encontrado
    }

    public List<Familiar> getFamiliares() {
        return familiaresData;
    }

    public int getIdFamiliar() {
        return idLogado;
    }

    public UserCredentials loadCredentials() {
        loadData();
        if(userCredentialsData.get(0)!=null){
            return userCredentialsData.get(0);
        }else{
            return null;
        }

    }

    public void saveCredentials(UserCredentials userCredentials) {
        userCredentialsData.clear();
        userCredentialsData.add(userCredentials);
        saveData();
    }
}
