package com.example.gps_g33.controller.familiares;

import com.example.gps_g33.controller.funcionarios.ComunicationServer;
import com.example.gps_g33.modelos.Data;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private Data data;

    public Client(Socket socket) {
        data = Data.getInstance();
        data.setClient(this);
        try {
            new Thread(() -> {
                try {
                    this.socket = socket;
                    this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Agora você pode chamar métodos ou fazer qualquer coisa que precise com o socket
                    // ...
                } catch (IOException e) {
                    System.out.println("Erro ao criar o servidor");
                    e.printStackTrace();
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Erro ao criar o servidor");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessageToServer(String messageToServer) {
        try {
            bufferedWriter.write(messageToServer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar mensagem para o server");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void receiveMessageFromServer(VBox vbox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        if (bufferedReader.ready()) {
                            String messageFromClient = bufferedReader.readLine();

                            if (messageFromClient.equals("Exiting...")) {
                                ComunicationServer.addLabel("Funcionário saiu da conversa\n Saindo do chat...", vbox);
                                sleep(3000);
                                closeEverything(socket, bufferedReader, bufferedWriter);
                                break;
                            }
                            ComunicationServer.addLabel(messageFromClient, vbox);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao receber mensagem do server");
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {

            if(bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null&&!socket.isClosed()){
                bufferedWriter.write("Exiting...");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
            //Platform.exit();
            //System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Socket getSocket() {
        return socket;
    }
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

}
