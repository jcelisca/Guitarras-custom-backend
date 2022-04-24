package co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "ordenes")
public class OrdenDTO {
    private String id = UUID.randomUUID().toString().substring(0, 5);
    private String UID;
    private String idGuitarra;
    private String comprobante;
}
