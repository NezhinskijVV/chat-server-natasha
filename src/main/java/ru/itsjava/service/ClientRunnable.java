package ru.itsjava.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@AllArgsConstructor
public class ClientRunnable implements Runnable, Observer {
    private final Socket socket;
    private final Observable server;

    @SneakyThrows
    @Override
    public void run() {
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientMessage;
        while ((clientMessage = clientReader.readLine()) != null) {
            System.out.println(clientMessage);
            server.notifyObservers(clientMessage);
        }
    }

    @SneakyThrows
    @Override
    public void notify(String message) {
        PrintWriter clientWriter = new PrintWriter(socket.getOutputStream());
        clientWriter.println(message);
        clientWriter.flush();
    }
}
