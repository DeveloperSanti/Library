package library.persistence.repository;

import library.persistence.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity,Long> {
}
