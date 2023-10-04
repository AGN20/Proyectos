package U05E03;

import java.util.Scanner;
import U05E02.CartaBrisca;
import U05E02.BarajaESP;

public class Brisca {

	Scanner teclado = new Scanner(System.in);

	// declaramos los objetos
	BarajaESP mazo = new BarajaESP();
	JugadorBrisca humano = new JugadorBrisca();
	JugadorBrisca programa = new JugadorBrisca();
	int turno = 0;
	CartaBrisca muestra;

	// Creamos el constructo Carta brisca
	public Brisca() {

		// Barajamos el mazo
		mazo.baraja();

		// Llenamos las manos de cartas
		for (int i = 0; i < 3; i++) {
			humano.mano[i] = mazo.sacaCarta();
			programa.mano[i] = mazo.sacaCarta();
		}

		// Mostramos la ultima carta del mazo
		muestra = mazo.dimeCarta(0);

		// Decidimos el Turno de forma aleatoria
		if (turno == 0) {
			turno = (int) (Math.random() * 2) + 1;

			if (turno == 1) {
				System.out.println("Empieza el jugador");
			} else {
				System.out.println("Empieza el programa");
			}
			
			System.out.println(muestra.toString());
			
		}

		// mientras los jugadores tengan cartas en su mano hacemos...
		while (humano.mano[0] != null || humano.mano[1] != null | humano.mano[2] != null && programa.mano[0] != null
				|| programa.mano[1] != null || programa.mano[2] != null) {

			// Creamos do cartas
			CartaBrisca cHumano = null;
			CartaBrisca cPrograma = null;
			int eleccionH = 0;
			int eleccionP = 0;
			String hPalo = null;
			int hValor = 0;
			String pPalo = null;
			int pValor = 0;
			String mPalo = muestra.getPalo();
			int mValor = muestra.getValor();

			// Una jugada consta de dos turnos
			for (int i = 0; i < 2; i++) {
				// Si turno es 1, juega humano, elige que carta quiere jugar, y cede el turno al
				// rival
				if (turno == 1) {
					humano.misCartas();
					System.out.println("Elije que carta quieres tirar(1-3):");
					eleccionH = teclado.nextInt();
					cHumano = humano.mano[eleccionH - 1];
					humano.mano[eleccionH - 1] = null;
					hPalo = cHumano.getPalo();
					hValor = cHumano.getValor();
					turno = 2;
				} else {
					// Si turno es diferente de 1, juega programa, que elige una carta aleatoria de
					// su mano, y cede el turno al rival
					eleccionP = (int) (Math.random() * 3) + 1;
					cPrograma = programa.mano[eleccionP - 1];
					if (cPrograma == null) {
						for (int j = 2; j >= 0; j--) {
							if(cPrograma == null) {
								cPrograma = programa.mano[j];
							}
						}
					}
					System.out.println("El programa saca " + cPrograma);
					programa.mano[eleccionP - 1] = null;
					pPalo = cPrograma.getPalo();
					pValor = cPrograma.getValor();
					turno = 1;

				}
			}

			int[] valorBrisca = { 1, 3, 12, 11, 10, 7, 6, 5, 4, 2 };
			int posValorH = 0;
			int posValorP = 0;

			for (int i = 0; i < valorBrisca.length; i++) {
				if (valorBrisca[i] == hValor) {
					posValorH = i;
				}
				if (valorBrisca[i] == pValor) {
					posValorP = i;
				}
			}

			int ganador = 0;

			// Palos iguales, distinto valor, gana 1
			if (valorBrisca[posValorH] > valorBrisca[posValorP] && hPalo.equals(pPalo)) {
				ganador = 1;

				// Palos 1 igual a muestra y palo 2 no, gana 1
			} else if (hPalo.equals(mPalo) && !pPalo.equals(mPalo)) {
				ganador = 1;
				// Palos distintos y palo 2 no es muestra, gana 1
			} else if (!hPalo.equals(pPalo) && !pPalo.equals(mPalo)) {
				ganador = 1;
				// En cualqueir otro caso, gana 2
			} else {
				ganador = 2;
			}

			// El que gana suma los puntos de las cartas jugadas a su puntuación
			if (ganador == 1) {
				humano.puntos = humano.puntos + cHumano.getPuntos() + cPrograma.getPuntos();
			} else {
				programa.puntos = programa.puntos + cHumano.getPuntos() + cPrograma.getPuntos();
			}

			// El ganador saca carta primero el perdedor despues
			if (mazo.hayCarta() == true) {
				for (int j = 1; j <= 2; j++) {
					if (ganador == 1) {
						humano.mano[eleccionH - 1] = mazo.sacaCarta();
						ganador = 2;
					} else {
						programa.mano[eleccionP - 1] = mazo.sacaCarta();
						ganador = 1;
					}
				}
			}
		} // Fin mientras

		System.out.println("*****EL JUEGO A TERMINADO*****");
		if (humano.puntos > programa.puntos) {
			System.out.println("El ganador es el Jugador, con " + humano.puntos + " puntos");
		} else {
			System.out.println("El ganador es la Maquina, con " + programa.puntos + " puntos");
		}
	}

	public static void main(String[] args) {

		Brisca partida = new Brisca();

	}

}
