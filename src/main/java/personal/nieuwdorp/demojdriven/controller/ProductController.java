package personal.nieuwdorp.demojdriven.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.nieuwdorp.demojdriven.controller.domain.ProductResponse;
import personal.nieuwdorp.demojdriven.repository.ProductRepository;
import personal.nieuwdorp.demojdriven.repository.domain.Product;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    Collection<ProductResponse> all() {
        return productRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    private ProductResponse convert(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setUuid(product.getUuid());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
