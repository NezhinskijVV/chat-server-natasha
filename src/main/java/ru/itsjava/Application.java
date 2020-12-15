package ru.itsjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
    public final static int PORT = 8081;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("==Server starts==");

        while (true) {
            Socket socket = serverSocket.accept();

            if (socket != null) {
                System.out.println("==Client connected==");

                BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String input;
                while ((input = clientReader.readLine()) != null) {
                    System.out.println(input);
                }
            }
        }


    }
}
//1. Паттерн Одиночка ( Singleton) + реализация для JDBC
//2. Паттерн Наблюдатель ( Observable)
//3. Переписать проект + скинуть ссылки на него

//4*. Табичка по порождающим паттернам
// 1 Зачем нужны? 2 Пример из стандартной библиотеки 3. Идея реализации