package ir.nas.repository.category;

import ir.nas.model.Category;

public class CategoryRepositoryImpl extends CategoryRepository
{
    @Override
    protected Category updateModel(final Category src, final Category target)
    {
        src.setName(target.getName());

        src.setBooks(target.getBooks());

        src.setCreatedAt(target.getCreatedAt());
        src.setUpdateAt(target.getUpdateAt());

        return src;
    }
}
