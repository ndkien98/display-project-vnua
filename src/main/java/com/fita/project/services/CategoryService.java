package com.fita.project.services;

import com.fita.project.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    // Lấy ra tất cả các thể loại
    List<CategoryDTO> getCategories();

    // Lấy ra thể loại theo "id"
    CategoryDTO getCategoryById(int id);

    // Lấy ra thể loại theo "mã thể loại"
    CategoryDTO getCategoryByCategoryCode(String categoryCode);

    // Thêm thể loại
    void addCategory(CategoryDTO categoryDTO);

    // Sửa thể loại
    void editCategory(int id, CategoryDTO categoryDTO);

    // Xoá thể loại
    void deleteCategory(int id);

}
