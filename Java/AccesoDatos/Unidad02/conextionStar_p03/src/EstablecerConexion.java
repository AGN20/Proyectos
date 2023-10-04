import java.sql.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EstablecerConexion {
	
	public static void main(String[]args)throws ClassNotFoundException, SQLException {
		
		//Creamos la conexion
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/starwars", "star", "wars");
		
		//Cramos los selects necesarios
		String selectPlanetas = "SELECT * FROM planets";
		String insert7 = "INSERT INTO films VALUES(7, 'Episode VII', 'The Force Awakens')";
		String insert8 = "INSERT INTO films VALUES(8, 'Episode VIII', 'The last jedi')";
		String insert9 = "INSERT INTO films VALUES(9, 'Episode IX', 'The rise of Skywalker')";
		String selectJedi = "SELECT c.* FROM characters c JOIN character_affiliations cf ON c.id = cf.id_character JOIN affiliations a ON cf.id_affiliation = a.id WHERE a.affiliation = 'Jedi Order' ";
		String deathAndKiller = "SELECT st.name, c.name FROM (SELECT c.* FROM deaths d JOIN characters c ON d.id_character = c.id WHERE d.id_film = 3) st JOIN deaths d ON st.id = d.id_character JOIN characters c ON d.id_killer = c.id";
		
		//Creamos el Statement
		Statement sentencia = conexion.createStatement();
		
		//Resultado
		ResultSet rs1 = sentencia.executeQuery(selectPlanetas);		
		
		System.out.println("PLANETAS");
		while(rs1.next()) {
			System.out.printf("%d, %s, %d, %d, %d, %s, %s, %s, %s, %s, %s, %s \n", rs1.getInt(1), rs1.getString(2), rs1.getInt(3), rs1.getInt(4), rs1.getInt(5), rs1.getString(6), rs1.getString(7), rs1.getString(8), rs1.getString(9), rs1.getString(10), rs1.getString(11), rs1.getString(12), rs1.getString(13));
		}
		rs1.close();
		System.out.println("------------------------------------------------------------");
		System.out.println("");
		
		ResultSet rs5 = sentencia.executeQuery(selectJedi);
		
		System.out.println("ORDEN JEDI");
		while(rs5.next()) {
			System.out.printf("%d, %s, %d, %f, %s, %s, %s, %s, %s, %d, %s, %s, %s \n", rs5.getInt(1), rs5.getString(2), rs5.getInt(3), rs5.getFloat(4), rs5.getString(5), rs5.getString(6), rs5.getString(7), rs5.getString(8), rs5.getString(9), rs5.getInt(10), rs5.getString(11),  rs5.getString(12),  rs5.getString(13)  );
		}
		rs5.close();
		System.out.println("------------------------------------------------------------");
		System.out.println("");
		
		ResultSet rs6 = sentencia.executeQuery(deathAndKiller);
		System.out.println("CADA MUERTO CON SU ASESINO");
		while(rs6.next()) {
			System.out.printf("%s, %s \n", rs6.getString(1), rs6.getString(2) );
		}
		rs6.close();
		System.out.println("------------------------------------------------------------");
		System.out.println("");
		
		System.out.println("Elementos Añadidos");
		System.out.println("");
		
		//Capturaremos la excepcion por si ya se an creado
		try {
			int rs2 = sentencia.executeUpdate(insert7);
			System.out.println("Elemento Creado");
		}catch( Exception e){
			System.out.println("Este elemento ya a sido creado");
		}
		ResultSet rsm1 = sentencia.executeQuery("SELECT * FROM films WHERE id = 7");
		rsm1.next();
		System.out.printf("%d, %s, %s \n", rsm1.getInt(1), rsm1.getString(2), rsm1.getString(3) );
		
		try {
			int rs3 = sentencia.executeUpdate(insert8);
			System.out.println("Elemento Creado");
		}catch( Exception e){
				System.out.println("Este elemento ya a sido creado");
		}
		ResultSet rsm2 = sentencia.executeQuery("SELECT * FROM films WHERE id = 8");
		rsm2.next();
		System.out.printf("%s, %s, %s \n", rsm2.getInt(1), rsm2.getString(2), rsm2.getString(3) );
		
		try {
			int rs4 = sentencia.executeUpdate(insert9);
			System.out.println("Elemento Creado");
		}catch( Exception e){
				System.out.println("Este elemento ya a sido creado");
		}
		ResultSet rsm3 = sentencia.executeQuery("SELECT * FROM films WHERE id = 9");
		rsm3.next();
		System.out.printf("%s, %s, %s \n", rsm3.getInt(1), rsm3.getString(2), rsm3.getString(3) );
		
		
		sentencia.close();
		conexion.close();
		
	}
	
	
}
