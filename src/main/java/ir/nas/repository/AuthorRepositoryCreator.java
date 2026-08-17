package ir.nas.repository;

import java.util.UUID;

import ir.nas.model.Author;
import ir.nas.repository.author.AuthorRepositoryImpl;

public class AuthorRepositoryCreator extends RepositoryCreator<AuthorRepositoryImpl, Author, UUID>
{
    @Override
    public AuthorRepositoryImpl repository()
    {
        return new AuthorRepositoryImpl();
    }
}
