package ru.itsjava.service;

import lombok.SneakyThrows;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements Server, Observable {
    public final static int PORT = 8081;
    private final List<Observer> observerList = new ArrayList<>();

    @SneakyThrows
    @Override
    public void start() {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("==Server starts==");

        while (true) {
            Socket socket = serverSocket.accept();

            if (socket != null) {
                System.out.println("==Client connected==");
                ClientRunnable clientRunnable = new ClientRunnable(socket, this);
                observerList.add(clientRunnable);
                new Thread(clientRunnable).start();
            }
        }

    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observerList) {
            observer.notify(message);
        }
    }
}
