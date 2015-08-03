package pl.spring.demo.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.IdAware;

@Component
public class BookEntity implements IdAware {

	private Long id;
	private String title;
	private List<AuthorTo> authors;

	public BookEntity() {
	}

	public BookEntity(Long id, String title, List<AuthorTo> authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorTo> authors) {
		this.authors = authors;
	}

	@Override
	public boolean equals(Object obj) {
		if (!((BookEntity) obj).getAuthors().equals(this.authors))
			return false;
		if (((BookEntity) obj).getId() != this.id)
			return false;
		if (((BookEntity) obj).getTitle().compareTo(this.title) != 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return title;
	}
	
}
