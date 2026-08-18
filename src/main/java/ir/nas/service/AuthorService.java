package ir.nas.service;

import java.util.List;
import java.util.UUID;

import ir.nas.exception.repository.ModelNotFoundException;
import ir.nas.model.Author;
import ir.nas.repository.RepositoryFactory;
import ir.nas.repository.author.AuthorRepository;

public final class AuthorService
{
    private final AuthorRepository aRepository;

    public AuthorService(final RepositoryFactory repositoryFactory)
    {
        this.aRepository = repositoryFactory.getInstance();
    }

    public final UUID addAuthor(final Author auhtor)
    {
        return this.aRepository.create(auhtor);
    }

    public final Author findAuthorById(final UUID id)
    {
        return this.aRepository.read(id).orElseThrow(() -> {
            throw new ModelNotFoundException("Author Not Found By This Id [%d]".formatted(id));
        });
    }

    public final Author updateAuthor(final Author auhtor)
    {
        return this.aRepository.update(auhtor);
    }

    public final boolean deleteAuthor(final UUID id)
    {
        return this.aRepository.delete(id);
    }

    public final List<Author> findAllAuthor()
    {
        return this.aRepository.findAll();
    }

    public final Author findAuthorByFirstname(final String firstName)
    {
        return this.aRepository.findByName(firstName).orElseThrow(() -> {
            throw new ModelNotFoundException("Author Not Found By This First Name [%s]".formatted(firstName));
        });
    }

    public final List<Author> findAuthorByBirthDate(final int birthDate)
    {
        return this.aRepository.findByBirthDate(birthDate);
    }
}
