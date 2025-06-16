package territorybattles.controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import territorybattles.logic.TBCalculator;

public class ResultsViewController {

    @FXML private TextArea phase1Results;
    @FXML private TextArea phase2Results;
    @FXML private TextArea phase3Results;
    @FXML private TextArea phase4Results;
    @FXML private TextArea phase5Results;
    @FXML private TextArea phase6Results;
    @FXML private Label finalStarCountLabel;
    @FXML private Label guildGPLabel;

    // Call this from MainViewController after loading the FXML
    public void setResultsData(
            String guildGP,
            String mustafar, String geonosis, String dathomir, String haven, String malachor, String deathstar,
            String coruscant, String bracca, String kashyyyk, String lothal, String kafrene, String scarif,
            String corellia, String felucia, String tatooine, String kessel, String vandor, String hoth
    ) {
        try {
            int[] platoons = {
                parseInt(mustafar), parseInt(geonosis), parseInt(dathomir), parseInt(haven), parseInt(malachor), parseInt(deathstar),
                parseInt(corellia), parseInt(felucia), parseInt(tatooine), parseInt(kessel), parseInt(vandor), parseInt(hoth),
                parseInt(coruscant), parseInt(bracca), parseInt(kashyyyk), parseInt(lothal), parseInt(kafrene), parseInt(scarif)
            };
            int gp = parseInt(guildGP);

            List<String> results = TBCalculator.calculatePhases(gp, platoons);
            
            // Set phase results as before, but skip the last element
            phase1Results.setText(!results.isEmpty() ? results.get(0) : "");
            phase2Results.setText(results.size() > 1 ? results.get(1) : "");
            phase3Results.setText(results.size() > 2 ? results.get(2) : "");
            phase4Results.setText(results.size() > 3 ? results.get(3) : "");
            phase5Results.setText(results.size() > 4 ? results.get(4) : "");
            phase6Results.setText(results.size() > 5 ? results.get(5) : "");

            // Get the final star count from the last element
            String last = results.get(results.size() - 1);
            int finalStarCount = 0;
            if (last.startsWith("FINAL_STAR_COUNT:")) {
                finalStarCount = Integer.parseInt(last.substring("FINAL_STAR_COUNT:".length()));
            }
            finalStarCountLabel.setText("Final Star Count: " + finalStarCount);
            guildGPLabel.setText("Guild GP: " + guildGP); // Set the Guild GP label
        } catch (Exception e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Exception in setResultsData");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    public static ResultsViewController loadResultsView(String guildGP,
            String mustafar, String geonosis, String dathomir, String haven, String malachor, String deathstar,
            String coruscant, String bracca, String kashyyyk, String lothal, String kafrene, String scarif,
            String corellia, String felucia, String tatooine, String kessel, String vandor, String hoth) throws IOException {
        FXMLLoader loader = new FXMLLoader(ResultsViewController.class.getResource("/resultsView.fxml"));
        Parent resultsRoot = loader.load();
        ResultsViewController resultsController = loader.getController();
        resultsController.setResultsData(
            guildGP, mustafar, geonosis, dathomir, haven, malachor, deathstar,
            coruscant, bracca, kashyyyk, lothal, kafrene, scarif,
            corellia, felucia, tatooine, kessel, vandor, hoth
        );
        return resultsController;
    }

    // Helper method to safely parse integers from strings
    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent mainRoot = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(mainRoot));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit() {
        javafx.application.Platform.exit();
    }

    // Placeholder for the actual final star count calculation logic
    private int calculateFinalStarCount(int[] platoons) {
        // Implement your logic to calculate the final star count based on the platoons
        return 0;
    }
}
