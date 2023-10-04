package practica_04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[]args){
		//EJERCICO 1
		/*
		ejercicio1(sesion);
		ejercicio2(sesion);
		ejercicio3(sesion);
		ejercicio4(sesion);
		*/
		//EJERCICIO 2
		/*
		ejercicio1P05();
		ejercicio1_2P05();
		ejercicio2P05();
		ejercicio3P05();
		ejercicio4P05();
		*/
	}
	
	/*
	public static void ejercicio1(Session sesion) {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		
		//Select que nos dara las personas que tengan el apedido Potter
		String familyPotter = "from Person where last_name = 'Potter'";

		
		System.out.println("Ejercicio 1:");
		System.out.println("");
		Query q = sesion.createQuery(familyPotter);
		List <Person> dl = q.list();
		for(Person l: dl) System.out.println(l.getFirstName()+" "+l.getLastName()+" - "+l.getHouse().getName());
		System.out.println("");
		
		sesion.close();

	}
	
	public static void ejercicio2( Session sesion) {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		
		System.out.println("Ejercicio 2:");
		System.out.println("");
		//Select que nos da el apedido y el nombre de las personas matriculadas
		String allAlumns = "from Person p inner join p.courses_1 group by 1 order by p.lastName";
		//Select que nos da el numeros total de personas matriculadas
		String numAlumns = "select count(distinct p.id) from Person p inner join p.courses_1 c";
		
		ScrollableResults sc = sesion.createQuery(allAlumns).scroll();
		while(sc.next()) {
			Person p = (Person)sc.get(0);
			System.out.println(p.getLastName()+", "+p.getFirstName());
		}
		
		long p1 = (long)sesion.createQuery(numAlumns).uniqueResult();
		System.out.println("Son "+p1+" Alumnos");
		System.out.println("");
		
		sesion.close();
		
	}
	
	public static void ejercicio3(Session sesion) {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		
		System.out.println("Ejercicio 3:");
		System.out.println("");
		//Select que nos dara los id's de Harry, Ron y Hermione
		List<Person> idsPersonajes = sesion.createQuery("from Person where first_name = 'Harry' or first_name = 'Ron' or first_name = 'Hermione'").getResultList();
		
		for( Person i: idsPersonajes) {
			int idPersonaje = i.getId();
			
			//Select que dara quien da los puntos y cuantos puntos da
			List<HousePoints> puntos = sesion.createQuery("from HousePoints where receiver = ?1").setParameter(1,idPersonaje).getResultList();
				
				for(HousePoints z: puntos) {
					int punt = z.getPoints();

					System.out.println(z.getPersonByGiver().getFirstName()+" "+ z.getPersonByGiver().getLastName() + " -> " + z.getPoints() + 
							" para " + z.getPersonByReceiver().getFirstName()+" "+z.getPersonByReceiver().getLastName());
					
				}
				
			
		}
		
		//Sumamos los puntos y sacamos los puntos totales
		long puntos = (long) sesion.createQuery("select sum(points) from HousePoints where personByReceiver.id = ?1 or personByReceiver.id = ?2 or personByReceiver.id = ?3").setParameter(1,8).setParameter(2,19).setParameter(3,28).uniqueResult();
		
		System.out.println("Puntos totales: " + puntos);
		System.out.println("");
		
		sesion.close();
		
	}
	
	public static void ejercicio4(Session sesion) {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		
		System.out.println("Ejercicio 4:");
		System.out.println("");
		
		//Select que nos da los estudiantes que estudian pociones y que son de griffindon con INNER JOIN
		System.out.println("CON INNER JOIN----------------------------------------------------");
		String estu = "from Person p inner join p.house h inner join p.courses_1 c where h.name like 'Gryffindor' and c.name like 'Potions'";
		
		ScrollableResults scr = sesion.createQuery(estu).scroll();
		while(scr.next()) {
			Person alumn = (Person)scr.get(0);
			Course curso = (Course)scr.get(2);
			System.out.println(alumn.getFirstName() + " " + alumn.getLastName() + " estudia " + curso.getName() + " con " + curso.getPerson().getFirstName() + " " + curso.getPerson().getLastName());	
		}
		
		String numAlumns = "select count(distinct p.id) from Person p inner join p.house h inner join p.courses_1 c where h.name like 'Gryffindor' and c.name like 'Potions'";
		long totalAlumnos = (long) sesion.createQuery(numAlumns).uniqueResult();
		System.out.println("Total estudiantes: " + totalAlumnos);
		
		System.out.println("");
		
		System.out.println("CON FETCH---------------------------------------------------------");
		//Select que nos da los estudiantes que estudian pociones y que son de griffindon con INNER JOIN
		String estu2 = "from Person p join fetch p.house h join fetch p.courses_1 c where h.name like 'Gryffindor' and c.name like 'Potions'";
		
		ScrollableResults scr2 = sesion.createQuery(estu2).scroll();
		while(scr2.next()) {
			Person alumn = (Person)scr2.get(0);
			System.out.println(alumn.getFirstName() + " " + alumn.getLastName() + " estudia Potions con Severus Snape");	
		}
		
		String numAlumns2 = "select count(distinct p.id) from Person p inner join p.house h inner join p.courses_1 c where h.name like 'Gryffindor' and c.name like 'Potions'";
		long totalAlumnos2 = (long) sesion.createQuery(numAlumns2).uniqueResult();
		System.out.println("Total estudiantes: " + totalAlumnos2);
		
		sesion.close();
		
	}
	*/
	
	//PRACTICA 05
	
	//--> Creacion de la tabla libros
	public static void ejercicio1P05() {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		Transaction tx = sesion.beginTransaction();
		 
		String sql = "CREATE TABLE books(\r\n"
				 + "	id INTEGER,\r\n"
				 + "	title varchar(200),\r\n"
				 + "	year INTEGER,\r\n"
				 + "	subject INTEGER,\r\n"
				 + "	PRIMARY KEY (id),\r\n"
				 + "	FOREIGN KEY (subject) REFERENCES course(id)"
				 + ");";
		 
		sesion.createSQLQuery(sql).executeUpdate();
		tx.commit();
		 	
	}
	
	//--> Insertamos todos los libros en books
	public static void ejercicio1_2P05() {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("./src/files/Harry_Potter_libros.csv"));
			String line = br.readLine();
			int cont = 0;
			while (null!=line) {
				String [] fields = line.split(";");
				
				cont++;
				if(!fields[0].equals("Title")) {
					System.out.println(Arrays.toString(fields));
					Session sesion = sf.openSession();
					Transaction tx = sesion.beginTransaction();
					
					Books book = new Books(); 
					
					String curso = "from Course c where c.name like '" + fields[2]+"'";
					Course c = (Course) sesion.createQuery(curso).uniqueResult();
					
					book.setId(cont-1);
					book.setTitle(fields[0]);
					book.setYear(Integer.parseInt(fields[1]));
					book.setCourse(c);
					
					
					sesion.save(book);
					tx.commit();
					sesion.close();
				}
		        
		        line = br.readLine();
			}
			
			br.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	//--> PUNTOS QUITADOS A H Y P POR SS Y MULTIPLICALOS
	public static void ejercicio2P05() {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		Transaction tx = sesion.beginTransaction();
		
		String sql = "select sum(h.points) from HousePoints h where giver = 99 and receiver = 19 or receiver = 28";
		long puntosHR = (long) sesion.createQuery(sql).uniqueResult();
		System.out.println("Puntos quitados a H y R por SS: " + puntosHR);
		
		String insert = "INSERT INTO HousePoints (personByGiver, personByReceiver, points) " + 
		"SELECT h.personByGiver, h.personByReceiver, h.points FROM HousePoints h WHERE h.personByGiver = 99 and h.personByReceiver = 19 or h.personByReceiver = 28";
		sesion.createQuery(insert).executeUpdate();
		tx.commit();
		
		String sql2 = "select sum(h.points) from HousePoints h where giver = 99 and receiver = 19 or receiver = 28";
		long puntosHR2 = (long) sesion.createQuery(sql2).uniqueResult();
		System.out.println("Puntos quitados a H y R por SS: " + puntosHR2);
		
		sesion.close();
	}
	
	//--> ALUMNOS DE MUGGLE STUDIES Y LE AÑADIMOS LOS DE POTIONS
	public static void ejercicio3P05() {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		Transaction  tx = sesion.beginTransaction();
		
		String sql = "select count(p.id) from Person p inner join p.courses_1 c where c.name like 'Muggle Studies'";
		
		String personPociones = "from Person p inner join p.courses_1 c where c.name like 'Potions'";
		
		String sql2 = "from Course c where c.name like 'Muggle Studies'";
		
		Long numAlumnos = (Long) sesion.createQuery(sql).uniqueResult();
		System.out.println("Alumnos matriculados en Muggle Studies: " + numAlumnos);
		
		ScrollableResults scr = sesion.createQuery(personPociones).scroll();
		
		Course muggle = (Course) sesion.createQuery(sql2).uniqueResult();
		
		while(scr.next()) {
			
			if(sesion.isOpen() == false) {
				sesion = sf.openSession();
				tx = sesion.beginTransaction();
			}
			
			Person alumn = (Person)scr.get(0);
			
			Set<Course> cursos = alumn.getCourses_1();		
			cursos.add(muggle);
			alumn.setCourses_1(cursos);
			sesion.save(alumn);
			tx.commit();
			
			sesion.close();

		}
		
		Long numAlumnos2 = (Long) sesion.createQuery(sql).uniqueResult();
		System.out.println("Alumnos matriculados en Muggle Studies: " + numAlumnos2);
		
		
		
		sesion.close();
	}
	
	//--> ALUMNOS DE Flying Y LE AÑADIMOS LOS DE Transfiguration
		public static void ejercicio4P05() {
			Configuration cfg = new Configuration().configure();
			SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
			
			Session sesion = sf.openSession();
			Transaction  tx = sesion.beginTransaction();
			
			String sql = "select count(p.id) from Person p inner join p.courses_1 c where c.name like 'Flying'";
			
			String personPociones = "from Person p inner join p.courses_1 c where c.name like 'Transfiguration'";
			
			String sql2 = "from Course c where c.name like 'Flying'";
			
			Long numAlumnos = (Long) sesion.createQuery(sql).uniqueResult();
			System.out.println("Alumnos matriculados en Volar: " + numAlumnos);
			
			ScrollableResults scr = sesion.createQuery(personPociones).scroll();
			
			Course muggle = (Course) sesion.createQuery(sql2).uniqueResult();
			
			while(scr.next()) {
				
				if(sesion.isOpen() == false) {
					sesion = sf.openSession();
					tx = sesion.beginTransaction();
				}
				
				Person alumn = (Person)scr.get(0);
				
				Set<Course> cursos = alumn.getCourses_1();		
				cursos.add(muggle);
				alumn.setCourses_1(cursos);
				sesion.save(alumn);
				tx.commit();
				
				sesion.close();

			}
			
			Long numAlumnos2 = (Long) sesion.createQuery(sql).uniqueResult();
			System.out.println("Alumnos matriculados en Volar: " + numAlumnos2);
			
			
			
			sesion.close();
		}
	
}
