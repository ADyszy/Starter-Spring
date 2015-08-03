package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

@Service
public class BookMapper {

	@Autowired
	private Sequence sequence;

	public BookEntity getBook(BookTo book) {
		if (book != null)
			return new BookEntity(book.getId(), book.getTitle(), authorStringToList(book.getAuthors()));
		return null;
	}

	public BookTo getBook(BookEntity book) {
		if (book != null)
			return new BookTo(book.getId(), book.getTitle(), authorListToString(book.getAuthors()));
		return null;
	}

	private boolean isLastAuthorOnList(AuthorTo authorTo, List<AuthorTo> list) {
		if (list.get(list.size()-1) == authorTo)
			return true;
		return false;
	}
	
	private String authorListToString(List<AuthorTo> authors) {
		String str = "";
		for (AuthorTo authorTo : authors){
			str += authorTo.getFirstName() + " " + authorTo.getLastName();
			if(!isLastAuthorOnList(authorTo, authors))
				str += ",";
		}
		return str;
	}

	private List<AuthorTo> authorStringToList(String authors) {
		if(authors == null) return null;
		String[] tmp = authors.split(",");
		List<AuthorTo> authorsList = new ArrayList<AuthorTo>();
		for (int i = 0; i < tmp.length; i++) {
			String [] tmp2 = tmp[i].split(" ");
			String name = (tmp2.length > 0)? tmp2[0]:null;
			String lastName = (tmp2.length > 1)? tmp2[1]:null;
			authorsList.add(new AuthorTo(sequence.nextValue(authorsList), name, lastName));
		}
		return authorsList;
	}
}
