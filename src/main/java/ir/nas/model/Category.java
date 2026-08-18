package ir.nas.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = { "books" })
@ToString(callSuper = true, exclude = { "books" })
@SuperBuilder
@Entity
@Table(name = "categories")
@SequenceGenerator(name = "category_gen_seq", sequenceName = "category_seq", initialValue = 0, allocationSize = 1)
public class Category extends BaseModel<Long>
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen_seq")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    // private List<Book> books = new ArrayList<>();
    private List<Book> books;

    public void addBook(Book book)
    {
        if (this.books == null)
            this.books = new ArrayList<>();

        this.books.add(book);
        book.setCategory(this);
    }
}
