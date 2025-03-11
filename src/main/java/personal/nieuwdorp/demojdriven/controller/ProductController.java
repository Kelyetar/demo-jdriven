package personal.nieuwdorp.demojdriven.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import personal.nieuwdorp.demojdriven.controller.domain.ProductResponse;
import personal.nieuwdorp.demojdriven.repository.ProductRepository;
import personal.nieuwdorp.demojdriven.repository.domain.Product;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static boolean matchesAllSearchTerms(Product product, String[] searchTerms) {
        return Arrays.stream(searchTerms).allMatch(term -> stringMatchesAnyPartOfTheProductName(product, term));
    }

    private static boolean stringMatchesAnyPartOfTheProductName(Product product, String term) {
        return Arrays.stream(product.getName().toLowerCase().split("[^a-z]+")).anyMatch(namePart -> namePart.startsWith(term));
    }

    @GetMapping("/products")
    Collection<ProductResponse> all() {
        return productRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @GetMapping("/find-products")
    Collection<ProductResponse> find(@RequestParam(required = false) String queryString) {
        if (queryString == null) {
            return productRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
        } else {
            String[] searchTerms = queryString.toLowerCase().split("[^a-z]+");
            return productRepository.findAll().stream().filter(product -> matchesAllSearchTerms(product, searchTerms)).map(this::convert).collect(Collectors.toList());
        }
    }

    private ProductResponse convert(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setUuid(product.getUuid());
        productResponse.setPrice(product.getPrice());
        productResponse.setName(product.getName());
        return productResponse;
    }

}
