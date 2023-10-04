package pascua;

import java.io.*;

public class U06E05ADN {

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		
		int i = args[0].lastIndexOf(".");
		String ext = args[0].substring(i+1);

		if(ext.equals("ADN")) {
			try {
				/* Leemos los bytes del fichero */
				FileInputStream datos = new FileInputStream(args[0]);
				byte[] leeido = datos.readAllBytes();
				
				int[] bits = new int[leeido.length];
				
				for(int x=0; x < leeido.length; x++) {
					bits[x] = Integer.parseInt(String.format("%8s", Integer.toBinaryString(leeido[x] & 0xFF)).replace(" ", "0"));
				}
				
				comprimir comp = new comprimir(bits);
				
				datos.close();
				
			}catch ( FileNotFoundException e ){
				System.out.println(e);
			}
		}else if(ext.equals("ADC")) {
			/* Leemos los bytes del fichero */
			try {
				/* Leemos los bytes del fichero */
				FileInputStream datos = new FileInputStream(args[0]);
				System.out.println(datos);
			}catch ( FileNotFoundException e ){
				System.out.println(e);
			}
		}else {
			System.out.println("El fichero seleccionado no contiene ni extension .ADN ni .ADC");
		}
	}
		
}




class comprimir{

	/* Constructor que comprimira el fichero */
	public comprimir(int[] bits){
		 
	}
	
}