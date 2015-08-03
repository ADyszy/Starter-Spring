package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;
    
    private BookMapper bookMapper;
    
    private Sequence sequence;
    
    @Before
    public void setUpt() {
    	sequence = new Sequence();
    	bookMapper = new BookMapper();
        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(bookMapper, "sequence", sequence);
        Whitebox.setInternalState(bookService, "bookMapper", bookMapper);
    }


    @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", "author wd");
        AuthorTo author = new AuthorTo(1L, "author wd", null);
        Mockito.when(bookDao.save(Mockito.any(BookEntity.class))).thenReturn(new BookEntity(1L, "title", Arrays.asList(author)));
        // when
        BookTo result = bookService.saveBook(book);
        // then 
		Mockito.verify(bookDao).save(Mockito.any(BookEntity.class));
        assertEquals(1L, result.getId().longValue());
    }

}
