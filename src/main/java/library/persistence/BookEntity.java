package library.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private Long authorId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String editorial;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private int quantity;

    private Date publishedDate;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String language;

    private int pages;

    @ManyToOne
    @JoinColumn(name = "authorId", insertable = false, updatable = false)
    private AuthorEntity authorEntity;

}
