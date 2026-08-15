package ir.nas.util;

import java.util.function.Function;

import ir.nas.exception.db.DBConnectionException;
import ir.nas.model.BaseModel;
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

    public static final <T> T persist(final Function<EntityManager, T> function)
    {
        return new HibernateUtil().inTxReturn(function);
    }

    private final <T> T inTxReturn(final Function<EntityManager, T> function)
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
}
