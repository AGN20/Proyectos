package practica_04;

import java.util.List;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[]args){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory( new StandardServiceRegistryBuilder().configure().build());
		
		Session sesion = sf.openSession();
		
		ejercicio1(sesion);
		ejercicio2(sesion);
		ejercicio3(sesion);
		ejercicio4(sesion);
		
		sesion.close();
	}
	
	public static void ejercicio1(Session sesion) {

		//Select que nos dara las personas que tengan el apedido Potter
		String familyPotter = "from Person where last_name = 'Potter'";

		
		System.out.println("Ejercicio 1:");
		System.out.println("");
		Query q = sesion.createQuery(familyPotter);
		List <Person> dl = q.list();
		for(Person l: dl) System.out.println(l.getFirstName()+" "+l.getLastName()+" - "+l.getHouse().getName());
		System.out.println("");

	}
	
	public static void ejercicio2( Session sesion) {

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
		
	}
	
	public static void ejercicio3(Session sesion) {

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
		
	}
	
	public static void ejercicio4(Session sesion) {

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
		
	}
	
}
