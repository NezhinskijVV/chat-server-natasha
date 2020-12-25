package ru.itsjava.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itsjava.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@RequiredArgsConstructor
public class ClientRunnable implements Runnable, Observer {
    private final Socket socket;
    private final Observable server;
    private User user;

    @SneakyThrows
    @Override
    public void run() {
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientMessage;
        authorization(clientReader);
        while ((clientMessage = clientReader.readLine()) != null) {
            System.out.println(clientMessage);
            server.notifyObservers(user.getName() + ":" + clientMessage);
        }
    }

    @SneakyThrows
    public void authorization(BufferedReader clientReader) {
        String clientMessage = clientReader.readLine();
        //!auth!login:password
        if (clientMessage.startsWith("!auth!")) {
            String[] loginAndPass = clientMessage.substring(6).split(":");
            user = new User(loginAndPass[0], loginAndPass[1]);
            notify("Успешная авторизация");
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
