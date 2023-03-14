module com.example.productorconsumidor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.productorconsumidor to javafx.fxml;
    exports com.example.productorconsumidor;
}