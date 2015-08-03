package pl.spring.demo.to;

public class AuthorTo implements IdAware {
	private long id;
	private String firstName;
	private String lastName;

	public AuthorTo() {
	}

	public AuthorTo(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (((AuthorTo) obj).getFirstName() != null)
			if (((AuthorTo) obj).getFirstName().compareTo(this.firstName) != 0)
				return false;
		if (((AuthorTo) obj).getLastName() != null)
			if (((AuthorTo) obj).getLastName().compareTo(this.lastName) != 0)
				return false;
		if (((AuthorTo) obj).getId() != null)
			if (((AuthorTo) obj).getId() != this.id)
				return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

}
