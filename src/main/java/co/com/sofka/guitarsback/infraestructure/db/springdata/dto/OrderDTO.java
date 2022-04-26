package co.com.sofka.guitarsback.infraestructure.db.springdata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "ordenes")
public class OrderDTO {

    private String id = UUID.randomUUID().toString().substring(0, 5);
    private LocalDate fecha;
    private List<ShoppingCartDTO> carrito;
    private String uid;
    private Double total;
    private String comprobante;
}
