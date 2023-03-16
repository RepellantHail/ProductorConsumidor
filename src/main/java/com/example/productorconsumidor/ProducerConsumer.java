package com.example.productorconsumidor;

import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import org.apache.commons.lang3.StringUtils;
import java.util.LinkedList;

public class ProducerConsumer {
    LinkedList<Integer> list = new LinkedList<>();
//    HelloController objectLabel = new HelloController();
     int capacity = 4;
     public static String strStateP = "Mimido..";
    public static String strStateC = "Mimido..";
    public static Color lblBG = Color.ROYALBLUE ;
    int time = 3000;
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
                while (list.size() == capacity) {
                    strStateP = "Descansando";
                    lblBG = Color.LIGHTGOLDENRODYELLOW;
                    wait();
                }
                System.out.println("Productor "+ nameCounter +" produced-"+ value);
                HelloController.strAlmacen += "*";
                strStateP = "Chambeando";
//                HelloController.updateLabel();
                //objectLabel.updateLabel();
                //HelloController.updateMessage();
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
                while (list.size() == 0) {
                    strStateC = "Descansando";
                    lblBG = Color.LIGHTGREEN;
                    wait();
                }
                int val = list.removeFirst();
                System.out.println("Consumidor "+ nameCounter +" consumido-" + val);
                HelloController.strAlmacen = StringUtils.chop(HelloController.strAlmacen);
                strStateC = "Chambeando";
                notify();
                Thread.sleep(time);
            }
        }
    }
}