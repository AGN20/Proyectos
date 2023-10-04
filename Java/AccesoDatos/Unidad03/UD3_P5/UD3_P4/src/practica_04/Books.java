package practica_04;
// Generated 17 dic. 2021 16:21:26 by Hibernate Tools 5.5.7.Final

/**
 * Books generated by hbm2java
 */
public class Books implements java.io.Serializable {

	private int id;
	private Course course;
	private String title;
	private Integer year;

	public Books() {
	}

	public Books(int id) {
		this.id = id;
	}

	public Books(int id, Course course, String title, Integer year) {
		this.id = id;
		this.course = course;
		this.title = title;
		this.year = year;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
