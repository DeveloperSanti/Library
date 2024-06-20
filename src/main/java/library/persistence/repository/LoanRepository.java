package library.persistence.repository;

import library.persistence.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<LoanEntity,Long> {

    List<LoanEntity> findByDateDelivery(Date date);
    List<LoanEntity> findByDateReturn(Date date);
}
