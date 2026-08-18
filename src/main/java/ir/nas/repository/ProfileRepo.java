package ir.nas.repository;

import ir.nas.model.BaseModel;
import ir.nas.repository.base.Repository;
import ir.nas.repository.profile.ProfileRepositoryImpl;

public class ProfileRepo extends RepositoryFactory
{
    @SuppressWarnings("unchecked")
    @Override
    protected <R extends Repository<T, ID>, T extends BaseModel<ID>, ID> R repoInstance()
    {
        return (R) new ProfileRepositoryImpl();
    }
}
