package personal.nieuwdorp.demojdriven.repository.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class Product {
    @Id
    private UUID uuid;
    private BigDecimal price;
}
