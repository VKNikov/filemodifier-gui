package filemodifier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;

public class AboutController {

    @FXML
    private Button closeButton;

    public void handleCloseAction() {
        Window stage = closeButton.getScene().getWindow();
        stage.hide();
    }
}
