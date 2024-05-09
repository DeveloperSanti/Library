package library.persistence.repository;

import library.persistence.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
}
