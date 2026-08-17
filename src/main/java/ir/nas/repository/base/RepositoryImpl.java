package ir.nas.repository.base;

import java.util.List;
import java.util.Optional;

import ir.nas.exception.repository.ModelNotFoundException;
import ir.nas.model.BaseModel;
import ir.nas.util.ColorCMD;
import ir.nas.util.HibernateUtil;

public abstract class RepositoryImpl<T extends BaseModel<ID>, ID> implements Repository<T, ID>
{
    private final Class<T> clazz;

    public RepositoryImpl(final Class<T> clazz)
    {
        this.clazz = clazz;
    }

    @Override
    public final ID create(final T t)
    {
        return HibernateUtil.transaction(em -> {
            em.persist(t);
            return t.getId();
        });
    }

    @Override
    public final boolean delete(final ID id)
    {
        return HibernateUtil.transaction(em -> {
            T findedT = em.find(clazz, id);
            if (findedT == null)
                throw new ModelNotFoundException(
                        ColorCMD.repositoryError("RepositoryImpl Error [delete()]: Model Not Found With This ID."));

            em.remove(findedT);
            return true;
        });
    }

    @Override
    public final List<T> findAll()
    {
        final String FIND_ALL_QUERY_STRING = "FROM ".concat(clazz.getSimpleName());
        return HibernateUtil.findAll(clazz, FIND_ALL_QUERY_STRING);
    }

    @Override
    public final Optional<T> read(final ID id)
    {
        return Optional.ofNullable(
                HibernateUtil.find(clazz, id));
    }

    @Override
    public final T update(final T t)
    {
        return HibernateUtil.transaction(em -> {
            T findedT = em.find(clazz, t.getId());
            return this.updateModel(findedT, t);
        });
    }

    protected abstract T updateModel(T src, T target);
}
