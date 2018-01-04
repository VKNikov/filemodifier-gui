package filemodifier;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    private Stage fileOpener;

    public void handleRunAction() {
        if (isAllInformationPresent()) {
            FileModifier.modifyFiles(filePath.getText(), fileName.getText(), regexPattern.getText(), replacementText.getText());
        }
    }

    private boolean isAllInformationPresent() {
        return filePath.getText() != null && fileName.getText() != null && regexPattern.getText() != null && replacementText.getText() != null;
    }

    public void handleQuitAction(ActionEvent event) {
        Platform.exit();
    }

    public void handlePickPathAction(ActionEvent event) {
        fileOpener= new Stage();
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
}
