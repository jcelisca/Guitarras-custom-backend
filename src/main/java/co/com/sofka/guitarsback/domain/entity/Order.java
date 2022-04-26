package co.com.sofka.guitarsback.domain.entity;

import co.com.sofka.guitarsback.domain.valueobjects.ShoppingCart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private String id;
    private LocalDate fecha;
    private List<ShoppingCart> carrito;
    private String uid;
    private Double total;
    private String comprobante;
}
