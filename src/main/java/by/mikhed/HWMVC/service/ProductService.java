package by.mikhed.HWMVC.service;

import by.mikhed.HWMVC.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);

    Product update(Product product);

    void save(Product product);

    void deleteById(Integer id);

}
