import java.sql.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class EstablecerConexion {
	
	public static void main(String[]args)throws ClassNotFoundException, SQLException {
		
		try {
			
			String cadena;
			
			Connection con = DriverManager.getConnection("jdbc:sqlite:./src/veterinaria.db");
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet resul = dbmd.getTables(null, "mibd", null, null);
			
			while(resul.next()) {
				
				String catalogo = resul.getString("TABLE_CAT");
				String esquema = resul.getString("TABLE_SCHEM");
				String nombre = resul.getString("TABLE_NAME");
				String tipo = resul.getString("TABLE_TYPE");
				
				ResultSet pKey = dbmd.getPrimaryKeys(catalogo, esquema, nombre);
				String pPrimari = "";
				while(pKey.next()) {
					pPrimari = pKey.getString(4);
				}
				pKey.close();
				
				ResultSet pImportKey = dbmd.getImportedKeys(catalogo, esquema, nombre);
				String pImportT = "";
				String pImport = "";
				String pImportR = "";
				while(pImportKey.next()) {
					pImportT = pImportKey.getString(3);
					pImport = pImportKey.getString(4);
					pImportR = pImportKey.getString(8);
				}
				pImportKey.close();
				
				cadena = "CREATE TABLE " + nombre + " (";
				
				ResultSet columnas = dbmd.getColumns(null, "midb", nombre, null);
				
				
				while( columnas.next()) {
					
					String nombreCol = columnas.getString("COLUMN_NAME");
					String tipoCol = columnas.getString("TYPE_NAME");
					String tamCol = columnas.getString("COLUMN_SIZE");
					String nula = columnas.getString("IS_NULLABLE");
					
					cadena = cadena + "\n	" + nombreCol + " " + tipoCol + ",";
				}
				
				
				cadena = cadena + "\n	PRIMARY KEY (" + pPrimari + ")";
				
				
				if(pImport != "") {
					cadena = cadena + ", \n	FOREIGN KEY (" + pImportR + ") REFERENCES "+ pImportT +"("+ pImport +")";
				}
				
				cadena = cadena + "\n );";
				
				System.out.println(cadena);
				
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
