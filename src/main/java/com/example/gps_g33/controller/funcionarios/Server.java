package com.example.gps_g33.controller.funcionarios;

import com.example.gps_g33.modelos.Data;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private Data data;

    private void initializeSocket(int port) {

        try {
            this.serverSocket = new ServerSocket(port);
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Erro ao criar o servidor");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    public Server(int port) {
        data = Data.getInstance();
        data.setServer(this);
        new Thread(() -> {
            initializeSocket(port);
        }).start();
    }

    public void sendMessageToClient(String messageToClient) {
        try {
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar mensagem para o cliente");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void receiveMessageFromClient(VBox vbox) {
        //espera ate que algum cliente se conecte
        while (socket == null) {
            try {
                Thread.sleep(1000);
                System.out.println("Aguardando que o cliente se conecte...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket != null && socket.isConnected()) {
                    try {
                        if (bufferedReader.ready()) {
                            String messageFromClient = bufferedReader.readLine();
                            ComunicationServer.addLabel(messageFromClient, vbox);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao receber mensagem do cliente");
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
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
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
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
