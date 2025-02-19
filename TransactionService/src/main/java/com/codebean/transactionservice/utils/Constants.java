package com.codebean.transactionservice.utils;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 27 Jan 2025 16:40
@Last Modified 27 Jan 2025 16:40
Version 1.0
*/

public class Constants {
    public static final String USER_ID = "userId";

    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String LOGIN_FAIL = "Login failed";
    public static final String SUCCESS = "Success";
    public static final String UPDATES_SUCCESS = "Updates successful";
    public static final String BAD_DATA = "Bad data";

    // Transaction-related messages
    public static final String TRANSACTION_SUCCESSFUL = "Transaction successful";
    public static final String TRANSACTION_SUCCESS_UPDATE = "Transaction successful update";
    public static final String TRANSACTION_FAILED = "Transaction failed";
    public static final String TRANSACTION_NOT_FOUND = "Transaction not found";
    public static final String TRANSACTION_FAILED_TO_CREATE = "Transaction creation failed";
    public static final String TRANSACTION_FAILED_TO_UPDATE = "Transaction update failed";
    public static final String TRANSACTION_FAILED_TO_DELETE = "Transaction deletion failed";
    public static final String TRANSACTION_FAILED_TO_GET = "Failed to retrieve transaction";
    public static final String INSUFFICIENT_BALANCE = "Insufficient balance";
    public static final String TRANSACTION_LIMIT_EXCEEDED = "Transaction limit exceeded";

    // cart
    public static final String PRODUCT_ADD_TO_CART_SUCCESS = "Product added to cart successfully";
    public static final String CART_NOT_EMPTY = "Cart is not empty";
    public static final String CART_ITEM_REMOVED_SUCCESS = "Item removed from cart successfully";
    public static final String CART_UPDATED_SUCCESS = "Cart updated successfully";
    public static final String CART_EMPTY = "Your cart is empty";
    public static final String CART_CHECKOUT_SUCCESS = "Checkout completed successfully";
    public static final String CART_ITEM_NOT_FOUND = "Item not found in cart";
    public static final String CART_OPERATION_FAILED = "Cart operation failed. Please try again";

    public static final String CART_FAILED_TO_GET = "Failed to retrieve cart";
    public static final String CART_ADD_ITEM_FAILED = "Failed to add item to cart";
    public static final String CART_REMOVE_ITEM_FAILED = "Failed to remove item from cart";
    public static final String CART_UPDATE_FAILED = "Failed to update cart";
    public static final String CART_CHECKOUT_FAILED = "Checkout failed. Please try again";
    public static final String CART_EMPTY_CANNOT_CHECKOUT = "Cannot proceed to checkout. Your cart is empty";
    public static final String CART_ITEM_NOT_AVAILABLE = "The selected item is no longer available";
    public static final String CART_NOT_FOUND = "Cart not found";

    public static final String FAILED_TO_GET_USER_OR_PRODUCT = "Failed to retrieve user or product";
    public static final String FAILED_TO_GET_PRODUCT = "Failed to retrieve product";

    public static final String INSUFFICIENT_STOCK = "Insufficient stock";

}
