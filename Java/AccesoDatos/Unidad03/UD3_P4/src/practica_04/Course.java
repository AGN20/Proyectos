package practica_04;
// Generated 13 dic. 2021 15:47:02 by Hibernate Tools 5.5.7.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Course generated by hbm2java
 */
public class Course implements java.io.Serializable {

	private int id;
	private Person person;
	private String name;
	private Set persons = new HashSet(0);

	public Course() {
	}

	public Course(int id) {
		this.id = id;
	}

	public Course(int id, Person person, String name, Set persons) {
		this.id = id;
		this.person = person;
		this.name = name;
		this.persons = persons;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getPersons() {
		return this.persons;
	}

	public void setPersons(Set persons) {
		this.persons = persons;
	}

}
