package triqui;

import java.util.Scanner;

public class Triqui {
    private char[][] tablero;
    private char jugadorActual;
    

    public Triqui() {
        tablero = new char[3][3];
        jugadorActual = 'X'; // Empieza el jugador 'X'
        inicializarTablero();
    }

    // Inicializa el tablero con espacios en blanco
    public void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    // Imprime el tablero actual
    public void imprimirTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    // Realiza un movimiento
    public boolean realizarMovimiento(int fila, int columna) {
        if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ') {
            tablero[fila][columna] = jugadorActual;
            return true;
        } else {
            return false;
        }
    }

    // Cambia el jugador actual
    public void cambiarJugador() {
        if (jugadorActual == 'X') {
            jugadorActual = 'O';
        } else {
            jugadorActual = 'X';
        }
    }

    // Verifica si hay un ganador
    public boolean hayGanador() {
        return (verificarFilas() || verificarColumnas() || verificarDiagonales());
    }

    // Verifica las filas para encontrar un ganador
    private boolean verificarFilas() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Verifica las columnas para encontrar un ganador
    private boolean verificarColumnas() {
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] != ' ' && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) {
                return true;
            }
        }
        return false;
    }

    // Verifica las diagonales para encontrar un ganador
    private boolean verificarDiagonales() {
        return (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) ||
               (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Triqui triqui = new Triqui();
            int fila, columna;
            System.out.println("¡Bienvenido al juego de Triqui!");
            System.out.println("El tablero tiene las siguientes coordenadas:");
            System.out.println("0,0 | 0,1 | 0,2");
            System.out.println("-----+-----+-----");
            System.out.println("1,0 | 1,1 | 1,2");
            System.out.println("-----+-----+-----");
            System.out.println("2,0 | 2,1 | 2,2");
            System.out.println();
            while (!triqui.hayGanador()) {
                System.out.println("Turno del jugador " + triqui.jugadorActual);
                triqui.imprimirTablero();
                System.out.print("Ingresa la fila y la columna separadas por un espacio: ");
                fila = scanner.nextInt();
                columna = scanner.nextInt();
                
                if (triqui.realizarMovimiento(fila, columna)) {
                    if (triqui.hayGanador()) {
                        triqui.imprimirTablero();
                        System.out.println("¡El jugador " + triqui.jugadorActual + " ha ganado!");
                    } else {
                        triqui.cambiarJugador();
                    }
                } else {
                    System.out.println("Movimiento inválido. Inténtalo de nuevo.");
                }
            }
        }
    }
}
