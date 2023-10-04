package examen;

import java.io.*;

public class E06E04 {

	public static void main(String[] args) throws ClassNotFoundException{
		
		String dirActual = System.getProperty("user.dir");
		File f = new File(dirActual + "/src/examen/ficheros/conceptos.dat");
		ArbolSN arbol = null;
		if(f.exists()) {
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream( new FileInputStream(f));
				arbol = (ArbolSN)ois.readObject();
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {ois.close();}
				catch(IOException el){}
			}
		}else {
			try{f.createNewFile();}
			catch(IOException e) {}
		}
		
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream( new FileOutputStream(f) );
			oos.writeObject(arbol);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try{oos.close();}
			catch(IOException el) {}
		}
		
		
	}
	
	class ArbolSN{
		String texto;
		ArbolSN hijoS;
		ArbolSN hijoN;
		ArbolSN padre;
	}
	
}
