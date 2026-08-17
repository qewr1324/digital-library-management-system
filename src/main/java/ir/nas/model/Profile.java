package ir.nas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
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
@SuperBuilder
@ToString(callSuper = true, exclude = { "author" })
@EqualsAndHashCode(callSuper = true, exclude = { "author" })
@Entity
@Table(name = "profiles")
@SequenceGenerator(name = "profile_gen_seq", sequenceName = "profile_seq", initialValue = 0, allocationSize = 1)
public class Profile extends BaseModel<Long>
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_gen_seq")
    private Long id;

    @Lob
    @Column(name = "bio")
    private String bio;

    @Column(name = "website", length = 50, unique = true)
    private String website;

    @OneToOne(mappedBy = "profile")
    private Author author;
}
