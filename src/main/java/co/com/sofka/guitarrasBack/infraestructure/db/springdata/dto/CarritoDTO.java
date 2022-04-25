package co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto;

import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarritoDTO {
    private GuitarraDTO guitarra;
    private LuthierDTO luthier;
    private Integer cantidad;
    private Double total;
}
