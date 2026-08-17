package ir.nas;

import java.util.UUID;

import ir.nas.model.Book;
import ir.nas.repository.BookRepositoryCreator;
import ir.nas.repository.RepositoryCreator;
import ir.nas.repository.book.BookRepositoryImpl;
import ir.nas.service.BookService;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");

        RepositoryCreator<BookRepositoryImpl, Book, UUID> repositoryCreator = new BookRepositoryCreator();
        // BookService bookService = new BookService(repositoryCreator);
    }
}