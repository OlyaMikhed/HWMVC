package by.mikhed.HWMVC.service.impl;

import by.mikhed.HWMVC.entity.Product;
import by.mikhed.HWMVC.repository.ProductRepository;
import by.mikhed.HWMVC.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = productRepository.findById(product.getId())
                .orElseThrow(EntityNotFoundException::new);
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setDescription(product.getDescription());
        return productRepository.save(productToUpdate);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
