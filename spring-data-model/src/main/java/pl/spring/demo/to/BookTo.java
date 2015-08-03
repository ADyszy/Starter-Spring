package pl.spring.demo.to;

public class BookTo implements IdAware {
	private Long id;
	private String title;
	private String authors;

	public BookTo() {
	}

	public BookTo(Long id, String title, String authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	@Override
	public boolean equals(Object obj) {
		if (((BookTo) obj).getId() != this.id)
			return false;
		if (((BookTo) obj).getAuthors().compareTo(this.authors) != 0)
			return false;
		if (((BookTo) obj).getTitle().compareTo(this.title) != 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return title + ", " + authors + ", " + id;

	}
}
