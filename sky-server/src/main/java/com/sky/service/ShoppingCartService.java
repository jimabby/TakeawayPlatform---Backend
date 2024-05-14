package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * add shopping cart
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * View shopping cart
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * Empty Shopping cart
     */
    void emptyShoppingCart();

    /**
     * delete item in shopping cart
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
