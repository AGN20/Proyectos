package examen;

import java.io.*;

public class E06E02 {

	String municipio;
	
	
	public static void main(String[] args)  {
		
		IndiceMuni[] im = new IndiceMuni[8104];
		String dirActual = System.getProperty("user.dir");
		File f = new File(dirActual + "/src/examen/ficheros/munipoblacion.dat");
		if(!f.exists()) {
			System.out.println("El fichero indicado no existe");
			System.exit(0);
		}
		
		
		FileInputStream fis;
		
		try {
			fis = new FileInputStream(f);
			for(int i=0; i< 8103; i++) {
				byte[] b = new byte [90];
				fis.read(b);
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	//Definimos la clase IndiceMuni
	private class IndiceMuni{
		String municipio;
		long posicion;
	}
	
	//constructo
	E06E02() implements compareTo{
		
	}
	
	public String toString(){
	
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
