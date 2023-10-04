import java.util.Scanner; //importamos el Scanner

public class Ejercicio4{
	
	public static void main (String[] args){
		
		Scanner teclado = new Scanner (System.in); // Creamos el Scanner
		//Creamos las variables
		final double LongitudCanion = 5;
		int posiinix = 0;
		int posiiniy = 24;
		double xc = 0;
		double yc = 0;
		double alfa;
		double vi;

		System.out.print("Teclee angulo (grados): ");
		alfa = teclado.nextDouble();
		System.out.print("Teclee velocidad (m/s): ");
		vi = teclado.nextDouble();
		
		alfa = alfa * Math.PI / 180;
		
		double xp = xc + LongitudCanion * Math.cos(alfa);
		double yp = yc + LongitudCanion * Math.sin(alfa);
		
		System.out.printf("xp es : (%1.2f,  %1.2f) \n", xp, yp);
		
		double g = 9.8;
		
		double tiemposubida = vi * Math.sin(alfa) / g;
		double tiempovuelo = 2 * tiemposubida;
		
		System.out.printf(" tiempo de subida es :     %1.2f segundos y de vuelo %1.2f segundos \n", tiemposubida, tiempovuelo);
		
		double hmax = (Math.pow(vi,2)*Math.pow(Math.sin(alfa),2)) / (2*g);
		double xmax = (Math.pow(vi,2)*Math.sin(2*alfa)) / g;
		
		System.out.printf(" Max. altura:              %1.2f metros \n", hmax);
		System.out.printf(" Max. distancia:           %1.2f metros \n", xmax);
		
		double tiem0 = 0.00;
		double tiem1 = tiempovuelo * 25 / 100;
		double tiem2 = tiempovuelo * 50 / 100;
		double tiem3 = tiempovuelo * 75 / 100;
		double tiem4 = tiempovuelo * 100 / 100;
		
		double posx0 = xp + (vi * Math.cos(alfa)) * tiem0;
		double posx1 = xp + (vi * Math.cos(alfa)) * tiem1;
		double posx2 = xp + (vi * Math.cos(alfa)) * tiem2;
		double posx3 = xp + (vi * Math.cos(alfa)) * tiem3;
		double posx4 = xp + (vi * Math.cos(alfa)) * tiem4;
		
		double posy0 = (yp + (vi * Math.sin(alfa)) * tiem0) - (0.5 * g * tiem0 * tiem0);
		double posy1 = (yp + (vi * Math.sin(alfa)) * tiem1) - (0.5 * g * tiem1 * tiem1);
		double posy2 = (yp + (vi * Math.sin(alfa)) * tiem2) - (0.5 * g * tiem2 * tiem2);
		double posy3 = (yp + (vi * Math.sin(alfa)) * tiem3) - (0.5 * g * tiem3 * tiem3);
		double posy4 = (yp + (vi * Math.sin(alfa)) * tiem4) - (0.5 * g * tiem4 * tiem4);
		
		int realx0 = posiinix + (int)posx0 / 5;
		int realx1 = posiinix + (int)posx1 / 5;
		int realx2 = posiinix + (int)posx2 / 5;
		int realx3 = posiinix + (int)posx3 / 5;
		int realx4 = posiinix + (int)posx4 / 5;
		
		int realy0 = posiiniy - (int)posy0 / 5;
		int realy1 = posiiniy - (int)posy1 / 5;
		int realy2 = posiiniy - (int)posy2 / 5;
		int realy3 = posiiniy - (int)posy3 / 5;
		int realy4 = posiiniy - (int)posy4 / 5;
		
		System.out.print("+-----------+-------------------------+-----------------------+\n");
		System.out.print("| Tiempo    |  Posicion en el mundo   | Posicion en Pantalla  |\n");
		System.out.print("+-----------+-------------------------+-----------------------+\n");
		System.out.printf("|%10.2f |(%10.2f ,%10.2f) |   (%5d, %5d)      |\n", tiem0, posx0, posy0, realx0, realy0 );
		System.out.print("+-----------+-------------------------+-----------------------+\n");
		System.out.printf("|%10.2f |(%10.2f ,%10.2f) |   (%5d, %5d)      |\n", tiem1, posx1, posy1, realx1, realy1 );
		System.out.print("+-----------+-------------------------+-----------------------+\n");
		System.out.printf("|%10.2f |(%10.2f ,%10.2f) |   (%5d, %5d)      |\n", tiem2, posx2, posy2, realx2, realy2 );
		System.out.print("+-----------+-------------------------+-----------------------+\n");
		System.out.printf("|%10.2f |(%10.2f ,%10.2f) |   (%5d, %5d)      |\n", tiem3, posx3, posy3, realx3, realy3 );
		System.out.print("+-----------+-------------------------+-----------------------+\n");
		System.out.printf("|%10.2f |(%10.2f ,%10.2f) |   (%5d, %5d)      |\n", tiem4, posx4, posy4, realx4, realy4 );
		System.out.print("+-----------+-------------------------+-----------------------+\n");
		

	}	
}