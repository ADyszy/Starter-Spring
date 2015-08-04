package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {

	BookMapper bookMapper = new BookMapper();
	Sequence sequence;
	
	@Before
	public void testInit() {
		sequence = new Sequence();
		Whitebox.setInternalState(bookMapper, "sequence", sequence);
	}
	
	@Test
	public void testShouldMapBookFromToToEntityAndBackwards() {
		//given
		BookTo bookTo = new BookTo(1L, "title", "authors authors");
		//when
		BookEntity bookEntity = bookMapper.getBook(bookTo);
		BookTo bookTo2 = bookMapper.getBook(bookEntity);
		//then
		assertTrue(bookTo.equals(bookTo2));
	}
	
	@Test
	public void testShouldMapBookFromEntityToMapAndBackwards() {
		//given
		AuthorTo author = new AuthorTo(1L,"name","lastame");
		BookEntity bookEntity = new BookEntity(1L, "title", Arrays.asList(author));
		//when
		BookTo bookTo = bookMapper.getBook(bookEntity);
		BookEntity bookEntity2 = bookMapper.getBook(bookTo);
		//then
		assertTrue(bookEntity2.equals(bookEntity));
	}
	
	@Test
	public void testShouldReturnNullWhenArgumentIsNull(){
		//given
		BookTo bt = null;
		BookEntity be = null;
		//when
		//then
		assertNull(bookMapper.getBook(bt));
		assertNull(bookMapper.getBook(be));
	}

}
