package pl.spring.demo.dao;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BookDao {

    List<BookEntity> findAll();

    List<BookEntity> findBookByTitle(String title);

    List<BookEntity> findBooksByAuthor(String author);

    BookEntity save(BookEntity book);
}
