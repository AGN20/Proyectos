import java.util.Scanner; //importamos el Scanner
import java.util.Date; //importamos 

public class Ejercicio7{
	
	public static void main (String[] args){
		
		Scanner teclado = new Scanner (System.in); // Creamos el Scanner
		Date fecha = new Date();
		
		//Creamos las variables
		int dia;
		int mes;
		int anio;
		int plazo;
		
		
		System.out.print("Dia del mes (1-31): " );
		dia = teclado.nextInt();
		
		System.out.print("Mes del año (1-12): " );
		mes = teclado.nextInt();
		
		System.out.print("Año: " );
		anio = teclado.nextInt();
		
		System.out.print("Plazo (dias naturales): ");
		plazo = teclado.nextInt();
		
		//Calculamos los Dias
		
		int diacom =  dia + plazo;
		int diaresta = diacom - 31;
		int diareal = (diacom > 31)? diaresta : diacom;
		
		//Calculamos los Meses
		
		int messuma = mes + 1;
		int mescom = (diacom > 31)? messuma : mes;
		int mesresta = mescom - 12;
		int mesreal = (mescom > 12)? mesresta : mescom;
		
		//Calculamos el Año
		
		int aniosuma = anio + 1;
		int anioreal = (messuma > 12)? aniosuma : anio;
		
		//Pasamos los datos al Date, para poder establecer el dia y mes
		
		Date fecharesultado = new Date(diareal, mesreal, anioreal);
		int diasemanal = fecharesultado.getDay();
		int mesanual = fecharesultado.getMonth();

		// Indicamos que si el dia es sabado o domingo no de el viernes como dia de entrega
		
		int diasab = diasemanal - 1 & diareal - 1;
		diasemanal = (diasemanal == 6)? diasab : diareal;
		int diado = diasemanal - 2 & diareal - 2;
		diasemanal = (diasemanal == 0)? diado : diareal;
		
		//Indicamos que cada solucion tiene in nombre

		String lunes = (diasemanal == 1)? "lunes":"";
		String martes = (diasemanal == 2)? "martes":"";
		String miercoles = (diasemanal == 3)? "miercoles":"";
		String jueves = (diasemanal == 4)? "jueves":"";
		String viernes = (diasemanal == 5)? "viernes":"";
		
		String enero = (mesanual == 1)? "Enero":"";
		String febrero = (mesanual == 2)? "Febrero":"";
		String marzo = (mesanual == 3)? "Marzo":"";
		String abril = (mesanual == 4)? "Abril":"";
		String mayo = (mesanual == 5)? "Mayo":"";
		String junio = (mesanual == 6)? "Junio":"";
		String julio = (mesanual == 7)? "Julio":"";
		String agosto = (mesanual == 8)? "Agosto":"";
		String septiembre = (mesanual == 9)? "Septiembre":"";
		String octubre = (mesanual == 10)? "Octubre":"";
		String noviembre = (mesanual == 11)? "Noviembre":"";
		String dezember = (mesanual == 12)? "Diciembre":"";
		
		//Mostramos por pantalla la solucion
		
		System.out.println("Tienes que entregarlo " + diareal + " el dia " + lunes + martes + miercoles + jueves + viernes + " de " + enero + febrero + marzo + abril + mayo + junio + julio + agosto + septiembre + octubre + noviembre + dezember + " del ");
	
	}	
}