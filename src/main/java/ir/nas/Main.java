package ir.nas;

import java.math.BigDecimal;
import java.util.ArrayList;

import ir.nas.enums.StockStatus;
import ir.nas.model.Author;
import ir.nas.model.Book;
import ir.nas.model.Profile;
import ir.nas.model.embeddable.Address;
import ir.nas.repository.BookRepo;
import ir.nas.repository.RepositoryFactory;
import ir.nas.service.BookService;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Start");
        BookRepo bookRepo = new BookRepo();
        RepositoryFactory repositoryFactory = bookRepo;
        BookService bookService = new BookService(repositoryFactory);

        Address address = Address.builder()
                .city("Teyrun")
                .street("molavi")
                .postalCode("9876543210")
                .build();

        Address address2 = Address.builder()
                .city("Teyrun")
                .street("azadi")
                .postalCode("8765467837")
                .build();

        Address address3 = Address.builder()
                .city("Teyrun")
                .street("Hooshyar")
                .postalCode("1234567890")
                .build();

        Profile profile = Profile.builder()
                .bio("Hiiiii I Am AmirHusein")
                .website("amirhussein.com")
                .build();

        Profile profile2 = Profile.builder()
                .bio("Hiiiii I Am Hootan")
                .website("hootan.com")
                .build();

        Profile profile3 = Profile.builder()
                .bio("Hiiiii I Am Reza")
                .website("reza.com")
                .build();

        Author author = Author.builder()
                .name("Amir Hussein")
                .birthDate(1997)
                .build();

        Author author2 = Author.builder()
                .name("Hootan")
                .birthDate(1996)
                .build();

        Author author3 = Author.builder()
                .name("Reza")
                .birthDate(2000)
                .build();

        Book book = Book.builder()
                .title("Java Design Pattern")
                .ISBN("123-213-312")
                .publicationYear(1997)
                .price(new BigDecimal(19.95))
                .stockStatus(StockStatus.COMMING_SOON)
                .publisherAddress(address)
                .build();

        Book book3 = Book.builder()
                .title("Game Design")
                .ISBN("321-231-132")
                .publicationYear(1990)
                .price(new BigDecimal(20.95))
                .stockStatus(StockStatus.IN_STOCK)
                .publisherAddress(address3)
                .build();

        Book book2 = Book.builder()
                .title("Game Arts")
                .ISBN("231-123-312")
                .publicationYear(2005)
                .price(new BigDecimal(50.99))
                .stockStatus(StockStatus.OUT_OF_STOCK)
                .publisherAddress(address2)
                .build();

        author.addProfile(profile);
        author2.addProfile(profile2);
        author3.addProfile(profile3);

        book.addAuthor(author);
        book2.addAuthor(author2);
        book3.addAuthor(author3);

        bookService.addBook(book);
        bookService.addBook(book2);
        bookService.addBook(book3);

        Book findedBook = bookService.findByISBNWithAuthors("231-123-312");

        findedBook.setPrice(new BigDecimal(1));
        bookService.updateBook(findedBook);

        // bookService.deleteBook(findedBook.getId());
    }
}