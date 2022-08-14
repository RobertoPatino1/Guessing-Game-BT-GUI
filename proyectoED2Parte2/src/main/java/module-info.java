module com.mycompany.proyectoed2parte2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyectoed2parte2 to javafx.fxml;
    exports com.mycompany.proyectoed2parte2;
}
