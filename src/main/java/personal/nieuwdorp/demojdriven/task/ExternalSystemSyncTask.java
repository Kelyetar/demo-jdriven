package personal.nieuwdorp.demojdriven.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import personal.nieuwdorp.demojdriven.client.ProductClient;
import personal.nieuwdorp.demojdriven.client.domain.Product;
import personal.nieuwdorp.demojdriven.repository.ProductRepository;

import java.math.RoundingMode;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
class ExternalSystemSyncTask implements Runnable {
    private final ProductClient productClient;
    private final ProductRepository productRepository;

    public ExternalSystemSyncTask(ProductClient productClient, ProductRepository productRepository) {
        this.productClient = productClient;
        this.productRepository = productRepository;
    }

    private static personal.nieuwdorp.demojdriven.repository.domain.Product createRepositoryProduct(Product productFromExternalSystem) {
        personal.nieuwdorp.demojdriven.repository.domain.Product product = new personal.nieuwdorp.demojdriven.repository.domain.Product();
        product.setUuid(productFromExternalSystem.getUuid());
        product.setPrice(productFromExternalSystem.getPrice().setScale(2, RoundingMode.HALF_EVEN));
        product.setName(productFromExternalSystem.getName());
        return product;
    }

    public void run() {
        Collection<Product> products = productClient.getAll();
        productRepository.saveAll(products.stream().map(ExternalSystemSyncTask::createRepositoryProduct).collect(Collectors.toSet()));
    }
}
