package personal.nieuwdorp.demojdriven.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import personal.nieuwdorp.demojdriven.repository.domain.Product;

import java.util.Collection;
import java.util.UUID;

@Repository
interface ProductRepository extends CrudRepository<Product, UUID> {
    @Override
    @NonNull
    Collection<Product> findAll();
}
