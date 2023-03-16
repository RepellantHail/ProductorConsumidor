package com.example.productorconsumidor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;

public class HelloController {
    public static String strAlmacen="";
    @FXML
    Image imageAlmacen = new Image(getClass().getResourceAsStream("herrero.png"));
    ImageView imageViewAlmacen = new ImageView(imageAlmacen);
    @FXML
    protected TextField tfCapacity;
    public ImageView imVw0;
    @FXML
    public  Label lblAlmacen = new Label(strAlmacen);

    @FXML
    protected TextField tfTiempo;




    @FXML
    public void onInicioButtonClick() {
        System.out.println("Iniciando Programa...");
        lblAlmacen.setText("");

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lblAlmacen.setText(strAlmacen);
                        }
                    });
                    Thread.sleep(10);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        HelloApplication.startPrograma();
    }

    @FXML
    public void onStopButtonClick(){
        System.out.println("Detener Hilos...");
        //imVw0.setVisible(false);
        imageViewAlmacen.setFitHeight(30);
        imageViewAlmacen.setFitWidth(30);
//        lblAlmacen.setGraphic(imageViewAlmacen);
        lblAlmacen.setText("Almacen Vacio");
       // lblAlmacen.setText(lblAlmacen.getText()+"*");
        //lblAlmacen.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/home/jearim/IdeaProjects/ProductorConsumidor/src/main/resources/com/example/productorconsumidor/herrero.png"))));

    }

    public void capacidadEntered(){
        System.out.println("Modificando Capacidad...");
        HelloApplication.modificarCap(Integer.parseInt(tfCapacity.getText()));
    }

    public void tiempoEntered(){
        System.out.println("Modificando Tiempo...");
        HelloApplication.modificarTiempo(Integer.parseInt(tfTiempo.getText()));
    }

    public  void updateLabel(){
        System.out.println("Update Label Message");
        lblAlmacen.setBackground(Background.fill(Color.BLACK));
        lblAlmacen.setText(strAlmacen);
    }


}