package ejercicios;

import java.util.Scanner;

public class U3EP02 {

	public static void main(String[]args) {
		
		Scanner teclado = new Scanner(System.in);
		
		String [] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		double [] precipitacion = new double[12];
		double masLluvioso = 0;
		
		System.out.println("        PRECIPITACIONES ANUALES");
		System.out.println("        =======================");
		System.out.println("");
		System.out.println("");
		
		for(int i = 0; i < meses.length ; i++) {
			
			System.out.print( meses[i] + ": ");
			precipitacion[i] = teclado.nextInt();
			
		}
		
		double mallor = precipitacion[0];
		String mesMallor = "";
		
		for(int i = 1; i < precipitacion.length; i++ ) {
			
			if(precipitacion[i] > mallor) {
				
				mallor = precipitacion[i];
				mesMallor = meses[i];
				masLluvioso = mallor;
				
			}
			
		}
			
		
		double media = (precipitacion[0] + precipitacion[1] + precipitacion[2] + precipitacion[3] + precipitacion[4] + precipitacion[5] + precipitacion[6] + precipitacion[7] + precipitacion[8] + precipitacion[9] + precipitacion[10] + precipitacion[11])/12 ;
		
		double mediana = (precipitacion[5] + precipitacion[6])/2;
		
		double desviacion = Math.sqrt((Math.pow((precipitacion[0] - media), 2) + Math.pow((precipitacion[1] - media), 2) + Math.pow((precipitacion[2] - media), 2) + Math.pow((precipitacion[3] - media), 2) + Math.pow((precipitacion[4] - media), 2) + Math.pow((precipitacion[5] - media), 2) + Math.pow((precipitacion[6] - media), 2) + Math.pow((precipitacion[7] - media), 2) + Math.pow((precipitacion[8] - media), 2) + Math.pow((precipitacion[9] - media), 2) + Math.pow((precipitacion[10] - media), 2) + Math.pow((precipitacion[11] - media), 2) )/12 ) ;
		
		System.out.println("");
		System.out.println("El mes más lluvioso " + mesMallor + " con " + masLluvioso + " mm");
		System.out.printf("Media: %1.2f \n", media);
		System.out.println("Mediana: " + mediana);
		System.out.printf("Desviación: %1.2f \n", desviacion);
		
		teclado.close();
		
	}
	
}
