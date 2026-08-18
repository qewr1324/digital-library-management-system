package ir.nas;

import java.math.BigDecimal;
import java.util.UUID;

import ir.nas.enums.StockStatus;
import ir.nas.model.Book;
import ir.nas.model.embeddable.Address;
import ir.nas.repository.BookRepo;
// import ir.nas.repository.BookRepositoryCreator;
import ir.nas.repository.RepositoryFactory;
import ir.nas.repository.book.BookRepositoryImpl;
import ir.nas.service.BookService;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");

        // RepositoryFactory<BookRepositoryImpl, Book, UUID> repositoryCreator = new BookRepositoryCreator();
        // BookService bookService = new BookService(repositoryCreator);
        BookRepo bookRepo = new BookRepo();
        RepositoryFactory repositoryFactory = bookRepo;
        BookService bookService = new BookService(repositoryFactory);

                Address address = Address.builder()
                .city("Teyrun")
                .street("Hooshyar")
                .postalCode("9876543210")
                .build();

        Address address2 = Address.builder()
                .city("Teyrun")
                .street("Hooshyar")
                .postalCode("8765467837")
                .build();

        Book book = Book.builder()
                .title("Game Design")
                .ISBN("321-231-132")
                .publicationYear(1997)
                .price(new BigDecimal(20.95))
                .stockStatus(StockStatus.IN_STOCK)
                .publisherAddress(address)
                .build();

        Book book2 = Book.builder()
                .title("Game Arts")
                .ISBN("231-123-312")
                .publicationYear(2005)
                .price(new BigDecimal(50.99))
                .stockStatus(StockStatus.OUT_OF_STOCK)
                .publisherAddress(address2)
                .build();
    }
}