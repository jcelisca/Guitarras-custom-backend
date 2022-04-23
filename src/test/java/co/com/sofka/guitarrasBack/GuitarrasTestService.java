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
import reactor.core.publisher.Mono;

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

        when(repository.save(any())).thenReturn(Mono.just(guitarra));

        Mono<Guitarra> guitarra1 = service.save(guitarra);
        Assertions.assertEquals("rtrt2", guitarra1.block().getId());
        Assertions.assertEquals("Acustica", guitarra1.block().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", guitarra1.block().getModelo());
    }
}
