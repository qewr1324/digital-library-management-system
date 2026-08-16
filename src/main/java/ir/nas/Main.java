package ir.nas;

import ir.nas.model.Author;
import ir.nas.model.Book;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");

        Author tAuthor = new Author().new Builder().build();
        Author author = Author.builder().build();
        Book book = Book.builder().ISBN(null).build();

        // new Book().id
    }
}