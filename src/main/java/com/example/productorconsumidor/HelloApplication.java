package com.example.productorconsumidor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.io.IOException;

public class HelloApplication extends Application {
    final static ProducerConsumer pc = new ProducerConsumer();

    @Override
    public void start(Stage stage) throws Exception {

        //End Of image View
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Productor Consumidor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public static void startPrograma(){
        System.out.println("Starting Process...");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try { iniciarHilos();}
                catch (InterruptedException e) { throw new RuntimeException(e); }
            }
        };
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        //Se pasa el Hilo a funcion detener
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public static void iniciarHilos() throws InterruptedException {
        // Create producer thread
        Thread productor = new Thread(new Runnable() {
            @Override
            public void run() {
                try { pc.produce();}
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        // Create consumer thread
        Thread  consumidor = new Thread(new Runnable() {
            @Override
            public void run() {
                try { pc.consume(); }
                catch (InterruptedException e) {  e.printStackTrace();}
            }
        });
        // Start both threads
        productor.start();  consumidor.start();
        // t1 finishes before t2
        productor.join();  consumidor.join();
    }

    public static void modificarCap(int cap){
        pc.setCapacity(cap);
    }

    public static void modificarTiempo(int time){
        pc.setTime(time);
    }

}