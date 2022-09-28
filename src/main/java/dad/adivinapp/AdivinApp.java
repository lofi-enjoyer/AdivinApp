package dad.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class AdivinApp extends Application {

    private TextField numeroTextField;
    private Button comprobarButton;
    private Label inputLabel;
    private VBox root;

    private Alert successAlert;
    private Alert failAlert;
    private Alert errorAlert;

    private int number;
    private int count;

    @Override
    public void start(Stage stage) throws IOException {
        this.number = new Random().nextInt(100) + 1;
        System.out.println(number);

        this.numeroTextField = new TextField();
        numeroTextField.setPromptText("Introduce un número");

        this.comprobarButton = new Button("Comprobar");
        comprobarButton.setOnAction(this::onComprobarButton);
        comprobarButton.setDefaultButton(true);

        this.inputLabel = new Label();
        inputLabel.setText("Introduce un número del 1 al 100");

        this.successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("¡Has ganado!");

        this.failAlert = new Alert(Alert.AlertType.WARNING);
        failAlert.setTitle("¡Has fallado!");

        this.errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setContentText("El número introducido no es válido.");

        this.root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(inputLabel, numeroTextField, comprobarButton);
        root.setFillWidth(false);
        root.setSpacing(5);

        Scene scene = new Scene(root, 320, 200);

        stage.setTitle("AdivinApp");
        stage.setScene(scene);
        stage.show();
    }

    private void onComprobarButton(ActionEvent event) {
        int inputNumber = 0;
        try {
            inputNumber = Integer.parseInt(numeroTextField.getText());
        } catch (Exception e) {}

        numeroTextField.setText("");

        if (inputNumber < 1 || inputNumber > 100) {
            errorAlert.show();
            return;
        }
        count++;

        if (inputNumber == number) {
            successAlert.setContentText("Solo has necesitado " + count + " intentos.\n\nVuelve a jugar y hazlo mejor.");
            successAlert.show();

            count = 0;
            number = new Random().nextInt(100) + 1;
            System.out.println(number);
        } else {
            String compareResult = inputNumber > number ? "mayor" : "menor";
            failAlert.setContentText("El número a adivinar es " + compareResult + " que " + inputNumber + ".\n\nVUelve a intentarlo.");
            failAlert.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}