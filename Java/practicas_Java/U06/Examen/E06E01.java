package examen;

import java.io.*;

import javax.swing.JFileChooser;

public class E06E01 {

	public static void main(String[] args) throws FileNotFoundException{
		String[] campos = null;
		int totalAltasMujeres = 0;
		int totalAltasHombres = 0;
		int totalBajasMujeres = 0;
		int totalBajasHombres = 0;
		
		File f = null;
		BufferedReader br = null;
		try {
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(jfc);
			String ruta = jfc.getSelectedFile().getAbsolutePath();
			f = new File(ruta);
			while(!f.getPath().contains(".csv")) {
				System.out.println("El fichero especificado no es un .csv");
				return;
			}
			while(!f.getPath().contains("altasYbajas")) {
				System.out.println("El fichero debe ser altasYbajas");
				return;
			}
			
			br = new BufferedReader(new FileReader(f));
			
			String linea;
			
			while((linea = br.readLine()) != null) {
				campos = linea.split(";");
				
				if(campos[3] != null) {
					totalAltasHombres += Integer.parseInt(campos[3]); 
				}
				if(campos[4] != null) {
					totalAltasMujeres += Integer.parseInt(campos[4]); 
				}
				if(campos[5] != null) {
					totalBajasHombres += Integer.parseInt(campos[5]); 
				}
				if(campos[6] != null) {
					totalBajasMujeres += Integer.parseInt(campos[6]); 
				}
				
				int totalBajas = totalBajasHombres+totalBajasMujeres;
				int totalAltas = totalAltasHombres+totalAltasMujeres;
				
				System.out.println("Crecimiento de cantidad de mujeres: " + (totalAltasMujeres-totalBajasMujeres));
				System.out.println("Crecimiento de cantidad de hombres: " + (totalAltasHombres-totalBajasHombres));
				System.out.println("Crecimiento global de la poblacion: " + (totalAltas-totalBajas));
				
				br.close();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
}
