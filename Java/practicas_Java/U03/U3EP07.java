package ejercicios;

import java.util.Scanner;
import java.util.Date;

public class U3EP07 {

	public static void main(String[]args) {
		
		Scanner teclado = new Scanner(System.in);
		
		/* Parte A */
		
		int[] numeros = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double[] porcentaje = {0.99, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09, 0.09}; 
		
		int n = 0;
		
		System.out.print("Tamaño: ");
		n = teclado.nextInt();
		System.out.println("");
		
		int[][] matriz = new int[n][n];
		
		for(int i = 0; i < matriz.length; i++) {
			
			for(int j = 0; j < matriz.length; j++) {
				
				if(Math.random() < porcentaje[j]) {
					
					matriz[i][j] = numeros[j];
					
				}
				
			}
			
		}
		
		System.out.println("Matriz casi nula: ");
		
		for(int i = 0; i < n; i++) {
			
			System.out.print("|");
			
			for (int j = 0; j < n; j++) {
				System.out.print(" " + matriz[i][j] + " ");
			}
			
			System.out.println("|");
			
		}
		
		System.out.println("");
		System.out.println("Matriz óptima: ");
		
		for(int i = 0; i < n; i++) {
			
			
			for (int j = 0; j < n; j++) {
				
				if(matriz[i][j] != 0) {
					System.out.print("|" + (i+1) + ", " + (j+1) + ", " + matriz[i][j] + "|");
				}
			}

			
		}
		
		
		
		/* Parte B */
		
		Date fecha = new Date();
		String[] fecha1 = new String[n];
		double[] prob = new double[n];
		prob[0] = 0.99;
		
		System.out.println("");
		System.out.print("Tamaño: ");
		n = teclado.nextInt();
		System.out.println("");
		
		String[][] matriz2 = new String[n][n];
		
		for(int i = 1; i < n-1 ; i++) {
			
			fecha1[i] = String.valueOf(fecha);
			prob[i] = 0.1 / n;
			
		}
		
		for(int i = 0; i < matriz2.length; i++) {
			
			for(int j = 0; j < matriz2.length; j++) {
				
				if(Math.random() < prob[j]) {
					
					matriz2[i][j] = fecha1[j];
					
				}
				
			}
			
		}
		
		System.out.println("Matriz casi nula: ");
		
		for(int i = 0; i < n; i++) {
			
			System.out.print("|");
			
			for (int j = 0; j < n; j++) {
				System.out.print(" " + matriz2[i][j] + " ");
			}
			
			System.out.println("|");
			
		}
		
		System.out.println("");
		System.out.println("Matriz óptima: ");
		
		for(int i = 0; i < n; i++) {
			
			
			for (int j = 0; j < n; j++) {
				
				if(matriz2[i][j] != null) {
					System.out.print("|" + (i+1) + ", " + (j+1) + ", " + matriz2[i][j] + "|");
				}
			}

			
		}
		
		teclado.close();
		
	}
	
}
