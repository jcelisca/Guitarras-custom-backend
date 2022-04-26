package co.com.sofka.guitarsback;

import co.com.sofka.guitarsback.application.service.GuitarService;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.GuitarDTO;
import co.com.sofka.guitarsback.infraestructure.rest.spring.controller.GuitarController;
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
@ContextConfiguration(classes ={Guitar.class, GuitarDTO.class, GuitarController.class})
class GuitarControllerTest {

    @MockBean
    private GuitarService service;

    @Autowired
    private WebTestClient webTestClient;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("GET / filtro por tipo-marca-modelo")
    void filtrarGuitara(){
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        Flux<Guitar> list = Flux.just(modelMapper().map(guitar, Guitar.class));

        when(service.findByTipo("Acustica")).thenReturn(list);

        webTestClient.get()
                .uri("/guitarra/{tipo}/{modelo}/{marca}","Acustica","Concert","FENDER")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Guitar.class)
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
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        Guitar guitar2 = new Guitar();
        guitar2.setId("rtrt3");
        guitar2.setTipo("Acustica");
        guitar2.setModelo("Grand Concert");
        guitar2.setMarca("FENDER");
        guitar2.setPrecio(2500000D);
        guitar2.setNumCuerdas(6);
        guitar2.setTipoCuerda("Nylon");
        guitar2.setAfinacion("E");

        Flux<Guitar> list = Flux.just(modelMapper().map(guitar2, Guitar.class), modelMapper().map(guitar, Guitar.class));

        when(service.findByTipo("Acustica")).thenReturn(list);

        webTestClient.get()
                .uri("/guitarra/{tipo}/{modelo}/{marca}/{numCuerdas}/{tipoCuerdas}/{afinacion}","Acustica","Concert","FENDER",6, "Acero", "E")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Guitar.class)
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
