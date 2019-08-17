package com.ithar.malik.java.spring.application.services;

import com.ithar.malik.java.spring.application.commands.ProductForm;
import com.ithar.malik.java.spring.application.domain.Product;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductService {

    List<Product> listAll();

    Product getById(String id);

    Product saveOrUpdate(Product product);

    void delete(String id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}