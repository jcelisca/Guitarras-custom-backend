package co.com.sofka.guitarsback.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guitar {
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
