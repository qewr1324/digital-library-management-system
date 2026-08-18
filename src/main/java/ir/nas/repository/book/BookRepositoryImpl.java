package ir.nas.repository.book;

import java.util.List;
import java.util.Optional;

import ir.nas.enums.StockStatus;
import ir.nas.model.Book;
import ir.nas.util.HibernateUtil;

public final class BookRepositoryImpl extends BookRepository
{
    @Override
    public final Optional<Book> findByISBN(final String ISBN)
    {
        final String FIND_BY_ISBN_QUERY = "FROM Book b WHERE b.ISBN = :book_isbn";
        return Optional.ofNullable(HibernateUtil.transaction(em -> {
            return em.createQuery(FIND_BY_ISBN_QUERY, Book.class)
                    .setParameter("book_isbn", ISBN)
                    .getSingleResult();
        }));
    }

    @Override
    public final List<Book> findByPublicationYear(final int publicationYear)
    {
        final String FIND_BY_PUBLICATION_YEAR_QUERY = "FROM Book b WHERE b.publicationYear = :book_publicationYear";
        return HibernateUtil.transaction(em -> {
            return em.createQuery(FIND_BY_PUBLICATION_YEAR_QUERY, Book.class)
                    .setParameter("book_publicationYear", publicationYear)
                    .getResultList();
        });
    }

    @Override
    public final List<Book> findByStockStatus(final StockStatus stockStatus)
    {
        final String FIND_BY_STOCK_STATUS_QUERY = "FROM Book b WHERE b.stockStatus = :book_stockStatus";
        return HibernateUtil.transaction(em -> {
            return em.createQuery(FIND_BY_STOCK_STATUS_QUERY, Book.class)
                    .setParameter("book_stockStatus", stockStatus)
                    .getResultList();
        });
    }

    @Override
    public final Optional<Book> findByTitle(final String title)
    {
        final String FIND_BY_TITLE_QUERY = "FROM Book b WHERE b.title = :book_title";
        return Optional.ofNullable(HibernateUtil.transaction(em -> {
            return em.createQuery(FIND_BY_TITLE_QUERY, Book.class)
                    .setParameter("book_title", title)
                    .getSingleResult();
        }));
    }

    @Override
    protected final Book updateModel(final Book src, final Book target)
    {
        src.setTitle(target.getTitle());
        src.setISBN(target.getISBN());
        src.setPublicationYear(target.getPublicationYear());
        src.setPrice(target.getPrice());
        src.setStockStatus(target.getStockStatus());

        src.setPublisherAddress(target.getPublisherAddress());
        src.getPublisherAddress().setCity(target.getPublisherAddress().getCity());
        src.getPublisherAddress().setStreet(target.getPublisherAddress().getStreet());
        src.getPublisherAddress().setPostalCode(target.getPublisherAddress().getPostalCode());

        src.setAuthors(target.getAuthors());
        src.setCategory(target.getCategory());

        src.setCreatedAt(target.getCreatedAt());
        src.setUpdateAt(target.getUpdateAt());

        return src;
    }
}
