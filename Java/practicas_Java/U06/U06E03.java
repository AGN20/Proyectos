package pascua;

import java.io.*;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Date;

public class U06E03 {

	public static void main(String[] args) throws IllegalArgumentException, IOException {

		Scanner teclado = new Scanner(System.in);

		/* Utilizamos getPropety para saber la ruta absoluta del 
		   directorio donde nos encontramos */
		String dirActual = System.getProperty("user.dir");
		System.out.println("Capeta actual: " + dirActual);

		/* Preguntamos al usuario por una carpeta y una extension */
		System.out.println("Carpeta a examinar: ");
		String dE = teclado.next();

		System.out.println("Explora ficheros que acaban en: ");
		String fExtension = teclado.next();

		/* Creamos un fichero con los datos dirActual y dE */
		File fDirectorio = new File(dirActual + "\\" + dE);

		System.out.println("[" + dE + "]");

		/*
		 * Creamos una array que guardara todos los ficheros que tiene nuestra carpeta
		 */
		File[] contenido = fDirectorio.listFiles();

		for (int i = 0; i < contenido.length; i++) {

			IdentifiedScheme is = new IdentifiedScheme(contenido[i], fDirectorio, fExtension);
		}
		
		teclado.close();
		
	}
}

/* Creamos la clase Filtro */
class Filtro implements FilenameFilter {

	private String extension;

	/* consturctor */
	public Filtro(String extension) {
		this.extension = extension;
	}

	/* metodo que devuelbe el fillero con extension indicada */
	public boolean accept(File dir, String name) {
		return name.endsWith(extension);
	}

}

/* Creamos la clase IdentifiedScheme */
class IdentifiedScheme {

	private FileInputStream fFinal;

	/* Constructo */
	public IdentifiedScheme(File eleccion, File padre, String ext) throws IllegalArgumentException, IOException {

		Filtro filt = new Filtro(ext);

		if (!eleccion.isDirectory()) {

			String name = eleccion.getName();

			/* inicializamos el FileImputStream fFinal */
			fFinal = new FileInputStream(eleccion);
			int b;
			int pesa = 0;

			do {
				b = fFinal.read();
				pesa++;
			} while ((b = fFinal.read()) >= 0);

			/* Ultima fecha de modificacion */
			long fechaMod = eleccion.lastModified();
			Date ultMod = new Date(fechaMod * 1000);

			String permisos;

			if (!eleccion.canExecute() && !eleccion.canRead() && !eleccion.canWrite()) {
				permisos = "---";
			} else if (!eleccion.canExecute() && eleccion.canRead() && !eleccion.canWrite()) {
				permisos = "R--";
			} else if (!eleccion.canExecute() && !eleccion.canRead() && eleccion.canWrite()) {
				permisos = "-W-";
			} else if (eleccion.canExecute() && !eleccion.canRead() && !eleccion.canWrite()) {
				permisos = "--X";
			} else if (!eleccion.canExecute() && eleccion.canRead() && eleccion.canWrite()) {
				permisos = "RW-";
			} else if (eleccion.canExecute() && eleccion.canRead() && !eleccion.canWrite()) {
				permisos = "R-X";
			} else if (eleccion.canExecute() && !eleccion.canRead() && eleccion.canWrite()) {
				permisos = "-WX";
			} else {
				permisos = "RWX";
			}

			/* trasfoemar el fichero en ruta */
			Path ruta = eleccion.toPath();

			/* Sacamos el propietario del fichero */
			UserPrincipal user = Files.getOwner(ruta);
			String userPropietario = user.toString();

			/* Datos que imprimiremos */
			String datos = (name + ", " + pesa + ", " + ultMod + ", Permisos(RWX): " + permisos + ", Propietario: "
					+ userPropietario);

			/* Padre del fillero elegido */
			File padreF = eleccion.getParentFile();

			datos = padre(padre, padreF, datos);
			if (filt.accept(eleccion, eleccion.getName())) {
				System.out.println(datos);
			}

			/* Si es un directorio */
		} else if (eleccion.isDirectory()) {

			String datos = "[" + eleccion.getName() + "]";
			System.out.println(padre(padre, eleccion, datos));
			File[] elegidos = eleccion.listFiles();
			if (elegidos.length >= 1) {
				for (int i = 0; i < elegidos.length; i++) {
					new IdentifiedScheme(elegidos[i], elegidos[i].getParentFile(), ext);
				}
			}

		}
		
	}

	/* metodo Padre */
	private String padre(File padre, File eleccion, String datos) {

		/* Si la ruta elegida es igual a la de padre ponemos x, si no hacemos y */
		if (eleccion.getParent().equals(padre.toString())) {
			String datosD = padre(padre, eleccion.getParentFile(), (" " + datos));
			return datosD;
		} else {
			return datos;
		}
	}
}
