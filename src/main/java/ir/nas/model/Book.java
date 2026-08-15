package ir.nas.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import ir.nas.enums.StockStatus;
import ir.nas.model.embeddable.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@ToString(callSuper = true, exclude = { "authors", "category" })
@EqualsAndHashCode(callSuper = true, exclude = { "authors", "category" })
@SuperBuilder
@Entity
@Table(name = "books")
public class Book extends BaseModel<UUID>
{
    @Id
    @UuidGenerator(style = Style.VERSION_7)
    private UUID id;

    @Column(name = "title", nullable = false, length = 50, unique = true)
    private String title;

    @Column(name = "isbn", nullable = false, length = 50, unique = true)
    private String ISBN;

    @Column(name = "publication_year", nullable = false, unique = true)
    private int publicationYear;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    @Embedded
    private Address publisherAddress;

    @ManyToMany
    private List<Author> authors;

    @ManyToOne
    private Category category;
}
