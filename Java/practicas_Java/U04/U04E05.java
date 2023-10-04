package Ejercicio5;

import java.util.Scanner;

public class U04E05 {
	
	public static void main (String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		//filas del laberinto
		System.out.println("Cuantas filas quieres en el laberinto(mas de 3): ");
		int fL = teclado.nextInt();
		
		while( fL < 4) {
			System.out.println("Cuantas filas quieres en el laberinto(mas de 3): ");
			fL = teclado.nextInt();
		}
		
		//columnas del laberinto
		System.out.println("Cuantas columnas quieres en el laberinto(mas de 3): ");
		int cL = teclado.nextInt();
		
		while( cL < 4) {
			System.out.println("Cuantas columnas quieres en el laberinto(mas de 3): ");
			cL = teclado.nextInt();
		}
		
		//Probabilidad
		System.out.println("Que probabilidad quieres de que una celda tenga pared(mayor a 0 y menor de 1): ");
		double p = teclado.nextDouble();
		
		while( 0 > p && p > 1) {
			System.out.println("Que probabilidad quieres de que una celda tenga pared(mayor a 0 y menor de 1): ");
			p = teclado.nextDouble();
		}
		
		//fila de salida
		System.out.println("Donde quieres que este la salida: ");
		int fS = teclado.nextInt();
		
		while(0 >= fS && fS > fL-1) {
			System.out.println("Donde quieres que este la salida: ");
			fS = teclado.nextInt();
		}
		
		//fila de meta
		System.out.println("Donde quieres que este la entrada: ");
		int fM = teclado.nextInt();
		
		while( 0 >= fM && fM < fL-1) {
			System.out.println("Donde quieres que este la entrada: ");
			fM = teclado.nextInt();
		}
		
		//semilla
		System.out.println("Indica la semilla que quieres: ");
		long semilla = teclado.nextInt();
		
		Laberinto lab = new Laberinto(fL,cL,fS,fM,semilla,p);
		
		lab.dibuja(0,0);
		lab.moverA(fS, 0);
		
		System.out.println("Ruta" + lab.ruta );
		
	}
	
}
