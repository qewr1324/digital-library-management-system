package ir.nas.service;

import java.util.List;
import java.util.UUID;

import ir.nas.enums.StockStatus;
import ir.nas.exception.repository.ModelNotFoundException;
import ir.nas.model.Book;
import ir.nas.repository.RepositoryFactory;
import ir.nas.repository.book.BookRepository;

public final class BookService
{
    private final BookRepository bRepository;

    public BookService(final RepositoryFactory repositoryFactory)
    {
        this.bRepository = repositoryFactory.getInstance();
    }

    public final UUID addBook(final Book book)
    {
        return this.bRepository.create(book);
    }

    public final Book findBookById(final UUID id)
    {
        return this.bRepository.read(id).orElseThrow(() -> {
            throw new ModelNotFoundException("Book Not Found By This Id [%d]".formatted(id));
        });
    }

    public final Book updateBook(final Book book)
    {
        return this.bRepository.update(book);
    }

    public final boolean deleteBook(final UUID id)
    {
        return this.bRepository.delete(id);
    }

    public final List<Book> findAllBook()
    {
        return this.bRepository.findAll();
    }

    public final Book findBookByTitle(final String title)
    {
        return this.bRepository.findByTitle(title).orElseThrow(() -> {
            throw new ModelNotFoundException("Book Not Found By This Title [%s]".formatted(title));
        });
    }

    public final Book findBookByISBN(final String ISBN)
    {
        return this.bRepository.findByISBN(ISBN).orElseThrow(() -> {
            throw new ModelNotFoundException("Book Not Found By This ISBN [%s]".formatted(ISBN));
        });
    }

    public final Book findByISBNWithAuthors(final String ISBN)
    {
        return this.bRepository.findByISBNWithAuthors(ISBN).orElseThrow(() -> {
            throw new ModelNotFoundException("Book Not Found By This ISBN [%s]".formatted(ISBN));
        });
    }

    public final List<Book> findBookByPublicationYear(final int publicationYear)
    {
        return this.bRepository.findByPublicationYear(publicationYear);
    }

    public final List<Book> findBookByStockStatus(final StockStatus stockStatus)
    {
        return this.bRepository.findByStockStatus(stockStatus);
    }
}
