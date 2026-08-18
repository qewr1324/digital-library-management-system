package ir.nas.repository;

import ir.nas.model.BaseModel;
import ir.nas.repository.base.Repository;

public abstract class RepositoryFactory
{
    protected abstract <R extends Repository<T, ID>, T extends BaseModel<ID>, ID> R repoInstance();

    public <R extends Repository<T, ID>, T extends BaseModel<ID>, ID> R getInstance()
    {
        R repo = this.repoInstance();
        return repo;
    }
}
