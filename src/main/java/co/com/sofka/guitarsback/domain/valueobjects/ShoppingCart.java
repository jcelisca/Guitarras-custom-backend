package co.com.sofka.guitarsback.domain.valueobjects;

import co.com.sofka.guitarsback.domain.entity.Guitar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {
    private Guitar guitarra;
    private Luthier luthier;
    private Integer cantidad;
    private Double total;
}