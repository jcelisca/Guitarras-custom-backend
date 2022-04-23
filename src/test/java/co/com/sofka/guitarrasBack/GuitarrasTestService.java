package co.com.sofka.guitarrasBack;

import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.domain.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
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
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Guitarra.class, GuitarraDTO.class, GuitarraService.class})
public class GuitarrasTestService {

    @MockBean
    private GuitarraRepository repository;

    @Autowired
    private GuitarraService service;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("Guardar guitarra")
    void guardar(){

        Guitarra guitarra = new Guitarra();
        guitarra.setId("rtrt2");
        guitarra.setTipo("Acustica");
        guitarra.setModelo("Guitarra acustica concert");
        guitarra.setMarca("FENDER");
        guitarra.setPrecio(2500000D);
        guitarra.setNumCuerdas(6);
        guitarra.setTipoCuerda("Acero");
        guitarra.setAfinacion("E");

        when(repository.save(any())).thenReturn(Mono.just(guitarra));

        Mono<Guitarra> guitarra1 = service.save(guitarra);
        Assertions.assertEquals("rtrt2", guitarra1.block().getId());
        Assertions.assertEquals("Acustica", guitarra1.block().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", guitarra1.block().getModelo());
        Assertions.assertEquals("FENDER", guitarra1.block().getMarca());
        Assertions.assertEquals(2500000D, guitarra1.block().getPrecio());
        Assertions.assertEquals(6, guitarra1.block().getNumCuerdas());
        Assertions.assertEquals("Acero", guitarra1.block().getTipoCuerda());
        Assertions.assertEquals("E", guitarra1.block().getAfinacion());
    }

    @Test
    @DisplayName("Find All Guitarras")
    void findAllTest(){
        Guitarra guitarra = new Guitarra();
        guitarra.setId("rtrt2");
        guitarra.setTipo("Acustica");
        guitarra.setModelo("Guitarra acustica concert");
        guitarra.setMarca("FENDER");
        guitarra.setPrecio(2500000D);
        guitarra.setNumCuerdas(6);
        guitarra.setTipoCuerda("Acero");
        guitarra.setAfinacion("E");
        Flux<Guitarra> list = Flux.just(modelMapper().map(guitarra, Guitarra.class));

        when(repository.findAll()).thenReturn(list);

        Flux<Guitarra> lista = service.findAll();
        Assertions.assertEquals("rtrt2", lista.blockFirst().getId());
        Assertions.assertEquals("Acustica", lista.blockFirst().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", lista.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista.blockFirst().getAfinacion());
    }

}
