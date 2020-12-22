package ru.itsjava;

import ru.itsjava.service.ServerImpl;

public class Application {

    public static void main(String[] args) {
        new ServerImpl().start();
    }
}
//1. Паттерн Одиночка ( Singleton) + реализация для JDBC
//2. Паттерн Наблюдатель ( Observable)
//3. Переписать проект + скинуть ссылки на него

//4*. Табичка по порождающим паттернам
// 1 Зачем нужны? 2 Пример из стандартной библиотеки 3. Идея реализации