package com.spring.rental.category;

import com.spring.rental.category.dto.CategoryResponseDTO;
import com.spring.rental.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> new CategoryResponseDTO(category.getName(), category.getId()))
                .collect(Collectors.toList());
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category", id));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public CategoryResponseDTO updateCategory(Long id, Category updatedCategory) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("category", id);
        }
        updatedCategory.setId(id);
        categoryRepository.save(updatedCategory);
        return new CategoryResponseDTO(updatedCategory.getName(), id);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("category", id);
        }
        categoryRepository.deleteById(id);
    }
}
