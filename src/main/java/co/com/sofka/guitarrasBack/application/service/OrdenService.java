package co.com.sofka.guitarrasBack.application.service;

import co.com.sofka.guitarrasBack.application.repository.OrdenRepository;
import co.com.sofka.guitarrasBack.domain.entity.Orden;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.CarritoDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
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

    public Double sumaDePrecios(List<CarritoDTO> carrito) {
        Double total = 0D;
        total = carrito.stream().collect(Collectors.summingDouble(i -> {
            Double resultado = 0D;
            resultado += i.getTotal();
            return resultado;
        }));
        return total;
    }
}
