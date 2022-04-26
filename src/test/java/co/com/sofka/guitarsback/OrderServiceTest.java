package co.com.sofka.guitarsback;

import co.com.sofka.guitarsback.application.repository.OrderRepository;
import co.com.sofka.guitarsback.application.service.OrderService;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.domain.entity.Order;
import co.com.sofka.guitarsback.domain.valueobjects.ShoppingCart;
import co.com.sofka.guitarsback.domain.valueobjects.Luthier;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.OrderDTO;
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
@ContextConfiguration(classes ={Order.class, OrderDTO.class, OrderService.class, Guitar.class, ShoppingCart.class, Luthier.class})
class OrderServiceTest {

    @MockBean
    private OrderRepository repository;

    @Autowired
    private OrderService service;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    @DisplayName("Guardar orden")
    void guardar(){
        Order order = new Order();
        order.setId("gyig5");
        order.setComprobante("6235462354765");
        order.setUid("igy3i4kh34uobio");

        when(repository.save(any())).thenReturn(Mono.just(order));

        Mono<Order> mono = service.save(order);

        Assertions.assertEquals("gyig5", mono.block().getId());
        Assertions.assertEquals("6235462354765", mono.block().getComprobante());
        Assertions.assertEquals("igy3i4kh34uobio", mono.block().getUid());
    }

    @Test
    @DisplayName("Mostrar todas laas ordenes")
    void mostrarTodo(){
        Order order = new Order();
        order.setId("gyig5");
        order.setComprobante("6235462354765");
        order.setUid("igy3i4kh34uobio");

        when(repository.findAll()).thenReturn(Flux.just(order));

        Flux<Order> mono = service.findAll();

        Assertions.assertEquals("gyig5", mono.blockFirst().getId());
        Assertions.assertEquals("6235462354765", mono.blockFirst().getComprobante());
        Assertions.assertEquals("igy3i4kh34uobio", mono.blockFirst().getUid());
    }

    @Test
    @DisplayName("Actualizar orden")
    void updateOrden(){

        Order order = new Order();
        order.setId("gyig5");
        order.setUid("igy3i4kh34uobio");

        when(repository.findById("gyig5")).thenReturn(Mono.just(order));

        Order order1 = new Order();
        order1.setId("gyig5");
        order1.setUid("igy3i4kh34uobio");
        order1.setComprobante("78798");

        when(repository.save(any())).thenReturn(Mono.just(order1));

        Mono<Order> ordenMono = service.update("gyig5","igy3i4kh34uobio" ,"78798");
        Assertions.assertEquals("78798", ordenMono.block().getComprobante());
        Assertions.assertEquals("gyig5", ordenMono.block().getId());
        Assertions.assertEquals("igy3i4kh34uobio", ordenMono.block().getUid());

    }
}
