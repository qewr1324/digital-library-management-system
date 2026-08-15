package ir.nas.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

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
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = { "profile", "books" })
@EqualsAndHashCode(callSuper = true, exclude = { "profile", "books" })
@SuperBuilder
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

    @OneToOne
    private Profile profile;

    @ManyToMany
    private List<Book> books;
}
