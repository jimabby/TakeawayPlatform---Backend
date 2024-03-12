package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/category")
@Slf4j
@Api(tags = "Category API")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * Add Category
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add Category")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("Add Category: {}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**4
     * Page Query 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Page Query")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("Page Query: {}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete the Category
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete Category")
    public Result<String> deleteById(Long id) {
        log.info("Delete Category: {}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * Update the category
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update Category")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * Start or stop the category
     * 启用禁用分类
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Start/Stop Category")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id) {
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * Search the category by type
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Search category by type")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
