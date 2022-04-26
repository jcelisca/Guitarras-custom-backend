package co.com.sofka.guitarsback.infraestructure.db.springdata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartDTO {
    private GuitarDTO guitarra;
    private LuthierDTO luthier;
    private Integer cantidad;
    private Double total;
}
