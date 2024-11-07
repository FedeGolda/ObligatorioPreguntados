module com.mycompany.obligatoriopreguntados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    opens Modelo to com.fasterxml.jackson.databind; 
   
    opens com.mycompany.obligatoriopreguntados to javafx.fxml;
    exports com.mycompany.obligatoriopreguntados;
}
