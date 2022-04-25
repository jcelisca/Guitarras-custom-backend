//package co.com.sofka.guitarrasBack;
//
//import co.com.sofka.guitarrasBack.application.repository.OrdenRepository;
//import co.com.sofka.guitarrasBack.application.service.OrdenService;
//import co.com.sofka.guitarrasBack.domain.entity.Orden;
//import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@WebFluxTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes ={Orden.class, OrdenDTO.class, OrdenService.class})
//public class OrdenTestService {
//
//    @MockBean
//    private OrdenRepository repository;
//
//    @Autowired
//    private OrdenService service;
//
//    ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//
//    @Test
//    @DisplayName("Guardar orden")
//    void guardar(){
//        Orden orden = new Orden();
//        orden.setId("gyig5");
//        orden.setIdGuitarra("hiu4h");
//        orden.setComprobante("6235462354765");
//        orden.setUID("igy3i4kh34uobio");
//
//        when(repository.save(any())).thenReturn(Mono.just(orden));
//
//        Mono<Orden> mono = service.save(orden);
//
//        Assertions.assertEquals("gyig5", mono.block().getId());
//        Assertions.assertEquals("hiu4h", mono.block().getIdGuitarra());
//        Assertions.assertEquals("6235462354765", mono.block().getComprobante());
//        Assertions.assertEquals("igy3i4kh34uobio", mono.block().getUID());
//    }
//
//    @Test
//    @DisplayName("Mostrar todas laas ordenes")
//    void mostrarTodo(){
//        Orden orden = new Orden();
//        orden.setId("gyig5");
//        orden.setIdGuitarra("hiu4h");
//        orden.setComprobante("6235462354765");
//        orden.setUID("igy3i4kh34uobio");
//
//        when(repository.findAll()).thenReturn(Flux.just(orden));
//
//        Flux<Orden> mono = service.findAll();
//
//        Assertions.assertEquals("gyig5", mono.blockFirst().getId());
//        Assertions.assertEquals("hiu4h", mono.blockFirst().getIdGuitarra());
//        Assertions.assertEquals("6235462354765", mono.blockFirst().getComprobante());
//        Assertions.assertEquals("igy3i4kh34uobio", mono.blockFirst().getUID());
//    }
//}
