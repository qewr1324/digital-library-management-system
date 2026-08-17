package ir.nas.repository;

import java.util.UUID;

import ir.nas.model.Book;
import ir.nas.repository.book.BookRepositoryImpl;

public class BookRepositoryCreator extends RepositoryCreator<BookRepositoryImpl, Book, UUID>
{
    @Override
    public final BookRepositoryImpl repository()
    {
        return new BookRepositoryImpl();
    }
}
