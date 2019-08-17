package com.ithar.malik.java.spring.application.repositories;

import com.ithar.malik.java.spring.application.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductRepository extends CrudRepository<Product, String> {
}
