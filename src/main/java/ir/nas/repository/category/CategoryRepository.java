package ir.nas.repository.category;

import ir.nas.model.Category;
import ir.nas.repository.base.RepositoryImpl;

public abstract class CategoryRepository extends RepositoryImpl<Category, Long>
{
    public CategoryRepository()
    {
        super(Category.class);
    }
}
