package pascua;

import java.io.*;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;

public class U6E02A {

	static double num;
	static DecimalFormat form = new DecimalFormat("0.000000");
	
	public static void main(String[] args) throws FileNotFoundException {
		

		
		try {
			
			long t = System.currentTimeMillis();
			
			JFileChooser fileC = new JFileChooser();
			fileC.showOpenDialog(fileC);
			
			String ruta = fileC.getSelectedFile().getAbsolutePath();
			File f = new File(ruta);

			
			FileInputStream inputF = new FileInputStream(f);
			int[] contInt = new int[250];
			int[] repetidos = new int [250];
			byte[] b = new byte[250];
			
			int tb = inputF.read(b);
			
			double tiempo = (t/1000)%60;
			
			System.out.println("Peso: " + tb + " bytes. Proceso realizado en " + tiempo + " segundos.");
			
			for (int y= 0; y < b.length; y++) {
				repetidos[y] = b[y];
				for(int x= 0; x < b.length; x++) {
					if (b[y] == b[x]) {
						contInt[y]++;
						if(y != x) {
							b[y] = 0;
						}
					}
				}
				
				if (b[y] != 0) {
					num = (double) contInt[y] / tb;
					System.out.println("h: [" + b[y] + "] " + form.format(num));
				}
			}
			
			inputF.close();
			
		}catch (IOException e) {
			
			e.printStackTrace();
			
		}
	
	}
	
}
