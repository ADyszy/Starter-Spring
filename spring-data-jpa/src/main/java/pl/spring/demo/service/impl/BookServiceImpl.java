package pl.spring.demo.service.impl;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {
	
	@Autowired
    private BookDao bookDao;
	
	@Autowired
	private BookMapper bookMapper;

	//TODO: three below to be checked
    @Override
    public List<BookTo> findAllBooks() {
    	List<BookTo> tmpList = new ArrayList<BookTo>();
        for(BookEntity be : bookDao.findAll()){
        	tmpList.add(bookMapper.getBook(be));
        }
        return tmpList;
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
    	List<BookTo> tmpList = new ArrayList<BookTo>();
        for(BookEntity be : bookDao.findBookByTitle(title)){
        	tmpList.add(bookMapper.getBook(be));
        }
        return tmpList;
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	 List<BookTo> tmpList = new ArrayList<BookTo>();
         for(BookEntity be : bookDao.findBooksByAuthor(author)){
         	tmpList.add(bookMapper.getBook(be));
         }
         return tmpList;
    }

    @Override
    public BookTo saveBook(BookTo book) {
        return bookMapper.getBook(bookDao.save(bookMapper.getBook(book)));
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
