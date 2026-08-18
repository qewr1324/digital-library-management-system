package ir.nas.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
// import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = { "authors", "category" })
@EqualsAndHashCode(callSuper = true, exclude = { "authors", "category" })
// @SuperBuilder
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

    @Column(name = "publication_year", nullable = false)
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

    public static Builder builder()
    {
        return new Book().new Builder();
    }

    public class Builder
    {
        private final Book book;

        public Builder()
        {
            this.book = new Book();
        }

        public Builder id(final UUID id)
        {
            this.book.setId(id);
            return this;
        }

        public Builder title(final String title)
        {
            this.book.setTitle(title);
            return this;
        }

        public Builder ISBN(final String ISBN)
        {
            this.book.setISBN(ISBN);
            return this;
        }

        public Builder publicationYear(final int publicationYear)
        {
            this.book.setPublicationYear(publicationYear);
            return this;
        }

        public Builder price(final BigDecimal price)
        {
            this.book.setPrice(price);
            return this;
        }

        public Builder stockStatus(final StockStatus stockStatus)
        {
            this.book.setStockStatus(stockStatus);
            return this;
        }

        public Builder publisherAddress(final Address publisherAddress)
        {
            this.book.setPublisherAddress(publisherAddress);
            return this;
        }

        public Builder authors(final List<Author> authors)
        {
            this.book.setAuthors(authors);
            return this;
        }

        public Builder category(final Category category)
        {
            this.book.setCategory(category);
            return this;
        }

        public Builder createdAt(final LocalDateTime createdLocalDateTime)
        {
            this.book.setCreatedAt(createdLocalDateTime);
            return this;
        }

        public Builder updatedAt(final LocalDateTime updatedLocalDateTime)
        {
            this.book.setUpdateAt(updatedLocalDateTime);
            return this;
        }

        public Book build()
        {
            return this.book;
        }

    }
}
