package co.com.sofka.guitarrasBack;

import co.com.sofka.guitarrasBack.application.repository.OrdenRepository;
import co.com.sofka.guitarrasBack.application.service.OrdenService;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import co.com.sofka.guitarrasBack.domain.entity.Orden;
import co.com.sofka.guitarrasBack.domain.valueobjects.Carrito;
import co.com.sofka.guitarrasBack.domain.valueobjects.Luthier;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Orden.class, OrdenDTO.class, OrdenService.class, Guitarra.class, Carrito.class, Luthier.class})
class OrdenServiceTest {

    @MockBean
    private OrdenRepository repository;

    @Autowired
    private OrdenService service;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("Guardar orden")
    void guardar(){
        Orden orden = new Orden();
        orden.setId("gyig5");
        orden.setComprobante("6235462354765");
        orden.setUID("igy3i4kh34uobio");

        when(repository.save(any())).thenReturn(Mono.just(orden));

        Mono<Orden> mono = service.save(orden);

        Assertions.assertEquals("gyig5", mono.block().getId());
        Assertions.assertEquals("6235462354765", mono.block().getComprobante());
        Assertions.assertEquals("igy3i4kh34uobio", mono.block().getUID());
    }

    @Test
    @DisplayName("Mostrar todas laas ordenes")
    void mostrarTodo(){
        Orden orden = new Orden();
        orden.setId("gyig5");
        orden.setComprobante("6235462354765");
        orden.setUID("igy3i4kh34uobio");

        when(repository.findAll()).thenReturn(Flux.just(orden));

        Flux<Orden> mono = service.findAll();

        Assertions.assertEquals("gyig5", mono.blockFirst().getId());
        Assertions.assertEquals("6235462354765", mono.blockFirst().getComprobante());
        Assertions.assertEquals("igy3i4kh34uobio", mono.blockFirst().getUID());
    }

    @Test
    @DisplayName("Actualizar orden")
    void updateOrden(){

        Orden orden = new Orden();
        orden.setId("gyig5");
        orden.setUID("igy3i4kh34uobio");

        when(repository.findById("gyig5")).thenReturn(Mono.just(orden));

        Orden orden1 = new Orden();
        orden1.setId("gyig5");
        orden1.setUID("igy3i4kh34uobio");
        orden1.setComprobante("78798");

        when(repository.save(any())).thenReturn(Mono.just(orden1));

        Mono<Orden> ordenMono = service.update("gyig5","igy3i4kh34uobio" ,"78798");
        Assertions.assertEquals("78798", ordenMono.block().getComprobante());
        Assertions.assertEquals("gyig5", ordenMono.block().getId());
        Assertions.assertEquals("igy3i4kh34uobio", ordenMono.block().getUID());

    }
}
