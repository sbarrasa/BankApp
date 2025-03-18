package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.product.Product;

public record ProductUpdatePair(Product searchProduct, Product updateProduct) {
}
