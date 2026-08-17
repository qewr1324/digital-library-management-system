package ir.nas.repository.base;

import java.util.List;
import java.util.Optional;

import ir.nas.model.BaseModel;

public interface Repository<T extends BaseModel<ID>, ID>
{
    ID create(T t);

    Optional<T> read(ID id);

    T update(T t);

    boolean delete(ID id);

    List<T> findAll();
}

/*
    interface Product
    {
        void create();
        void read();
    }

    class Product implements Product
    {
        void create() {...}
        void read() {...}
    }

    abstract class Creator
    {
        abstract Product addProduct();
        abstract Product findById();

        public void create()
        {
            Product addProducts = addProduct();
            addProducts.create();
        }
    }

    class Creator extends Creator
    {
        Product addProduct() {...}
        Product findById() {...}
    }
*/
