package lotr;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Main {

	public static void main(String[]args) {
		
		ODB odb = ODBFactory.open("lotr");
		
		
		//Creacion de la BBDD
		relams(odb);
		characters(odb);
		spose(odb);
		books(odb);
		chapter(odb);
		movies(odb);
		moveInBook(odb);
		dialogs(odb);
		
		/*
		Book b = new Book("Libro prueba", null, null);
		Realm r = new Realm("prueba", "Prueba", "Prueba", null);
		Character pj = new Character("PEPE prueba", "www.prueba.com", "human", "11/07", "Male", "11/07/1000", "black", "111", "Maria Prueba", r);
		Chapter  c = new Chapter("Chapter Prueba", b);
		Movie m = new Movie("Peli Prueba", 5, 100, 29, 34, 23, null, null);
		Dialog d = new Dialog("dialog Prueba", m, pj);
		
		OID oid_b = odb.store(b);
		odb.commit();
		OID oid_r = odb.store(r);
		odb.commit();
		OID oid_pj = odb.store(pj);
		odb.commit();
		OID oid_c = odb.store(c);
		odb.commit();
		OID oid_m = odb.store(m);
		odb.commit();
		OID oid_d = odb.store(d);
		odb.commit();
		*/
	}
	
	//Cogemos todos los objetos de realms
	public static void relams(ODB odb) {
		try {
			//Cremos la conexion
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
			//Creamos el statement
			Statement sentencia = con.createStatement();
			//Ejecutamos la onsulta
			ResultSet rsRealms = sentencia.executeQuery("SELECT * FROM realms");

			System.out.println("Introducimos Realms");
			while(rsRealms.next()) {
				String id = rsRealms.getString(1);
				String name = rsRealms.getString(2);
				int population = rsRealms.getInt(3);
				int area = rsRealms.getInt(4);
				
				System.out.println("ID = "+id+", Nombre = "+name+", population = "+population+", area = "+area);
				
				//Añadimos el nuevo objeto de Real y lo guardamos
				Realm r = new Realm(id, name, ""+population, ""+area, null);
				OID oid_save = odb.store(r);
				odb.commit();
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Cogemos todos los objetos de Character
		public static void characters(ODB odb) {
			try {
				//Cremos la conexion
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
				//Creamos el statement
				Statement sentencia = con.createStatement();
				//Ejecutamos la onsulta
				ResultSet rsCharacter = sentencia.executeQuery("SELECT * FROM characters");

				System.out.println("Introducimos Characters");
				while(rsCharacter.next()) {
					String id = rsCharacter.getString(1);
					String name = rsCharacter.getString(2);
					String wikiUrl = rsCharacter.getString(3);
					String race = rsCharacter.getString(4);
					String birth = rsCharacter.getString(5);
					String gender = rsCharacter.getString(6);
					String death = rsCharacter.getString(7);
					String hair = rsCharacter.getString(8);
					String height = rsCharacter.getString(9);
					String id_realm = rsCharacter.getString(10);
					
					Character c = new Character(id,  name,  wikiUrl,  race,  birth,  gender,  death, hair,  height, null,  null);
					
					if(id_realm != null) {
					
						IQuery consultar = new CriteriaQuery(Realm.class, Where.equal("id", id_realm));
						
						Objects <Realm> objetos = odb.getObjects(consultar);
						
						while(objetos.hasNext()) {
							Realm rm = objetos.next();
							
								c.setRealm(rm);
								Set <Character> charar = new HashSet<Character>();
								charar.add(c);
								rm.setCharacter(charar);
								
								OID oid_save2 = odb.store(rm);
								odb.commit();
							
						}
						
					}
					
					OID oid_save = odb.store(c);
					odb.commit();
				
				}
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Para cada character su espose
		public static void spose(ODB odb) {
			try {
				//Cremos la conexion
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
				//Creamos el statement
				Statement sentencia = con.createStatement();

				ResultSet rsMarriage = sentencia.executeQuery("SELECT * FROM marriage");
				
				while(rsMarriage.next()) {
					
					String id_spouse1 = rsMarriage.getString(1);
					String id_spouse2 = rsMarriage.getString(2);
					
					
					
					IQuery consultar = new CriteriaQuery(Character.class, Where.equal("id", id_spouse1));
					Objects <Character> objetos = odb.getObjects(consultar);
					

					
					while(objetos.hasNext()) {
						Character ch = objetos.next();
						
						if(ch.getId().equals(id_spouse1) && id_spouse2 == null) {
							ch.setSpouse("Unknown person");
						}else {
							ch.setSpouse(id_spouse2);
						}
						
						OID oid_save = odb.store(ch);
						odb.commit();
						
					}
					
				}
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Cogemos todos los objetos de Books
		public static void books(ODB odb) {
			try {
				//Cremos la conexion
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
				//Creamos el statement
				Statement sentencia = con.createStatement();
				//Ejecutamos la onsulta
				ResultSet rsBooks = sentencia.executeQuery("SELECT * FROM books");

				System.out.println("Introducimos Books");
				while(rsBooks.next()) {
					String id = rsBooks.getString(1);
					String title = rsBooks.getString(2);

					
					//Añadimos el nuevo objeto de Real y lo guardamos
					Book bk = new Book(id, title, null, null);
					OID oid_save = odb.store(bk);
					odb.commit();
				}
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Metemos todos los chapters u les ponemos sus libros, a libros le ponemos sus chapters
		public static void chapter(ODB odb) {
			try {
				//Cremos la conexion
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
				//Creamos el statement
				Statement sentencia = con.createStatement();
				//Ejecutamos la consulta
				ResultSet rsChapter = sentencia.executeQuery("SELECT * FROM chapters");

				System.out.println("Introducimos chapters");
				while(rsChapter.next()) {
					String id = rsChapter.getString(1);
					String chapter_name = rsChapter.getString(2);
					String id_book = rsChapter.getString(3);
					
					Chapter ch = new Chapter(id,  chapter_name,  null);
					
					if(rsChapter != null) {
					
						IQuery consultar = new CriteriaQuery(Book.class, Where.equal("id", id_book));
						Objects <Book> objetos = odb.getObjects(consultar);
						
						while(objetos.hasNext()) {
							Book bk = objetos.next();
							
								ch.setBook(bk);
								
								if(bk.getChapter() == null) {
									Set <Chapter> charar = new HashSet<Chapter>();
									charar.add(ch);
									bk.setChapter(charar);
									
									OID oid_save2 = odb.store(bk);
									odb.commit();
								}else {
									Set <Chapter> charar = bk.getChapter();
									charar.add(ch);
									bk.setChapter(charar);
									
									OID oid_save2 = odb.store(bk);
									odb.commit();
								}
						}
						
					}
					
					OID oid_save = odb.store(ch);
					odb.commit();
				
				}
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Cogemos todos los objetos de movies
		public static void movies(ODB odb) {
			try {
				//Cremos la conexion
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
				//Creamos el statement
				Statement sentencia = con.createStatement();
				//Ejecutamos la onsulta
				ResultSet rsMovies = sentencia.executeQuery("SELECT * FROM movies");

				System.out.println("Introducimos movies");
				while(rsMovies.next()) {
					String id = rsMovies.getString(1);
					String name = rsMovies.getString(2);
					int runtimeInMinutes = rsMovies.getInt(3);
					int budgetInMillions = rsMovies.getInt(4);
					int boxOfficeRevenuelnMillions = rsMovies.getInt(5);
					int academyAwardNominations = rsMovies.getInt(6);
					int rottenTomatoesScore = rsMovies.getInt(7);

					
					//Añadimos el nuevo objeto de Real y lo guardamos
					Movie mo = new Movie(id, name, runtimeInMinutes, budgetInMillions, boxOfficeRevenuelnMillions, academyAwardNominations, rottenTomatoesScore, null, null);
					OID oid_save = odb.store(mo);
					odb.commit();
				}
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		public static void moveInBook(ODB odb) {
			try {
				//Cremos la conexion
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
				//Creamos el statement
				Statement sentencia = con.createStatement();

				ResultSet rsBM = sentencia.executeQuery("SELECT * FROM books_movies");
				
				while(rsBM.next()) {
					String id_movie = rsBM.getString(2);
					String id_book = rsBM.getString(3);
					
					IQuery consultar = new CriteriaQuery(Book.class, Where.equal("id", id_book));
					Objects <Book> objetos = odb.getObjects(consultar);
					
					IQuery consultar2 = new CriteriaQuery(Movie.class, Where.equal("id", id_movie));
					Objects <Movie> objetos2 = odb.getObjects(consultar2);
					
					while(objetos.hasNext()) {
						Book b = objetos.next();
						
						while(objetos2.hasNext()) {
							
							Movie m = objetos2.next();
							
							if(b.getMovie() == null) {
								Set <Movie> sm = new HashSet<Movie>();
								sm.add(m);
								
								b.setMovie(sm);
								
								OID oid_save = odb.store(b);
								odb.commit();
							}else {
								Set <Movie> sm = b.getMovie();
								sm.add(m);
								
								b.setMovie(sm);
								
								OID oid_save = odb.store(b);
								odb.commit();
							}
							
							if(m.getBook() == null) {
								Set <Book> sb = new HashSet<Book>();
								sb.add(b);
								
								m.setBook(sb);
								
								OID oid_save2 = odb.store(m);
								odb.commit();
							}else {
								Set <Book> sb = m.getBook();
								sb.add(b);
								
								m.setBook(sb);
								
								OID oid_save2 = odb.store(m);
								odb.commit();
							}
							
						}
						
					}

					
				}
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void dialogs(ODB odb) {
			try {
				//Cremos la conexion
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lotr", "root", null);
				//Creamos el statement
				Statement sentencia = con.createStatement();
				//Ejecutamos la consulta
				ResultSet rsDialogs = sentencia.executeQuery("SELECT * FROM dialogs");

				System.out.println("Introducimos dialogs");
				
				int num = 0;
				
				while(rsDialogs.next()) {
					String id = rsDialogs.getString(1);
					String dialog = rsDialogs.getString(2);
					String id_movie = rsDialogs.getString(3);
					String id_character = rsDialogs.getString(3);
					
					Dialog di = new Dialog(id,  dialog,  null, null);
					
					if(rsDialogs != null) {
					
						IQuery consultar = new CriteriaQuery(Movie.class, Where.equal("id", id_movie));
						Objects <Movie> objetos = odb.getObjects(consultar);
						
						
						
						while(objetos.hasNext()) {
							Movie mo = objetos.next();
							
							di.setMovie(mo);
							
							if(mo.getDialog() == null) {
								Set <Dialog> dialogos = new HashSet<Dialog>();
								dialogos.add(di);
								mo.setDialog(dialogos);
								
								System.out.println("ALOJO");
								
								OID oid_save2 = odb.store(mo);
								odb.commit();
							}else {
								Set <Dialog> dialogos = mo.getDialog();
								dialogos.add(di);
								mo.setDialog(dialogos);
								
								num++;
								
								System.out.println("MORA" + " " + num);
								
								OID oid_save2 = odb.store(mo);
								odb.commit();
							}
						}
						
					}
					
					OID oid_save = odb.store(di);
					odb.commit();
				
				}
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
}
