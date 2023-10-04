package lotr;

import java.util.Set;

public class Movie {
	private String id;
	private String name;
	private int runtimeInMinutes;
	private int budgetInMillions;
	private int boxOfficeRevenuelnMillions;
	private int academyAwardNominations;
	private int rottenTomatoesScore;
	private Set book;
	private Set Dialog;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRuntimeInMinutes() {
		return runtimeInMinutes;
	}
	public void setRuntimeInMinutes(int runtimeInMinutes) {
		this.runtimeInMinutes = runtimeInMinutes;
	}
	public int getBudgetInMillions() {
		return budgetInMillions;
	}
	public void setBudgetInMillions(int budgetInMillions) {
		this.budgetInMillions = budgetInMillions;
	}
	public int getBoxOfficeRevenuelnMillions() {
		return boxOfficeRevenuelnMillions;
	}
	public void setBoxOfficeRevenuelnMillions(int boxOfficeRevenuelnMillions) {
		this.boxOfficeRevenuelnMillions = boxOfficeRevenuelnMillions;
	}
	public int getAcademyAwardNominations() {
		return academyAwardNominations;
	}
	public void setAcademyAwardNominations(int academyAwardNominations) {
		this.academyAwardNominations = academyAwardNominations;
	}
	public int getRottenTomatoesScore() {
		return rottenTomatoesScore;
	}
	public void setRottenTomatoesScore(int rottenTomatoesScore) {
		this.rottenTomatoesScore = rottenTomatoesScore;
	}
	public Set getBook() {
		return book;
	}
	public void setBook(Set book) {
		this.book = book;
	}
	public Set getDialog() {
		return Dialog;
	}
	public void setDialog(Set dialog) {
		Dialog = dialog;
	}
	public Movie(String id, String name, int runtimeInMinutes, int budgetInMillions, int boxOfficeRevenuelnMillions,
			int academyAwardNominations, int rottenTomatoesScore, Set book, Set dialog) {
		super();
		this.id = id;
		this.name = name;
		this.runtimeInMinutes = runtimeInMinutes;
		this.budgetInMillions = budgetInMillions;
		this.boxOfficeRevenuelnMillions = boxOfficeRevenuelnMillions;
		this.academyAwardNominations = academyAwardNominations;
		this.rottenTomatoesScore = rottenTomatoesScore;
		this.book = book;
		Dialog = dialog;
	}
	
	
	
}