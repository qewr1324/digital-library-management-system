package ir.nas.service;

import java.util.List;
import java.util.UUID;

import ir.nas.enums.StockStatus;
import ir.nas.exception.repository.ModelNotFoundException;
import ir.nas.model.Author;
import ir.nas.model.Book;
import ir.nas.repository.book.BookRepository;
import ir.nas.repository.book.BookRepositoryImpl;
// import ir.nas.util.Validation;

public final class BookService
{
    private final BookRepository bRepository;

    public BookService(final BookRepositoryImpl bRepository)
    {
        this.bRepository = bRepository;
    }

    private final void validateBook(final Book book)
    {
        // Validation.of()
        //         .requireNotNull(book)
        //         .requireString(book.getTitle())
        //         .requireString(book.getISBN())
        //         .requireNotNegative(book.getPrice())
        //         .validate();
    }

    private final void validateBookId(final Long id)
    {
        // Validation.of()
        //         .requireNotNegative(id)
        //         .validate();
    }

    public final UUID addBook(final Book book)
    {
        this.validateBook(book);
        return this.bRepository.create(book);
    }

    public final Book findBookById(final UUID id)
    {
        // this.validateBookId(id);
        return this.bRepository.read(id).orElseThrow(() -> {
            throw new ModelNotFoundException("Book Not Found By This Id [%d]".formatted(id));
        });
    }

    public final Book updateBook(final Book book)
    {
        this.validateBook(book);
        return this.bRepository.update(book);
    }

    public final boolean deleteBook(final UUID id)
    {
        // this.validateBookId(id);
        return this.bRepository.delete(id);
    }

    public final List<Book> findAllBook()
    {
        return this.bRepository.findAll();
    }

    public final Book findBookByTitle(final String title)
    {
        // this.validateBookId(id);
        return this.bRepository.findByTitle(title).orElseThrow(() -> {
            throw new ModelNotFoundException("Book Not Found By This Title [%s]".formatted(title));
        });
    }

    public final Book findBookByISBN(final String ISBN)
    {
        // this.validateBookId(id);
        return this.bRepository.findByISBN(ISBN).orElseThrow(() -> {
            throw new ModelNotFoundException("Book Not Found By This ISBN [%s]".formatted(ISBN));
        });
    }

    public final List<Book> findBookByPublicationYear(final int publicationYear)
    {
        // this.validateBookId(id);
        return this.bRepository.findByPublicationYear(publicationYear);
    }

    public final List<Book> findBookByStockStatus(final StockStatus stockStatus)
    {
        // this.validateBookId(id);
        return this.bRepository.findByStockStatus(stockStatus);
    }
}
