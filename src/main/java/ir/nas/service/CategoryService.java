package ir.nas.service;

import java.util.List;

import ir.nas.exception.repository.ModelNotFoundException;
import ir.nas.model.Category;
import ir.nas.repository.RepositoryFactory;
import ir.nas.repository.category.CategoryRepository;

public class CategoryService
{
    private final CategoryRepository cRepository;

    public CategoryService(final RepositoryFactory repositoryFactory)
    {
        this.cRepository = repositoryFactory.getInstance();
    }

    public final Long addCategory(final Category category)
    {
        return this.cRepository.create(category);
    }

    public final Category findCategoryById(final Long id)
    {
        return this.cRepository.read(id).orElseThrow(() -> {
            throw new ModelNotFoundException("Category Not Found By This Id [%d]".formatted(id));
        });
    }

    public final Category updateCategory(final Category category)
    {
        return this.cRepository.update(category);
    }

    public final boolean deleteCategory(final Long id)
    {
        return this.cRepository.delete(id);
    }

    public final List<Category> findAllCategory()
    {
        return this.cRepository.findAll();
    }
}
