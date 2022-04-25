package co.com.sofka.guitarrasBack.domain.entity;

import co.com.sofka.guitarrasBack.domain.valueobjects.Carrito;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Orden {

    private String id;
    private LocalDate fecha;
    private List<Carrito> carrito;
    private String UID;
    private Double total;
    private String comprobante;
}
