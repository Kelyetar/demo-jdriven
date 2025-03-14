package personal.nieuwdorp.demojdriven.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personal.nieuwdorp.demojdriven.client.domain.Product;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class ProductClient {
    private static final String PRODUCT = "product";
    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Collection<Product> getAll() {
        return List.of(Objects.requireNonNull(restTemplate.getForObject("/%s".formatted(PRODUCT), Product[].class)));
    }
}
