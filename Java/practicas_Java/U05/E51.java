package U05E05;

import java.util.Scanner;

public class E51 {

	static Scanner teclado = new Scanner(System.in);
	static int cuentaEjecuciones = 0;
	final static char PIEZAMAX = 'X';
	final static char PIEZAMIN = 'O';
	final static char ESTADOMAX = 'X';
	final static char ESTADOMIN = 'O';
	static char partida = 'J'; // 'E'(empate) 'M'(gana MAX) 'm'(gana MIN) 'J'(en Juego)

	public static void main(String[] args) {
		char[][] t = tableroNuevo();
		char turno;
		// Sortear el turno
		System.out.println("\n\nSorteando quien sale primero...");
		double suerte = Math.random();
		System.out.println(tableroToString(t));
		if (suerte < 0.5) {
			System.out.println("\nSalgo yo, jeje... Mis piezas " + PIEZAMAX + "\n");
			turno = ESTADOMAX;
		} else {
			System.out.println("\nTiene suerte... Usted sale con " + PIEZAMIN + "\n");
			turno = ESTADOMIN;
		}
		String disponibles; // Movimientos disponibles
		while (partida == 'J') {
			disponibles = jugadasDisponibles(t);
			if (disponibles.length() == 0) // Nadie gana y ya no se puede mover
				partida = 'E';
			else {
				if (turno == ESTADOMAX) {
					Jugada3enRaya j = valorMinMax(t, ESTADOMAX);
					System.out.println("Muevo a la casilla " + j.j + "\n");
					t[j.j / 3][j.j % 3] = PIEZAMAX;
					if (ganaMAX(t))
						partida = 'M';
					else
						turno = ESTADOMIN; // El siguiente turno es de MIN
				} else {
					int mov;
					do {
						System.out.print("\nPuede mover a casillas (" + disponibles + "). Elija una: ");
						mov = teclado.nextInt();
					} while (disponibles.indexOf(Integer.toString(mov)) < 0);
					t[mov / 3][mov % 3] = PIEZAMIN;
					if (ganaMIN(t))
						partida = 'm';
					else
						turno = ESTADOMAX;
				}
				System.out.println(tableroToString(t));
			}
		} // while
		if (partida == 'E')
			System.out.println("Bien jugado, hemos empatado.");
		else if (partida == 'M')
			System.out.println("Gano yo. Debe mejorar...");
		else
			System.out.println("Enhorabuena, ha ganado!!.");
		} // main
	
		static public char[][] tableroNuevo() {
			char[][] t = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
			return t;
		}
	
		static public String tableroToString(char[][] t) {
			String s = "\n POSICIONES ACTUALMENTE\n" + " +---+---+---+ +---+---+---+\n" + " | 0 | 1 | 2 | | " + t[0][0]
					+ " | " + t[0][1] + " | " + t[0][2] + " |\n" + " +---+---+---+ +---+---+---+\n" + " | 3 | 4 | 5 | | "
					+ t[1][0] + " | " + t[1][1] + " | " + t[1][2] + " |\n" + " +---+---+---+ +---+---+---+\n"
					+ " | 6 | 7 | 8 | | " + t[2][0] + " | " + t[2][1] + " | " + t[2][2] + " |\n"
					+ " +---+---+---+ +---+---+---+\n";
			return s;
		}
	
		// Genera una cadena con las posiciones donde se pueden poner fichas
		static public String jugadasDisponibles(char[][] t) {
			String s = "";
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (t[i][j] == ' ')
						s += (i * 3 + j);
			return s;
		}
	
