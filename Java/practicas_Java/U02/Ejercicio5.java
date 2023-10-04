import java.util.Scanner; //importamos el Scanner

public class Ejercicio5{
	
	public static void main (String[] args){
		
		Scanner teclado = new Scanner (System.in); // Creamos el Scanner
		//Creamos las variables
		Double pescuestionario;
		Double pesactividad;
		String error;
		
		// Pedimos que nos escriban los datos en las variables
		System.out.printf("Teclee peso del cuestionario (0.0-1.0):");
		pescuestionario = teclado.nextDouble();
		System.out.printf("Teclee peso de la actividad (0.0-1.0):");
		pesactividad = teclado.nextDouble();
		
		// Indicamos que si la suma de los pesosno da 1 nos da error
		double sumaresultado = (pescuestionario + pesactividad);
		error = (sumaresultado < 1.0 )? "ERROR EN PESOS!!":"";
		System.out.printf(error + "\n");
		//Calculamos la nota del cuestionario y la actividad
		int notacuestionario = (int)(Math.ceil(pescuestionario)*5);
		int notaactividad = (int)(Math.ceil(pesactividad)*5);
		//Mostramos la nota del cuestionario por pantalla
		System.out.printf("Nota de cuestionario en [0-10]: " + notacuestionario + "\n");
		System.out.printf("Nota de la actividad en [0-10]: " + notaactividad + "\n");
		// calculamos la nota en [0-10]
		double notadiez = sumaresultado * 5;
		// calculamos la nota en [10-50]
		double notacincu = 10+((notadiez-0)/(10-0))*(50-10);
		//Mostramos por pantalla las notas en [0-10] y [10-50]
		System.out.printf("Nota en [0, 10] es " + "%1.2f" + "\n", notadiez );
		System.out.printf("Nota en [10, 50] es " + "%1.2f" + "\n", notacincu ); 
	
	}	
}