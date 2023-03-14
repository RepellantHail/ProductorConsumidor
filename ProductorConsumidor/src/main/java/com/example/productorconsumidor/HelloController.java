package com.example.productorconsumidor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {

    final ProducerConsumer pc = new ProducerConsumer();
    @FXML
    private Button btnInicio;
    private Button btnStop;
    @FXML
    private ImageView imgMiner;

    @FXML
    public void onInicioButtonClick() throws InterruptedException {
        System.out.println("Iniciando...");

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try { pc.produce();}
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try { pc.consume(); }
                catch (InterruptedException e) {  e.printStackTrace();}
            }
        });
        // Start both threads
        t1.start();  t2.start();
        // t1 finishes before t2
        t1.join();  t2.join();
    }

    @FXML
    public void onStopButtonClick(){
        System.out.println("Detener Hilos...");
    }
}