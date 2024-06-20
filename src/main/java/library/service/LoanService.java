package library.service;

import library.persistence.entity.LoanEntity;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface LoanService {

    List<LoanEntity> findAllLoan();

    ResponseEntity<Object> findLoanById(Long id);

    ResponseEntity<Object> findLoanByDate(Date date, String type);

    ResponseEntity<Object> saveLoan(LoanEntity loan);

    ResponseEntity<Object> deleteLoanById(Long id);

}
