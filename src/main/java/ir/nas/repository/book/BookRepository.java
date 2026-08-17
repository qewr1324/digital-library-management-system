package ir.nas.repository.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ir.nas.enums.StockStatus;
import ir.nas.model.Book;
import ir.nas.repository.base.RepositoryImpl;

public abstract class BookRepository extends RepositoryImpl<Book, UUID>
{
    public BookRepository()
    {
        super(Book.class);
    }

    public abstract Optional<Book> findByTitle(final String title);

    public abstract Optional<Book> findByISBN(final String ISBN);

    public abstract List<Book> findByPublicationYear(final int publicationYear);

    public abstract List<Book> findByStockStatus(final StockStatus stockStatus);
}
