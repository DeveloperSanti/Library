package library.service.impl;

import library.persistence.entity.LoanEntity;
import library.persistence.repository.LoanRepository;
import library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    HashMap<String, Object> info;

    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<LoanEntity> findAllLoan() {
        return (List<LoanEntity>) loanRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> findLoanById(Long id) {

        info = new HashMap<>();
        if (!loanRepository.existsById(id)) {

            info.put("Error", true);
            info.put("message", "Loan with id " + id + " does not exist");
            return new ResponseEntity<>(
                    info,
                    HttpStatus.NOT_FOUND
            );
        }

        info.put("message", "Loan with id " + id + " found");
        info.put("Loan", loanRepository.findById(id));
        return new ResponseEntity<>(
                info,
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Object> findLoanByDate(Date date, String type) {
        info = new HashMap<>();
        List<LoanEntity> loans;

        if (type.equalsIgnoreCase("delivery")) {
            loans = loanRepository.findByDateDelivery(date);
        } else if (type.equalsIgnoreCase("return")) {
            loans = loanRepository.findByDateReturn(date);
        } else {
            info.put("Error", true);
            info.put("message", "Invalid type. Use 'delivery' or 'return'");
            return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
        }

        if (loans.isEmpty()) {
            info.put("Error", true);
            info.put("message", "No loans found on date " + date + " for type " + type);
            return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
        }

        info.put("message", "Loans found on date " + date + " for type " + type);
        info.put("loans", loans);
        return new ResponseEntity<>(info, HttpStatus.OK);

    }


    @Override
    public ResponseEntity<Object> saveLoan(LoanEntity loan) {
        info = new HashMap<>();

        if (loan.getLoanId()==null) {
            info.put("Error", true);
            info.put("message", "Loan with id " + loan.getLoanId() + " Already exist loan");
            return new ResponseEntity<>(
                    info,
                    HttpStatus.CONFLICT
            );

        }

        info.put("message", "Added loan successfully");
        if (loanRepository.findById(loan.getLoanId()).isPresent()) {
            info.put("Loan", "Loan updated successful");
        }
        info.put("Loan", loan);
        loanRepository.save(loan);
        return new ResponseEntity<>(
                info,
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Object> deleteLoanById(Long id) {

        info = new HashMap<>();

        if (!loanRepository.existsById(id)) {
            info.put("Error", true);
            info.put("message", "Loan with id " + id + " does not exist");
            return new ResponseEntity<>(
                    info,
                    HttpStatus.NOT_FOUND
            );
        }

        info.put("message", "Removed loan successfully with id " + id);
        loanRepository.deleteById(id);
        return new ResponseEntity<>(
                info,
                HttpStatus.NO_CONTENT
        );
    }

}
