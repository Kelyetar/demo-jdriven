package personal.nieuwdorp.demojdriven.repository;

import org.springframework.stereotype.Component;
import personal.nieuwdorp.demojdriven.repository.domain.Product;

import java.util.Collection;
import java.util.Set;

@Component
public class ProductProvider {
    private final ProductRepository productRepository;

    ProductProvider(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Collection<Product> findAll() {
        return productRepository.findAll();
    }

    public void saveAll(Set<Product> products) {
        productRepository.saveAll(products);
    }
}
