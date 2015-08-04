package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;

public class BookDaoImplTest {

	BookDaoImpl bookDaoImpl;

	private Set<BookEntity> initTestBooks() {
		Set<BookEntity> books = new HashSet<>();
		books.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorTo(1L, "William", "Szekspir"))));
		books.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo(2L, "Hanna", "Ożogowska"))));
		books.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorTo(3L, "Jan", "Parandowski"))));
		books.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo(4L, "Edmund", "Niziurski"))));
		books.add(new BookEntity(5L, "Pan Samochodzik i Fantomas",
				Arrays.asList(new AuthorTo(5L, "Zbigniew", "Nienacki"))));
		books.add(new BookEntity(6L, "Zemsta", Arrays.asList(new AuthorTo(6L, "Aleksander", "Fredro"))));
		return books;
	}

	@Before
	public void testInit() {
		bookDaoImpl = new BookDaoImpl();

		// to be sure of test data.
		Whitebox.setInternalState(bookDaoImpl, "ALL_BOOKS", initTestBooks());
	}

	@Test
	public void testShouldFindAllBooks() {
		List<BookEntity> testList = bookDaoImpl.findAll();
		assertNotNull(testList);
		assertTrue(!testList.isEmpty());
	}

	@Test
	public void testShouldFindBooksByWholeTitle() {
		List<BookEntity> testList = bookDaoImpl.findBookByTitle("Opium w rosole");
		assertNotNull(testList);
		assertTrue(!testList.isEmpty());
		assertEquals(2L, testList.get(0).getId().longValue());
	}

	
	@Test
	public void testShouldFindBooksByOneWord() {
		List<BookEntity> testList = bookDaoImpl.findBookByTitle("Opium");
		assertNotNull(testList);
		assertTrue(!testList.isEmpty());
		assertEquals(2L, testList.get(0).getId().longValue());
	}

	@Test
	public void testShouldFindBooksByAPrefix() {
		List<BookEntity> testList = bookDaoImpl.findBookByTitle("roso");
		assertNotNull(testList);
		assertTrue(!testList.isEmpty());
		assertEquals(2L, testList.get(0).getId().longValue());
	}

	@Test
	public void testShouldGiveEmptyList() {
		List<BookEntity> testList = bookDaoImpl.findBookByTitle(null);
		assertNotNull(testList);
		assertTrue(testList.isEmpty());
	}

	@Test
	public void testShouldFindBooksByWholeAuthor() {
		List<BookEntity> testList = bookDaoImpl.findBooksByAuthor("William Szekspir");
		assertNotNull(testList);
		assertTrue(!testList.isEmpty());
		assertEquals(1L, testList.get(0).getId().longValue());
	}
	
	@Test
	public void testShouldFindBooksByOneWordOfAuthor() {
		List<BookEntity> testList = bookDaoImpl.findBooksByAuthor("Szekspir");
		assertNotNull(testList);
		assertTrue(!testList.isEmpty());
		assertEquals(1L, testList.get(0).getId().longValue());
	}
	
	@Test
	public void testShouldFindBooksByAPrefixOfAuthor() {
		List<BookEntity> testList = bookDaoImpl.findBooksByAuthor("Szeks");
		assertNotNull(testList);
		assertTrue(!testList.isEmpty());
		assertEquals(1L, testList.get(0).getId().longValue());
	}
}
