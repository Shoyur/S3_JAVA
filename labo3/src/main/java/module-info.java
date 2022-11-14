module labo3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens labo3 to javafx.fxml;
    exports labo3;
}
