package library.persistence.repository;

import library.persistence.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {

    Optional<AuthorEntity> findByName(String authorName);

}
