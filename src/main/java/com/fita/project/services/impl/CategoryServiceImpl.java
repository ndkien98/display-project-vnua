package com.fita.project.services.impl;

import com.fita.project.dao.CategoryRepository;
import com.fita.project.dto.CategoryDTO;
import com.fita.project.repository.entity.Category;
import com.fita.project.services.CategoryService;
import com.fita.project.ulti.CommonUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * Lấy tất cả các thể loại trong cơ sở dữ liệu
     *
     * @return List<CategoryDTO>
     */
    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        return getCategoryDTOS(categories);
    }

    private List<CategoryDTO> getCategoryDTOS(List<Category> categories) {

        if (CommonUtil.isNull(categories)) return new ArrayList<>();

        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        //Convert category (Entity) -> categoryDTO (DTO)
        for (Category category : categories) {
            categoriesDTO.add(getCategoryDTO(category));
        }
        return categoriesDTO;
    }

    /**
     * Lấy thể loại trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @return CategoryDTO
     */
    @Override
    public CategoryDTO getCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElse(new Category());
        //Convert category (Entity) -> categoryDTO (DTO)
        return getCategoryDTO(category);
    }

    private CategoryDTO getCategoryDTO(Category category) {
        if (CommonUtil.isNull(category)) return new CategoryDTO();
        return modelMapper.map(category, CategoryDTO.class);
    }

    /**
     * Lấy thể loại trong cơ sở dữ liệu dựa theo id
     *
     * @param categoryCode
     * @return CategoryDTO
     */
    @Override
    public CategoryDTO getCategoryByCategoryCode(String categoryCode) {
        Category category = categoryRepository.findByCategoryCode(categoryCode);

        //Convert category (Entity) -> categoryDTO (DTO)
        return getCategoryDTO(category);
    }

    /**
     * Thêm 1 thể loại vào cơ sở dữ liệu
     *
     * @param categoryDTO
     */
    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        if (CommonUtil.isNull(categoryDTO)) return;
        categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
    }

    /**
     * Sửa thể loại trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     * @param categoryDTO
     */
    @Override
    public void editCategory(int id, CategoryDTO categoryDTO) {
        // Lấy thể loại cần sửa
        Category categoryToUpdate = categoryRepository.getOne(id);

        // Cập nhật dữ liệu mới
        categoryToUpdate.setCategoryName(categoryDTO.getCategoryName());

        // Lưu lại vào cơ sở dữ liệu
        categoryRepository.save(categoryToUpdate);
    }

    /**
     * Xoá thể loại trong cơ sở dữ liệu dựa theo id
     *
     * @param id
     */
    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
