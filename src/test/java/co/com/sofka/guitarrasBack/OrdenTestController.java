package co.com.sofka.guitarrasBack;

import co.com.sofka.guitarrasBack.application.service.OrdenService;
import co.com.sofka.guitarrasBack.domain.entity.Orden;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
import co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller.OrdenController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Orden.class, OrdenDTO.class, OrdenController.class})
public class OrdenTestController {

    @MockBean
    private OrdenService service;

    @Autowired
    private WebTestClient webTestClient;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("POST / Guardar orden")
    void guardarOrden(){
        Orden orden = new Orden();
        orden.setId("gyig5");
//        orden.setIdGuitarra("hiu4h");
        orden.setComprobante("6235462354765");
        orden.setUID("igy3i4kh34uobio");

        Mono<Orden> mono = Mono.just(modelMapper().map(orden, Orden.class));
        when(service.save(any())).thenReturn(mono);

        webTestClient.post()
                .uri("/orden/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mono), Orden.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Orden.class)
                .value(response->{
                    Assertions.assertEquals("gyig5",response.getId());
//                    Assertions.assertEquals("hiu4h",response.getIdGuitarra() );
                    Assertions.assertEquals("6235462354765", response.getComprobante());
                    Assertions.assertEquals("igy3i4kh34uobio", response.getUID());
                });
    }
}
