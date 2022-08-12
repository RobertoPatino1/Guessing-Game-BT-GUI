module com.mycompany.proyectoed2parte2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyectoed2parte2 to javafx.fxml;
    exports com.mycompany.proyectoed2parte2;
}
