package co.com.sofka.guitarsback;

import co.com.sofka.guitarsback.application.service.OrderService;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.domain.entity.Order;
import co.com.sofka.guitarsback.domain.valueobjects.Luthier;
import co.com.sofka.guitarsback.domain.valueobjects.ShoppingCart;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.OrderDTO;
import co.com.sofka.guitarsback.infraestructure.rest.spring.controller.OrderController;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Order.class, OrderDTO.class, OrderController.class, Guitar.class})
public class OrderControllerTest {
    @MockBean
    private OrderService service;

    @Autowired
    private WebTestClient webTestClient;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("Guardar orden completa")
    void guardarOrden(){
        Order order = new Order();
        order.setId("gyig5");
        order.setComprobante("6235462354765");
        order.setFecha(LocalDate.now());

        //Guitarra
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Guitarra acustica concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        //Carrito
        Luthier luthierVO = new Luthier();
        luthierVO.calcularPrecio(true);

        List<ShoppingCart> lista = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setGuitarra(guitar);
        shoppingCart.setLuthier(luthierVO);
        shoppingCart.setCantidad(1);
        shoppingCart.setTotal((luthierVO.getPrecio()+ guitar.getPrecio())* shoppingCart.getCantidad());
        lista.add(shoppingCart);

        order.setCarrito(lista);



        order.setTotal(2750000D);

        order.setUid("igy3i4kh34uobio");


        when(service.guardarOrden(any(),any(),any())).thenReturn(Mono.just(order));

        webTestClient.post()
                .uri("/orden/crear/{luthier}/{uid}", true, "igy3i4kh34uobio")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(guitar), Guitar.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class)
                .value(response->{
                    Assertions.assertEquals("gyig5", response.getId());
                    Assertions.assertEquals("6235462354765", response.getComprobante());
                    Assertions.assertEquals("igy3i4kh34uobio", response.getUid());
                    Assertions.assertEquals(guitar.getMarca(), response.getCarrito().get(0).getGuitarra().getMarca());
                    Assertions.assertEquals(2750000D, response.getTotal());
                });
    }


    @Test
    @DisplayName("Actualizar orden completa")
    void update(){
        Order order = new Order();
        order.setId("gyig5");
        order.setComprobante("6235462354765");
        order.setFecha(LocalDate.now());

        //Guitarra
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Guitarra acustica concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        //Carrito
        Luthier luthierVO = new Luthier();
        luthierVO.calcularPrecio(true);

        List<ShoppingCart> lista = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setGuitarra(guitar);
        shoppingCart.setLuthier(luthierVO);
        shoppingCart.setCantidad(1);
        shoppingCart.setTotal((luthierVO.getPrecio()+ guitar.getPrecio())* shoppingCart.getCantidad());
        lista.add(shoppingCart);

        order.setCarrito(lista);

        order.setTotal(2750000D);

        order.setUid("igy3i4kh34uobio");


        when(service.update(any(),any(),any())).thenReturn(Mono.just(order));

        webTestClient.get()
                .uri("/orden/actualizar/{id}/{uid}/{comprobante}", "gyig5", "igy3i4kh34uobio", "6235462354765")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class)
                .value(response->{
                    Assertions.assertEquals("gyig5", response.getId());
                    Assertions.assertEquals("6235462354765", response.getComprobante());
                    Assertions.assertEquals("igy3i4kh34uobio", response.getUid());
                    Assertions.assertEquals(guitar.getMarca(), response.getCarrito().get(0).getGuitarra().getMarca());
                    Assertions.assertEquals(2750000D, response.getTotal());
                });
    }


    @Test
    @DisplayName("Actualizar orden con documento")
    void updateWithDocument(){
        Order order = new Order();
        order.setId("gyig5");
        order.setComprobante("Nuevo comprobante");
        order.setFecha(LocalDate.now());

        //Guitarra
        Guitar guitar = new Guitar();
        guitar.setId("rtrt2");
        guitar.setTipo("Acustica");
        guitar.setModelo("Guitarra acustica concert");
        guitar.setMarca("FENDER");
        guitar.setPrecio(2500000D);
        guitar.setNumCuerdas(6);
        guitar.setTipoCuerda("Acero");
        guitar.setAfinacion("E");

        //Carrito
        Luthier luthierVO = new Luthier();
        luthierVO.calcularPrecio(true);

        List<ShoppingCart> lista = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setGuitarra(guitar);
        shoppingCart.setLuthier(luthierVO);
        shoppingCart.setCantidad(1);
        shoppingCart.setTotal((luthierVO.getPrecio()+ guitar.getPrecio())* shoppingCart.getCantidad());
        lista.add(shoppingCart);

        order.setCarrito(lista);

        order.setTotal(2750000D);

        order.setUid("igy3i4kh34uobio");

        Order orderComprobante = new Order();
        orderComprobante.setComprobante("prueba");


        when(service.updateWithDocuement(any(),any(),any())).thenReturn(Mono.just(order));

        webTestClient.put()
                .uri("/orden/actualizar/{id}/{uid}", "gyig5", "igy3i4kh34uobio")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(orderComprobante), Order.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class)
                .value(response->{
                    Assertions.assertEquals("gyig5", response.getId());
                    Assertions.assertEquals("Nuevo comprobante", response.getComprobante());
                    Assertions.assertEquals("igy3i4kh34uobio", response.getUid());
                    Assertions.assertEquals(guitar.getMarca(), response.getCarrito().get(0).getGuitarra().getMarca());
                    Assertions.assertEquals(2750000D, response.getTotal());
                });
    }

}
