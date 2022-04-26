package co.com.sofka.guitarrasBack;

import co.com.sofka.guitarrasBack.application.repository.GuitarraRepository;
import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
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

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Guitarra.class, GuitarraDTO.class, GuitarraService.class})
class GuitarraServiceTest {

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

    @Test
    @DisplayName("Find By Tipo")
    void findByTipoTest(){
        Guitarra guitarra = new Guitarra();
        guitarra.setId("rtrt2");
        guitarra.setTipo("Acustica");
        guitarra.setModelo("Guitarra acustica concert");
        guitarra.setMarca("FENDER");
        guitarra.setPrecio(2500000D);
        guitarra.setNumCuerdas(6);
        guitarra.setTipoCuerda("Acero");
        guitarra.setAfinacion("E");

        Guitarra guitarra2 = new Guitarra();
        guitarra2.setId("rtrt3");
        guitarra2.setTipo("Electrica");
        guitarra2.setModelo("Guitarra acustica concert");
        guitarra2.setMarca("FENDER");
        guitarra2.setPrecio(2500000D);
        guitarra2.setNumCuerdas(6);
        guitarra2.setTipoCuerda("Acero");
        guitarra2.setAfinacion("E");


        Flux<Guitarra> list = Flux.just(modelMapper().map(guitarra, Guitarra.class));
        Flux<Guitarra> list2 = Flux.just(modelMapper().map(guitarra2, Guitarra.class));

        when(repository.findByTipo("Acustica")).thenReturn(list);
        when(repository.findByTipo("Electrica")).thenReturn(list2);

        Flux<Guitarra> lista = service.findByTipo("Acustica");
        Assertions.assertEquals("rtrt2", lista.blockFirst().getId());
        Assertions.assertEquals("Acustica", lista.blockFirst().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", lista.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista.blockFirst().getAfinacion());

        Flux<Guitarra> lista2 = service.findByTipo("Electrica");
        Assertions.assertEquals("rtrt3", lista2.blockFirst().getId());
        Assertions.assertEquals("Electrica", lista2.blockFirst().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", lista2.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista2.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista2.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista2.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista2.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista2.blockFirst().getAfinacion());

    }

    @Test
    @DisplayName("Find By Marca")
    void findByMarcaTest(){
        Guitarra guitarra = new Guitarra();
        guitarra.setId("rtrt2");
        guitarra.setTipo("Acustica");
        guitarra.setModelo("Guitarra acustica concert");
        guitarra.setMarca("FENDER");
        guitarra.setPrecio(2500000D);
        guitarra.setNumCuerdas(6);
        guitarra.setTipoCuerda("Acero");
        guitarra.setAfinacion("E");

        Guitarra guitarra2 = new Guitarra();
        guitarra2.setId("rtrt3");
        guitarra2.setTipo("Electrica");
        guitarra2.setModelo("Guitarra acustica concert");
        guitarra2.setMarca("YAMAHA");
        guitarra2.setPrecio(2500000D);
        guitarra2.setNumCuerdas(6);
        guitarra2.setTipoCuerda("Acero");
        guitarra2.setAfinacion("E");


        Flux<Guitarra> list = Flux.just(modelMapper().map(guitarra, Guitarra.class));
        Flux<Guitarra> list2 = Flux.just(modelMapper().map(guitarra2, Guitarra.class));

        when(repository.findByMarca("FENDER")).thenReturn(list);
        when(repository.findByMarca("YAMAHA")).thenReturn(list2);

        Flux<Guitarra> lista = service.findByMarca("FENDER");
        Assertions.assertEquals("rtrt2", lista.blockFirst().getId());
        Assertions.assertEquals("Acustica", lista.blockFirst().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", lista.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista.blockFirst().getAfinacion());

        Flux<Guitarra> lista2 = service.findByMarca("YAMAHA");
        Assertions.assertEquals("rtrt3", lista2.blockFirst().getId());
        Assertions.assertEquals("Electrica", lista2.blockFirst().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", lista2.blockFirst().getModelo());
        Assertions.assertEquals("YAMAHA", lista2.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista2.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista2.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista2.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista2.blockFirst().getAfinacion());

    }

    @Test
    @DisplayName("Find By Modelo")
    void findByModeloTest(){
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
        guitarra2.setTipo("Electrica");
        guitarra2.setModelo("Grand Concert");
        guitarra2.setMarca("YAMAHA");
        guitarra2.setPrecio(2500000D);
        guitarra2.setNumCuerdas(6);
        guitarra2.setTipoCuerda("Acero");
        guitarra2.setAfinacion("E");


        Flux<Guitarra> list = Flux.just(modelMapper().map(guitarra, Guitarra.class));
        Flux<Guitarra> list2 = Flux.just(modelMapper().map(guitarra2, Guitarra.class));

        when(repository.findByModelo("Concert")).thenReturn(list);
        when(repository.findByModelo("Grand Concert")).thenReturn(list2);

        Flux<Guitarra> lista = service.findByModelo("Concert");
        Assertions.assertEquals("rtrt2", lista.blockFirst().getId());
        Assertions.assertEquals("Acustica", lista.blockFirst().getTipo());
        Assertions.assertEquals("Concert", lista.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista.blockFirst().getAfinacion());

        Flux<Guitarra> lista2 = service.findByModelo("Grand Concert");
        Assertions.assertEquals("rtrt3", lista2.blockFirst().getId());
        Assertions.assertEquals("Electrica", lista2.blockFirst().getTipo());
        Assertions.assertEquals("Grand Concert", lista2.blockFirst().getModelo());
        Assertions.assertEquals("YAMAHA", lista2.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista2.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista2.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista2.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista2.blockFirst().getAfinacion());

    }

    @Test
    @DisplayName("Find By ID")
    void findByIdTest(){
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
        guitarra2.setTipo("Electrica");
        guitarra2.setModelo("Grand Concert");
        guitarra2.setMarca("YAMAHA");
        guitarra2.setPrecio(2500000D);
        guitarra2.setNumCuerdas(6);
        guitarra2.setTipoCuerda("Acero");
        guitarra2.setAfinacion("E");

        when(repository.findById("rtrt2")).thenReturn(Mono.just(guitarra));
        when(repository.findById("rtrt3")).thenReturn(Mono.just(guitarra2));

        Mono<Guitarra> lista = service.findById("rtrt2");
        Assertions.assertEquals("rtrt2", lista.block().getId());
        Assertions.assertEquals("Acustica", lista.block().getTipo());
        Assertions.assertEquals("Concert", lista.block().getModelo());
        Assertions.assertEquals("FENDER", lista.block().getMarca());
        Assertions.assertEquals(2500000D, lista.block().getPrecio());
        Assertions.assertEquals(6, lista.block().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.block().getTipoCuerda());
        Assertions.assertEquals("E", lista.block().getAfinacion());

        Mono<Guitarra> lista2 = service.findById("rtrt3");
        Assertions.assertEquals("rtrt3", lista2.block().getId());
        Assertions.assertEquals("Electrica", lista2.block().getTipo());
        Assertions.assertEquals("Grand Concert", lista2.block().getModelo());
        Assertions.assertEquals("YAMAHA", lista2.block().getMarca());
        Assertions.assertEquals(2500000D, lista2.block().getPrecio());
        Assertions.assertEquals(6, lista2.block().getNumCuerdas());
        Assertions.assertEquals("Acero", lista2.block().getTipoCuerda());
        Assertions.assertEquals("E", lista2.block().getAfinacion());

    }

}
