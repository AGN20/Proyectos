package ejercicios;

import java.util.Scanner;

public class U3EP03 {

	public static void main(String[]args) {
		
		Scanner teclado = new Scanner(System.in);
		
		double [][] horas = new double[0][12];
		String [] asignaturas = new String [0];
		String espera;
		int cantidad = 0;
		
		int menu;
		
		do {
			
			System.out.println("	HORAS DE ESTUDIO");
			System.out.println("	================");
			System.out.println("");
			System.out.println("");
			System.out.println("1. Definir tablas.");
			System.out.println("2. Modificar horas.");
			System.out.println("3. Total horas por asignatura.");
			System.out.println("4. Total horas por meses");
			System.out.println("5. Nombre y horas de asignatura más estudiada");
			System.out.println("6. Salir");
			System.out.println("");
			System.out.print("Escoja una opción (1-6): ");
			menu = teclado.nextInt();
			System.out.println("");
			
	
			if (menu <= 6 && menu >= 1) {
				
				switch(menu) {
				case 1:
					
					System.out.print("Cuántas asignaturas: ");
					cantidad = teclado.nextInt();
					espera = teclado.nextLine();
					
					String [] asignaturas2 = new String[cantidad];
					double [][] horas2 = new double[cantidad][12];
					

					for(int i = 0; i < cantidad; i++) {
						
						int lugar = i+1;
						System.out.print(" Asignatura " + lugar + ": ");
						String asignatura = teclado.nextLine();
						
						asignaturas2[i] = asignatura;
						
					}
					
					asignaturas = asignaturas2;
					horas = horas2;
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					
					break;
					
				case 2:
					
					System.out.print("Asignatura: ");
					espera = teclado.nextLine();
					String asignatura = teclado.nextLine();
					int esta = 0;
					
					for (int i = 0; i < cantidad ; i++) {
						
						if( asignatura.equals(asignaturas[i])) {
							
							esta = i;
							
						}
						
					}
					
					
					if(asignaturas[esta].contentEquals(asignatura)) {
						
						int mes = 0;
						
						do {
							
							System.out.print("Mes (1-12); ");
							mes = teclado.nextInt();
							
						}while( mes < 1 || mes > 12 );
						
						mes = mes - 1;
						
						System.out.print("Horas: ");
						horas[esta][mes] = teclado.nextInt();
						
						System.out.println();
						System.out.print(" pulse ENTER para menú... \n");
						espera = teclado.nextLine();
						espera = teclado.nextLine();
					
					} else {
						
						System.out.println("    No encontrada, pulse ENTER para menú...");
						
					}
					
					break;
				
				case 3:
					
					double[] total = new double[cantidad];
					
					System.out.println("     HORAS ANUALES POR ASIGNATURA");
					
					for(int j = 0; j < cantidad; j++) {

						for(int x = 0; x < 12; x++) {
							
							total[j] = total[j] + horas[j][x];
							
						}
						
						
						System.out.printf("%10s %6.2f \n", asignaturas[j], total[j]);
						
					}
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					espera = teclado.nextLine();
					
					break;
				
				case 4:
					
					double[] suma = new double[12] ;
					
					System.out.println("      HORAS TOTALES MENSUALES");
					
					for(int i = 0; i < 12; i++) {
						
						for( int j = 0; j < cantidad; j++) {
							
							suma[i] = suma[i] + horas[j][i];
							
						}
						
						System.out.printf("%2s %6.2f \n", i+1 , suma[i]);
						
					}
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					espera = teclado.nextLine();
					
					break;
				
				case 5:
					
					double[] maximo = new double[cantidad];
					double maxReal = 0;
					int numeroClase = 0;
					
					for(int j = 0; j < cantidad; j++) {

						for(int x = 0; x < 12; x++) {
							
							maximo[j] = maximo[j] + horas[j][x];
							
							if (maxReal < maximo[j]) {
								
								maxReal = maximo[j];
								numeroClase = j;
								
							}
							
						}
							
					}
					
					System.out.println("La más estudiada es " + asignaturas[numeroClase] + " con " + maxReal + " horas");
					
					System.out.println();
					System.out.print(" pulse ENTER para menú... \n");
					espera = teclado.nextLine();
					espera = teclado.nextLine();
					
					break;
					
				case 6:
	
					break;
	
				}
				
			}
			
		}while( menu < 6 );
		
		teclado.close();
		
	}
	
}
