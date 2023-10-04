package pascua;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class U06E01 {

	public static void main(String[] args) throws IOException {
		
		Scanner teclado = new Scanner(System.in);
		/* Preguntamos el espacio natural */
		System.out.print("Espacios Naturales de la Provincia: ");
		String provincia = teclado.nextLine();
		String[] campos = null;
		int totalSup = 0;
		int noExiste = 0;
		int numLineas = 0;
		/* Indicamos el fichero del que vamos a leer */
		BufferedReader doc = new BufferedReader(new FileReader("D:/java/Ejercicios/PracticaU06/src/pascua/ficheros/espacios1.csv"));
		
		try {
			
			String linea;
			int numl = 0;
			
			while((linea = doc.readLine()) != null) {
				numl++;
				campos = linea.split(";");
			
			
				if(campos.length != 5) {
					System.out.println(" Línea " + numl + " INCORRECTA: " + linea);
				}
			
				if(campos[0].toLowerCase().contains(provincia.toLowerCase())) {
				
					System.out.printf("Espacio: %s Tipo: %s Superficie (Ha): %s.00 %n", campos[1],campos[3],campos[4]);
				
					if(campos[4] != null) {
						totalSup += Integer.parseInt(campos[4]);
					}
				
				}else {
					noExiste++;
				}
			
				numLineas++;
			
			}
			
			if(noExiste == numLineas) {
				System.out.println("Provincia: " + provincia + " no se encuentra en el fichero indicado");
			}else {
				System.out.println("Total de Superficie = "+ totalSup + " Ha");
			}
			
		}catch(FileNotFoundException e){
			
			e.printStackTrace();
			
		} finally {
			teclado.close();
			doc.close();
			System.exit(0);
		}
		
	}

}
