package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		if (title == null)
			return new ArrayList<>();
	
		Set<BookEntity> founds = new HashSet<>();
		title = title.toLowerCase();
		String[] argTitleSubs = title.split(" ");
		for (BookEntity bookEntity : ALL_BOOKS) {
			String[] entitiesTitleSubs = bookEntity.getTitle().split(" ");
			addBookIfMatchingToPrefix(argTitleSubs, entitiesTitleSubs, bookEntity, founds);
		}
		return new ArrayList<>(substractFullTitleFits(title, founds) );
	}

	private Set<BookEntity> substractFullTitleFits(String key, Set<BookEntity> bookEntities) {
		Set<BookEntity> tmpSet = new HashSet<>();
		for (BookEntity bookEntity : bookEntities)
			if (bookEntity.getTitle().toLowerCase().compareTo(key.toLowerCase()) == 0)
				tmpSet.add(bookEntity);
		if (!tmpSet.isEmpty())
			return tmpSet;
		return bookEntities;
	}

	private void addBookIfMatchingToPrefix(String[] prefixes, String[] checkedWords, BookEntity bookEntity,
			Set<BookEntity> bookSet) {
		for (String prefix : prefixes)
			for (String checkedWord : checkedWords)
				if (prefixMatch(prefix, checkedWord))
					bookSet.add(bookEntity);
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		if (author == null)
			return new ArrayList<>();
		Set<BookEntity> founds = new HashSet<>();
		author = author.toLowerCase();
		String[] searchedAuths = author.split(" ");
		for (BookEntity bookEntity : ALL_BOOKS) {
			booksAuthorListPrefixMatch(bookEntity, searchedAuths, founds);
		}
		return new ArrayList<>(founds);
	}

	private void booksAuthorListPrefixMatch(BookEntity bookEntity, String[] searchedAuths, Set<BookEntity> actualFounds) {
		for (AuthorTo authorTo : bookEntity.getAuthors()) {
			String[] bookAuths = authorTo.toString().split(" ");
			addBookIfMatchingToPrefix(searchedAuths, bookAuths, bookEntity, actualFounds);
		}
	}
	
	private boolean prefixMatch(String prefix, String checkedWord) {
		if (prefix.length() > checkedWord.length())
			return false;
		if (checkedWord.toLowerCase().substring(0, prefix.length()).contains(prefix))
			return true;
		return false;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorTo(1L, "William", "Szekspir"))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo(2L, "Hanna", "Ożogowska"))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorTo(3L, "Jan", "Parandowski"))));
		ALL_BOOKS
				.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo(4L, "Edmund", "Niziurski"))));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas",
				Arrays.asList(new AuthorTo(5L, "Zbigniew", "Nienacki"))));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", Arrays.asList(new AuthorTo(6L, "Aleksander", "Fredro"))));
	}

	public Long nextSequence() {
		return sequence.nextValue(ALL_BOOKS);
	}
}
