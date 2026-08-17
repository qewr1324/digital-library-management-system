package ir.nas.util;

import java.util.List;
import java.util.function.Function;

import ir.nas.exception.db.DBConnectionException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public final class HibernateUtil
{
    private static final String PERSISTENCE_UNIT_NAME_STRING = "postgresql-unit";
    private static EntityManagerFactory emf;

    static {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME_STRING);
    }

    private HibernateUtil()
    {}

    public synchronized static final <T> T transaction(final Function<EntityManager, T> function)
    {
        return new HibernateUtil().inTxReturn(function);
    }

    public synchronized static final <T, ID> T find(final Class<T> clazz, final ID id)
    {
        return new HibernateUtil().inPureReturn(clazz, id);
    }

    public synchronized static final <T> List<T> findAll(final Class<T> clazz, final String query)
    {
        return new HibernateUtil().inListReturn(clazz, query);
    }

    private synchronized final <T> T inTxReturn(final Function<EntityManager, T> function)
    {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        String message = null;

        try (em) {

            if (!tx.isActive())
                tx.begin();

            T obj = function.apply(em);
            tx.commit();
            return obj;

        } catch (PersistenceException e) {

            e.printStackTrace();
            message = e.getMessage();
            throw new DBConnectionException(
                    ColorCMD.dbError("HibernateUtil Erorr [inTxReturn()]: ".concat(message)));

        } catch (Exception e) {

            e.printStackTrace();
            message = e.getMessage();
            throw new DBConnectionException(
                    ColorCMD.dbError("HibernateUtil Erorr [inTxReturn()]: ".concat(message)));

        } finally {
            if (tx.isActive())
                tx.rollback();
        }
    }

    private synchronized final <T, ID> T inPureReturn(final Class<T> clazz, final ID id)
    {
        final EntityManager em = emf.createEntityManager();

        try (em) {

            return em.find(clazz, id);

        } catch (PersistenceException e) {

            e.printStackTrace();
            throw new DBConnectionException(
                    ColorCMD.dbError("HibernateUtil Error [isNullReturn()]: ".concat(e.getMessage())));

        } catch (Exception e) {

            e.printStackTrace();
            throw new DBConnectionException(
                    ColorCMD.dbError("HibernateUtil Error [isNullReturn()]: ".concat(e.getMessage())));
        }
    }

    private synchronized final <T> List<T> inListReturn(final Class<T> clazz, final String query)
    {
        final EntityManager em = emf.createEntityManager();

        try (em) {

            return em.createQuery(query, clazz).getResultList();

        } catch (PersistenceException e) {

            e.printStackTrace();
            throw new DBConnectionException(
                    ColorCMD.dbError("HibernateUtil Error [isNullReturn()]: ".concat(e.getMessage())));

        } catch (Exception e) {

            e.printStackTrace();
            throw new DBConnectionException(
                    ColorCMD.dbError("HibernateUtil Error [isNullReturn()]: ".concat(e.getMessage())));
        }
    }
}
