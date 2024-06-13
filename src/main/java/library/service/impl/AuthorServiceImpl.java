package library.service.impl;

import library.persistence.entity.AuthorEntity;
import library.persistence.repository.AuthorRepository;
import library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    HashMap<String, Object> info;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorEntity> getAllAuthor() {
        return (List<AuthorEntity>) authorRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> getAuthorById(Long authorId) {
        info = new HashMap<>();
        if(!authorRepository.existsById(authorId)){
            info.put("Error", true);
            info.put("message", "Author not found");
            return new ResponseEntity<>(
                    info,
                    HttpStatus.NOT_FOUND
            );
        }
        info.put("message", "Author found");
        info.put("author", authorRepository.findById(authorId));
        return new ResponseEntity<>(
                info,
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Object> findByName(String author) {
        info = new HashMap<>();
        if (author.isEmpty()) {
            info.put("message", "Author not found");
            return new ResponseEntity<>(
                    info,
                    HttpStatus.CONFLICT
            );
        }
        info.put("message", "Author found");
        info.put("author", authorRepository.findByName(author));
        return new ResponseEntity<>(
                info,
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Object> saveAuthor(AuthorEntity author) {

        Optional<AuthorEntity> res = authorRepository.findByName(author.getAuthorName());
        info = new HashMap<>();

        if (res.isPresent() && author.getAuthorId()==null) {
            info.put("Error", true);
            info.put("message", "The author already exists");
            return new ResponseEntity<>(
                    info,
                    HttpStatus.CONFLICT
            );
        }
        info.put("message", "Author saved successfully");
        if(authorRepository.findById(author.getAuthorId()).isPresent()){
            info.put("message", "Author updated successfully");
        }

        authorRepository.save(author);
        info.put("data", author);
        return new ResponseEntity<>(
                info,
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Object> deleteAuthor(Long authorId) {
        info = new HashMap<>();

        if(!authorRepository.existsById(authorId)){
            info.put("Error", true);
            info.put("message", "Author not found");
            return new ResponseEntity<>(
                    info,
                    HttpStatus.NOT_FOUND
            );
        }

        info.put("message", "Author deleted successfully");
        authorRepository.deleteById(authorId);
        return new ResponseEntity<>(
                info,
                HttpStatus.NO_CONTENT
        );
    }
}
