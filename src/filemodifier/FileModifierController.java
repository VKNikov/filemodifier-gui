package filemodifier;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class FileModifierController {

    @FXML
    private TextField filePath;
    @FXML
    private TextField fileName;
    @FXML
    private TextField regexPattern;
    @FXML
    private TextField replacementText;

    public void handleRunAction() {
        if (isAllInformationPresent()) {
            FileModifier.modifyFiles(filePath.getText().trim(), fileName.getText().trim(), regexPattern.getText().trim(), replacementText.getText().trim());
        } else {
            setWarningMessages();
        }
    }

    private void setWarningMessages() {
        if (filePath.getText().equals("")) {
            filePath.setText("Please enter a valid path!");
            filePath.setStyle("-fx-text-fill: red");
        }
        if (fileName.getText().equals("")) {
            fileName.setText("Please enter a file name to look for!");
            fileName.setStyle("-fx-text-fill: red");
        }
        if (regexPattern.getText().equals("")) {
            regexPattern.setText("Please enter a text pattern to look for!");
            regexPattern.setStyle("-fx-text-fill: red");
        }
        if (replacementText.getText().equals("")) {
            replacementText.setText("Pleace enter a replacement text!");
            replacementText.setStyle("-fx-text-fill: red");
        }
    }

    private boolean isAllInformationPresent() {
        return !filePath.getText().equals("") && !fileName.getText().equals("") && !regexPattern.getText().equals("") && !replacementText.getText().equals("");
    }

    public void handleQuitAction(ActionEvent event) {
        Platform.exit();
    }

    public void handlePickPathAction(ActionEvent event) {
        Stage fileOpener = new Stage();
        fileOpener.setTitle("Choose path to file");
        fileOpener.initModality(Modality.APPLICATION_MODAL);

        Pane layout = new VBox();
        fileOpener.setScene(new Scene(layout));

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select folder to open...");
        File file = directoryChooser.showDialog(fileOpener);
        if (file != null) {
            filePath.setText(file.getAbsolutePath());
        }
    }

    public void resetTextStyle(MouseEvent event) {
        if (fileName.getStyle().equals("-fx-text-fill: red")) {
            removeTextFiieldStyling();
        }
    }

    private void removeTextFiieldStyling() {
        fileName.setStyle(null);
        fileName.clear();
        filePath.setStyle(null);
        filePath.clear();
        regexPattern.setStyle(null);
        regexPattern.clear();
        replacementText.setStyle(null);
        replacementText.clear();
    }
}
