package com.example.manager.controller;


import com.example.manager.client.ProductRestClient;
import com.example.manager.controller.paylaod.UpdateProductPayload;
import com.example.manager.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductRestClient productRestClient;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productRestClient.findProduct(productId).orElseThrow();
    }

    @GetMapping
    public String getProduct() {
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayload updateProductPayload) {
        this.productRestClient.updateProduct(
                product.getId(),
                updateProductPayload.title(),
                updateProductPayload.details()
        );
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productRestClient.deleteProduct(product.getId());
        return "redirect:/catalogue/products/list";
    }
}