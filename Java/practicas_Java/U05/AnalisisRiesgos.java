package U05E07;

import java.util.Scanner;
import U05E07.Activo;

public class AnalisisRiesgos {

	private Scanner teclado = new Scanner(System.in);
	
	double[][] dD = new double[10][10];
	double[][] dI = new double[10][10];
	Activo[] activos = new Activo[10];
	String[] s = new String[contarCaminos(0)];
	int a;
	int b;
	
	//Leemos las dependencias directas
	public void leerDependencias() {
		
		double valor = 0;
		do {
			System.out.println("Indica las dependencias directas: ");
			System.out.println("Indica la posicion A de la tabla: ");
			a = teclado.nextInt();
			System.out.println("Indica la posicion B de la tabla: ");
			b = teclado.nextInt();
			if(a <= 0 || b <= 0 && a >= 9 || b >= 9) {
				if(a<=0 || b<=0) {
					System.out.println("Dependencias introducidas correctamente");
				}else {
					System.out.println("A -> " + a + "o B -> "+ b + ", no estan entre 0 y 9.");
				}
			}else {
				do {
					System.out.println("Indica el valor de la dependencia: ");
					valor = teclado.nextDouble();
				}while(valor < 0 || valor > 1  );
			}
		}while(a >= 0 && b >= 0);
		dD[a][b] = valor;
	}

	//Contamos los caminos
	public int contarCaminos(int f) throws IllegalArgumentException {
		if(f<0 || f>9) { 
			throw new IllegalArgumentException("La fila no se encuentra entre 0 y 9");
			}
		int contador = 0;
		for(int i=0; i < dD[f].length; i++) {
			if(dD[f][i] != 0) {
				contador++;
			}
		}
		return contador;
	}
	
	//Generar caminos
	public String[] generaCaminos(int a, double[][] dD) {
		boolean hayBucles;
		boolean continuar;
		String bucle;
		int hastaCam;
		int desdeCam;
		String c;
		

		for(int j = 0; j < s.length; j++) {
			for(int x = 0; x < dD[a].length-1; x++) {
				if(dD[a][x] > 0) {
					s[j] = s[j]+(a+"->"+x); //Falta numero

				}
			}

			hayBucles = false;
			continuar = true;
			bucle = "";
			hastaCam = -1;

			while(continuar && !hayBucles) {
				continuar = false;
				desdeCam = hastaCam + 1;
				hastaCam = s.length;
				for(int camino = desdeCam; camino < hastaCam; camino++) {
					c = s[camino];
					int b = Character.getNumericValue( c.charAt(c.length()-1));
					for(int d = 0; d < contarCaminos(b); d++) {
						int y = Character.getNumericValue( c.charAt(c.length()-1));
						if (dD[b][y]>= 0) {
							if (c.indexOf(y) >= 0) {
								hayBucles = true;
								bucle = c + "->" + y;
								break;
							} else {
								s[j] += s[j] + c + "->" + y;
								continuar = true;
							}
						}

					}
					if (hayBucles) break;
				}
			
			
			}

		}
		
		return s;
	}
	
	//Suma Pareto
	public double suma_pareto(double x, double y) {
		return 1-(1-x)*(1-y);
	}
	
	//Calcular dependencias Indirectas
	public void calcularDI(int a, String[] s) {
		double grado;
		for(int c=0; c<s.length; c++) {
			grado = 1.0;
			for(int camino=0; camino<s.length; camino++) {
				int x = Character.getNumericValue( s[camino].charAt(0));
				int y = Character.getNumericValue( s[camino].charAt(s[camino].length()-1));
				grado = grado * dD[x][y];
			}
			int ultimo = c;
			if(dI[a][ultimo] == 0) {
				dI[a][ultimo] = 0.0;
			}else {
				dI[a][ultimo] = suma_pareto(dI[a][ultimo], grado);
			}
		}
	}
	
}
