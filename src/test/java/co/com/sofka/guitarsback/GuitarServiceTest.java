package co.com.sofka.guitarsback;

import co.com.sofka.guitarsback.application.repository.GuitarRepository;
import co.com.sofka.guitarsback.application.service.GuitarService;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.GuitarDTO;
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
@ContextConfiguration(classes ={Guitar.class, GuitarDTO.class, GuitarService.class})
class GuitarServiceTest {

    @MockBean
    private GuitarRepository repository;

    @Autowired
    private GuitarService service;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("Guardar guitarra")
    void guardar(){

        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Guitarra acustica concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        when(repository.save(any())).thenReturn(Mono.just(guitar));

        Mono<Guitar> guitarra1 = service.save(guitar);
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
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Guitarra acustica concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");
        Flux<Guitar> list = Flux.just(modelMapper().map(guitar, Guitar.class));

        when(repository.findAll()).thenReturn(list);

        Flux<Guitar> lista = service.findAll();
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
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Guitarra acustica concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        Guitar guitar2 = new Guitar();
        guitar2.setId("rtrt3");
        guitar2.setTipo("Electrica");
        guitar2.setModelo("Guitarra acustica concert");
        guitar2.setMarca("FENDER");
        guitar2.setPrecio(2500000D);
        guitar2.setNumCuerdas(6);
        guitar2.setTipoCuerda("Acero");
        guitar2.setAfinacion("E");


        Flux<Guitar> list = Flux.just(modelMapper().map(guitar, Guitar.class));
        Flux<Guitar> list2 = Flux.just(modelMapper().map(guitar2, Guitar.class));

        when(repository.findByTipo("Acustica")).thenReturn(list);
        when(repository.findByTipo("Electrica")).thenReturn(list2);

        Flux<Guitar> lista = service.findByTipo("Acustica");
        Assertions.assertEquals("rtrt2", lista.blockFirst().getId());
        Assertions.assertEquals("Acustica", lista.blockFirst().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", lista.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista.blockFirst().getAfinacion());

        Flux<Guitar> lista2 = service.findByTipo("Electrica");
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
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Guitarra acustica concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        Guitar guitar2 = new Guitar();
        guitar2.setId("rtrt3");
        guitar2.setTipo("Electrica");
        guitar2.setModelo("Guitarra acustica concert");
        guitar2.setMarca("YAMAHA");
        guitar2.setPrecio(2500000D);
        guitar2.setNumCuerdas(6);
        guitar2.setTipoCuerda("Acero");
        guitar2.setAfinacion("E");


        Flux<Guitar> list = Flux.just(modelMapper().map(guitar, Guitar.class));
        Flux<Guitar> list2 = Flux.just(modelMapper().map(guitar2, Guitar.class));

        when(repository.findByMarca("FENDER")).thenReturn(list);
        when(repository.findByMarca("YAMAHA")).thenReturn(list2);

        Flux<Guitar> lista = service.findByMarca("FENDER");
        Assertions.assertEquals("rtrt2", lista.blockFirst().getId());
        Assertions.assertEquals("Acustica", lista.blockFirst().getTipo());
        Assertions.assertEquals("Guitarra acustica concert", lista.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista.blockFirst().getAfinacion());

        Flux<Guitar> lista2 = service.findByMarca("YAMAHA");
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
        guitar2.setTipo("Electrica");
        guitar2.setModelo("Grand Concert");
        guitar2.setMarca("YAMAHA");
        guitar2.setPrecio(2500000D);
        guitar2.setNumCuerdas(6);
        guitar2.setTipoCuerda("Acero");
        guitar2.setAfinacion("E");


        Flux<Guitar> list = Flux.just(modelMapper().map(guitar, Guitar.class));
        Flux<Guitar> list2 = Flux.just(modelMapper().map(guitar2, Guitar.class));

        when(repository.findByModelo("Concert")).thenReturn(list);
        when(repository.findByModelo("Grand Concert")).thenReturn(list2);

        Flux<Guitar> lista = service.findByModelo("Concert");
        Assertions.assertEquals("rtrt2", lista.blockFirst().getId());
        Assertions.assertEquals("Acustica", lista.blockFirst().getTipo());
        Assertions.assertEquals("Concert", lista.blockFirst().getModelo());
        Assertions.assertEquals("FENDER", lista.blockFirst().getMarca());
        Assertions.assertEquals(2500000D, lista.blockFirst().getPrecio());
        Assertions.assertEquals(6, lista.blockFirst().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.blockFirst().getTipoCuerda());
        Assertions.assertEquals("E", lista.blockFirst().getAfinacion());

        Flux<Guitar> lista2 = service.findByModelo("Grand Concert");
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
        guitar2.setTipo("Electrica");
        guitar2.setModelo("Grand Concert");
        guitar2.setMarca("YAMAHA");
        guitar2.setPrecio(2500000D);
        guitar2.setNumCuerdas(6);
        guitar2.setTipoCuerda("Acero");
        guitar2.setAfinacion("E");

        when(repository.findById("rtrt2")).thenReturn(Mono.just(guitar));
        when(repository.findById("rtrt3")).thenReturn(Mono.just(guitar2));

        Mono<Guitar> lista = service.findById("rtrt2");
        Assertions.assertEquals("rtrt2", lista.block().getId());
        Assertions.assertEquals("Acustica", lista.block().getTipo());
        Assertions.assertEquals("Concert", lista.block().getModelo());
        Assertions.assertEquals("FENDER", lista.block().getMarca());
        Assertions.assertEquals(2500000D, lista.block().getPrecio());
        Assertions.assertEquals(6, lista.block().getNumCuerdas());
        Assertions.assertEquals("Acero", lista.block().getTipoCuerda());
        Assertions.assertEquals("E", lista.block().getAfinacion());

        Mono<Guitar> lista2 = service.findById("rtrt3");
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
