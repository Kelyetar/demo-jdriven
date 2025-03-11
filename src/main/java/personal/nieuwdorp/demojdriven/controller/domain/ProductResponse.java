package personal.nieuwdorp.demojdriven.controller.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductResponse {
    private UUID uuid;
    private BigDecimal price;
    private String name;
}
