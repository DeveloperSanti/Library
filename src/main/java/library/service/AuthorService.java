package library.service;

import library.persistence.entity.AuthorEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<AuthorEntity> getAllAuthor();

    ResponseEntity<Object> getAuthorById(Long authorId);

    ResponseEntity<Object> findByName(String authorName);

    ResponseEntity<Object> saveAuthor(AuthorEntity author);

    ResponseEntity<Object> deleteAuthor(Long authorId);

}
