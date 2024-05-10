package library.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String name;

    private String address;

    private String phone;

    private String email;

    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "loanId", insertable = false, updatable = false)
    private LoanEntity loan;

}
