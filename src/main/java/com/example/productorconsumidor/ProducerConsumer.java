package com.example.productorconsumidor;

import javafx.concurrent.Task;
import java.util.LinkedList;

public class ProducerConsumer {
    LinkedList<Integer> list = new LinkedList<>();
     int capacity = 2;
    int time = 300;
    int nameCounter = 0;

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int _capacity){
        this.capacity = _capacity;
    }

    public int getTime(){
        return time;
    }
    public void setTime(int _time){
        time = _time;
    }

    protected void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (list.size() == capacity)
                    wait();
                System.out.println("Productor "+ nameCounter +" produced-"+ value);
                list.add(value++);
                notify();
                Thread.sleep(time);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (list.size() == 0)
                    wait();
                int val = list.removeFirst();
                System.out.println("Consumidor "+ nameCounter +" consumido-" + val);
                notify();
                Thread.sleep(time);
            }
        }
    }
}