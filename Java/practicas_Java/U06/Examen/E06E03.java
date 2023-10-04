package examen;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import examen.E06E02.IndiceMuni;

public class E06E03 {

String municipio;
	
	
	public static void main(String[] args)  {
		
		Scanner teclado = new Scanner(System.in);
		
		IndiceMuni[] im = new IndiceMuni[8104];
		String dirActual = System.getProperty("user.dir");
		File f = new File(dirActual + "/src/examen/ficheros/munipoblacion.dat");
		if(!f.exists()) {
			System.out.println("El fichero indicado no existe");
			System.exit(0);
		}
		
		System.out.print("Municipio: ");
		String municipio = teclado.nextLine();
		
		BufferedInputStream doc = new BufferedInputStream( new FileInputStream(f));
		
		try {
			
			byte[] b = new byte [90];
			for(int i= 0; i< 8104; i++) {
					linea = doc.read(b);
					im[i] = doc.toString();
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private class IndiceMuni{
		String municipio;
		long posicion;
	}
	
	
	public String toString() implements compareTo {
	
		Object o = this;
		String s = o.toString();
		return s;
		
	}
	
	public int compareTo (IndiceMuni p) {
		
		int n = 0;
		
		if(municipio.length() < p.municipio.length()) {
			n = -1;
		}
		if(municipio.contentEquals(p.municipio)) {
			n = 0;
		}
		if(municipio.length() > p.municipio.length()) {
			n = 1;
		}
		return n;
	}
	
}
