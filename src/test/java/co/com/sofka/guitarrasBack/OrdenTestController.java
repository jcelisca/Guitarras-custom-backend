package co.com.sofka.guitarrasBack;

import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.application.service.OrdenService;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import co.com.sofka.guitarrasBack.domain.entity.Orden;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
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
@ContextConfiguration(classes ={Orden.class, OrdenDTO.class, OrdenController.class, Guitarra.class, GuitarraService.class})
public class OrdenTestController {

    @MockBean
    private OrdenService service;

    @MockBean
    private GuitarraRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("POST / Guardar orden")
    void guardarOrden(){
        OrdenDTO orden = new OrdenDTO();
        orden.setComprobante("68576g7tg67tg");
        orden.setUID("g7g76g76g76");

        Mono<Orden> mono = Mono.just(modelMapper().map(orden, Orden.class));
        when(service.guardarOrden(new Guitarra(), true,"g7g76g76g76")).thenReturn(mono);

        webTestClient.post()
                .uri("/orden/crear/{luthier}/{UID}/{comprobante}",true,"g7g76g76g76","68576g7tg67tg")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Guitarra()), Guitarra.class)
                .exchange()
                //.expectStatus().isOk()
                .expectBody(Orden.class)
                .value(response->{
                    //Assertions.assertEquals("gyig5",response.getId());
                    Assertions.assertEquals("68576g7tg67tg", response.getComprobante());
                    Assertions.assertEquals("g7g76g76g76", response.getUID());
                });
    }
}
