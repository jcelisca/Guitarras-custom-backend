package co.com.sofka.guitarrasBack;

import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller.GuitarraController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Guitarra.class, GuitarraDTO.class, GuitarraController.class})
public class GuitarrasTestController {

    @MockBean
    private GuitarraService service;

    @Autowired
    private WebTestClient webTestClient;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("GET / filtro por tipo-marca-modelo")
    void filtrarGuitara(){
        Guitarra guitarra = new Guitarra();
        guitarra.setId("rtrt2");
        guitarra.setTipo("Acustica");
        guitarra.setModelo("Concert");
        guitarra.setMarca("FENDER");
        guitarra.setPrecio(2500000D);
        guitarra.setNumCuerdas(6);
        guitarra.setTipoCuerda("Acero");
        guitarra.setAfinacion("E");

        Flux<Guitarra> list = Flux.just(modelMapper().map(guitarra, Guitarra.class));

        when(service.findByTipo("Acustica")).thenReturn(list);

        webTestClient.get()
                .uri("/guitarra/{tipo}/{modelo}/{marca}","Acustica","Concert","FENDER")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Guitarra.class)
                .value(response->{
                    Assertions.assertEquals("rtrt2", response.get(0).getId());
                    Assertions.assertEquals("Acustica", response.get(0).getTipo());
                    Assertions.assertEquals("Concert", response.get(0).getModelo());
                    Assertions.assertEquals("FENDER", response.get(0).getMarca());
                    Assertions.assertEquals(2500000D, response.get(0).getPrecio());
                    Assertions.assertEquals(6, response.get(0).getNumCuerdas());
                    Assertions.assertEquals("Acero", response.get(0).getTipoCuerda());
                    Assertions.assertEquals("E", response.get(0).getAfinacion());
                });
    }

    @Test
    @DisplayName("GET / filtro guitarra completo")
    void filtroGuitarraCompleto(){
        Guitarra guitarra = new Guitarra();
        guitarra.setId("rtrt2");
        guitarra.setTipo("Acustica");
        guitarra.setModelo("Concert");
        guitarra.setMarca("FENDER");
        guitarra.setPrecio(2500000D);
        guitarra.setNumCuerdas(6);
        guitarra.setTipoCuerda("Acero");
        guitarra.setAfinacion("E");

        Guitarra guitarra2 = new Guitarra();
        guitarra2.setId("rtrt3");
        guitarra2.setTipo("Acustica");
        guitarra2.setModelo("Grand Concert");
        guitarra2.setMarca("FENDER");
        guitarra2.setPrecio(2500000D);
        guitarra2.setNumCuerdas(6);
        guitarra2.setTipoCuerda("Nylon");
        guitarra2.setAfinacion("E");

        Flux<Guitarra> list = Flux.just(modelMapper().map(guitarra2, Guitarra.class), modelMapper().map(guitarra, Guitarra.class));

        when(service.findByTipo("Acustica")).thenReturn(list);

        webTestClient.get()
                .uri("/guitarra/{tipo}/{modelo}/{marca}/{numCuerdas}/{tipoCuerdas}/{afinacion}","Acustica","Concert","FENDER",6, "Acero", "E")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Guitarra.class)
                .value(response->{
                    Assertions.assertEquals("rtrt2", response.get(0).getId());
                    Assertions.assertEquals("Acustica", response.get(0).getTipo());
                    Assertions.assertEquals("Concert", response.get(0).getModelo());
                    Assertions.assertEquals("FENDER", response.get(0).getMarca());
                    Assertions.assertEquals(2500000D, response.get(0).getPrecio());
                    Assertions.assertEquals(6, response.get(0).getNumCuerdas());
                    Assertions.assertEquals("Acero", response.get(0).getTipoCuerda());
                    Assertions.assertEquals("E", response.get(0).getAfinacion());
                });
    }
}
