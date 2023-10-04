import java.sql.*;

public class EstablecerConexion {
	
	public static void main(String[]args) {
		
		try {
			
			Connection con = DriverManager.getConnection("jdbc:sqlite:./src/veterinaria.db");
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet resul = dbmd.getTables(null, "mibd", null, null);
			
			while(resul.next()) {
				
				String catalogo = resul.getString("TABLE_CAT");
				String esquema = resul.getString("TABLE_SCHEM");
				String nombre = resul.getString("TABLE_NAME");
				String tipo = resul.getString("TABLE_TYPE");
				
				ResultSet columnas = dbmd.getColumns(null, "midb", nombre, null);
				
				System.out.println("catalogo: " + catalogo + " esquema: " + esquema + " nombre: " + nombre + " tipo: " + tipo );
				while( columnas.next()) {
					
					String nombreCol = columnas.getString("COLUMN_NAME");
					String tipoCol = columnas.getString("TYPE_NAME");
					String tamCol = columnas.getString("COLUMN_SIZE");
					String nula = columnas.getString("IS_NULLABLE");
					
					System.out.println(" ---> nombre: " + nombreCol+ " tipo: " + tipoCol + " tamaño: " + tamCol +" nulo: " + nula );
					
				}
				
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
