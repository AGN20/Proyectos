package Ejercicio2;

import java.util.Scanner;

public class MiCo {

	static int conb = 0;
	static int con2b = 0;
	public static final String TEXTOAZUL = "\u001B[34m", TEXTOROJO = "\u001B[31m", TEXTONEGRO = "\u001B[30m",
			TEXTOVERDE = "\u001B[32m", TEXTOMARRON = "\u001B[33m", TEXTOPURPURA = "\u001B[35m",
			TEXTOGRIS = "\u001B[37m", TEXTOCYAN = "\u001B[36m";

	public static final String TEXTOAZULCLARO = "\u001B[1;34m", TEXTOROJOCLARO = "\u001B[1;31m",
			TEXTONEGROCLARO = "\u001B[1;30m", TEXTOVERDECLARO = "\u001B[1;32m", TEXTOMARRONCLARO = "\u001B[1;33m",
			TEXTOPURPURACLARO = "\u001B[1;35m", TEXTOGRISCLARO = "\u001B[1;37m", TEXTOCYANCLARO = "\u001B[1;36m";

	public static final String FONDOAZUL = "\u001B[44m", FONDOROJO = "\u001B[41m", FONDONEGRO = "\u001B[40m",
			FONDOVERDE = "\u001B[42m", FONDOMARRON = "\u001B[43m", FONDOPURPURA = "\u001B[45m",
			FONDOGRIS = "\u001B[47m", FONDOCYAN = "\u001B[46m";

	public static final String RESET = "\u001B[0m", BORRARPANTALLA = "\033[2J";

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		System.out.println("Los colores oscuros disponibles son: " + TEXTOROJO + "rojo " + TEXTOAZUL + "azul "
				+ TEXTONEGRO + FONDOAZUL + "negro" + RESET + " " + TEXTOVERDE + "verde " + TEXTOMARRON + "marron "
				+ TEXTOPURPURA + "purpura " + TEXTOGRIS + "gris " + TEXTOCYAN + "cyan " + RESET);
		System.out.println("Los colores CLARO disponibles son: " + TEXTOROJOCLARO + "rojoclaro " + TEXTOAZULCLARO
				+ "azulclaro " + TEXTONEGROCLARO + FONDOAZUL + "negroclaro" + RESET + " " + TEXTOVERDECLARO
				+ "verdeclaro " + TEXTOMARRONCLARO + "marronclaro " + TEXTOPURPURACLARO + "purpuraclaro "
				+ TEXTOGRISCLARO + "grisclaro " + TEXTOCYANCLARO + "cyanclaro " + RESET);

		System.out.println("");
		System.out.println("¿De que color quieres que sea el texto? : ");
		String color = teclado.nextLine();
		System.out.println("¿De que color quieres que sea el fondo? : ");
		String fondo = teclado.nextLine();

		System.out.println("");
		System.out.println("¿En que fila quieres que se situe el texto? : ");
		int fila = teclado.nextInt();
		System.out.println("¿En que columna quieres que se situe el texto? : ");
		int col = teclado.nextInt();

		System.out.println("");
		cls();
		setColor(color, fondo);
		setCursor(fila, col);

		System.out.println("Este texto deberia aparecer modificado" + RESET);

		if (conb < 1) {

			System.out.println("El color de texto que has introducido no esta dentro de la paleta");

		}

		if (con2b < 1) {

			System.out.println("El color de fondo que has introducido no esta dentro de la paleta");

		}

	}

	public static void cls() {

		System.out.print("\033[2J");

	}

	public static void setColor(String texto, String fondo) {
		int con = 0, con2 = 0;

		if (texto.equalsIgnoreCase("rojo")) {
			System.out.println(TEXTOROJO);

			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("negro")) {
			System.out.println(TEXTONEGRO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("azul")) {
			System.out.println(TEXTOAZUL);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("verde")) {
			System.out.println(TEXTOVERDE);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("marron")) {
			System.out.println(TEXTOMARRON);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("purpura")) {
			System.out.println(TEXTOPURPURA);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("gris")) {
			System.out.println(TEXTOGRIS);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("cyan")) {
			System.out.println(TEXTOCYAN);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("rojoclaro")) {
			System.out.println(TEXTOROJO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("negroCLARO")) {
			System.out.println(TEXTONEGRO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("azulCLARO")) {
			System.out.println(TEXTOAZULCLARO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("verdeCLARO")) {
			System.out.println(TEXTOVERDECLARO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("marronCLARO")) {
			System.out.println(TEXTOMARRONCLARO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("purpuraCLARO")) {
			System.out.println(TEXTOPURPURACLARO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("grisCLARO")) {
			System.out.println(TEXTOGRISCLARO);
			con = +1;
		}
		;

		if (texto.equalsIgnoreCase("cyanCLARO")) {
			System.out.println(TEXTOCYANCLARO);
			con = +1;
		}
		;


		if (fondo.equalsIgnoreCase("rojo")) {
			System.out.println(FONDOROJO);
			con2 = +1;
		}
		;

		if (fondo.equalsIgnoreCase("negro")) {
			System.out.println(FONDONEGRO);
			con2 = +1;
		}
		;

		if (fondo.equalsIgnoreCase("azul")) {
			System.out.println(FONDOAZUL);
			con2 = +1;
		}
		;

		if (fondo.equalsIgnoreCase("verde")) {
			System.out.println(FONDOVERDE);
			con2 = +1;
		}
		;

		if (fondo.equalsIgnoreCase("marron")) {
			System.out.println(FONDOMARRON);
			con2 = +1;
		}
		;

		if (fondo.equalsIgnoreCase("purpura")) {
			System.out.println(FONDOPURPURA);
			con2 = +1;
		}
		;

		if (fondo.equalsIgnoreCase("gris")) {
			System.out.println(FONDOGRIS);
			con2 = +1;
		}
		;

		if (fondo.equalsIgnoreCase("cyan")) {
			System.out.println(FONDOCYAN);
			con2 = +1;
		}
		;

		conb = con;
		con2b = con2;
	}

	public static void setCursor(int fila, int col) {

		final String posicion = "\033[" + fila + ";" + col + "H";

		System.out.print(posicion);

	}

}
