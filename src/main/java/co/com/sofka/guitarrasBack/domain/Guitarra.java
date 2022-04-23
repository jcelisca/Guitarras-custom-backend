package co.com.sofka.guitarrasBack.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guitarra {

    private String id;
    private String tipo;
    private String modelo;
    private String marca;
    private Double precio;
    private String imagen;
    private Integer numCuerdas;
    private String tipoCuerda;
    private String afinacion;

}
