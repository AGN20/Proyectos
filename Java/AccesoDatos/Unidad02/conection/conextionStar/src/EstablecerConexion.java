import java.sql.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EstablecerConexion {
	
	static Connection con;

	static void conec() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String USR = "star";
		final String PWD = "wars";
		con = DriverManager.getConnection("jdbc:mysql://localhost/starwars", USR, PWD);
		System.out.println("Conectado...");

	}

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		String s = bd();
		System.out.println("");
		
		Boolean b = false;
		while (b == false) {
			System.out.print("¿Quiere guardar el archivo o mostrarlo por pantalla? (mostrar, guardar, salir): ");
			String respuesta = entrada.next();
			System.out.println("\n");
			if (respuesta.equalsIgnoreCase("guardar")) {
				try {
					guadra(s);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("   Archivo guardado!!! Saliendo...");
				b = true;
			} else if (respuesta.equalsIgnoreCase("mostrar")) {
				System.out.println(s);
				System.out.println("  Mostrado, Saliendo...");
				b = true;
			} else if (respuesta.equalsIgnoreCase("salir")) {
				System.out.println("   Saliendo...");
				b = true;
			} else {
				System.out.println("   ERROR: No ha tecleado Mostrar o Guardar, vuelva a repetir...");
				b = false;
			}
		}
		System.out.println("\n");
		entrada.close();
		System.exit(0);

	}

	static String bd() {
		String result = "";
		try {
			conec();
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet bdgeneral = dbmd.getTables("starwars", null, null, null);
			int i = 0;
			while (bdgeneral.next()) {
				i++;

				// Nombre de tablas
				String tabla = bdgeneral.getString("TABLE_NAME");
				result += "Tabla " + tabla + "\n";
				result += "\n";

				// Primary Keys
				ResultSet bdpk = dbmd.getPrimaryKeys(null, null, tabla);
				String pk = "Clave primaria: ";
				while (bdpk.next()) {
					pk += bdpk.getString(4);
					if(bdpk.next()) {
						pk+=" - ";
					}
				}

				result += pk + "\n";

				// Columnas
				ResultSet bdtablas = dbmd.getColumns(null, "starwars", tabla, null);

				result += "Columnas:\n";

				while (bdtablas.next()) {
					String nombreCol = bdtablas.getString("COLUMN_NAME");
					String tipoCol = bdtablas.getString("TYPE_NAME");
					result += nombreCol + " - " + tipoCol  +"\n";
				}
				result += "\n";
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	private static void guadra(String data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("starBD.txt")));
		String escribir = data;
		writer.write(escribir);
		writer.close();

	}
	
}
