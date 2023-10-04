package Ejercicio1;

import java.util.Scanner;

public class Fraccion {
	
	int n;
	int d;
	

	/* Constructores  */
	
	public Fraccion() { 
		
		n = 1;
		d = 1;
		
		}
	
	public Fraccion(int n, int d) throws IllegalArgumentException {
		
		this.n = n;
		this.d = d;
		
		simplifica();
		
		if (d == 0) {	
			throw new IllegalArgumentException("Denominador no puede ser 0");	
		}
		if(d < 0) {
			this.d = -d;
			this.n = -n;
		}
		
	}
	
	public Fraccion( double n ) {
		
		
		String a = Double.toString(n);
		String x = a.replace(".","");
		int b = Integer.parseInt(x);
		
		this.n = b;
		d = 100;
		
		simplifica();
		
	}
	
	/* Getters y Setters */
	
	
	/* Operación Interna */
	
	private void simplifica() {
		
		int a = (int)n;
		int b = d;
		int mcd;
		
		int t;
		
		while (b != 0) {
			t = b;
			b = a % b;
			a = t;
		}
		
		mcd = a;
		
		n = n/mcd;
		
		d = d/mcd;
		
	}
	
	/* Operaciones Externas */

	public boolean equals(Object o) {
		
		if(o == null) return false;
		if(o instanceof Fraccion) {
			Fraccion o1 = (Fraccion) o;
			return o1.n == this.n && o1.d == this.d;
		}return false;
		
	}
	
	//Ejecutable
	
	public static void main(String[]args) {
		
		Fraccion f1 = new Fraccion();
		Fraccion f2 = new Fraccion();
		Scanner teclado = new Scanner(System.in);
		
		
		System.out.print("Primera: ");
		String cadena = teclado.nextLine();
		
		if(!cadena.contains("/")) {
			
			if(!cadena.contains(".")) {
				
				int x = Integer.parseInt(cadena);
				int y = 1;
				
				f1.n = x;
				f1.d = y;
				
			}else {
				
				Fraccion fx = new Fraccion(Double.parseDouble(cadena));
				
				f1 = fx;
				
			}
				
		}else{
			
			String[] partes = cadena.split("/");
			
			if(partes.length == 1) {
			
				String[] parte2 = new String[2];
				parte2[0] = partes[0];
				parte2[1] = "1";
				partes = parte2;
				
				
				int x = Integer.parseInt(partes[0]);
				int y = Integer.parseInt(partes[1]);
				
				f1.n = x;
				f1.d = y;
				
				
			}else {
				
				int x = Integer.parseInt(partes[0]);
				int y = Integer.parseInt(partes[1]);
				
				f1.n = x;
				f1.d = y;
				
			}
			
		}
		
		System.out.print("Segunda: ");
		String cadena2 = teclado.nextLine();
		
		if(!cadena2.contains("/")) {
			
			if(!cadena2.contains(".")) {
				
				int x = Integer.parseInt(cadena2);
				int y = 1;
				
				f2.n = x;
				f2.d = y;
				
			}else {
				
				Fraccion fx = new Fraccion(Double.parseDouble(cadena2));
				
				f2 = fx;
				
			}
				
		}else{
			
			String[] partes = cadena2.split("/");
			
			if(partes.length == 1) {
			
				String[] parte2 = new String[2];
				parte2[0] = partes[0];
				parte2[1] = "1";
				partes = parte2;
				
				
				int x = Integer.parseInt(partes[0]);
				int y = Integer.parseInt(partes[1]);
				
				f2.n = x;
				f2.d = y;
				
				
			}else {
				
				int x = Integer.parseInt(partes[0]);
				int y = Integer.parseInt(partes[1]);
				
				f2.n = x;
				f2.d = y;
				
			}
			
		}
		
		//Equivalente primera fraccion
		if (cadena.contains("/")) {
			System.out.print("Equivalente de " + ((float)f1.n/(float)f1.d) + ": " + f1.n + "/" + f1.d + "\n");
		}
		//Equivalente primera fraccion
		if (cadena2.contains("/")) {
			System.out.print("Equivalente de " + ((float)f2.n/(float)f2.d) + ": " + f2.n + "/" + f2.d + "\n");
		}
		
		//Numerador y denominador de primera fracción
		if(cadena.contains("/")) {
			System.out.print("Numerador de " + f1. n + "/" + f1.d + " es " + f1.n + "\n");
			System.out.print("Denominador de " + f1.n + "/" + f1.d + " es " + f1.d + "\n");
		}
		if(cadena2.contains("/")) {
			System.out.print("Numerador de " + f2.n + "/" + f2.d + " es " + f2.n + "\n");
			System.out.print("Denominador de " + f2.n + "/" + f2.d + " es " + f2.d + "\n");
		}
		
		
		int sumaN = f1.n + f2.n;
		int sumaD = f1.d + f2.d;
		
		//suma
		Fraccion suma = new Fraccion(sumaN,sumaD);
		System.out.println("La suma de " + f1.n + "/" + f1.d + " y " + f2.n + "/" + f2.d + " es " + suma.n + "/" + suma.d);
		
		//equals
		System.out.println(f1.n + "/" + f1.d + " es igual a " + f2.n + "/" + f2.d + " : " + f1.equals(f2));
		System.out.println(suma.n + "/" + suma.d + " es igual a " + f2.n + "/" + f2.d + " : " + suma.equals(f2));
		
		
	}
	
	
}
