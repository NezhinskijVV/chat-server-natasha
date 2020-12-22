package ru.itsjava.service;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
//отсылка сообщений за исключением одного