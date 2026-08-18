package ir.nas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ir.nas.enums.StockStatus;
import ir.nas.model.Book;
import ir.nas.model.Category;
import ir.nas.model.embeddable.Address;
import ir.nas.repository.BookRepo;
import ir.nas.repository.CategoryRepo;
import ir.nas.repository.RepositoryFactory;
import ir.nas.service.BookService;
import ir.nas.service.CategoryService;

public class MainTest
{
    // private RepositoryFactory repositoryFactory;
    private static BookService bService;
    private static CategoryService cService;

    @BeforeAll
    public static void setup()
    {
        // this.repositoryFactory = new BookRepo();
        bService = new BookService(new BookRepo());

        // this.repositoryFactory = new CategoryRepo();
        cService = new CategoryService(new CategoryRepo());
    }

    @Test
    @DisplayName("Purpose: Test the save and findById methods of the BookRepository.")
    public void testSaveAndFindBook()
    {
        Address address = Address.builder()
                .city("Teyrun")
                .street("Hooshyar")
                .postalCode("1234567890")
                .build();

        Book book = Book.builder()
                .title("Java Design Pattern")
                .ISBN("123-213-312")
                .publicationYear(1997)
                .price(new BigDecimal(19.95))
                .stockStatus(StockStatus.COMMING_SOON)
                .publisherAddress(address)
                .build();

        UUID savedBookID = bService.addBook(book);
        Book retrievedBook = bService.findBookById(savedBookID);

        assertNotNull(retrievedBook);
        assertEquals(retrievedBook.getTitle(), book.getTitle());
        assertNotNull(retrievedBook.getPublisherAddress());
        assertNotNull(retrievedBook.getPublisherAddress().getCity());

        /*
            Purpose: Test the save and findById methods of the BookRepository.
            Steps
            1. Create a new Book object using the Builder pattern.
            2. Save the book.
            3. Retrieve the book by its ID.
            4. Assert that the retrieved book is not null and its title matches the original title.
            5. Verify that the book's publisherAddress is correctly stored and retrieved
        */
    }

    @Test
    @DisplayName("Purpose: Test the CascadeType.PERSIST behavior between Category and Book.")
    public void testCategoryCascadePersist()
    {
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

        Category category = Category.builder()
                .name("Game")
                .books(Arrays.asList(book, book2))
                .build();

        Long categoryId = cService.addCategory(category);
        Category retrieveCategory = cService.findCategoryById(categoryId);

        assertNotNull(retrieveCategory);
        /*
            Purpose: Test the CascadeType.PERSIST behavior between Category and Book.
            Steps
            1. Create a new Category object.
            2. Create at least two new Book objects using the Builder pattern.
            3. Add the books to the category's books collection.
            4. Save only the Category object.
            5. Retrieve the category by its ID.
            6. Assert that the retrieved category is not null.
            7. Verify that the category contains the books that were added before saving.
            8. Verify that the books were automatically persisted to the database through CascadeType.PERSIST.
        */
    }

    @AfterEach
    public void done()
    {

    }
}
