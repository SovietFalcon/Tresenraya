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
    boolean jugando = false;
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
    @FXML Label alerta_verde;

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

    public void resetearCasillas() {
        c11.setText("");c12.setText("");c13.setText("");
        c21.setText("");c22.setText("");c23.setText("");
        c31.setText("");c32.setText("");c33.setText("");
    }

    public void desactivarControles() {
        jugar.setDisable(true);
        pcvspc.setDisable(true);
        playervspc.setDisable(true);
        playervsplayer.setDisable(true);
        jugador1.setDisable(true);
        jugador2.setDisable(true);
    }


    @FXML public void comenzarPartida(ActionEvent event) {

        Button b = (Button) event.getSource();
        jugando = true;
        juego = new int[3][3];
        desactivarControles();

        if (modo == 3 || modo == 2 && jugador == 1) {
            resetearCasillas();
            jugador = 1;

            alerta_azul.setText("");
            alerta_roja.setText("");
            alerta_verde.setText("");
            getTurno();
        }
    }


    @FXML public void clickCuadricula(ActionEvent event) {

        Button b = (Button) event.getSource();
        int[] coord = {0,0};

        if (jugando) {  // comprobació de si el joc està en marxa
            coord = getCoord(b.getId());
            if (juego[coord[0]][coord[1]] == 0) {  // comprobació de si la casella està ocupada
                juego[coord[0]][coord[1]] = jugador;  //implementació a l'array
                alerta_roja.setText("");

                //pintar casella
                if (jugador == 1) {
                    b.setText("X");
                } else if (jugador == 2) {
                    b.setText("O");
                }

                if (comprobarGanador(juego) != 0) {
                    anunciarGanador(comprobarGanador(juego));
                } else {

                    cambiarTurno();
                    getTurno();
                }
                //TEST
                //System.out.println(coord[0] + " " + coord[1]);


            } else {
                alerta_roja.setText("¡Cuadrícula ocupada!");
            }

        }


    }

    public void getTurno() {
        alerta_azul.setText("Turno del jugador " + jugador);
    }

    public void cambiarTurno() {
        if (jugador == 1) {
            jugador = 2;
        } else if (jugador == 2) {
            jugador = 1;
        }
    }

    public int comprobarGanador(int[][] juego) {
        int ganador = 0;

        if (juego[0][0] != 0 && juego[0][0] == juego[0][1] && juego[0][1] == juego[0][2]) {
            ganador = juego[0][0];
            // [*][*][*]
            // [ ][ ][ ]
            // [ ][ ][ ]
        } else if (juego[0][0] != 0 && juego[0][0] == juego[1][0] && juego[1][0] == juego[2][0]) {
            ganador = juego[0][0];
            // [*][ ][ ]
            // [*][ ][ ]
            // [*][ ][ ]
        } else if (juego[2][0] != 0 && juego[2][0] == juego[2][1] && juego[2][1] == juego[2][2]) {
            ganador = juego[2][0];
            // [ ][ ][ ]
            // [ ][ ][ ]
            // [*][*][*]
        } else if (juego[2][2] != 0 && juego[2][2] == juego[1][2] && juego[1][2] == juego[0][2]) {
            ganador = juego[2][2];
            // [ ][ ][*]
            // [ ][ ][*]
            // [ ][ ][*]
        } else if (juego[0][1] != 0 && juego[0][1] == juego[1][1] && juego[1][1] == juego[2][1]) {
            ganador = juego[0][1];
            // [ ][*][ ]
            // [ ][*][ ]
            // [ ][*][ ]
        } else if (juego[1][0] != 0 && juego[1][0] == juego[1][1] && juego[1][1] == juego[1][2]) {
            ganador = juego[1][0];
            // [ ][ ][ ]
            // [*][*][*]
            // [ ][ ][ ]
        } else if (juego[0][0] != 0 && juego[0][0] == juego[1][1] && juego[1][1] == juego[2][2]) {
            ganador = juego[0][0];
            // [*][ ][ ]
            // [ ][*][ ]
            // [ ][ ][*]
        } else if (juego[0][2] != 0 && juego[0][2] == juego[1][1] && juego[1][1] == juego[2][0]) {
            ganador = juego[0][2];
            // [ ][ ][*]
            // [ ][*][ ]
            // [*][ ][ ]
        } else if (juego[0][0] != 0 && juego[0][1] != 0 && juego[0][2] != 0 &&
                    juego[1][0] != 0 && juego[1][1] != 0 && juego[1][2] != 0 &&
                    juego[2][0] != 0 && juego [2][1] != 0 && juego[2][2] != 0) {
            ganador = 3;

            // [*][*][*]
            // [*][*][*]
            // [*][*][*]
        }

        // return 0 if no hay ganador
        // return 1/2 if jugador 1/2 ha ganado
        // return 3 if empate
        return ganador;
    }

    public void anunciarGanador(int jugador) {

        jugando = false;
        alerta_azul.setText("");
        jugar.setDisable(false);
        pcvspc.setDisable(false);
        playervspc.setDisable(false);
        playervsplayer.setDisable(false);


        if (jugador != 3) {
            alerta_verde.setText("¡ HA GANADO EL JUGADOR " + jugador + " !");
        } else {
            alerta_verde.setText("¡ EMPATE !");
        }

    }

}
