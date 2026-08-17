package ir.nas.repository.author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ir.nas.model.Author;
import ir.nas.repository.base.RepositoryImpl;

public abstract class AuthorRepository extends RepositoryImpl<Author, UUID>
{
    public AuthorRepository()
    {
        super(Author.class);
    }

    public abstract Optional<Author> findByName(final String name);

    public abstract List<Author> findByBirthDate(final int birthDate);
}
