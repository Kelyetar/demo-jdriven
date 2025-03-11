package personal.nieuwdorp.demojdriven.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import personal.nieuwdorp.demojdriven.controller.domain.ProductResponse;
import personal.nieuwdorp.demojdriven.repository.ProductProvider;
import personal.nieuwdorp.demojdriven.repository.domain.Product;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
public class ProductController {
    private final ProductProvider productProvider;

    public ProductController(ProductProvider productProvider) {
        this.productProvider = productProvider;
    }

    private static boolean matchesAllSearchTerms(Product product, String[] searchTerms) {
        return Arrays.stream(searchTerms).allMatch(term -> stringMatchesAnyPartOfTheProductName(product, term));
    }

    private static boolean stringMatchesAnyPartOfTheProductName(Product product, String term) {
        String[] nameParts = product.getName().toLowerCase().split("[^a-z]+");
        return Arrays.stream(nameParts).anyMatch(namePart -> namePart.startsWith(term));
    }

    @GetMapping("/find-products")
    Collection<ProductResponse> find(@RequestParam(required = false) String queryString) {
        if (queryString == null || queryString.isEmpty()) {
            return productProvider.findAll().stream().map(this::convert).collect(Collectors.toList());
        } else {
            String[] searchTerms = queryString.toLowerCase().split("[^a-z]+");
            return productProvider.findAll().stream().filter(product -> matchesAllSearchTerms(product, searchTerms)).map(this::convert).collect(Collectors.toList());
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
