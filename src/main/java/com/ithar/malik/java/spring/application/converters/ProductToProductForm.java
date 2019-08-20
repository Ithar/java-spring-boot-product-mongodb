package com.ithar.malik.java.spring.application.converters;

import com.ithar.malik.java.spring.application.commands.ProductForm;
import com.ithar.malik.java.spring.application.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// TODO [IM 19-08-20] - Use mapstruct
@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId().toHexString());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }
}
