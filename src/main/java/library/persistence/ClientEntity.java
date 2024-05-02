package library.persistence;


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

}
