package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * Add Category
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * Page Query
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Delete the category by id
     * @param id
     */
    void deleteById(Long id);

    /**
     * update the category
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * Start or stop the category
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Search the category by type
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
