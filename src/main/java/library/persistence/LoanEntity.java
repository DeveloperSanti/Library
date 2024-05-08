package library.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "loan")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private Date dateDelivery;

    private Date dateReturn;

    private Boolean returned;

    @OneToMany(mappedBy = "loan")
    private List<BookEntity> books;

    @OneToMany(mappedBy = "loan")
    private List<ClientEntity> clients;

}
