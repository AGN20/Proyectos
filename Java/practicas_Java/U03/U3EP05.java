package ejercicios;

import java.util.Scanner;

public class U3EP05 {

	public static void main(String[]args) {
		
		Scanner teclado = new Scanner(System.in);
		
		
		int a = 0;
		int b = 0;
		int c = 0;
		String texto1 = "";
		String texto2 = "";
		float parecido;
		
		System.out.print("Texto1: ");
		texto1 = teclado.nextLine();
		System.out.print("Texto2: ");
		texto2 = teclado.nextLine();
		
		String[] palabras1 = texto1.split("\\s+");
		String[] palabras2 = texto2.split("\\s+");	
		
		for(int i = 0; i < palabras1.length; i++) {
			
			for(int j = 0; j < palabras2.length; j++) {
				
				if(palabras1[i].equals(palabras2[j])) {
				
					a++;
					
				}
			}
		}	
		
		b = palabras1.length - a ;
		c = palabras2.length -a ;
		
		System.out.print(" a = "+ a + " b = " + b + " c = " + c + "\n");
		
		float suma = a + b + c;
		parecido = a / suma ;
		
		System.out.printf(" Parecido [0,1]: %1.8f", parecido);
		
		teclado.close();
		
	}
	
}
