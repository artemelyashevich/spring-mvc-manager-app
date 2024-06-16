package com.example.manager.controller;

import com.example.manager.controller.paylaod.NewProductPayload;
import com.example.manager.entity.Product;
import com.example.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("/create")
    public String createProduct(NewProductPayload newProductPayload) {
        Product product = this.productService.createProduct(newProductPayload.title(), newProductPayload.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }
}
