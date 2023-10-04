package ejercicios;

import java.util.Scanner;

public class U3EP04 {

public static void main(String[]args) {
		

		Scanner teclado = new Scanner(System.in);
		
		int menu;
		String[] contactos = new String[0];
		String[][] direcciones = new String[0][0];
		String espera;
		int tamContact = 0;
		int posicion = 0;
		
		do {
		
			System.out.println("	AGENDA DE CONTACTOS	 ");
			System.out.println("	===================	 ");
			System.out.println("");
			System.out.println("1. Nuevo contacto.");
			System.out.println("2. Borrar contacto.");
			System.out.println("3. Modificar direccion de un contacto.");
			System.out.println("4. Busca contactos");
			System.out.println("5. Salir");
			System.out.println("");
			System.out.print("Escoja una opción (1-5): ");
			menu = teclado.nextInt();
			System.out.println("\n");
	
			
	
			if (menu <= 4 && menu >= 1) {
				
				switch(menu) {
				case 1:
					
					tamContact++;
					String[] contacActual = new String[tamContact];
					String[][] direcActual = new String[tamContact][];
					posicion = tamContact-1;
					
					System.out.print("Nombre: ");
					espera = teclado.nextLine();
					contacActual[posicion] = teclado.nextLine();
					System.out.println("        Posición " + posicion);
					
					
					for(int i = 0; i < tamContact; i++) {
						
						if (contacActual[i] == null) {
							
							if(contactos[i] == null) {
								
								contactos[i] = contactos[i+1];
								direcciones[i] = direcciones[i+1];
								
							}else {
								contacActual[i] = contactos[i];
							
							}
						
						}
					
					}
					
					contactos = contacActual;
					direcciones = direcActual;
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					
					break;
					
				case 2:
					
					boolean esta = false;
					int posBorrado = 0;
					
					while( esta == false) {
						System.out.print("Posicion a borrar [0-" + posicion + "]: ");
						posBorrado = teclado.nextInt();
						
						if(posBorrado <= posicion && posBorrado >= 0) {
							
							esta = true;
							
						}
						
					}
					
					System.out.print("  Quieres borrar el contacto " + contactos[posicion] + " (s/n): ");
					String confirmar = teclado.nextLine();
					
					if( confirmar.equalsIgnoreCase("s")) {
						
						contactos[posBorrado] = null;
						direcciones[posBorrado][0] = null;
						
						System.out.print("");
						System.out.print("     Contacto y direcciones borradas.");
						System.out.print("");
					}
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					
					break;
				
				case 3:
					
					int numero = 0;
					int numero2 = 1;
					boolean estaEn = false;
					int posMod = 0;
					String opcion;
					String dato = null;
					
					while( estaEn == false) {
						System.out.print("Posicion a Modificar [0-" + posicion + "]: ");
						posMod = teclado.nextInt();
						espera = teclado.nextLine();
						
						if(posMod <= posicion && posMod >= 0) {
							
							estaEn = true;
							
							if(contactos[posMod] == null) {
								
								System.out.println("    No existe el contacto");
								
							}else {
							
								
								String[][] direcion = new String[0][0];
								int lugar = 0;
								boolean borrado = false;
								
								do{
									
									String[][] nueDireccion = new String[posMod+1][numero2];
									
									System.out.println("DIRECCIONES de " + contactos[posMod]);
									
									for(int i = 0; i < numero ; i++) {
										
										nueDireccion[posMod][i] = direcion[posMod][i];
										System.out.println(i + "   " + direcion[posMod][i]);
										
									}
									
									System.out.print("(I) Insertar  (B) Borrar  (S) Salir  Operación: ");
									opcion = teclado.nextLine();
									
									if(opcion.equalsIgnoreCase("i")) {

												
											System.out.print("Insertar:    ");
											dato = teclado.nextLine();
											
											if( borrado != true) {
											
												nueDireccion[posMod][numero] = dato;
											
												numero++;
												numero2++;
											
											}else {
												
												nueDireccion[posMod][lugar] = dato;
												borrado = false;
												
											}
											
										
									}
									if(opcion.equalsIgnoreCase("b")) {
										
										int numeroX = numero - 1; 
										System.out.print("Borrar [0-" + numeroX + "]");
										lugar = teclado.nextInt();
										
										nueDireccion[posMod][lugar] = "";
										
										borrado = true;
										
									}
									
									direcion = nueDireccion;
									
								}while(opcion.equalsIgnoreCase("s") == false);
								
								direcciones[posMod] = direcion[posMod];
								
							}
							
						}
						
					}
					
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					
					break;
				
				case 4:

					
					System.out.print("Comienza por la frase: ");
					espera = teclado.nextLine();
					String frase = teclado.nextLine();
					System.out.println("");
					System.out.print("Contiene la frase:     ");
					String frase2 = teclado.nextLine();
					System.out.println("");
					
					if(!frase.equals("")) {
						
						boolean busca = false;
						
						for(int i = 0; i < contactos.length; i++) {
							
							busca = contactos[i].startsWith(frase);
							
							if(busca) {
								
								System.out.println("    (" + i + ") DIRECCIONES DE " + contactos[i]);
								
								for(int j = 0; j < direcciones[i].length-1; j++) {
									
									System.out.println("   " + j + "   " + direcciones[i][j]);
									
								}
								
							}
							
						}
						
					}
					
					if(!frase2.equals("")) {
						
						int encuentra = 0;
						
						for(int i = 0; i < contactos.length; i++) {
							
							encuentra = contactos[i].indexOf(frase);
							
							if(encuentra != -1) {
								
								System.out.println("(" + i + ") DIRECCIONES DE " + contactos[i]);
								
								int j = 0;
								int x = 1;
								
								do {
									
									System.out.println("   " + j + "   " + direcciones[i][j]);
									j++;
									x++;
									
								}while( x < direcciones[i].length);
								
							}
							
						}
						
					}
					
					if( frase2.equals("") || frase.equals("") ) {
						
						for(int i = 0; i < contactos.length; i++) {
							
							System.out.println("(" + i + ") DIRECCIONES DE " + contactos[i]);
								
							int j = 0;
							int x = 1;
								
							do {
									
								System.out.println("   " + j + "   " + direcciones[i][j]);
								j++;
								x++;
									
							}while( x < direcciones[i].length);
						}
						
					}
						
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					
					break;
					
				case 5:
					
					break;
	
				}
			
			}
			
		}while( menu < 5 );
		
		teclado.close();

	}
}	

