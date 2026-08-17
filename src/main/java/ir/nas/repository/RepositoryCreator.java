package ir.nas.repository;

import ir.nas.model.BaseModel;
import ir.nas.repository.base.Repository;

public abstract class RepositoryCreator<R extends Repository<T, ID>, T extends BaseModel<ID>, ID>
{
    protected abstract R repository();

    public final R getRepositoryInstance()
    {
        return (R) this.repository();
    }
}
