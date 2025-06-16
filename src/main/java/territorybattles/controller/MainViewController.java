package territorybattles.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainViewController {

    @FXML private TextField guildGPField;
    @FXML private TextField mustafarField;
    @FXML private TextField geonosisField;
    @FXML private TextField dathomirField;
    @FXML private TextField havenField;
    @FXML private TextField malachorField;
    @FXML private TextField deathstarField;
    @FXML private TextField coruscantField;
    @FXML private TextField braccaField;
    @FXML private TextField kashyyykField;
    @FXML private TextField lothalField;
    @FXML private TextField kafreneField;
    @FXML private TextField scarifField;
    @FXML private TextField corelliaField;
    @FXML private TextField feluciaField;
    @FXML private TextField tatooineField;
    @FXML private TextField kesselField;
    @FXML private TextField vandorField;
    @FXML private TextField hothField;

    // Call this method from your FXML on the Calculate button's onAction
    @FXML
    private void handleCalculate(ActionEvent event) {

        try {
            String guildGP = guildGPField.getText();
            if (guildGP == null || guildGP.trim().isEmpty()) {
                // Show alert if Guild GP is empty
                Window owner = ((Node) event.getSource()).getScene().getWindow();
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.initOwner(owner); // <-- This is critical!
                alert.setTitle("Missing Guild GP");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a value for Guild GP before submitting.");
                alert.showAndWait();
                return; // Stop further processing
            }
            // Check if guildGP is numeric
            try {
                Integer.parseInt(guildGP.trim());
            } catch (NumberFormatException ex) {
                Window owner = ((Node) event.getSource()).getScene().getWindow();
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.initOwner(owner);
                alert.setTitle("Invalid Guild GP");
                alert.setHeaderText(null);
                alert.setContentText("Guild GP must be a numeric value.");
                alert.showAndWait();
                return;
            }

            // Gather all planet fields into an array for easy checking
            TextField[] planetFields = {
                mustafarField, geonosisField, dathomirField, havenField, malachorField, deathstarField,
                coruscantField, braccaField, kashyyykField, lothalField, kafreneField, scarifField,
                corelliaField, feluciaField, tatooineField, kesselField, vandorField, hothField
            };
            String[] planetNames = {
                "Mustafar", "Geonosis", "Dathomir", "Haven", "Malachor", "Death Star",
                "Coruscant", "Bracca", "Kashyyyk", "Lothal", "Kafrene", "Scarif",
                "Corellia", "Felucia", "Tatooine", "Kessel", "Vandor", "Hoth"
            };

            for (int i = 0; i < planetFields.length; i++) {
                String value = planetFields[i].getText().trim();
                if (!value.isEmpty()) {
                    try {
                        int num = Integer.parseInt(value);
                        if (num < 0 || num > 6) {
                            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                            alert.setTitle("Invalid Value");
                            alert.setHeaderText(null);
                            alert.setContentText(planetNames[i] + " must be a number between 0 and 6.");
                            alert.showAndWait();
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        Window owner = ((Node) event.getSource()).getScene().getWindow();
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.initOwner(owner);
                        alert.setTitle("Invalid Value");
                        alert.setHeaderText(null);
                        alert.setContentText(planetNames[i] + " must be a number between 0 and 6.");
                        alert.showAndWait();
                        return;
                    }
                }
            }

            // Load results view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resultsView.fxml"));
            Parent resultsRoot = loader.load();
            ResultsViewController resultsController = loader.getController();
            resultsController.setResultsData(
                guildGP,
                mustafarField.getText(), geonosisField.getText(), dathomirField.getText(), havenField.getText(), malachorField.getText(), deathstarField.getText(),
                coruscantField.getText(), braccaField.getText(), kashyyykField.getText(), lothalField.getText(), kafreneField.getText(), scarifField.getText(),
                corelliaField.getText(), feluciaField.getText(), tatooineField.getText(), kesselField.getText(), vandorField.getText(), hothField.getText()
            );
            // Show the results view!
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(resultsRoot));
            stage.show();
        } catch (Exception e) {
        }
    }
}