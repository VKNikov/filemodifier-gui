package filemodifier;

import com.filemodifier.FileModifier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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
            FileModifier fileModifier = new FileModifier();
            try {
                fileModifier.execute(filePath.getText().trim(), fileName.getText().trim(), regexPattern.getText().trim(), replacementText.getText().trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            replacementText.setText("Please enter a replacement text!");
            replacementText.setStyle("-fx-text-fill: red");
        }
    }

    private boolean isAllInformationPresent() {
        return !filePath.getText().equals("") && !fileName.getText().equals("") && !regexPattern.getText().equals("") && !replacementText.getText().equals("");
    }

    public void handleQuitAction() {
        Platform.exit();
    }

    public void handlePickPathAction() {
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

    public void resetTextStyle() {
        if (fileName.getStyle().equals("-fx-text-fill: red")) {
            removeTextFieldStyling();
        }
    }

    private void removeTextFieldStyling() {
        fileName.setStyle(null);
        fileName.clear();
        filePath.setStyle(null);
        filePath.clear();
        regexPattern.setStyle(null);
        regexPattern.clear();
        replacementText.setStyle(null);
        replacementText.clear();
    }

    public void handleAboutAction() throws IOException {
        Stage aboutWindow = new Stage();
        aboutWindow.setTitle("About FileModifier");
        Parent layout = FXMLLoader.load(getClass().getResource("about.fxml"));

        aboutWindow.setScene(new Scene(layout));
        aboutWindow.show();
    }
}
