package lotr;

import java.util.Set;

public class Realm {
	private String id;
	private String name;
	private String population;
	private String area;
	private Set Character;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Set getCharacter() {
		return Character;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCharacter(Set character) {
		Character = character;
	}
	public Realm(String id, String name, String population, String area, Set character) {
		super();
		this.id = id;
		this.name = name;
		this.population = population;
		this.area = area;
		Character = character;
	}
	
}
