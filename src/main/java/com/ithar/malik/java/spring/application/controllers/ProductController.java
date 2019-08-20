package com.ithar.malik.java.spring.application.controllers;

import com.ithar.malik.java.spring.application.commands.ProductForm;
import com.ithar.malik.java.spring.application.converters.ProductToProductForm;
import com.ithar.malik.java.spring.application.domain.Product;
import com.ithar.malik.java.spring.application.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Slf4j
@Controller
public class ProductController {

    private final static String PRODUCT_FORM_VIEW = "product/product-form";
    private final static String PRODUCT_LIST_VIEW = "product/list";
    private final static String PRODUCT_SHOW_VIEW = "product/show";

    private final ProductService productService;
    private final ProductToProductForm productToProductForm;

    public ProductController(ProductService productService, ProductToProductForm productToProductForm) {
        this.productService = productService;
        this.productToProductForm = productToProductForm;
    }

    @RequestMapping("/")
    public String redirectToList() {
        return "redirect:/product/list";
    }

    @RequestMapping({"/product/list", "/product"})
    public String listProducts(Model model) {

        log.info("Listing all products");

        model.addAttribute("products", productService.listAll());
        return PRODUCT_LIST_VIEW;
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {

        log.info("Getting a product with id: {}", id);

        model.addAttribute("product", productService.getById(id));

        return PRODUCT_SHOW_VIEW;
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        log.info("Edit a product with id: {}", id);

        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);

        model.addAttribute("productForm", productForm);
        return PRODUCT_FORM_VIEW;
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model) {

        log.info("New product");

        model.addAttribute("productForm", new ProductForm());
        return PRODUCT_FORM_VIEW;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult) {

        log.info("Save or update a product");

        if (bindingResult.hasErrors()) {
            return PRODUCT_FORM_VIEW;
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id) {

        log.info("Deleting a product with id: {}", id);

        productService.delete(id);
        return "redirect:/product/list";
    }
}
