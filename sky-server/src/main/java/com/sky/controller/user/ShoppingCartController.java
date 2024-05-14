package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端购物车相关接口")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * add shopping cart
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("add shopping cart")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("添加购物车，商品信息为：{}",shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * View shopping cart
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("View Shopping cart")
    public Result<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCartService.showShoppingCart();
        return Result.success(list);
    }

    /**
     * Empty shopping cart
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("Empty Shopping Cart")
    public Result empty() {
        shoppingCartService.emptyShoppingCart();
        return Result.success();
    }

    /**
     * delete item in shopping cart
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("delete item in shopping cart")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("删除购物车中商品，商品：{}",shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }
}
