package ir.nas.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
@ToString(callSuper = true, exclude = { "profile", "books" })
@EqualsAndHashCode(callSuper = true, exclude = { "profile", "books" })
// @SuperBuilder
@Entity
@Table(name = "authors")
public class Author extends BaseModel<UUID>
{
    @Id
    @UuidGenerator(style = Style.VERSION_7)
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "birth_date")
    private int birthDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public void addProfile(final Profile profile)
    {
        this.profile = profile;
        profile.setAuthor(this);
    }

    public void addBook(final Book book)
    {
        if (this.books == null)
            this.books = new ArrayList<>();

        this.books.add(book);
    }

    public static Builder builder()
    {
        return new Author().new Builder();
    }

    public class Builder
    {
        private final Author author;

        public Builder()
        {
            this.author = new Author();
        }

        public Builder id(final UUID id)
        {
            this.author.setId(id);
            return this;
        }

        public Builder name(final String name)
        {
            this.author.setName(name);
            return this;
        }

        public Builder birthDate(final int birthDate)
        {
            this.author.setBirthDate(birthDate);
            return this;
        }

        public Builder profile(final Profile profile)
        {
            this.author.setProfile(profile);
            return this;
        }

        public Builder books(final List<Book> books)
        {
            this.author.setBooks(new ArrayList<>(books));
            return this;
        }

        public Builder createdAt(final LocalDateTime createdLocalDateTime)
        {
            this.author.setCreatedAt(createdLocalDateTime);
            return this;
        }

        public Builder updatedAt(final LocalDateTime updatedLocalDateTime)
        {
            this.author.setUpdateAt(updatedLocalDateTime);
            return this;
        }

        public Author build()
        {
            return this.author;
        }
    }
}
