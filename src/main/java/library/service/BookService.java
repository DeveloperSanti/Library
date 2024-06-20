package library.service;

import library.persistence.entity.BookEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    List<BookEntity> findAllBook();

    ResponseEntity<Object> findBookById(Long bookId);

    ResponseEntity<Object> findBookByTitle(String title);

    ResponseEntity<Object> saveBook(BookEntity book);

    ResponseEntity<Object> deleteBook(Long bookId);

}
