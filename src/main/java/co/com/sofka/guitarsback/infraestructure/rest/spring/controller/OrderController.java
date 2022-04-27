package co.com.sofka.guitarsback.infraestructure.rest.spring.controller;

import co.com.sofka.guitarsback.application.service.OrderService;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "https://guitarras-custom-first.web.app")
public class OrderController {

    private final OrderService orderService;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostMapping("/orden/crear/{luthier}/{uid}")
    public Mono<OrderDTO> save(
            @RequestBody Guitar guitar,
            @PathVariable("luthier") Boolean luthier,
            @PathVariable("uid") String uid
    ) {
        return orderService.guardarOrden(guitar,luthier,uid)
                .map(orden1 -> modelMapper().map(orden1, OrderDTO.class));
    }

    @GetMapping("/orden/actualizar/{id}/{uid}/{comprobante}")
    public Mono<OrderDTO> update(
            @PathVariable("id") String id,
            @PathVariable("uid") String uid,
            @PathVariable("comprobante") String comprobante
    ){
        return orderService.update(id,uid,comprobante)
                .map(orden -> modelMapper().map(orden, OrderDTO.class));
    }

    @GetMapping("/orden")
    public Flux<OrderDTO> findAll() {
        return orderService.findAll()
                .map(orden -> modelMapper().map(orden, OrderDTO.class));
    }

    @GetMapping("/orden/id/{id}")
    public Mono<OrderDTO> findById(@PathVariable("id") String id){
        return orderService.findById(id)
                .map(orden -> modelMapper().map(orden, OrderDTO.class));
    }
}
