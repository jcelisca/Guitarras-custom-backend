package co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@Setter
public class LuthierDTO {
    private Boolean seleccionado;
    private Double precio;

    public void calcularPrecio(boolean isSeleccionado){
        if(isSeleccionado){
            this.seleccionado = isSeleccionado;
            this.precio = 250000D;
        } else {
            this.seleccionado = isSeleccionado;
            this.precio = 0D;
        }
    }
}
