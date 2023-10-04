package U05E07;

import java.util.Scanner;
import U05E07.AnalisisRiesgos;
import U05E07.Activo;


public class menu {

	public void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		AnalisisRiesgos analisis = new AnalisisRiesgos();
		
		
		System.out.println("*******************************");
		System.out.println("**MENÚ DE ANALISIS DE RIESGOS**");
		System.out.println("*******************************");
		System.out.println("");
		System.out.println("  1.Definir Activos del Sistema.");
		System.out.println("  2.Definir dependencias directas entre activos.");
		System.out.println("  3.Calcular y mostrar dependencias directas e indirectas.");
		System.out.println("  4.Salir.");
		System.out.println("");
		System.out.print("  Indique que quiere hacer(1,2,3,4): ");
		int opcion = teclado.nextInt();
		System.out.println("");
		
		switch(opcion) {
		
		case 1:
			for(int i=0;i<9;i++) {
				System.out.print("Indica el Activo " + i + ": ");
				String act = teclado.nextLine();
				Activo activo = new Activo(act);
				analisis.activos[i] = activo;
			}
		case 2:
			analisis.leerDependencias();
		case 3:
			analisis.generaCaminos(analisis.a, analisis.dD);
			analisis.calcularDI(analisis.a, analisis.s);
		case 4:
			break;
		}
		
	}

}
