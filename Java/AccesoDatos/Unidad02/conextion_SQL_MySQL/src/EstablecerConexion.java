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
		
		String[] newCadena = new String[4];
		String inserts = "";
		
		try {
			
			String cadena = "";
			
			Connection con = DriverManager.getConnection("jdbc:sqlite:./src/veterinaria.db");
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet resul = dbmd.getTables(null, "mibd", null, null);
			
			newCadena[0] = "DROP TABLE IF EXISTS propietario;";
			newCadena[4] = "DROP TABLE IF EXISTS mascota;";
			
			newCadena[0] = cadena;			
			cadena = "";
			
			int cont = 1;
			
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
					
					cadena = cadena + "\n	" + nombreCol + " " + tipoCol + ",";
				}
				
				
				cadena = cadena + "\n	PRIMARY KEY (" + pPrimari + ")";
				
				//Si hay clase forana instroducimos tambien la clase
				if(pImport != "") {
					cadena = cadena + ",\n	FOREIGN KEY (" + pImportR + ") REFERENCES "+ pImportT +"("+ pImport +");";
				}
				
				cadena = cadena + "\n ); \n";
				
				newCadena[cont] = cadena;
				
				cont++;
				
			}
			
			
			//sacamos los datos de las tablas
			String s1 = "SELECT * FROM propietario";
			String s2 = "SELECT * FROM mascota";
			
			Statement sentencia = con.createStatement();
			
			ResultSet rs1 = sentencia.executeQuery(s1);	

			Object[] datos1 = new Object[4];
			
			while(rs1.next()) {
				datos1[0] = rs1.getInt(1);
				datos1[1] = rs1.getString(2);
				datos1[2] = rs1.getString(3);
				datos1[3] = rs1.getDate(4);
			}

			String insert1 = "INSERT INTO propietario VALUES("+datos1[0]+",'"+datos1[1]+"','"+datos1[2]+"','"+datos1[3]+"');";
			
			ResultSet rs2 = sentencia.executeQuery(s2);	

			Object[] datos2 = new Object[4];
			
			while(rs1.next()) {
				datos2[0] = rs1.getInt(1);
				datos2[1] = rs1.getString(2);
				datos2[2] = rs1.getString(3);
				datos2[3] = rs1.getInt(4);
			}

			String insert2 = "INSERT INTO propietario VALUES("+datos2[0]+",'"+datos2[1]+"','"+datos2[2]+"','"+datos2[3]+"');";
			
			inserts = insert1 + "\n" + insert2;
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(newCadena[1]);
		System.out.println(newCadena[2]);
		
		//Conectamos co mysql y creamos las tablas
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria", "veterinaria", "veterinaria");
		Statement sentencia = conexion.createStatement();
		int rs1 = sentencia.executeUpdate(newCadena[0]);
		int rs2 = sentencia.executeUpdate(newCadena[4]);
		int rs3 = sentencia.executeUpdate(newCadena[2]);
		int rs4 = sentencia.executeUpdate(newCadena[1]);
		int rs5 = sentencia.executeUpdate(inserts);
		
		System.out.print(rs1 +"/"+ rs2);
		
		
		conexion.close();
		
	}
	
}
