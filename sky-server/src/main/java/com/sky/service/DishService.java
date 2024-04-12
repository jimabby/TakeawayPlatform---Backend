package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

public interface DishService {

    /**
     * Add dish and flavor
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * Dish page Query
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
}
