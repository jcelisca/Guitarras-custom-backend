package co.com.sofka.guitarrasBack.domain.valueobjects;

import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Carrito {
    private Guitarra guitarra;
    private Luthier luthier;
    private Integer cantidad;
    private Double total;
}