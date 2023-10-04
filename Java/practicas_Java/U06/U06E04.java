package pascua;

import java.io.*;
import java.util.Scanner;

public class U06E04 {

	public static void main(String[] args) throws IllegalArgumentException, IOException {

		Scanner teclado = new Scanner(System.in);

		/* Preguntamos por un fichero con extension .GPS */
		System.out.print("Fichero (.GPS): ");
		String fichero = teclado.nextLine();

		int i = fichero.lastIndexOf(".");
		String ext = fichero.substring(i + 1);
		String extLower = ext.toLowerCase();

		if (!extLower.equals("gps")) {
			System.out.println("Lo que a indicado no es un archivo con la extension .gps");
		} else {

			String origen;
			String destino;

			/* Repite, mientras los dos no esten vacios */
			do {
				/* Preguntamos por el origen */
				System.out.print("Origen: ");
				origen = teclado.nextLine();

				/* Preguntamos por el destino */
				System.out.print("Destino: ");
				destino = teclado.nextLine();

				/* Si las dos no estan vacias muestra la ruta mas corta */
				if (origen.isEmpty() == false || destino.isEmpty() == false) {

					/* Indica la ruta mas corta para llegar a nuestro destino */
					Grafo ruta = new Grafo(fichero);

				}

			} while (origen.isEmpty() == true || destino.isEmpty() == true);

		}
		
		teclado.close();
		
	}
}

class Grafo {

	private int nV = 0, nA = 0;
	private String[] v;
	private int[][] tA;
	private File f;
	long nLineas = 0;
	private final int MAYOR_ENTERO = 2147483647;

	public Grafo(String fichero) throws IOException {

		String actual = System.getProperty("user.dir");
		f = new File(actual + "/src/pascua/ficheros/" + fichero);
		String linea;
		String lineaFor;

		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedReader bf = new BufferedReader(new FileReader(f));
			/* contamos el numero de lineas */
			while ((linea = bf.readLine()) != null) {
				nLineas++;
			}

			for (int i = 0; i < nLineas; i++) {
				if ((lineaFor = bf.readLine()) != null) {
					if (i == 0) {
						nV = Integer.parseInt(lineaFor);
					} else if (i == 1) {
						nA = Integer.parseInt(lineaFor);
					} else {
						try {
							String[] lPartidas = lineaFor.split(" ");
							if (lPartidas.length == 2) {
								if (Integer.parseInt(lPartidas[0]) < 0 || Integer.parseInt(lPartidas[0]) > nV) {
									System.out.println("ERROR FORMATO en " + f.toString() + ", Línea " + i + 1);
									System.exit(0);
								}
							} else if (lPartidas.length == 3) {
								if (Integer.parseInt(lPartidas[0]) < 0 || Integer.parseInt(lPartidas[0]) > nA
										|| Integer.parseInt(lPartidas[1]) < 0 && Integer.parseInt(lPartidas[1]) > nA) {
									System.out.println("ERROR FORMATO en " + f.toString() + ", Línea " + i + 1);
									System.exit(0);
								}
							} else if (lPartidas.length != 3 && (i != 0 || i != 1)) {
								System.out.println("ERROR FORMATO en " + f.toString() + ", Línea " + i + 1);
								System.exit(0);
							}

							fis.close();
							bf.close();

						} catch (Exception e) {
							System.out.println(e);
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String caminoMasCorto(String origen, String destino) {

		String devuelve;

		if (origen.equals("") || destino.equals("")) {
			devuelve = "Ciudad origen/destino (origen: " + origen + ", destino: " + destino + ") no encontrada.";
		} else if (origen.equals(destino)) {
			devuelve = origen + " (+0)-> " + destino;
		} else {
			devuelve = cmcDijkstra(buscaVertices(origen), buscaVertices(destino));
		}

		return devuelve;
	}

	private String cmcDijkstra(int o, int d) {
		String salida;
		int S = 0;
		int[] D = new int[nV];
		int[] P = new int[nV];
		S = S+o;
		for(int i = 1; i < nV; i++) {
			if(i != 0 || existeArista(i,o)) {
				D[i] = tA[o][i];
			}else {
				D[i] = MAYOR_ENTERO;
			}
		}
		
		for(int i = 1; i < nV; i++) {
			if(S == nV) {
				break;
			}
		
		}
	}

	private int buscaVertices(String ciudad) {
		
		String linea;
		String lineaFor = "";
		int devolver = 0;
		
		try {
			
			FileInputStream fis = new FileInputStream(f);
			BufferedReader bf = new BufferedReader(new FileReader(f));
			
			while ((linea = bf.readLine()) != null) {
				nLineas++;
			}
			for (int i = 0; i < nLineas; i++) {
				try {
					String[] lPartidas = lineaFor.split(" ");
					if (lPartidas.length == 2) {
						if (lPartidas[1].equals(ciudad)) {
							devolver = Integer.parseInt(lPartidas[0]);
						} else {
							devolver = -1;
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}finally {
					fis.close();
					bf.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return devolver;
	}

	private boolean existeArista(int o, int d) {

		String linea;
		String lineaFor = "";
		boolean devolver = false;

		try {

			FileInputStream fis = new FileInputStream(f);
			BufferedReader bf = new BufferedReader(new FileReader(f));

			while ((linea = bf.readLine()) != null) {
				nLineas++;
			}
			for (int i = 0; i < nLineas; i++) {
				try {
					String[] lPartidas = lineaFor.split(" ");
					if (lPartidas.length == 3) {
						if (Integer.parseInt(lPartidas[0]) == o && Integer.parseInt(lPartidas[1]) == d) {
							devolver = true;
						} else {
							devolver = false;
						}
					}
					
					fis.close();
					bf.close();
					
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return devolver;
	}
}

	class ConjuntoEnteros{
		
		private int n;
		private int[] c;
		private int max;
		
		public ConjuntoEnteros(int max) {
			this.max = max;
		}
	

	
}
