package ejercicios;

import java.util.Scanner;

public class U3EP01 {

	public static void main(String[]args) {
		
		// Creamos el menu:

		Scanner teclado = new Scanner(System.in);
		int menu;
		String alfabeto = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		
		
		do {
		
			System.out.println("	CRIPTOGRAFIA CESAR	 ");
			System.out.println("");
			System.out.println("1. configurar.");
			System.out.println("2. Encriptar.");
			System.out.println("3. Desencriptar.");
			System.out.println("4. Salir");
			System.out.println("");
			System.out.println("Escoja una opción (1-4): ");
			menu = teclado.nextInt();
			System.out.println("\n");
			
	
			if (menu <= 4 && menu >= 1) {
				
				switch(menu) {
				case 1:
					
					int moduloAct = alfabeto.length();
					String espera;
					boolean noHayRepetidos = false;
					
					System.out.println("	CRIPTOGRAFIA CESAR-CONFIGURACION	 ");
					System.out.println("");
					System.out.println("Alfabeto actual: " + alfabeto);
					System.out.println("Módulo actual: " + moduloAct);
					System.out.println("");
					
					do {
						
						String alfabeto2;
						System.out.println(" Nuevo alfabeto (enter para no cambiar):");
						espera = teclado.nextLine();
						alfabeto2 = teclado.nextLine();
					
						if(alfabeto2.equals("")) {
							
							noHayRepetidos = true;
							
						}else {
							
							int longitud = alfabeto2.length();
							int i = 0;
							while( i < longitud || noHayRepetidos == false) {
								char caracter = alfabeto2.charAt(i);
								int comparar = alfabeto2.indexOf(caracter);
								if (comparar == i ) {
									alfabeto = alfabeto2;
									noHayRepetidos = true;
								}else {
									System.out.println("Tienes letras repetidas.");
									noHayRepetidos = false;
								}
								
								i++;
								
							}
							
							System.out.println("Módulo actual: " + longitud);
							
						}
						
					} while(noHayRepetidos == false);
				
					System.out.println("\n   Pulse ENTER para volver al menú:");
					espera = teclado.nextLine();
					
					break;
					
				case 2:
					
					System.out.println("	CRIPTOGRAFIA CESAR-ENCRIPTAR	 ");
					System.out.println("");
					
					boolean claveCorrecta = false;
					int clave;
					int longitud = alfabeto.length();
					String textoCifrado = "";
					
					
					do {
						System.out.print("Clave (1-" + longitud + "): ");
						clave = teclado.nextInt();
						
						// El numero debe de estar dentro de 1 y la longitud del alfabeto
						
						if( clave >= 1 && clave <= longitud ) {
							
							claveCorrecta = true;
							
					}
						
					} while( claveCorrecta == false );
					
					System.out.print("");
					System.out.print("Texto en claro: ");
					espera = teclado.nextLine();
					String texto = teclado.nextLine();
					
					//hacemos que nos remplace los caracteres que no son alfanumericos incluidos espacios
					
					String textoSinEspeciales = texto.replaceAll("[^A-Za-z0-9 ]", "");
					
					System.out.println(" Texto Filtrado: " + textoSinEspeciales);
					
					
					
					int longitudTexto = textoSinEspeciales.length();
					
					int i = 0;
					while( i < longitudTexto ) {
						
						char caracter = textoSinEspeciales.charAt(i);
						int lugar = alfabeto.indexOf(caracter) + clave;
						char nuevoCaracter = alfabeto.charAt(lugar);
						textoCifrado = textoCifrado + Character.toString(nuevoCaracter);
						i++;
					}
						
					System.out.println("Texto Cifrado : " + textoCifrado);
					
					System.out.println("\n   Pulse ENTER para volver al menú:");
					espera = teclado.nextLine();
					
					break;
				
				case 3:
					
					System.out.println("	CRIPTOGRAFIA CESAR-ENCRIPTAR	 ");
					System.out.println("");
					
					boolean clavePositiva = false;
					int claveDes;
					int longitudAlfabeto = alfabeto.length();
					String textoDescifrado = "";
					
					
					do {
						System.out.print("Clave (1-" + longitudAlfabeto + "): ");
						claveDes = teclado.nextInt();
						
						// El numero debe de estar dentro de 1 y la longitud del alfabeto
						
						if( claveDes >= 1 && claveDes <= longitudAlfabeto ) {
							
							clavePositiva = true;
							
					}
						
					} while( clavePositiva == false );
					
					System.out.print("");
					System.out.print("Texto cifrado: ");
					espera = teclado.nextLine();
					String textoHaDesencriptar = teclado.nextLine();
					
					System.out.println(" Cifra filtrada: " + textoHaDesencriptar );
					
					int longitudTextoDescifrar = textoHaDesencriptar.length();
					
					int j = 0;
					while( j < longitudTextoDescifrar ) {
						
						
						char caracterDecifrar = textoHaDesencriptar.charAt(j);
						int lugarDescifrar = alfabeto.indexOf(caracterDecifrar) - claveDes;
						char siguienteCaracter = alfabeto.charAt(lugarDescifrar);
						textoDescifrado = textoDescifrado + Character.toString(siguienteCaracter);
						j++;
					}
						
					System.out.println("Texto Cifrado : " + textoDescifrado);
					
					System.out.println("\n   Pulse ENTER para volver al menú:");
					espera = teclado.nextLine();
					
					break;
				
				case 4:
					
					break;
	
				}
				
			}
			
		}while( menu < 4 );
		
		teclado.close();

	}
}
