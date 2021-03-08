package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Gameslist {

    @FXML GridPane gridgames;
    @FXML Button ok;
    @FXML Button limpiar;
    int partidas = 0;

    public void addToGrid() {
        /*
        gridgames.add(new Label("test"), 0, 1, 1, 1);
        gridgames.add(new Label("test"), 1, 1, 1, 1);
        gridgames.add(new Label("test"), 2, 1, 1, 1);

         */
    }


    @FXML public void clickOk(ActionEvent event) throws Exception {
        Button b = (Button) event.getSource();

        App.stagegames.close();
    }

    @FXML public void clickLimpiar(ActionEvent event) throws Exception {
        Button b = (Button) event.getSource();

        addToGrid();
    }

}
