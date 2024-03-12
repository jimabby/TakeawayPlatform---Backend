package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CategoryServicempl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * Add Category
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        //Copy the property
        BeanUtils.copyProperties(categoryDTO, category);

        //set the status to be disabled
        category.setStatus(StatusConstant.DISABLE);

        //set the createTime, updateTime, createUser and updateUser
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    /**
     * Page Query 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        //下一条sql进行分页， 自动加入limit关键字分页
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * Delete Category by id
     * @param id
     */
    public void deleteById(Long id) {
        //查询当前分类是否关联菜品，如关联，则抛出业务异常
        Integer count = dishMapper.countByCategoryId(id);
        if(count > 0) {
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        //查询当前分类是否关联套餐，如关联，则抛出业务异常
        count = setmealMapper.countByCategoryId(id);
        if (count > 0) {
            //当前分类下有套餐，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        //删除分类数据
        categoryMapper.deleteById(id);
    }

    /**
     * update category
     * @param categoryDTO
     */
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        //设置修改时间，修改人
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.update(category);
    }

    /**
     * Start or stop the category
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /**
     * Search the category by type
     * @param type
     * @return
     */
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}
