package ir.nas.repository.author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ir.nas.model.Author;
import ir.nas.util.HibernateUtil;

public class AuthorRepositoryImpl extends AuthorRepository
{
    @Override
    public List<Author> findByBirthDate(final int birthDate)
    {
        final String FIND_BY_BIRTH_DATE_QUERY = "FROM Author a WHERE a.birthDate = :author_birthDate";
        return HibernateUtil.transaction(em -> {
            return em.createQuery(FIND_BY_BIRTH_DATE_QUERY, Author.class)
                    .setParameter("author_birthDate", birthDate)
                    .getResultList();
        });
    }

    @Override
    public Optional<Author> findByName(final String name)
    {
        final String FIND_BY_NAME_QUERY = "FROM Author a WHERE a.name = :author_name";
        return Optional.ofNullable(HibernateUtil.transaction(em -> {
            return em.createQuery(FIND_BY_NAME_QUERY, Author.class)
                    .setParameter("author_name", name)
                    .getSingleResult();
        }));
    }

    @Override
    protected Author updateModel(final Author src, final Author target)
    {
        src.setName(target.getName());
        src.setBirthDate(target.getBirthDate());

        // src.setBooks(target.getBooks());
        src.setBooks(new ArrayList<>(target.getBooks()));
        src.setProfile(target.getProfile());

        src.setCreatedAt(target.getCreatedAt());
        src.setUpdateAt(target.getUpdateAt());

        return src;
    }
}
