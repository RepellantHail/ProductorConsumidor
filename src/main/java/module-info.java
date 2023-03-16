module com.example.productorconsumidor {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;


    opens com.example.productorconsumidor to javafx.fxml;
    exports com.example.productorconsumidor;
}