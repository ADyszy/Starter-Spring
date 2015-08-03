package pl.spring.demo.dao;

import pl.spring.demo.to.BookTo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BookDao {

    List<BookTo> findAll();

    List<BookTo> findBookByTitle(String title);

    List<BookTo> findBooksByAuthor(String author);

    BookTo save(BookTo book);
}
