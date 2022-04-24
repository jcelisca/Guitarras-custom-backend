package co.com.sofka.guitarrasBack.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Orden {

    private String id;
    private String UID;
    private String idGuitarra;
    private String comprobante;
}
