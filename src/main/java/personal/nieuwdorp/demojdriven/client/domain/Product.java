package personal.nieuwdorp.demojdriven.client.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Product {
    private UUID uuid;
    private BigDecimal price;
}
