package ir.nas;

import java.util.UUID;

import ir.nas.model.Book;
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
    }
}