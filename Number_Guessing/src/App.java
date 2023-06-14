import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class App extends Application {

    private int randomNumber;
    private int attempts;
    private int score;

    private Label promptLabel;
    private TextField guessTextField;
    private Button guessButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Guessing Game");

        promptLabel = new Label("Enter a number between 1 and 100:");
        guessTextField = new TextField();
        guessButton = new Button("Guess");

        guessButton.setOnAction(e -> handleGuessButton());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(promptLabel, guessTextField, guessButton);

        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        startNewGame();
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        score = 0;
    }

    private void handleGuessButton() {
        String guessText = guessTextField.getText();
        int guess;

        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid input! Please enter a valid number.");
            return;
        }

        attempts++;
        score += 100 - (attempts * 10);

        if (guess == randomNumber) {
            showInfoAlert("Congratulations! You guessed the number in " + attempts + " attempt(s).\nYour score is "
                    + score + ".");
            startNewGame();
        } else if (guess < randomNumber) {
            showInfoAlert("Too low! Try a higher number.");
        } else {
            showInfoAlert("Too high! Try a lower number.");
        }

        guessTextField.clear();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
