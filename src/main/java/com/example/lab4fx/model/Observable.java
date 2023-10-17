package com.example.lab4fx.model;

// наблюдатель
public interface Observable {
    void registerObserver(Observer o);

    void notifyObservers(Notification notification);
}
