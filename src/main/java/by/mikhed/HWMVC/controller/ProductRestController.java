package by.mikhed.HWMVC.controller;

import by.mikhed.HWMVC.entity.Product;
import by.mikhed.HWMVC.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(Integer id) {
        return productService.findById(id);
    }
}
