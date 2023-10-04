package lotr;

import java.util.Set;

public class Book {
	private String id;
	private String title;
	private Set chapter;
	private Set movie;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set getChapter() {
		return chapter;
	}
	public void setChapter(Set chapter) {
		this.chapter = chapter;
	}
	public Set getMovie() {
		return movie;
	}
	public void setMovie(Set movie) {
		this.movie = movie;
	}
	public Book(String id, String title, Set chapter, Set movie) {
		super();
		this.id = id;
		this.title = title;
		this.chapter = chapter;
		this.movie = movie;
	}
	
}
