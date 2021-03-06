package co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "guitarra")
public class GuitarraDTO {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 5);
    private String tipo;
    private String modelo;
    private String marca;
    private Double precio;
    private String imagen;
    private Integer numCuerdas;
    private String tipoCuerda;
    private String afinacion;
}
