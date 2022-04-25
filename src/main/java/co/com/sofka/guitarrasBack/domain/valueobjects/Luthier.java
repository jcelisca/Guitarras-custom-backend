package co.com.sofka.guitarrasBack.domain.valueobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Luthier {

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

