package co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GuitarraResponseDTO {

    private String tipo;
    private String modelo;
    private String marca;
    private Integer numCuerdas;
    private String tipoCuerda;
    private String afinacion;
}
