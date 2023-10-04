package pascua;

import java.io.*;
import java.util.Base64;
import java.util.Scanner;

public class U06E07 {

	
	public static void main (String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		/* Pedimos el fichero de entrada */
		System.out.println("Teclee el Fichero binario: ");
		String fichero = teclado.nextLine();
		/* Indicamos la ruta inicial del fichero */
		String dirInicial = System.getProperty("user.dir");
		/* Creamos el objeto */
		File f = new File(dirInicial+ "/src/pascua/ficheros/" + fichero);
		/* Comprobamos si el fichero existe */
		if(!f.exists()) {
			System.out.println("El fichero indicado no existe");
			System.exit(0);
		}
		
		/* creamos una array de bytes con la longitud del fichero */
		byte[] b = new byte [(int)f.length()];
		FileInputStream fis;
		String cambio;
		/* Pedimos el fichero de salida */
		System.out.println("Teclee el fichero de salida: ");
		String fCopia = teclado.nextLine();
		/* Creamos el objeto */
		File fC = new File(dirInicial+ "/src/pascua/ficheros/" + fCopia);
		/* Comprobamos si el fichero existe */
		if(!fC.exists()) {
			System.out.println("El fichero indicado no existe");
			System.exit(0);
		}
		
		try {
			
			fis = new FileInputStream(f);
			fis.read(b);
			String deco = new String(Base64.getDecoder().decode(b));
			Decodificador n = new Decodificador();
			FileOutputStream fos = new FileOutputStream(fC);
			String d = n.decodificar(b);
			fos.write(d.getBytes());
			/* Cerramos los ficheros */
			fis.close();
			fos.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	static class Decodificador{
		public static String decodificar(byte[] b) {
			
			/* Nos creamos el abecedario */
			char[] abecedario = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
		            'Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f',
		            'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
		            'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'};
			/* Creamos el objeto donde guardaremos el resultado */
			StringBuilder guardar = new StringBuilder();
			
			int pad = 0;
			
			for(int i=0; i<b.length; i++) {
				
				int bi = ((b[i+1] & 0xFF) << 16) & 0xFFFFFF;
				
				if (i + 1 < b.length) {
					bi |= (b[i] & 0xFF) << 8;
				}else {
					pad++;
				}
				
				if (i + 2 < b.length) {
					bi |= (b[i+2] & 0xFF);
				}else {
					pad++;
				}
				
				for(int j = 0; j < 4 - pad;j++) {
					int c = (bi & 0xFC0000) >> 18;
					guardar.append(abecedario[c]);
					bi <<= 6;
				}
				
			}
			
			for(int j=0; j < pad; j++) {
				
				guardar.append("=");
				
			}
			
			return guardar.toString();
			
		}
	}
	
}
