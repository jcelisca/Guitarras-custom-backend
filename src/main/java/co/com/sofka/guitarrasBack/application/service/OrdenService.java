package co.com.sofka.guitarrasBack.application.service;

import co.com.sofka.guitarrasBack.application.repository.OrdenRepository;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import co.com.sofka.guitarrasBack.domain.entity.Orden;
import co.com.sofka.guitarrasBack.domain.valueobjects.Carrito;
import co.com.sofka.guitarrasBack.domain.valueobjects.Luthier;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository repository;

    public Mono<Orden> save(Orden orden){
        return repository.save(orden);
    }
    public Flux<Orden> findAll(){
        return this.repository.findAll();
    }
    public Mono<Orden> findById(String id){
        return repository.findById(id);
    }

    public Double sumaDePrecios(List<Carrito> carrito) {
        Double total = 0D;
        total = carrito.stream().collect(Collectors.summingDouble(i -> {
            Double resultado = 0D;
            resultado += i.getTotal();
            return resultado;
        }));
        return total;
    }

    public Mono<Orden> guardarOrden(Guitarra guitarra, Boolean luthier, String UID){

        Orden orden = new Orden();
        orden.setFecha(LocalDate.now());

        Luthier luthierVO = new Luthier();
        luthierVO.calcularPrecio(luthier);

        List<Carrito> lista = new ArrayList<>();
        Carrito carrito = new Carrito();

        carrito.setGuitarra(guitarra);
        carrito.setLuthier(luthierVO);
        carrito.setCantidad(1);
        carrito.setTotal((luthierVO.getPrecio()+guitarra.getPrecio())* carrito.getCantidad());
        lista.add(carrito);

        orden.setCarrito(lista);

        orden.setTotal(sumaDePrecios(lista));

        orden.setUID(UID);

        return repository.save(orden);
    }

    public Mono<Orden> update(String idOrden, String UID, String comprobante){
        return repository.findById(idOrden)
                .flatMap(orden -> {
                    if(UID.equals(orden.getUID())){
                        orden.setComprobante(comprobante);
                        return save(orden);
                    }
                    return Mono.empty();
                });
    }
}