		// SI estado == MAX -> return jugada con la máxima utilidad
		// SI estado == MIN -> return jugada con la mínima utilidad
		static Jugada3enRaya valorMinMax(char[][] t, char estado) {
			// ¿terminal? gana alguno/no hay movimientos
			// Comprobar a cuantas posiciones puede mover
			String movimientos = jugadasDisponibles(t);
			Jugada3enRaya j = new Jugada3enRaya();
			j.utilidad = utilidad(t);
			if (movimientos.length() == 0 || j.utilidad == 1 || j.utilidad == -1)
				return j;
			// Realizar cada movimiento posible y comprobar su utilidad
			if (estado == ESTADOMAX) { // juega MAX
				Jugada3enRaya mejor = new Jugada3enRaya();
				mejor.utilidad = -1;
				char[][] copia;
				for (int m = 0; m < movimientos.length(); m++) {
					copia = tableroClona(t);
					int mov = movimientos.charAt(m) - '0';
					copia[mov / 3][mov % 3] = PIEZAMAX; // Hacer el movimiento en la copia
					Jugada3enRaya temp = valorMinMax(copia, ESTADOMIN);
					if (temp.utilidad > mejor.utilidad) {
						mejor.j = mov;
						mejor.utilidad = temp.utilidad;
					}
				}
				return mejor;
			} else { // Estoy en estado MIN
				Jugada3enRaya peor = new Jugada3enRaya();
				peor.utilidad = 1000;
				char[][] copia;
				for (int m = 0; m < movimientos.length(); m++) {
					copia = tableroClona(t);
					int mov = movimientos.charAt(m) - '0';
					copia[mov / 3][mov % 3] = PIEZAMIN; // Hacer el movimiento en la copia
					Jugada3enRaya temp = valorMinMax(copia, ESTADOMAX /* , profundidad */);
					if (temp.utilidad < peor.utilidad) {
						peor.j = mov;
						peor.utilidad = temp.utilidad;
					}
				} // for
				return peor;
			} // else
		}
	
			// Hace una copia del tablero
			static char[][] tableroClona(char[][] t) {
				char[][] n = new char[3][3];
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++)
						n[i][j] = t[i][j];
				return n;
			}
	
			// Devuelve true si gana MAX o false en otro caso
			static boolean ganaMAX(char[][] t) {
			return t[0][0] == PIEZAMAX && t[0][1] == PIEZAMAX && t[0][2] == PIEZAMAX || //Hor
			t[1][0] == PIEZAMAX && t[1][1] == PIEZAMAX && t[1][2] == PIEZAMAX ||
			t[2][0] == PIEZAMAX && t[2][1] == PIEZAMAX && t[2][2] == PIEZAMAX ||
			t[0][0] == PIEZAMAX && t[1][0] == PIEZAMAX && t[2][0] == PIEZAMAX || //Ver
			t[0][1] == PIEZAMAX && t[1][1] == PIEZAMAX && t[2][1] == PIEZAMAX ||
			t[0][2] == PIEZAMAX && t[1][2] == PIEZAMAX && t[2][2] == PIEZAMAX ||
			t[0][0] == PIEZAMAX && t[1][1] == PIEZAMAX && t[2][2] == PIEZAMAX || //Diag
			t[2][0] == PIEZAMAX && t[1][1] == PIEZAMAX && t[0][2] == PIEZAMAX;
			}
			// Devuelve true si gana MIN o false en otro caso
			static boolean ganaMIN(char[][] t) {
			return t[0][0] == PIEZAMIN && t[0][1] == PIEZAMIN && t[0][2] == PIEZAMIN || //Hor
			t[1][0] == PIEZAMIN && t[1][1] == PIEZAMIN && t[1][2] == PIEZAMIN ||
			t[2][0] == PIEZAMIN && t[2][1] == PIEZAMIN && t[2][2] == PIEZAMIN ||
			t[0][0] == PIEZAMIN && t[1][0] == PIEZAMIN && t[2][0] == PIEZAMIN || //Ver
			t[0][1] == PIEZAMIN && t[1][1] == PIEZAMIN && t[2][1] == PIEZAMIN ||
			t[0][2] == PIEZAMIN && t[1][2] == PIEZAMIN && t[2][2] == PIEZAMIN ||
			t[0][0] == PIEZAMIN && t[1][1] == PIEZAMIN && t[2][2] == PIEZAMIN || //Dia
			t[2][0] == PIEZAMIN && t[1][1] == PIEZAMIN && t[0][2] == PIEZAMIN;
			}
			// Puntúa tablero y mira lo interesante que es para MAX:-1 pierde,1 gana,0 empate
			static double utilidad( char[][] t ) {
				if( ganaMAX(t) ) return 1;
				if( ganaMIN(t) ) return -1;
				return 0;
			}
	} // clase

	class Jugada3enRaya {
	int j; // Jugada: donde colocar la pieza fila = j / 3 y columna j % 3
	double utilidad; // Utilidad para MAX
}
	
	
