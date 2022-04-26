package co.com.sofka.guitarsback.application.service;

import co.com.sofka.guitarsback.application.repository.OrderRepository;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.domain.entity.Order;
import co.com.sofka.guitarsback.domain.valueobjects.ShoppingCart;
import co.com.sofka.guitarsback.domain.valueobjects.Luthier;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Mono<Order> save(Order order){
        return repository.save(order);
    }
    public Flux<Order> findAll(){
        return this.repository.findAll();
    }
    public Mono<Order> findById(String id){
        return repository.findById(id);
    }

    public Double sumaDePrecios(List<ShoppingCart> shoppingCart) {
        return shoppingCart.stream().collect(Collectors.summingDouble(i -> {
            Double resultado = 0D;
            resultado += i.getTotal();
            return resultado;
        }));
    }

    public Mono<Order> guardarOrden(Guitar guitar, Boolean luthier, String uid){

        Order order = new Order();
        order.setFecha(LocalDate.now());

        Luthier luthierVO = new Luthier();
        luthierVO.calcularPrecio(luthier);

        List<ShoppingCart> lista = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setGuitarra(guitar);
        shoppingCart.setLuthier(luthierVO);
        shoppingCart.setCantidad(1);
        shoppingCart.setTotal((luthierVO.getPrecio()+ guitar.getPrecio())* shoppingCart.getCantidad());
        lista.add(shoppingCart);

        order.setCarrito(lista);

        order.setTotal(sumaDePrecios(lista));

        order.setUid(uid);

        return repository.save(order);
    }

    public Mono<Order> update(String idOrden, String uid, String comprobante){
        return repository.findById(idOrden)
                .flatMap(order -> {
                    if(uid.equals(order.getUid())){
                        order.setComprobante(comprobante);
                        return save(order);
                    }
                    return Mono.empty();
                });
    }
}
