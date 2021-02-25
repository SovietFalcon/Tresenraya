package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class PrimaryController {

    int modo = 0;
    int[][] juegovacio = new int[3][3];
    int[][] juego = new int[3][3];
    boolean jugando = true;
    int jugador = 1;


    //radiobuttons
    @FXML RadioButton pcvspc;
    @FXML RadioButton playervspc;
    @FXML RadioButton playervsplayer;

    @FXML RadioButton jugador1;
    @FXML RadioButton jugador2;

    //botones cuadriculas
    @FXML Button c11;
    @FXML Button c12;
    @FXML Button c13;

    @FXML Button c21;
    @FXML Button c22;
    @FXML Button c23;

    @FXML Button c31;
    @FXML Button c32;
    @FXML Button c33;

    //botones general
    @FXML Button jugar;

    //alertas
    @FXML Label alerta_azul;
    @FXML Label alerta_roja;

    //Conseguir coordenada
    public int[] getCoord(String cuadricula) {
        int[] coord = {0,0};
        switch (cuadricula) {
            case "c11":
                coord[0] = 0;
                coord[1] = 0;
                break;
            case "c12":
                coord[0] = 0;
                coord[1] = 1;
                break;
            case "c13":
                coord[0] = 0;
                coord[1] = 2;
                break;
////////////////////////////
            case "c21":
                coord[0] = 1;
                coord[1] = 0;
                break;
            case "c22":
                coord[0] = 1;
                coord[1] = 1;
                break;
            case "c23":
                coord[0] = 1;
                coord[1] = 2;
                break;
////////////////////////////
            case "c31":
                coord[0] = 2;
                coord[1] = 0;
                break;
            case "c32":
                coord[0] = 2;
                coord[1] = 1;
                break;
            case "c33":
                coord[0] = 2;
                coord[1] = 2;
                break;

        }

        return coord;
    }

    @FXML public void pcVsPc(ActionEvent event) {
        modo = 1;
        System.out.println(modo);

        jugar.setDisable(false);
        jugador1.setDisable(true);
        jugador2.setDisable(true);
    }

    @FXML public void playerVsPc(ActionEvent event) {
        modo = 2;
        System.out.println(modo);

        jugador1.setDisable(false);
        jugador2.setDisable(false);
    }

    @FXML public void setJugador1(ActionEvent event) {
        jugador = 1;
        jugar.setDisable(false);
    }

    @FXML public void setJugador2(ActionEvent event) {
        jugador = 2;
        jugar.setDisable(false);
    }

    @FXML public void playerVsPlayer(ActionEvent event) {
        modo = 3;
        System.out.println(modo);

        jugar.setDisable(false);
        jugador1.setDisable(true);
        jugador2.setDisable(true);
    }

    @FXML public void comenzarPartida(ActionEvent event) {

        Button b = (Button) event.getSource();
        jugando = true;
        juego = juegovacio;

        if (modo == 3 || modo == 2 && jugador == 1) {
            c11.setText("");c12.setText("");c13.setText("");
            c21.setText("");c22.setText("");c23.setText("");
            c31.setText("");c32.setText("");c33.setText("");
            jugar.setDisable(true);
        }
    }


    @FXML public void clickCuadricula(ActionEvent event) {

        Button b = (Button) event.getSource();
        int[] coord = {0,0};

        if (jugando) {  // comprobación de si el juego está en marcha
            coord = getCoord(b.getId());
            if (juego[coord[0]][coord[1]] == 0) {  // comprobación de si la casilla está ocupada
                juego[coord[0]][coord[1]] = jugador;
                System.out.println(coord[0] + " " + coord[1]);
            } else {
                System.out.println("OCUPADA");
            }

        }


    }
}
