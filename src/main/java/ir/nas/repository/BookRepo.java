package ir.nas.repository;

import ir.nas.model.BaseModel;
import ir.nas.repository.base.Repository;
import ir.nas.repository.book.BookRepositoryImpl;

public class BookRepo extends RepositoryFactory
{
    @SuppressWarnings("unchecked")
    @Override
    protected <R extends Repository<T, ID>, T extends BaseModel<ID>, ID> R repoInstance()
    {
        return (R) new BookRepositoryImpl();
    }
}
