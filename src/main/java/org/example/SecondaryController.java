package org.example;

import java.io.IOException;
import java.util.EventListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecondaryController {

    @FXML Button ok;
    @FXML ProgressIndicator tiempo;

    @FXML public void onClickOk(ActionEvent event) throws Exception {
        Button b = (Button) event.getSource();

        App.stagesecundari.close();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


}